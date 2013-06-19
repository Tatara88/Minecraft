package org.minecraftnauja.p2p.provider.event;

import org.minecraftnauja.p2p.task.ITask;

/**
 * Interface for callbacks.
 * 
 * @param <T>
 *            type of the task.
 */
public interface ICallback<T extends ITask> {

	/**
	 * Called when the task has been started.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onStarted(T task);

	/**
	 * Called when the task has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onCompleted(T task);

	/**
	 * Called when the task has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onCancelled(T task);

	/**
	 * Called when the task caused an exception.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onException(T task);

}
