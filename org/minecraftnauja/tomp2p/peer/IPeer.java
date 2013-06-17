package org.minecraftnauja.tomp2p.peer;

import java.io.IOException;

import net.tomp2p.futures.FutureDHT;

import org.minecraftnauja.p2p.provider.IProvider;
import org.minecraftnauja.tomp2p.callback.GetCallback;
import org.minecraftnauja.tomp2p.callback.PutCallback;
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
