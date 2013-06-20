package org.minecraftnauja.p2p.provider.player;

import java.net.InetAddress;

import org.minecraftnauja.p2p.provider.ISubProvider;
import org.minecraftnauja.p2p.provider.event.ICallback;
import org.minecraftnauja.p2p.provider.player.event.PlayerListener;
import org.minecraftnauja.p2p.provider.player.task.IGetAddress;
import org.minecraftnauja.p2p.provider.player.task.IGetPlayers;

/**
 * Interface for peers providers.
 */
public interface IPlayerProvider extends ISubProvider<PlayerListener> {

	/**
	 * Gets the address of given player.
	 * 
	 * @param channel
	 *            the channel.
	 * @param player
	 *            the player.
	 * @return the task.
	 */
	public IGetAddress getAddress(String channel, String player);

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
	public IGetAddress getAddress(String channel, String player,
			ICallback<IGetAddress> callback);

	/**
	 * Gets the players at given address.
	 * 
	 * @param channel
	 *            the channel.
	 * @param address
	 *            the address.
	 * @return the task.
	 */
	public IGetPlayers getPlayers(String channel, InetAddress address);

	/**
	 * Gets the players at given address.
	 * 
	 * @param channel
	 *            the channel.
	 * @param address
	 *            the address.
	 * @param callback
	 *            a callback.
	 * @return the task.
	 */
	public IGetPlayers getPlayers(String channel, InetAddress address,
			ICallback<IGetPlayers> callback);

}
