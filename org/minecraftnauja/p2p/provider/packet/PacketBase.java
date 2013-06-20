package org.minecraftnauja.p2p.provider.packet;

import java.net.InetAddress;

/**
 * Base for packets.
 */
public abstract class PacketBase implements IPacket {

	/**
	 * The source.
	 */
	private transient IPacketProvider source;

	/**
	 * The channel.
	 */
	private String channel;

	/**
	 * The sender.
	 */
	private String sender;

	/**
	 * The sender's address.
	 */
	private transient InetAddress address;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPacketProvider getSource() {
		return source;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSource(IPacketProvider source) {
		this.source = source;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getChannel() {
		return channel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSender() {
		return sender;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSender(String sender) {
		this.sender = sender;
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
	public void setAddress(InetAddress address) {
		this.address = address;
	}

}
