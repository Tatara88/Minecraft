package org.minecraftnauja.p2p.task;

/**
 * Interface for tasks from the {@code IProvider}.
 * 
 * @param <T>
 *            type of the source.
 */
public interface ITask<T> {

	/**
	 * Gets the source.
	 * 
	 * @return the source.
	 */
	public T getSource();

	/**
	 * Gets the channel.
	 * 
	 * @return the channel.
	 */
	public String getChannel();

	/**
	 * Gets the error.
	 * 
	 * @return the error.
	 */
	public Throwable getError();

	/**
	 * Indicates if the task is running.
	 * 
	 * @return if the task is running.
	 */
	public boolean isRunning();

	/**
	 * Cancels the task.
	 */
	public void cancel();

}
