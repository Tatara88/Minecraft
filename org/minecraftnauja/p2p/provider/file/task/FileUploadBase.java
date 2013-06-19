package org.minecraftnauja.p2p.provider.file.task;

import java.io.File;

import org.minecraftnauja.p2p.provider.file.IFileProvider;

/**
 * Base for {@code IFileUpload}.
 */
public abstract class FileUploadBase extends FileTaskBase<IFileUpload>
		implements IFileUpload {

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
	public FileUploadBase(IFileProvider source, String channel, String name,
			File file) {
		super(source, channel, name, file);
	}

}
