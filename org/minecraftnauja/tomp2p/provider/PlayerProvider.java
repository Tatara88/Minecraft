package org.minecraftnauja.tomp2p.provider;

import org.minecraftnauja.p2p.exception.AlreadyRunningException;
import org.minecraftnauja.p2p.provider.event.ICallback;
import org.minecraftnauja.p2p.provider.player.PlayerProviderBase;
import org.minecraftnauja.p2p.provider.player.task.IPlayerGetAddress;
import org.minecraftnauja.p2p.provider.player.task.PlayerGetAddressBase;
import org.minecraftnauja.tomp2p.peer.IPeer;

/**
 * Implementation of the player provider.
 */
public final class PlayerProvider extends PlayerProviderBase {

	/**
	 * Peer instance.
	 */
	private final IPeer peer;

	/**
	 * Initializing constructor.
	 * 
	 * @param peer
	 *            peer instance.
	 */
	public PlayerProvider(IPeer peer) {
		super();
		this.peer = peer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPlayerGetAddress getAddress(String channel, String player,
			ICallback<IPlayerGetAddress> callback) {
		try {
			PlayerGetAddress task = new PlayerGetAddress(channel, player);
			task.start(callback);
			return task;
		} catch (AlreadyRunningException e) {
			return null;
		}
	}

	/**
	 * Task for getting a player address.
	 */
	private final class PlayerGetAddress extends PlayerGetAddressBase {

		/**
		 * Initializing constructor.
		 * 
		 * @param channel
		 *            the channel.
		 * @param player
		 *            the player.
		 */
		public PlayerGetAddress(String channel, String player) {
			super(PlayerProvider.this, channel, player);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public synchronized void start(
				final ICallback<IPlayerGetAddress> callback)
				throws AlreadyRunningException {
			address = null;
			if (callback != null) {
				callback.onStarted(this);
			}
			fireGetAddress(this);
			address = peer.getAddress(player);
			if (callback != null) {
				callback.onCompleted(this);
			}
			fireGotAddress(this);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean isRunning() {
			return false;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void cancel() {
		}

	}

}
