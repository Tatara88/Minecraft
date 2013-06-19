package org.minecraftnauja.p2p.provider.file.task;

import java.io.File;

import org.minecraftnauja.p2p.provider.file.IFileProvider;
import org.minecraftnauja.p2p.task.TaskBase;

/**
 * Base for {@code IFileTask}.
 * 
 * @param <U>
 *            type of the task.
 */
public abstract class FileTaskBase<T extends IFileTask> extends
		TaskBase<IFileProvider, T> implements IFileTask {

	/**
	 * The name.
	 */
	protected final String name;

	/**
	 * The file.
	 */
	protected final File file;

	/**
	 * Initializing constructor.
	 * 
	 * @param source
	 *            the source.
	 * @param channel
	 *            the channel.
	 * @param name
	 *            the name.
	 * @param file
	 *            the file.
	 */
	public FileTaskBase(IFileProvider source, String channel, String name,
			File file) {
		super(source, channel);
		this.name = name;
		this.file = file;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public File getFile() {
		return file;
	}

}
