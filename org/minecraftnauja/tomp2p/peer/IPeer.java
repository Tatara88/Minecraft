package org.minecraftnauja.tomp2p.peer;

import java.io.IOException;

import net.tomp2p.futures.FutureDHT;

import org.minecraftnauja.p2p.provider.IProvider;
import org.minecraftnauja.tomp2p.config.IPeerConfig;
import org.minecraftnauja.tomp2p.event.PeerNotifier;

/**
 * Interface for peers.
 * 
 * @param <T>
 *            type of the configuration.
 */
public interface IPeer<T extends IPeerConfig> extends IProvider {

	/**
	 * Gets its identifier.
	 * 
	 * @return its identifier.
	 */
	public String getId();

	/**
	 * Gets its configuration.
	 * 
	 * @return its configuration.
	 */
	public T getConfig();

	/**
	 * Indicates if the peer is running.
	 * 
	 * @return if the peer is running.
	 */
	public boolean isRunning();

	/**
	 * Indicates if the peer is shutdown.
	 * 
	 * @return if the peer is shutdown.
	 */
	public boolean isShutdown();

	/**
	 * Puts data in the DHT.
	 * 
	 * @param channel
	 *            the channel.
	 * @param location
	 *            key.
	 * @param data
	 *            value.
	 * @return the {@code FutureDHT}.
	 * @throws IOException
	 *             error with IO.
	 * 
	 */
	public FutureDHT put(String channel, String location, Object data)
			throws IOException;

	/**
	 * Gets data from the DHT.
	 * 
	 * @param channel
	 *            the channel.
	 * @param location
	 *            key.
	 * @return the {@code FutureDHT}.
	 */
	public FutureDHT get(String channel, String location);

}
