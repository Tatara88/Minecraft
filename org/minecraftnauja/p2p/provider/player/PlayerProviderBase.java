package org.minecraftnauja.p2p.provider.player;

import javax.swing.event.EventListenerList;

import org.minecraftnauja.p2p.provider.player.event.PlayerListener;
import org.minecraftnauja.p2p.provider.player.task.IPlayerGetAddress;

/**
 * Base for peers providers.
 */
public abstract class PlayerProviderBase implements IPlayerProvider {

	/**
	 * Listeners.
	 */
	private final EventListenerList listeners;

	/**
	 * Default constructor.
	 */
	public PlayerProviderBase() {
		listeners = new EventListenerList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addListener(PlayerListener listener) {
		listeners.add(PlayerListener.class, listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeListener(PlayerListener listener) {
		listeners.remove(PlayerListener.class, listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPlayerGetAddress getAddress(String channel, String player) {
		return getAddress(channel, player, null);
	}

	/**
	 * Notifies listeners that a {@code getAddress} started.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireGetAddress(IPlayerGetAddress task) {
		for (PlayerListener l : listeners.getListeners(PlayerListener.class)) {
			l.onGetAddress(task);
		}
	}

	/**
	 * Notifies listeners that a {@code getAddress} has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireGotAddress(IPlayerGetAddress task) {
		for (PlayerListener l : listeners.getListeners(PlayerListener.class)) {
			l.onGotAddress(task);
		}
	}

	/**
	 * Notifies listeners that a {@code getAddress} has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireGetAddressCancelled(IPlayerGetAddress task) {
		for (PlayerListener l : listeners.getListeners(PlayerListener.class)) {
			l.onGetAddressCancelled(task);
		}
	}

	/**
	 * Notifies listeners that a {@code getAddress} caused an exception.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireGetAddressException(IPlayerGetAddress task) {
		for (PlayerListener l : listeners.getListeners(PlayerListener.class)) {
			l.onGetAddressException(task);
		}
	}

}
