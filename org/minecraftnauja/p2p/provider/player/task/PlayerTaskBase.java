package org.minecraftnauja.p2p.provider.player.task;

import org.minecraftnauja.p2p.provider.player.IPlayerProvider;
import org.minecraftnauja.p2p.task.TaskBase;

/**
 * Base for {@code IPlayerTask}.
 * 
 * @param <T>
 *            type of the task.
 */
public abstract class PlayerTaskBase<T extends IPlayerTask> extends
		TaskBase<IPlayerProvider, T> implements IPlayerTask {

	/**
	 * Initializing constructor.
	 * 
	 * @param source
	 *            the source.
	 * @param channel
	 *            the channel.
	 */
	public PlayerTaskBase(IPlayerProvider source, String channel) {
		super(source, channel);
	}

}
