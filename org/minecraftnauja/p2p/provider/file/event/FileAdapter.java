package org.minecraftnauja.p2p.provider.file.event;

/**
 * Adapter for {@code IFileProvider} listeners.
 */
public class FileAdapter implements IFileListener {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onFileUploaded(IFileProviderEvent event) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onFileUploadException(IFileProviderEvent event) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onFileDownloaded(IFileProviderEvent event) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onFileDownloadException(IFileProviderEvent event) {
	}

}
