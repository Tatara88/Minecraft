package org.minecraftnauja.tomp2p.peer;

import java.io.IOException;

import org.minecraftnauja.tomp2p.config.ClientConfig;
import org.minecraftnauja.tomp2p.config.IClientConfig;
import org.minecraftnauja.tomp2p.exception.AlreadyRunningException;

/**
 * Interface for clients.
 */
public interface IClient extends IPeer<IClientConfig> {

	/**
	 * Starts the client.
	 * 
	 * @param id
	 *            peer identifier.
	 * @param config
	 *            client configuration.
	 * @throws AlreadyRunningException
	 *             the client is already running.
	 * @throws IOException
	 *             error with IO.
	 */
	public void start(String id, ClientConfig config)
			throws AlreadyRunningException, IOException;

	/**
	 * Stops the client.
	 */
	public void stop();

}
