package org.minecraftnauja.ping;

import org.minecraftnauja.p2p.provider.packet.PacketBase;

public class PacketPing extends PacketBase {

	/**
	 * Output "pong" instead of "ping".
	 */
	public boolean pong;

	/**
	 * How many time this packet must be sent.
	 */
	public int time;

	public PacketPing(int time) {
		super();
		this.time = time;
	}

}
