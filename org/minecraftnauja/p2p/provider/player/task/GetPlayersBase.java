package org.minecraftnauja.p2p.provider.player.task;

import java.net.InetAddress;
import java.util.Arrays;

import org.minecraftnauja.p2p.provider.player.IPlayerProvider;

/**
 * Base for {@code IPlayerGetPlayer}.
 */
public abstract class GetPlayersBase extends PlayerTaskBase<IGetPlayers>
		implements IGetPlayers {

	/**
	 * The address.
	 */
	protected final InetAddress address;

	/**
	 * The players.
	 */
	protected String[] players;

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
	public GetPlayersBase(IPlayerProvider source, String channel,
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
	public String[] getPlayers() {
		return players;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "GetPlayersBase [address=" + address + ", players="
				+ Arrays.toString(players) + ", source=" + source
				+ ", channel=" + channel + ", error=" + error + "]";
	}

}
