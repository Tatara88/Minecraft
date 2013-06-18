package org.minecraftnauja.tomp2p.peer;

import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;

import net.tomp2p.connection.Bindings;
import net.tomp2p.futures.FutureBootstrap;
import net.tomp2p.futures.FutureDiscover;
import net.tomp2p.p2p.Peer;
import net.tomp2p.p2p.PeerMaker;
import net.tomp2p.peers.Number160;

import org.minecraftnauja.p2p.P2P;
import org.minecraftnauja.tomp2p.TomP2P;
import org.minecraftnauja.tomp2p.config.ClientConfig;
import org.minecraftnauja.tomp2p.config.IClientConfig;
import org.minecraftnauja.tomp2p.exception.AlreadyRunningException;

import cpw.mods.fml.common.FMLLog;

/**
 * Client.
 */
public class Client extends PeerBase<IClientConfig> implements IClient {

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
	public synchronized void start(String id, ClientConfig config)
			throws AlreadyRunningException, IOException {
		if (isRunning()) {
			throw new AlreadyRunningException(this);
		}
		Peer peer = null;
		try {
			// Starts the peer.
			FMLLog.log(TomP2P.MOD_ID, Level.INFO,
					"Client %s: starting with %s...", id, config);
			fireStarting();
			Bindings b = new Bindings();
			b.addAddress(InetAddress.getByName(config.getAddress()));
			peer = new PeerMaker(Number160.createHash(id)).setBindings(b)
					.setPorts(config.getPort())
					.setEnableIndirectReplication(true).makeAndListen();
			peer.getConfiguration()
					.setBehindFirewall(config.isBehindFirewall());
			config.getStorageType().apply(peer, config);
			// Discovers outside address.
			InetAddress inetAddress = InetAddress.getByName(config
					.getServerAddress());
			FutureDiscover fd = peer.discover().setInetAddress(inetAddress)
					.setPorts(config.getServerPort()).start();
			FMLLog.log(TomP2P.MOD_ID, Level.INFO, "Client: discovering...");
			fd.awaitUninterruptibly();
			if (fd.isSuccess()) {
				FMLLog.log(TomP2P.MOD_ID, Level.INFO,
						"Client: outside address is %s", fd.getPeerAddress());
				// Bootstrap to master server.
				FutureBootstrap bootstrap = peer.bootstrap()
						.setInetAddress(inetAddress)
						.setPorts(config.getServerPort()).start();
				bootstrap.awaitUninterruptibly();
				if (bootstrap.isSuccess()) {
					// Started.
					setPeer(peer);
					setConfig(config);
					this.id = id;
					// Notifies.
					FMLLog.log(TomP2P.MOD_ID, Level.INFO, "Client: started");
					fireStarted();
				} else {
					FMLLog.log(TomP2P.MOD_ID, Level.SEVERE,
							"Client: could not bootstrap");
				}
			} else {
				FMLLog.log(TomP2P.MOD_ID, Level.SEVERE,
						"Client: failed because of %s", fd.getFailedReason());
			}
		} catch (IOException e) {
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
	public synchronized void stop() {
		if (isRunning()) {
			fireStopping();
			getPeer().shutdown();
			setPeer(null);
			setConfig(null);
			id = null;
			fireStopped();
		}
	}

}