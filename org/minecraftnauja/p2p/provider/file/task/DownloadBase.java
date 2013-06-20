package org.minecraftnauja.p2p.provider.file.task;

import java.io.File;

import org.minecraftnauja.p2p.provider.file.IFileProvider;

/**
 * Base for {@code IDownload}.
 */
public abstract class DownloadBase extends FileTaskBase<IDownload>
		implements IDownload {

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
	public DownloadBase(IFileProvider source, String channel, String name,
			File file) {
		super(source, channel, name, file);
	}

}
