package org.minecraftnauja.p2p.provider.event;

import org.minecraftnauja.p2p.task.ITask;

/**
 * Adapter for callbacks.
 * 
 * @param <T>
 *            type of the task.
 */
public class Callback<T extends ITask> implements ICallback<T> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onStarted(ITask task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCompleted(ITask task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCancelled(ITask task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onException(ITask task) {
	}

}
