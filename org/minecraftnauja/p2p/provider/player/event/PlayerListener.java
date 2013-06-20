package org.minecraftnauja.p2p.provider.player.event;

import java.util.EventListener;

import org.minecraftnauja.p2p.provider.player.task.IGetAddress;
import org.minecraftnauja.p2p.provider.player.task.IGetPlayer;

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
	public void onGetAddress(IGetAddress task);

	/**
	 * Called when a {@code getAddress} has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onGotAddress(IGetAddress task);

	/**
	 * Called when a {@code getAddress} has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onGetAddressCancelled(IGetAddress task);

	/**
	 * Called when a {@code getAddress} caused an exception.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onGetAddressException(IGetAddress task);

	/**
	 * Called when a {@code getPlayer} started.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onGetPlayer(IGetPlayer task);

	/**
	 * Called when a {@code getPlayer} has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onGotPlayer(IGetPlayer task);

	/**
	 * Called when a {@code getPlayer} has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onGetPlayerCancelled(IGetPlayer task);

	/**
	 * Called when a {@code getPlayer} caused an exception.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onGetPlayerException(IGetPlayer task);
	
}
