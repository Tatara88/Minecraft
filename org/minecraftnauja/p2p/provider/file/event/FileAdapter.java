package org.minecraftnauja.p2p.provider.file.event;

import org.minecraftnauja.p2p.provider.file.task.IDownload;
import org.minecraftnauja.p2p.provider.file.task.IUpload;

/**
 * Adapter for {@code IFileProvider} listeners.
 */
public class FileAdapter implements FileListener {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUpload(IUpload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUploaded(IUpload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUploadCancelled(IUpload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUploadException(IUpload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDownload(IDownload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDownloaded(IDownload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDownloadCancelled(IDownload task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDownloadException(IDownload task) {
	}

}
