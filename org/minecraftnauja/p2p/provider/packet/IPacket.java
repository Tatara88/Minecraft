package org.minecraftnauja.p2p.provider.packet;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * Interface for packets.
 */
public interface IPacket extends Serializable {

	/**
	 * Gets the source.
	 * 
	 * @return the source.
	 */
	public IPacketProvider getSource();

	/**
	 * Sets the source.
	 * 
	 * @param source
	 *            the source.
	 */
	public void setSource(IPacketProvider source);

	/**
	 * Gets the channel.
	 * 
	 * @return the channel.
	 */
	public String getChannel();

	/**
	 * Sets the channel.
	 * 
	 * @param channel
	 *            the channel.
	 */
	public void setChannel(String channel);

	/**
	 * Gets the sender.
	 * 
	 * @return the sender;
	 */
	public String getSender();

	/**
	 * Sets the sender.
	 * 
	 * @param sender
	 *            the sender.
	 */
	public void setSender(String sender);

	/**
	 * Gets the sender's address.
	 * 
	 * @return the sender's address.
	 */
	public InetAddress getAddress();

	/**
	 * Sets the sender's address.
	 * 
	 * @param address
	 *            the sender's address.
	 */
	public void setAddress(InetAddress address);

}
