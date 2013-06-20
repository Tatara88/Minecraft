package org.minecraftnauja.toolbox.packet;

import net.minecraft.client.Minecraft;

import org.minecraftnauja.toolbox.Toolbox;

/**
 * Packet sent when pinging someone.
 */
public class PacketPing extends PacketBase {

	/**
	 * Output "pong" instead of "ping".
	 */
	public boolean pong;

	/**
	 * How many time this packet must be sent.
	 */
	public int time;

	/**
	 * Initializing constructor.
	 * 
	 * @param time
	 *            how many time this packet must be sent.
	 */
	public PacketPing(int time) {
		super();
		this.time = time;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handle() {
		String sender = getSender();
		String address = getAddress().toString();
		if (!address.startsWith("/")) {
			address = '/' + address;
		}
		pong = !pong;
		if (pong) {
			// If pong is true, we are the one pinged.
			chat('[' + sender + address + "] ping");
			if (Toolbox.running) {
				// Provider still running, respond to ping.
				getSource().sendTo(Toolbox.MOD_ID, this, sender);
			}
		} else {
			// If pong is false, we are the one pinging.
			chat('[' + sender + address + "] pong");
			time--;
			if (Toolbox.running && time >= 1) {
				// Provider still running, ping again.
				getSource().sendTo(Toolbox.MOD_ID, this, sender);
			}
		}
	}

}
