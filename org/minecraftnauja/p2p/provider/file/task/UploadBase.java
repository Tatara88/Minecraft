package org.minecraftnauja.p2p.provider.file.task;

import java.io.File;

import org.minecraftnauja.p2p.provider.file.IFileProvider;

/**
 * Base for {@code IUpload}.
 */
public abstract class UploadBase extends FileTaskBase<IUpload>
		implements IUpload {

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
	public UploadBase(IFileProvider source, String channel, String name,
			File file) {
		super(source, channel, name, file);
	}

}
