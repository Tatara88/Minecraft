package org.minecraftnauja.p2p.provider.packet.task;

import org.minecraftnauja.p2p.provider.packet.IPacket;
import org.minecraftnauja.p2p.provider.packet.IPacketProvider;

/**
 * Base for {@code ISendTo}.
 */
public abstract class SendToBase extends PacketTaskBase<ISendTo> implements
		ISendTo {

	/**
	 * The player.
	 */
	protected final String player;

	/**
	 * Initializing constructor.
	 * 
	 * @param source
	 *            the source.
	 * @param channel
	 *            the channel.
	 * @param packet
	 *            the packet.
	 * @param player
	 *            the player.
	 */
	public SendToBase(IPacketProvider source, String channel, IPacket packet,
			String player) {
		super(source, channel, packet);
		this.player = player;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPlayer() {
		return player;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "SendToBase [player=" + player + ", packet=" + packet
				+ ", source=" + source + ", channel=" + channel + ", error="
				+ error + "]";
	}

}
