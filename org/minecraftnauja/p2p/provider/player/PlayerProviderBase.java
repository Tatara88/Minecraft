package org.minecraftnauja.p2p.provider.player;

import java.net.InetAddress;

import javax.swing.event.EventListenerList;

import org.minecraftnauja.p2p.provider.player.event.PlayerListener;
import org.minecraftnauja.p2p.provider.player.task.IGetAddress;
import org.minecraftnauja.p2p.provider.player.task.IGetPlayer;

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
	public IGetAddress getAddress(String channel, String player) {
		return getAddress(channel, player, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IGetPlayer getPlayer(String channel, InetAddress address) {
		return getPlayer(channel, address, null);
	}

	/**
	 * Notifies listeners that a {@code getAddress} started.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireGetAddress(IGetAddress task) {
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
	protected void fireGotAddress(IGetAddress task) {
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
	protected void fireGetAddressCancelled(IGetAddress task) {
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
	protected void fireGetAddressException(IGetAddress task) {
		for (PlayerListener l : listeners.getListeners(PlayerListener.class)) {
			l.onGetAddressException(task);
		}
	}

	/**
	 * Notifies listeners that a {@code getPlayer} started.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireGetPlayer(IGetPlayer task) {
		for (PlayerListener l : listeners.getListeners(PlayerListener.class)) {
			l.onGetPlayer(task);
		}
	}

	/**
	 * Notifies listeners that a {@code getPlayer} has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireGotPlayer(IGetPlayer task) {
		for (PlayerListener l : listeners.getListeners(PlayerListener.class)) {
			l.onGotPlayer(task);
		}
	}

	/**
	 * Notifies listeners that a {@code getPlayer} has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireGetPlayerCancelled(IGetPlayer task) {
		for (PlayerListener l : listeners.getListeners(PlayerListener.class)) {
			l.onGetPlayerCancelled(task);
		}
	}

	/**
	 * Notifies listeners that a {@code getPlayer} caused an exception.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireGetPlayerException(IGetPlayer task) {
		for (PlayerListener l : listeners.getListeners(PlayerListener.class)) {
			l.onGetPlayerException(task);
		}
	}

}
