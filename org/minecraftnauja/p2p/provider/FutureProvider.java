package org.minecraftnauja.p2p.provider;

import org.minecraftnauja.p2p.provider.file.FileProviderAdapter;
import org.minecraftnauja.p2p.provider.file.IFileProvider;

/**
 * Blank provider for a real provider to be created in the future.
 */
public class FutureProvider implements IProvider {

	/**
	 * Its name.
	 */
	private String name;

	/**
	 * The real provider.
	 */
	private IProvider provider;

	/**
	 * Empty provider.
	 */
	private IFileProvider fileProvider;

	/**
	 * Initializing constructor.
	 * 
	 * @param name
	 *            its name.
	 */
	public FutureProvider(String name) {
		this.name = name;
		fileProvider = new FileProviderAdapter();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Gets the real provider.
	 * 
	 * @return the real provider.
	 */
	public synchronized IProvider getProvider() {
		return provider;
	}

	/**
	 * Sets the real provider.
	 * 
	 * @param provider
	 *            new value.
	 */
	public synchronized void setProvider(IProvider provider) {
		this.provider = provider;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized IFileProvider getFileProvider() {
		return provider == null ? fileProvider : provider.getFileProvider();
	}

}
