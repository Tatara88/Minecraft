package org.minecraftnauja.p2p.provider.packet;

import org.minecraftnauja.p2p.provider.ISubProvider;
import org.minecraftnauja.p2p.provider.event.ICallback;
import org.minecraftnauja.p2p.provider.packet.event.PacketListener;
import org.minecraftnauja.p2p.provider.packet.task.ISendTo;

/**
 * Interface for packets providers.
 */
public interface IPacketProvider extends ISubProvider<PacketListener> {

	/**
	 * Sends a packet to given player.
	 * 
	 * @param channel
	 *            channel to use.
	 * @param packet
	 *            packet to send.
	 * @param player
	 *            name of the player.
	 * @return the task.
	 */
	public ISendTo sendTo(String channel, IPacket packet, String player);

	/**
	 * Sends a packet to given player.
	 * 
	 * @param channel
	 *            channel to use.
	 * @param packet
	 *            packet to send.
	 * @param player
	 *            name of the player.
	 * @param callback
	 *            a callback.
	 * @return the task.
	 */
	public ISendTo sendTo(String channel, IPacket packet, String player,
			ICallback<ISendTo> callback);

}
