package org.minecraftnauja.p2p.provider.packet.task;

import org.minecraftnauja.p2p.provider.packet.IPacket;
import org.minecraftnauja.p2p.provider.packet.IPacketProvider;
import org.minecraftnauja.p2p.task.TaskBase;

/**
 * Base for {@code IPacketTask}.
 * 
 * @param <U>
 *            type of the task.
 */
public abstract class PacketTaskBase<T extends IPacketTask> extends
		TaskBase<IPacketProvider, T> implements IPacketTask {

	/**
	 * The packet.
	 */
	protected final IPacket packet;

	/**
	 * Initializing constructor.
	 * 
	 * @param source
	 *            the source.
	 * @param channel
	 *            the channel.
	 * @param packet
	 *            the packet.
	 */
	public PacketTaskBase(IPacketProvider source, String channel, IPacket packet) {
		super(source, channel);
		this.packet = packet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPacket getPacket() {
		return packet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "PacketTaskBase [packet=" + packet + ", source=" + source
				+ ", channel=" + channel + ", error=" + error + "]";
	}

}
