package org.minecraftnauja.tomp2p;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

/**
 * Tracker for players.
 */
public class PlayerTracker implements IPlayerTracker {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onPlayerLogout(EntityPlayer player) {
		// Player disconnected, remove it.
		TomP2P.onLeft(player.username);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onPlayerLogin(EntityPlayer player) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onPlayerRespawn(EntityPlayer player) {
	}

}
