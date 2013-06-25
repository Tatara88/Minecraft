package org.minecraftnauja.tomp2p;

/**
 * Informations about a peer.
 */
public class Peer {

	/**
	 * Name.
	 */
	private final String player;

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
	 * @param player
	 *            name.
	 * @param address
	 *            address.
	 * @param port
	 *            port.
	 */
	public Peer(String player, String address, int port) {
		super();
		this.player = player;
		this.address = address;
		this.port = port;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name.
	 */
	public String getPlayer() {
		return player;
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
		return "Peer [player=" + player + ", address=" + address + ", port="
				+ port + "]";
	}

}
