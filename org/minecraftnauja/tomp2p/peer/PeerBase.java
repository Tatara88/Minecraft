package org.minecraftnauja.tomp2p.peer;

import java.io.IOException;
import java.net.InetAddress;

import net.tomp2p.futures.FutureDHT;
import net.tomp2p.p2p.Peer;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.peers.PeerMap;
import net.tomp2p.storage.Data;

import org.minecraftnauja.p2p.provider.ProviderBase;
import org.minecraftnauja.p2p.provider.file.IFileProvider;
import org.minecraftnauja.p2p.provider.player.IPlayerProvider;
import org.minecraftnauja.tomp2p.config.IPeerConfig;
import org.minecraftnauja.tomp2p.provider.FileProvider;
import org.minecraftnauja.tomp2p.provider.PlayerProvider;

/**
 * Base for peers.
 * 
 * @param <T>
 *            type of the configuration.
 */
public abstract class PeerBase<T extends IPeerConfig> extends ProviderBase
		implements IPeer<T> {

	/**
	 * Its instance.
	 */
	private Peer peer;

	/**
	 * Its configuration.
	 */
	private T config;

	/**
	 * The player provider.
	 */
	private final IPlayerProvider playerProvider;

	/**
	 * The file provider.
	 */
	private final IFileProvider fileProvider;

	/**
	 * Default constructor.
	 */
	public PeerBase() {
		super();
		playerProvider = new PlayerProvider(this);
		fileProvider = new FileProvider(this);
	}

	/**
	 * Gets its instance.
	 * 
	 * @return its instance.
	 */
	public synchronized Peer getPeer() {
		return peer;
	}

	/**
	 * Sets its instance.
	 * 
	 * @param peer
	 *            new value.
	 */
	protected synchronized void setPeer(Peer peer) {
		this.peer = peer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized T getConfig() {
		return config;
	}

	/**
	 * Sets its configuration.
	 * 
	 * @param config
	 *            new value.
	 */
	protected synchronized void setConfig(T config) {
		this.config = config;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized boolean isRunning() {
		return peer != null && peer.isRunning();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized boolean isShutdown() {
		return peer == null || peer.isShutdown();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPlayerProvider getPlayerProvider() {
		return playerProvider;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFileProvider getFileProvider() {
		return fileProvider;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FutureDHT put(String channel, String location, Object data)
			throws IOException {
		return peer.put(Number160.createHash(location)).setData(new Data(data))
				.setRefreshSeconds(2).setDirectReplication().start();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FutureDHT get(String channel, String location) {
		return peer.get(Number160.createHash(location)).start();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InetAddress getAddress(String player) {
		Number160 id = Number160.createHash(player);
		PeerMap pm = peer.getPeerBean().getPeerMap();
		for (PeerAddress pa : pm.getAll()) {
			if (pa.getID().equals(id)) {
				return pa.getInetAddress();
			}
		}
		return null;
	}

}
