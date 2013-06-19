package org.minecraftnauja.p2p.exception;

import org.minecraftnauja.p2p.task.ITask;

/**
 * Exception thrown when a task is already running.
 */
public class AlreadyRunningException extends Exception {

	/**
	 * Creates the exception message.
	 * 
	 * @param task
	 *            the task.
	 * @return the message.
	 */
	private static String message(ITask task) {
		return "Task already running " + task;
	}

	/**
	 * The task.
	 */
	private final ITask task;

	/**
	 * Initializing constructor.
	 * 
	 * @param task
	 *            the task.
	 */
	public AlreadyRunningException(ITask task) {
		super(message(task));
		this.task = task;
	}

	/**
	 * Gets the task.
	 * 
	 * @return the task.
	 */
	public ITask getTask() {
		return task;
	}

}
