package org.minecraftnauja.p2p.provider.player.task;

import java.net.InetAddress;

import org.minecraftnauja.p2p.provider.player.IPlayerProvider;

/**
 * Base for {@code IPlayerGetPlayer}.
 */
public abstract class GetPlayerBase extends PlayerTaskBase<IGetPlayer>
		implements IGetPlayer {

	/**
	 * The address.
	 */
	protected final InetAddress address;

	/**
	 * The player.
	 */
	protected String player;

	/**
	 * Initializing constructor.
	 * 
	 * @param source
	 *            the source.
	 * @param channel
	 *            the channel.
	 * @param address
	 *            the address.
	 */
	public GetPlayerBase(IPlayerProvider source, String channel,
			InetAddress address) {
		super(source, channel);
		this.address = address;
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
	public String getPlayer() {
		return player;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "GetPlayerBase [address=" + address + ", player=" + player
				+ ", source=" + source + ", channel=" + channel + ", error="
				+ error + "]";
	}

}
