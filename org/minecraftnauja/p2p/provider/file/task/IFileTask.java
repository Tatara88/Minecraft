package org.minecraftnauja.p2p.provider.file.task;

import java.io.File;

import org.minecraftnauja.p2p.provider.file.IFileProvider;
import org.minecraftnauja.p2p.task.ITask;

/**
 * Interface for tasks from the {@code IFileProvider}.
 */
public interface IFileTask extends ITask<IFileProvider> {

	/**
	 * Gets the name.
	 * 
	 * @return the name.
	 */
	public String getName();

	/**
	 * Gets the file.
	 * 
	 * @return the file.
	 */
	public File getFile();

}
