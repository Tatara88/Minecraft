package org.minecraftnauja.p2p.provider.player.task;

import java.net.InetAddress;

/**
 * Task for getting the address of a player.
 */
public interface IPlayerGetAddress extends IPlayerTask {

	/**
	 * Gets the player.
	 * 
	 * @return the player.
	 */
	public String getPlayer();

	/**
	 * Gets the address.
	 * 
	 * @return the address.
	 */
	public InetAddress getAddress();

}
