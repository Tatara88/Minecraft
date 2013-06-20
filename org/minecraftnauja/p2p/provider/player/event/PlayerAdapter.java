package org.minecraftnauja.p2p.provider.player.event;

import org.minecraftnauja.p2p.provider.player.task.IGetAddress;
import org.minecraftnauja.p2p.provider.player.task.IGetPlayer;

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
	public void onGetPlayer(IGetPlayer task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGotPlayer(IGetPlayer task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGetPlayerCancelled(IGetPlayer task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGetPlayerException(IGetPlayer task) {
	}

}
