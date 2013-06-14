package org.minecraftnauja.p2p.peer;

import java.io.IOException;

import org.minecraftnauja.p2p.callback.GetCallback;
import org.minecraftnauja.p2p.callback.PutCallback;
import org.minecraftnauja.p2p.config.IPeerConfig;
import org.minecraftnauja.p2p.event.PeerNotifier;

/**
 * Interface for peers.
 * 
 * @param <T>
 *            type of the configuration.
 */
public interface IPeer<T extends IPeerConfig> {

	/**
	 * Gets the mod identifier.
	 * 
	 * @return the mod identifier.
	 */
	public String getModId();

	/**
	 * Gets its key.
	 * 
	 * @return its key.
	 */
	public String getKey();

	/**
	 * Gets its configuration.
	 * 
	 * @return its configuration.
	 */
	public T getConfig();

	/**
	 * Gets the notifier for this peer.
	 * 
	 * @return the notifier for this peer.
	 */
	public PeerNotifier getNotifier();

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
	 * Gets the default location for this peer.
	 * 
	 * @return the default location.
	 */
	public String getDefaultLocation();

	/**
	 * Sets the default location for this peer.
	 * 
	 * @param location
	 *            new value.
	 */
	public void setDefaultLocation(String location);

	/**
	 * Gets the default domain for this peer.
	 * 
	 * @return the default domain.
	 */
	public String getDefaultDomain();

	/**
	 * Sets the default domain for this peer.
	 * 
	 * @param domain
	 *            new value.
	 */
	public void setDefaultDomain(String domain);

	/**
	 * Puts data in the DHT.
	 * 
	 * @param channel
	 *            the channel.
	 * @param location
	 *            key.
	 * @param data
	 *            value.
	 * @throws IOException
	 *             error with IO.
	 */
	public void put(String channel, String location, Object data)
			throws IOException;

	/**
	 * Puts data in the DHT.
	 * 
	 * @param channel
	 *            the channel.
	 * @param location
	 *            key.
	 * @param data
	 *            value.
	 * @param callback
	 *            callback functions.
	 * @throws IOException
	 *             error with IO.
	 */
	public void put(String channel, String location, Object data,
			PutCallback callback) throws IOException;

	/**
	 * Gets data from the DHT.
	 * 
	 * @param channel
	 *            the channel.
	 * @param location
	 *            key.
	 */
	public void get(String channel, String location);

	/**
	 * Gets data from the DHT.
	 * 
	 * @param channel
	 *            the channel.
	 * @param location
	 *            key.
	 * @param callback
	 *            callback functions.
	 */
	public void get(String channel, String location, GetCallback callback);

}
