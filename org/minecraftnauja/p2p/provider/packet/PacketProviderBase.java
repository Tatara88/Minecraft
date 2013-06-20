package org.minecraftnauja.p2p.provider.packet;

import javax.swing.event.EventListenerList;

import org.minecraftnauja.p2p.provider.packet.event.PacketListener;
import org.minecraftnauja.p2p.provider.packet.task.ISendTo;

/**
 * Base for packets providers.
 */
public abstract class PacketProviderBase implements IPacketProvider {

	/**
	 * Listeners.
	 */
	private final EventListenerList listeners;

	/**
	 * Default constructor.
	 */
	public PacketProviderBase() {
		listeners = new EventListenerList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addListener(PacketListener listener) {
		listeners.add(PacketListener.class, listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeListener(PacketListener listener) {
		listeners.remove(PacketListener.class, listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISendTo sendTo(String channel, IPacket packet, String player) {
		return sendTo(channel, packet, player, null);
	}

	/**
	 * Notifies listeners that a {@code sendTo} started.
	 * 
	 * @param task
	 *            the task.
	 */
	public void fireSendTo(ISendTo task) {
		for (PacketListener l : listeners.getListeners(PacketListener.class)) {
			l.onSendTo(task);
		}
	}

	/**
	 * Notifies listeners that a {@code sendTo} has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	public void fireSentTo(ISendTo task) {
		for (PacketListener l : listeners.getListeners(PacketListener.class)) {
			l.onSentTo(task);
		}
	}

	/**
	 * Notifies listeners that a {@code sendTo} has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	public void fireSendToCancelled(ISendTo task) {
		for (PacketListener l : listeners.getListeners(PacketListener.class)) {
			l.onSendToCancelled(task);
		}
	}

	/**
	 * Notifies listeners that a {@code sendTo} caused an exception.
	 * 
	 * @param task
	 *            the task.
	 */
	public void fireSendToException(ISendTo task) {
		for (PacketListener l : listeners.getListeners(PacketListener.class)) {
			l.onSendToException(task);
		}
	}

	/**
	 * Notifies listeners that a packet has been received.
	 * 
	 * @param packet
	 *            the packet.
	 */
	public void fireReceived(IPacket packet) {
		for (PacketListener l : listeners.getListeners(PacketListener.class)) {
			l.onReceived(packet);
		}
	}

}
