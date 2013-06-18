package org.minecraftnauja.tomp2p.peer;

import java.io.IOException;
import java.net.UnknownHostException;

import org.minecraftnauja.tomp2p.config.IServerConfig;
import org.minecraftnauja.tomp2p.exception.AlreadyRunningException;

/**
 * Interface for servers.
 */
public interface IServer extends IPeer<IServerConfig> {

	/**
	 * Starts the server.
	 * 
	 * @param config
	 *            server configuration.
	 * @throws AlreadyRunningException
	 *             the server is already running.
	 * @throws UnknownHostException
	 *             the host is unknown.
	 * @throws IOException
	 *             error with IO.
	 */
	public void start(IServerConfig config) throws AlreadyRunningException,
			UnknownHostException, IOException;

	/**
	 * Stops the server.
	 */
	public void stop();

}
