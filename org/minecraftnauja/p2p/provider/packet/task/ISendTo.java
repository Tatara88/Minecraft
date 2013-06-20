package org.minecraftnauja.p2p.provider.packet.task;

/**
 * Task for sending a packet to a player.
 */
public interface ISendTo extends IPacketTask {

	/**
	 * Gets the player.
	 * 
	 * @return the player.
	 */
	public String getPlayer();

}
