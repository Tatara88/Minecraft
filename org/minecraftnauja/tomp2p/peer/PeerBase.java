package org.minecraftnauja.tomp2p.peer;

import java.io.IOException;

import net.tomp2p.futures.FutureDHT;
import net.tomp2p.p2p.Peer;
import net.tomp2p.peers.Number160;
import net.tomp2p.storage.Data;

import org.minecraftnauja.p2p.provider.file.IFileProvider;
import org.minecraftnauja.tomp2p.config.IPeerConfig;
import org.minecraftnauja.tomp2p.event.DefaultPeerNotifier;
import org.minecraftnauja.tomp2p.event.PeerNotifier;
import org.minecraftnauja.tomp2p.provider.FileProvider;

/**
 * Base for peers.
 * 
 * @param <T>
 *            type of the configuration.
 */
public abstract class PeerBase<T extends IPeerConfig> implements IPeer<T> {

	/**
	 * Mod identifier.
	 */
	private final String modId;

	/**
	 * Its key.
	 */
	private final String key;

	/**
	 * Its configuration.
	 */
	private final T config;

	/**
	 * Its instance.
	 */
	private Peer peer;

	/**
	 * Notifier for this peer.
	 */
	private final DefaultPeerNotifier notifier;

	/**
	 * The default domain.
	 */
	private String defaultDomain;

	/**
	 * The file provider.
	 */
	private IFileProvider fileProvider;

	/**
	 * Initializing constructor.
	 * 
	 * @param modId
	 *            mod identifier.
	 * @param key
	 *            its key.
	 * @param config
	 *            its configuration.
	 */
	public PeerBase(String modId, String key, T config) {
		super();
		this.modId = modId;
		this.key = key;
		this.config = config;
		notifier = new DefaultPeerNotifier();
		defaultDomain = modId;
		fileProvider = new FileProvider(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getModId() {
		return modId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getKey() {
		return key;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getConfig() {
		return config;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PeerNotifier getNotifier() {
		return notifier;
	}

	/**
	 * Gets its instance.
	 * 
	 * @return its instance.
	 */
	public Peer getPeer() {
		return peer;
	}

	/**
	 * Sets its instance.
	 * 
	 * @param peer
	 *            new value.
	 */
	public void setPeer(Peer peer) {
		this.peer = peer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRunning() {
		return peer.isRunning();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isShutdown() {
		return peer.isShutdown();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDefaultDomain() {
		return defaultDomain;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaultDomain(String domain) {
		defaultDomain = domain;
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
	public FutureDHT put(final String channel, final String location,
			final Object data) throws IOException {
		return peer.put(Number160.createHash(location)).setData(new Data(data))
				.setRefreshSeconds(2).setDirectReplication().start();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FutureDHT get(final String channel, final String location) {
		return peer.get(Number160.createHash(location)).start();
	}

	/**
	 * Gets this peer as a server.
	 * 
	 * @return this peer as a server.
	 */
	public abstract Server toServer();

	/**
	 * Gets this peer as a client.
	 * 
	 * @return this peer as a client.
	 */
	public abstract Client toClient();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "PeerBase [modId=" + modId + ", key=" + key + ", config="
				+ config + ", peer=" + peer + "]";
	}

}
