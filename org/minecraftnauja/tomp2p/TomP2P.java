package org.minecraftnauja.tomp2p;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;
import java.util.logging.Level;

import net.tomp2p.connection.Bindings;
import net.tomp2p.futures.FutureBootstrap;
import net.tomp2p.futures.FutureDiscover;
import net.tomp2p.p2p.Peer;
import net.tomp2p.p2p.PeerMaker;
import net.tomp2p.peers.Number160;

import org.minecraftnauja.p2p.P2P;
import org.minecraftnauja.p2p.provider.FutureProvider;
import org.minecraftnauja.tomp2p.config.IClientConfig;
import org.minecraftnauja.tomp2p.config.ServerConfig;
import org.minecraftnauja.tomp2p.event.DefaultP2PNotifier;
import org.minecraftnauja.tomp2p.event.P2PListener;
import org.minecraftnauja.tomp2p.peer.Client;
import org.minecraftnauja.tomp2p.peer.IClient;
import org.minecraftnauja.tomp2p.peer.IServer;
import org.minecraftnauja.tomp2p.peer.PeerBase;
import org.minecraftnauja.tomp2p.peer.Server;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "TomP2P", name = "TomP2P", version = "1.0.0")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class TomP2P {

	/**
	 * Mod identifier.
	 */
	public static final String MOD_ID = "TomP2P";

	/**
	 * Mod instance.
	 */
	@Instance("TomP2P")
	public static TomP2P instance;

	/**
	 * Notifier.
	 */
	private DefaultP2PNotifier notifier;

	/**
	 * {@inheritDoc}
	 */
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		P2P.register(new FutureProvider(P2P.SERVER_PROVIDER));
		if (event.getSide() == Side.CLIENT) {
			P2P.register(new FutureProvider(P2P.CLIENT_PROVIDER));
		}
		notifier = new DefaultP2PNotifier();
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
	public static void startServer(String peerId, ServerConfig config)
			throws IOException {
		// Starts the peer.
		Server s = new Server(MOD_ID, "Server", config);
		FMLLog.log(MOD_ID, Level.INFO, "Server: starting with %s...", config);
		instance.notifier.notifyServerStarting(s);
		Bindings b = new Bindings();
		b.addAddress(InetAddress.getByName(config.getAddress()));
		Peer peer = new PeerMaker(Number160.createHash(peerId)).setBindings(b)
				.setPorts(config.getPort()).setEnableIndirectReplication(true)
				.makeAndListen();
		s.setPeer(peer);
		peer.getConfiguration().setBehindFirewall(config.isBehindFirewall());
		config.getStorageType().apply(peer, config);
		// Sets the provider.
		((FutureProvider) P2P.get(P2P.SERVER_PROVIDER)).setProvider(s);
		// Notifies.
		FMLLog.log(MOD_ID, Level.INFO, "Server: started");
		instance.notifier.notifyServerStarted(s);
	}

	/**
	 * Starts a client.
	 * 
	 * @param config
	 *            its configuration.
	 * @throws IOException
	 *             error with IO.
	 */
	public static void startClient(String peerId, IClientConfig config)
			throws IOException {
		Peer peer = null;
		try {
			// Starts the peer.
			Client c = new Client(MOD_ID, "Client", config);
			FMLLog.log(MOD_ID, Level.INFO,
					"Client (%s, %s): starting with %s...", "Client", "Client",
					config);
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
			FMLLog.log(MOD_ID, Level.INFO, "Client: discovering...");
			fd.awaitUninterruptibly();
			if (fd.isSuccess()) {
				FMLLog.log(MOD_ID, Level.INFO, "Client: outside address is %s",
						fd.getPeerAddress());
			} else {
				FMLLog.log(MOD_ID, Level.SEVERE,
						"Client: failed because of %s", fd.getFailedReason());
			}
			// Bootstrap to master server.
			FutureBootstrap bootstrap = peer.bootstrap()
					.setInetAddress(inetAddress)
					.setPorts(config.getServerPort()).start();
			bootstrap.awaitUninterruptibly();
			if (!bootstrap.isSuccess()) {
				FMLLog.log(MOD_ID, Level.SEVERE, "Client: could not bootstrap");
			} else {
				// Sets the provider.
				((FutureProvider) P2P.get(P2P.CLIENT_PROVIDER)).setProvider(c);
				// Notifies.
				FMLLog.log(MOD_ID, Level.INFO, "Client: started");
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
	 * Shutdown the server.
	 */
	public static void shutdownServer() {
		FutureProvider fp = (FutureProvider) P2P.get(P2P.SERVER_PROVIDER);
		PeerBase pb = (PeerBase) fp.getProvider();
		fp.setProvider(null);
		pb.getPeer().shutdown();
		instance.notifier.notifyServerShutdown((IServer) pb);
	}

	/**
	 * Shutdown the client.
	 */
	public static void shutdownClient() {
		FutureProvider fp = (FutureProvider) P2P.get(P2P.CLIENT_PROVIDER);
		PeerBase pb = (PeerBase) fp.getProvider();
		fp.setProvider(null);
		pb.getPeer().shutdown();
		instance.notifier.notifyClientShutdown((IClient) pb);
	}

}
