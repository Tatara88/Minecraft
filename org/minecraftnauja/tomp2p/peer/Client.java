package org.minecraftnauja.tomp2p.peer;

import net.tomp2p.p2p.Peer;

import org.minecraftnauja.tomp2p.config.IClientConfig;

/**
 * Client.
 */
public class Client extends PeerBase<IClientConfig> implements IClient {

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
	public Client(String modId, String key, IClientConfig config) {
		super(modId, key, config);
		defaultLocation = config.getServerAddress();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "Client";
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
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Client toClient() {
		return this;
	}

}
