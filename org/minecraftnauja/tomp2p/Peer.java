package org.minecraftnauja.tomp2p;

/**
 * Informations about a peer.
 */
public class Peer {

	/**
	 * Address.
	 */
	private final String address;

	/**
	 * Port.
	 */
	private final int port;

	/**
	 * Initializing constructor.
	 * 
	 * @param address
	 *            address.
	 * @param port
	 *            port.
	 */
	public Peer(String address, int port) {
		super();
		this.address = address;
		this.port = port;
	}

	/**
	 * Gets the address.
	 * 
	 * @return the address.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Gets the port.
	 * 
	 * @return the port.
	 */
	public int getPort() {
		return port;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Peer [address=" + address + ", port=" + port + "]";
	}

}
