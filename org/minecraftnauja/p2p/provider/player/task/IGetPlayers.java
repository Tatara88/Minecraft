package org.minecraftnauja.p2p.provider.player.task;

import java.net.InetAddress;

/**
 * Task for getting a player by its address.
 */
public interface IGetPlayers extends IPlayerTask {

	/**
	 * Gets the address.
	 * 
	 * @return the address.
	 */
	public InetAddress getAddress();

	/**
	 * Gets the players.
	 * 
	 * @return the players.
	 */
	public String[] getPlayers();

}
