package org.minecraftnauja.p2p.provider.file.event;

import java.io.File;
import java.util.EventObject;

import org.minecraftnauja.p2p.provider.file.IFileProvider;

/**
 * Base for {@code IFileProvider} events.
 */
public class FileProviderEvent extends EventObject implements
		IFileProviderEvent {

	/**
	 * The channel.
	 */
	private final String channel;

	/**
	 * The name.
	 */
	private final String name;

	/**
	 * The file.
	 */
	private final File file;

	/**
	 * The exception.
	 */
	private final Throwable error;

	/**
	 * Initializing constructor.
	 * 
	 * @param source
	 *            the source.
	 * @param channel
	 *            the channel.
	 * @param file
	 *            the file.
	 * @param name
	 *            the name.
	 */
	public FileProviderEvent(IFileProvider source, String channel, File file,
			String name) {
		this(source, channel, name, file);
	}

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
	public FileProviderEvent(IFileProvider source, String channel, String name,
			File file) {
		this(source, channel, name, file, null);
	}

	/**
	 * Initializing constructor.
	 * 
	 * @param source
	 *            the source.
	 * @param channel
	 *            the channel.
	 * @param file
	 *            the file.
	 * @param name
	 *            the name.
	 * @param error
	 *            the exception.
	 */
	public FileProviderEvent(IFileProvider source, String channel, File file,
			String name, Throwable error) {
		this(source, channel, name, file, error);
	}

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
	 * @param error
	 *            the exception.
	 */
	public FileProviderEvent(IFileProvider source, String channel, String name,
			File file, Throwable error) {
		super(source);
		this.channel = channel;
		this.name = name;
		this.file = file;
		this.error = error;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFileProvider getSource() {
		return (IFileProvider) super.getSource();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getChannel() {
		return channel;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Throwable getError() {
		return error;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "FileProviderEvent [channel=" + channel + ", name=" + name
				+ ", file=" + file + ", error=" + error + "]";
	}

}
