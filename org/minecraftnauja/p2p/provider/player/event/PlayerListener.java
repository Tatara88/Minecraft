package org.minecraftnauja.p2p.provider.player.event;

import java.util.EventListener;

import org.minecraftnauja.p2p.provider.player.task.IPlayerGetAddress;

/**
 * Interface for {@code IPlayerProvider} listeners.
 */
public interface PlayerListener extends EventListener {

	/**
	 * Called when a {@code getAddress} started.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onGetAddress(IPlayerGetAddress task);

	/**
	 * Called when a {@code getAddress} has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onGotAddress(IPlayerGetAddress task);

	/**
	 * Called when a {@code getAddress} has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onGetAddressCancelled(IPlayerGetAddress task);

	/**
	 * Called when a {@code getAddress} caused an exception.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onGetAddressException(IPlayerGetAddress task);
	
}
