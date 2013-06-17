package org.minecraftnauja.p2p.provider.file.event;

import java.io.File;
import java.io.Serializable;

import org.minecraftnauja.p2p.provider.file.IFileProvider;

/**
 * Interface for {@code IFileProvider} events.
 */
public interface IFileProviderEvent extends Serializable {

	/**
	 * Gets the {@code IFileProvider} that caused this event.
	 * 
	 * @return the source.
	 */
	public IFileProvider getSource();

	/**
	 * Gets the channel.
	 * 
	 * @return the channel.
	 */
	public String getChannel();

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

	/**
	 * Gets the exception.
	 * 
	 * @return the exception.
	 */
	public Throwable getError();

}
