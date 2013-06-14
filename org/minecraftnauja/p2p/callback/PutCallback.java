package org.minecraftnauja.p2p.callback;

import org.minecraftnauja.p2p.event.PutEvent;


/**
 * Callback for when a put operation is complete.
 */
public interface PutCallback {

	/**
	 * Called when a put operation is complete.
	 * 
	 * @param event
	 *            the event.
	 */
	public void onPut(PutEvent event);

	/**
	 * Called when a put operation failed.
	 * 
	 * @param event
	 *            the event.
	 */
	public void onPutFailed(PutEvent event);

}
