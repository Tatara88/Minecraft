package org.minecraftnauja.p2p.peer;

import net.tomp2p.p2p.Peer;

import org.minecraftnauja.p2p.config.IServerConfig;

/**
 * Server.
 */
public class Server extends PeerBase<IServerConfig> implements IServer {

	/**
	 * The default location.
	 */
	private String defaultLocation;

	/**
	 * Initializing constructor.
	 * 
	 * @param modId
	 *            the mod identifier.
	 * @param key
	 *            its key.
	 * @param config
	 *            its configuration.
	 */
	public Server(String modId, String key, IServerConfig config) {
		super(modId, key, config);
		defaultLocation = config.getAddress();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDefaultLocation() {
		return defaultLocation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDefaultLocation(String location) {
		defaultLocation = location;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Server toServer() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Client toClient() {
		return null;
	}

}
