package org.minecraftnauja.p2p.provider.player;

import org.minecraftnauja.p2p.provider.event.ICallback;
import org.minecraftnauja.p2p.provider.player.event.PlayerListener;
import org.minecraftnauja.p2p.provider.player.task.IPlayerGetAddress;

/**
 * Interface for peers providers.
 */
public interface IPlayerProvider {

	/**
	 * Adds given listener.
	 * 
	 * @param listener
	 *            the listener to add.
	 */
	public void addListener(PlayerListener listener);

	/**
	 * Removes given listener.
	 * 
	 * @param listener
	 *            the listener to remove.
	 */
	public void removeListener(PlayerListener listener);

	/**
	 * Gets the address of given player.
	 * 
	 * @param channel
	 *            the channel.
	 * @param player
	 *            the player.
	 * @return the task.
	 */
	public IPlayerGetAddress getAddress(String channel, String player);

	/**
	 * Gets the address of given player.
	 * 
	 * @param channel
	 *            the channel.
	 * @param player
	 *            the player.
	 * @param callback
	 *            a callback.
	 * @return the task.
	 */
	public IPlayerGetAddress getAddress(String channel, String player,
			ICallback<IPlayerGetAddress> callback);

}
