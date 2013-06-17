package org.minecraftnauja.p2p.provider;

import org.minecraftnauja.p2p.provider.file.IFileProvider;

/**
 * Interface for global providers.
 */
public interface IProvider {

	/**
	 * Gets its name.
	 * 
	 * @return its name.
	 */
	public String getName();

	/**
	 * Gets the file provider.
	 * 
	 * @return the file provider.
	 */
	public IFileProvider getFileProvider();

}
