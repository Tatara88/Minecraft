package org.minecraftnauja.p2p.provider.player.event;

import org.minecraftnauja.p2p.provider.player.task.IGetAddress;
import org.minecraftnauja.p2p.provider.player.task.IGetPlayers;

/**
 * Adapter for {@code IPlayerProvider} listeners.
 */
public class PlayerAdapter implements PlayerListener {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGetAddress(IGetAddress task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGotAddress(IGetAddress task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGetAddressCancelled(IGetAddress task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGetAddressException(IGetAddress task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGetPlayer(IGetPlayers task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGotPlayer(IGetPlayers task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGetPlayerCancelled(IGetPlayers task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGetPlayerException(IGetPlayers task) {
	}

}
