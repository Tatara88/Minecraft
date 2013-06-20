package org.minecraftnauja.p2p.provider.packet.event;

import org.minecraftnauja.p2p.provider.packet.IPacket;
import org.minecraftnauja.p2p.provider.packet.task.ISendTo;

/**
 * Adapter for {@code IPacketProvider} listeners.
 */
public class PacketAdapter implements PacketListener {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendTo(ISendTo task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSentTo(ISendTo task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendToCancelled(ISendTo task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onSendToException(ISendTo task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceived(IPacket packet) {
	}

}
