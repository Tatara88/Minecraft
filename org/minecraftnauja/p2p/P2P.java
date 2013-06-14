package org.minecraftnauja.p2p;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

import net.tomp2p.connection.Bindings;
import net.tomp2p.futures.FutureBootstrap;
import net.tomp2p.futures.FutureDiscover;
import net.tomp2p.p2p.Peer;
import net.tomp2p.p2p.PeerMaker;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.storage.StorageDisk;

import org.minecraftnauja.p2p.config.IClientConfig;
import org.minecraftnauja.p2p.config.ServerConfig;
import org.minecraftnauja.p2p.event.DefaultP2PNotifier;
import org.minecraftnauja.p2p.event.P2PListener;
import org.minecraftnauja.p2p.peer.Client;
import org.minecraftnauja.p2p.peer.IClient;
import org.minecraftnauja.p2p.peer.IServer;
import org.minecraftnauja.p2p.peer.PeerBase;
import org.minecraftnauja.p2p.peer.Server;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "P2P", name = "P2P", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class P2P {

	/**
	 * Mod identifier.
	 */
	public static final String MOD_ID = "P2P";

	/**
	 * Mod instance.
	 */
	@Instance("P2P")
	public static P2P instance;

	/**
	 * Peers by names and mods.
	 */
	private Map<String, Map<String, PeerBase>> peers;

	/**
	 * Notifier.
	 */
	private DefaultP2PNotifier notifier;

	/**
	 * {@inheritDoc}
	 */
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		peers = new HashMap<String, Map<String, PeerBase>>();
		notifier = new DefaultP2PNotifier();
	}

	/**
	 * {@inheritDoc}
	 */
	@Init
	public void load(final FMLInitializationEvent event) throws IOException {
	}

	/**
	 * {@inheritDoc}
	 */
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
	}

	/**
	 * Adds given listener.
	 * 
	 * @param listener
	 *            the listener to add.
	 */
	public static void addListener(P2PListener listener) {
		instance.notifier.addListener(listener);
	}

	/**
	 * Removes given listener.
	 * 
	 * @param listener
	 *            the listener to remove.
	 */
	public static void removeListener(P2PListener listener) {
		instance.notifier.removeListener(listener);
	}

	/**
	 * Starts a server.
	 * 
	 * @param modId
	 *            mod identifier.
	 * @param key
	 *            its key.
	 * @param peerId
	 *            peer id.
	 * @param config
	 *            its configuration.
	 * @throws IOException
	 *             error with IO.
	 */
	public static void startServer(String modId, String key, String peerId,
			ServerConfig config) throws IOException {
		// Starts the peer.
		Server s = new Server(modId, key, config);
		FMLLog.log(MOD_ID, Level.INFO, "Server %s: starting with %s...", key,
				config);
		instance.notifier.notifyServerStarting(s);
		Bindings b = new Bindings();
		b.addAddress(InetAddress.getByName(config.getAddress()));
		Peer peer = new PeerMaker(Number160.createHash(peerId)).setBindings(b)
				.setPorts(config.getPort()).setEnableIndirectReplication(true)
				.makeAndListen();
		s.setPeer(peer);
		peer.getConfiguration().setBehindFirewall(config.isBehindFirewall());
		config.getStorageType().apply(peer, config);
		// Adds the server to the map.
		Map<String, PeerBase> peers = instance.peers.get(modId);
		if (peers == null) {
			peers = new HashMap<String, PeerBase>();
			instance.peers.put(modId, peers);
		}
		peers.put(key, s);
		// Notifies.
		FMLLog.log(MOD_ID, Level.INFO, "Server %s: started", key);
		instance.notifier.notifyServerStarted(s);
	}

	/**
	 * Starts a client.
	 * 
	 * @param modId
	 *            mod identifier.
	 * @param key
	 *            its key.
	 * @param peerId
	 *            peer id.
	 * @param config
	 *            its configuration.
	 * @throws IOException
	 *             error with IO.
	 */
	public static void startClient(String modId, String key, String peerId,
			IClientConfig config) throws IOException {
		Peer peer = null;
		try {
			// Starts the peer.
			Client c = new Client(modId, key, config);
			FMLLog.log(MOD_ID, Level.INFO,
					"Client (%s, %s): starting with %s...", peerId, key, config);
			instance.notifier.notifyClientStarting(c);
			Bindings b = new Bindings();
			b.addAddress(InetAddress.getByName(config.getAddress()));
			peer = new PeerMaker(Number160.createHash(peerId)).setBindings(b)
					.setPorts(config.getPort())
					.setEnableIndirectReplication(true).makeAndListen();
			c.setPeer(peer);
			peer.getConfiguration()
					.setBehindFirewall(config.isBehindFirewall());
			config.getStorageType().apply(peer, config);
			// Discovers outside address.
			InetAddress inetAddress = InetAddress.getByName(config
					.getServerAddress());
			FutureDiscover fd = peer.discover().setInetAddress(inetAddress)
					.setPorts(config.getServerPort()).start();
			FMLLog.log(MOD_ID, Level.INFO, "Client %s: discovering...", key);
			fd.awaitUninterruptibly();
			if (fd.isSuccess()) {
				FMLLog.log(MOD_ID, Level.INFO,
						"Client %s: outside address is %s", key,
						fd.getPeerAddress());
			} else {
				FMLLog.log(MOD_ID, Level.SEVERE,
						"Client %s: failed because of %s", key,
						fd.getFailedReason());
			}
			// Bootstrap to master server.
			FutureBootstrap bootstrap = peer.bootstrap()
					.setInetAddress(inetAddress)
					.setPorts(config.getServerPort()).start();
			bootstrap.awaitUninterruptibly();
			if (!bootstrap.isSuccess()) {
				FMLLog.log(MOD_ID, Level.SEVERE,
						"Client %s: could not bootstrap", key);
			} else {
				// Adds the client to the map.
				Map<String, PeerBase> peers = instance.peers.get(modId);
				if (peers == null) {
					peers = new HashMap<String, PeerBase>();
					instance.peers.put(modId, peers);
				}
				peers.put(key, c);
				// Notifies.
				FMLLog.log(MOD_ID, Level.INFO, "Client %s: started", key);
				instance.notifier.notifyClientStarted(c);
			}
		} catch (IOException e) {
			if (peer != null) {
				peer.shutdown();
			}
			throw e;
		}
	}

	/**
	 * Gets the server with given key.
	 * 
	 * @param modId
	 *            mod identifier.
	 * @param key
	 *            its key.
	 * @return the server.
	 */
	public static IServer getServer(String modId, String key) {
		Map<String, PeerBase> peers = instance.peers.get(modId);
		if (peers != null) {
			PeerBase pb = peers.get(key);
			if (pb != null) {
				return pb.toServer();
			}
		}
		return null;
	}

	/**
	 * Gets the client with given key.
	 * 
	 * @param modId
	 *            mod identifier.
	 * @param key
	 *            its key.
	 * @return the client.
	 */
	public static IClient getClient(String modId, String key) {
		Map<String, PeerBase> peers = instance.peers.get(modId);
		if (peers != null) {
			PeerBase pb = peers.get(key);
			if (pb != null) {
				return pb.toClient();
			}
		}
		return null;
	}

	/**
	 * Shutdown the peer with given key.
	 * 
	 * @param modId
	 *            mod identifier.
	 * @param key
	 *            its key.
	 */
	public static void shutdown(String modId, String key) {
		Map<String, PeerBase> peers = instance.peers.get(modId);
		if (peers != null) {
			PeerBase pb = peers.remove(key);
			if (peers.isEmpty()) {
				instance.peers.remove(modId);
			}
			if (pb != null) {
				pb.getPeer().shutdown();
				IClient c = pb.toClient();
				if (c == null) {
					instance.notifier.notifyServerShutdown(pb.toServer());
				} else {
					instance.notifier.notifyClientShutdown(c);
				}
			}
		}
	}

}
