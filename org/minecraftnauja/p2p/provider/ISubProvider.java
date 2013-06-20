package org.minecraftnauja.p2p.provider;

import java.util.EventListener;

/**
 * Interface for providers.
 * 
 * @param <T>
 *            type for listeners.
 */
public interface ISubProvider<T extends EventListener> {

	/**
	 * Adds given listener.
	 * 
	 * @param listener
	 *            the listener to add.
	 */
	public void addListener(T listener);

	/**
	 * Removes given listener.
	 * 
	 * @param listener
	 *            the listener to remove.
	 */
	public void removeListener(T listener);

}
