package org.minecraftnauja.tomp2p.peer;

/**
 * Interface for clients.
 */
public interface IClient extends IPeer {

	/**
	 * Starts the client.
	 * 
	 * @param id
	 *            peer identifier.
	 * @param address
	 *            bootstrap address.
	 * @param port
	 *            bootstrap port.
	 * @throws Exception
	 *             any error.
	 */
	public void start(String id, String address, int port) throws Exception;

	/**
	 * Starts the client.
	 * 
	 * @param id
	 *            peer identifier.
	 * @throws Exception
	 *             any error.
	 */
	public void start(String id) throws Exception;

	/**
	 * Stops the client.
	 */
	public void stop();

}
