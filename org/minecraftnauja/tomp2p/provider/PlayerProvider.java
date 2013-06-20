package org.minecraftnauja.tomp2p.provider;

import java.net.InetAddress;

import org.minecraftnauja.p2p.exception.AlreadyRunningException;
import org.minecraftnauja.p2p.provider.event.ICallback;
import org.minecraftnauja.p2p.provider.player.PlayerProviderBase;
import org.minecraftnauja.p2p.provider.player.task.GetAddressBase;
import org.minecraftnauja.p2p.provider.player.task.GetPlayerBase;
import org.minecraftnauja.p2p.provider.player.task.IGetAddress;
import org.minecraftnauja.p2p.provider.player.task.IGetPlayer;
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
	public IGetAddress getAddress(String channel, String player,
			ICallback<IGetAddress> callback) {
		try {
			GetAddress task = new GetAddress(channel, player);
			task.start(callback);
			return task;
		} catch (AlreadyRunningException e) {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IGetPlayer getPlayer(String channel, InetAddress address,
			ICallback<IGetPlayer> callback) {
		try {
			GetPlayer task = new GetPlayer(channel, address);
			task.start(callback);
			return task;
		} catch (AlreadyRunningException e) {
			return null;
		}
	}

	/**
	 * Task for getting a player address.
	 */
	private final class GetAddress extends GetAddressBase {

		/**
		 * Initializing constructor.
		 * 
		 * @param channel
		 *            the channel.
		 * @param player
		 *            the player.
		 */
		public GetAddress(String channel, String player) {
			super(PlayerProvider.this, channel, player);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public synchronized void start(final ICallback<IGetAddress> callback)
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

	/**
	 * Task for getting a player.
	 */
	private final class GetPlayer extends GetPlayerBase {

		/**
		 * Initializing constructor.
		 * 
		 * @param channel
		 *            the channel.
		 * @param address
		 *            the address.
		 */
		public GetPlayer(String channel, InetAddress address) {
			super(PlayerProvider.this, channel, address);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public synchronized void start(final ICallback<IGetPlayer> callback)
				throws AlreadyRunningException {
			player = null;
			if (callback != null) {
				callback.onStarted(this);
			}
			fireGetPlayer(this);
			player = peer.getPlayer(address);
			if (callback != null) {
				callback.onCompleted(this);
			}
			fireGotPlayer(this);
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
