package org.minecraftnauja.p2p.provider.event;

import java.util.EventListener;

/**
 * Interface for providers listeners.
 */
public interface IProviderListener extends EventListener {

	/**
	 * Called when starting the provider.
	 * 
	 * @param event
	 *            the event.
	 */
	public void onStarting(IProviderEvent event);

	/**
	 * Called when the provider started.
	 * 
	 * @param event
	 *            the event.
	 */
	public void onStarted(IProviderEvent event);

	/**
	 * Called when stopping the provider.
	 * 
	 * @param event
	 *            the event.
	 */
	public void onStopping(IProviderEvent event);

	/**
	 * Called when the provider stopped.
	 * 
	 * @param event
	 *            the event.
	 */
	public void onStopped(IProviderEvent event);

}
