package org.minecraftnauja.p2p.task;

import org.minecraftnauja.p2p.exception.AlreadyRunningException;
import org.minecraftnauja.p2p.provider.event.ICallback;

/**
 * Base for {@code ITask}.
 * 
 * @param <T>
 *            type of the source.
 * @param <U>
 *            type of the task.
 */
public abstract class TaskBase<T, U extends ITask<T>> implements ITask<T> {

	/**
	 * The source.
	 */
	protected final T source;

	/**
	 * The channel.
	 */
	protected final String channel;

	/**
	 * The error.
	 */
	protected Throwable error;

	/**
	 * Initializing constructor.
	 * 
	 * @param source
	 *            the source.
	 * @param channel
	 *            the channel.
	 */
	public TaskBase(T source, String channel) {
		super();
		this.source = source;
		this.channel = channel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getSource() {
		return source;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getChannel() {
		return channel;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Throwable getError() {
		return error;
	}

	/**
	 * Starts the task.
	 * 
	 * @param callback
	 *            a callback.
	 * @throws AlreadyRunningException
	 *             task is already running.
	 */
	public abstract void start(ICallback<U> callback)
			throws AlreadyRunningException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "TaskBase [source=" + source + ", channel=" + channel
				+ ", error=" + error + "]";
	}

}
