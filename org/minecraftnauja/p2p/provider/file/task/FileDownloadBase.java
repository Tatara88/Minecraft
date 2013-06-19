package org.minecraftnauja.p2p.provider.file.task;

import java.io.File;

import org.minecraftnauja.p2p.provider.file.IFileProvider;

/**
 * Base for {@code IFileDownload}.
 */
public abstract class FileDownloadBase extends FileTaskBase<IFileDownload>
		implements IFileDownload {

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
	public FileDownloadBase(IFileProvider source, String channel, String name,
			File file) {
		super(source, channel, name, file);
	}

}
