package org.minecraftnauja.p2p.provider;

import org.minecraftnauja.p2p.provider.event.IProviderListener;
import org.minecraftnauja.p2p.provider.file.IFileProvider;
import org.minecraftnauja.p2p.provider.player.IPlayerProvider;

/**
 * Interface for global providers.
 */
public interface IProvider {

	/**
	 * Gets its name.
	 * 
	 * @return its name.
	 */
	public String getName();

	/**
	 * Gets the player provider.
	 * 
	 * @return the player provider.
	 */
	public IPlayerProvider getPlayerProvider();

	/**
	 * Gets the file provider.
	 * 
	 * @return the file provider.
	 */
	public IFileProvider getFileProvider();

	/**
	 * Adds given listener.
	 * 
	 * @param listener
	 *            the listener to add.
	 */
	public void addListener(IProviderListener listener);

	/**
	 * Removes given listener.
	 * 
	 * @param listener
	 *            the listener to remove.
	 */
	public void removeListener(IProviderListener listener);

}
