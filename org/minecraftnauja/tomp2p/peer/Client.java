package org.minecraftnauja.tomp2p.peer;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.tomp2p.connection.Bindings;
import net.tomp2p.connection.Bindings.Protocol;
import net.tomp2p.futures.FutureBootstrap;
import net.tomp2p.futures.FutureDiscover;
import net.tomp2p.p2p.Peer;
import net.tomp2p.p2p.PeerMaker;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;

import org.minecraftnauja.p2p.P2P;
import org.minecraftnauja.tomp2p.TomP2P;

import cpw.mods.fml.common.FMLLog;

/**
 * Client.
 */
public class Client extends PeerBase implements IClient {

	/**
	 * Its identifier.
	 */
	private String id;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return P2P.CLIENT_PROVIDER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void start(String id, String peerId, String address,
			int port) throws Exception {
		if (isRunning()) {
			return;
		}
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		Peer peer = null;
		try {
			// Starts the peer.
			FMLLog.log(TomP2P.MOD_ID, Level.INFO, "Client %s: starting", id);
			p.addChatMessage("[TomP2P] Connecting to " + address + ':' + port);
			fireStarting();
			Bindings b = new Bindings(Protocol.IPv6);
			b.addAddress(Inet6Address.getByName(TomP2P.config.address));
			peer = new PeerMaker(Number160.createHash(id)).setBindings(b)
					.setPorts(TomP2P.config.port)
					.setEnableIndirectReplication(true).makeAndListen();
			peer.getConfiguration().setBehindFirewall(
					TomP2P.config.behindFirewall);
			TomP2P.config.storageType.apply(peer);
			// Bootstrap to master server.
			InetAddress serverAddress = Inet6Address.getByName(address);
			PeerAddress pa = new PeerAddress(Number160.createHash(peerId),
					serverAddress, port, port);
			FutureBootstrap bootstrap = peer.bootstrap().setPeerAddress(pa)
					.start();
			p.addChatMessage("[TomP2P] Bootstrapping...");
			bootstrap.awaitUninterruptibly();
			if (bootstrap.isSuccess()) {
				// Started.
				setPeer(peer);
				this.id = id;
				// Notifies.
				FMLLog.log(TomP2P.MOD_ID, Level.INFO, "Client: started");
				p.addChatMessage("[TomP2P] Connected");
				fireStarted();
				return;
			} else {
				FMLLog.log(TomP2P.MOD_ID, Level.SEVERE,
						"Client: could not bootstrap");
				p.addChatMessage("[TomP2P] Bootstrap error");
				if (peer != null) {
					peer.shutdown();
				}
				throw new Exception("Bootstrap error");
			}
		} catch (Exception e) {
			FMLLog.log(TomP2P.MOD_ID, Level.SEVERE, e, "Client: failed");
			p.addChatMessage("[TomP2P] " + e.getStackTrace()[0] + ": "
					+ e.getMessage());
			if (peer != null) {
				peer.shutdown();
			}
			throw e;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void start(String id) throws Exception {
		if (isRunning()) {
			return;
		}
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
		Peer peer = null;
		try {
			// Starts the peer.
			FMLLog.log(TomP2P.MOD_ID, Level.INFO, "Client %s: starting", id);
			p.addChatMessage("[TomP2P] Starting the peer-to-peer network");
			fireStarting();
			Bindings b = new Bindings(Protocol.IPv6);
			b.addAddress(Inet6Address.getByName(TomP2P.config.address));
			peer = new PeerMaker(Number160.createHash(id)).setBindings(b)
					.setPorts(TomP2P.config.port)
					.setEnableIndirectReplication(true).makeAndListen();
			peer.getConfiguration().setBehindFirewall(
					TomP2P.config.behindFirewall);
			TomP2P.config.storageType.apply(peer);
			// Started.
			setPeer(peer);
			this.id = id;
			// Notifies.
			FMLLog.log(TomP2P.MOD_ID, Level.INFO, "Client: started");
			p.addChatMessage("[TomP2P] Started");
			fireStarted();
			return;
		} catch (IOException e) {
			FMLLog.log(TomP2P.MOD_ID, Level.SEVERE, e, "Client: failed");
			p.addChatMessage("[TomP2P] " + e.getStackTrace()[0] + ": "
					+ e.getMessage());
			throw e;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized void stop() {
		if (isRunning()) {
			fireStopping();
			getPeer().shutdown();
			setPeer(null);
			id = null;
			fireStopped();
		}
	}

}
