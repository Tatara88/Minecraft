package org.minecraftnauja.p2p.provider.packet.event;

import java.util.EventListener;

import org.minecraftnauja.p2p.provider.packet.IPacket;
import org.minecraftnauja.p2p.provider.packet.task.ISendTo;

/**
 * Interface for {@code IPacketProvider} listeners.
 */
public interface PacketListener extends EventListener {

	/**
	 * Called when a {@code sendTo} started.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onSendTo(ISendTo task);

	/**
	 * Called when a {@code sendTo} has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onSentTo(ISendTo task);

	/**
	 * Called when a {@code sendTo} has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onSendToCancelled(ISendTo task);

	/**
	 * Called when a {@code sendTo} caused an exception.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onSendToException(ISendTo task);

	/**
	 * Called when a packet has been received.
	 * 
	 * @param packet
	 *            received packet.
	 */
	public void onReceived(IPacket packet);

}
