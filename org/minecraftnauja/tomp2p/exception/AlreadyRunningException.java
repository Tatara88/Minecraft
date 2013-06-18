package org.minecraftnauja.tomp2p.exception;

import org.minecraftnauja.tomp2p.peer.IPeer;

/**
 * Exception thrown when a peer is already running.
 */
public class AlreadyRunningException extends Exception {

	/**
	 * Creates the exception message.
	 * 
	 * @param peer
	 *            peer instance.
	 * @return the message.
	 */
	private static String message(IPeer peer) {
		return "Peer " + peer + " is already running";
	}

	/**
	 * Peer instance.
	 */
	private final IPeer peer;

	/**
	 * Initializing constructor.
	 * 
	 * @param peer
	 *            peer instance.
	 */
	public AlreadyRunningException(IPeer peer) {
		super(message(peer));
		this.peer = peer;
	}

	/**
	 * Gets the peer instance.
	 * 
	 * @return the peer instance.
	 */
	public IPeer getPeer() {
		return peer;
	}

}
