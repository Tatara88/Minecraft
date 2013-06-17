package org.minecraftnauja.p2p.provider.file;

import java.io.File;

import org.minecraftnauja.p2p.provider.file.event.IFileCallback;
import org.minecraftnauja.p2p.provider.file.event.IFileListener;

/**
 * Adapter for {@code IFileProvider}.
 */
public class FileProviderAdapter implements IFileProvider {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addListener(IFileListener listener) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeListener(IFileListener listener) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void upload(String channel, File file, String name) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void upload(String channel, File file, String name,
			IFileCallback callback) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void download(String channel, String name, File file) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void download(String channel, String name, File file,
			IFileCallback callback) {
	}

}
