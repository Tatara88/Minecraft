package org.minecraftnauja.p2p.provider.player.task;

import java.net.InetAddress;

import org.minecraftnauja.p2p.provider.player.IPlayerProvider;

/**
 * Base for {@code IPlayerGetAddress}.
 */
public abstract class PlayerGetAddressBase extends
		PlayerTaskBase<IPlayerGetAddress> implements IPlayerGetAddress {

	/**
	 * The player.
	 */
	protected final String player;

	/**
	 * The address.
	 */
	protected InetAddress address;

	/**
	 * Initializing constructor.
	 * 
	 * @param source
	 *            the source.
	 * @param channel
	 *            the channel.
	 * @param player
	 *            the player.
	 */
	public PlayerGetAddressBase(IPlayerProvider source, String channel,
			String player) {
		super(source, channel);
		this.player = player;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPlayer() {
		return player;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InetAddress getAddress() {
		return address;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "PlayerGetAddressBase [player=" + player + ", address="
				+ address + ", source=" + source + ", channel=" + channel
				+ ", error=" + error + "]";
	}

}
