package org.minecraftnauja.p2p.provider.player.event;

import org.minecraftnauja.p2p.provider.player.task.IPlayerGetAddress;

/**
 * Adapter for {@code IPlayerProvider} listeners.
 */
public class PlayerAdapter implements PlayerListener {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGetAddress(IPlayerGetAddress task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGotAddress(IPlayerGetAddress task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGetAddressCancelled(IPlayerGetAddress task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGetAddressException(IPlayerGetAddress task) {
	}

}
