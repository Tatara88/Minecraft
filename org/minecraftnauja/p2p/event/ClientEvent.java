package org.minecraftnauja.p2p.event;

import org.minecraftnauja.p2p.peer.Client;
import org.minecraftnauja.p2p.peer.IClient;

/**
 * Event related to a client.
 */
public class ClientEvent extends PeerEvent<IClient> {

	/**
	 * Initializing constructor.
	 * 
	 * @param source
	 *            the source.
	 * @param client
	 *            the client.
	 */
	public ClientEvent(Object source, IClient client) {
		super(source, client);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "ClientEvent [peer=" + peer + ", source=" + source + "]";
	}

}
