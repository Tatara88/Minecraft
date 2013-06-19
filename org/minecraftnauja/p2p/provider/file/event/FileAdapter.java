package org.minecraftnauja.p2p.provider.file.event;

import org.minecraftnauja.p2p.provider.file.task.IFileDownload;
import org.minecraftnauja.p2p.provider.file.task.IFileUpload;

/**
 * Adapter for {@code IFileProvider} listeners.
 */
public class FileAdapter implements FileListener {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUpload(IFileUpload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUploaded(IFileUpload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUploadCancelled(IFileUpload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUploadException(IFileUpload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDownload(IFileDownload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDownloaded(IFileDownload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDownloadCancelled(IFileDownload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDownloadException(IFileDownload task) {
	}

}
