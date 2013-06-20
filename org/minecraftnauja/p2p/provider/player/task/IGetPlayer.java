package org.minecraftnauja.p2p.provider.player.task;

import java.net.InetAddress;

/**
 * Task for getting a player by its address.
 */
public interface IGetPlayer extends IPlayerTask {

	/**
	 * Gets the address.
	 * 
	 * @return the address.
	 */
	public InetAddress getAddress();

	/**
	 * Gets the player.
	 * 
	 * @return the player.
	 */
	public String getPlayer();

}
