package org.minecraftnauja.p2p.provider.file.event;

import java.util.EventListener;

import org.minecraftnauja.p2p.provider.file.task.IDownload;
import org.minecraftnauja.p2p.provider.file.task.IUpload;

/**
 * Interface for {@code IFileProvider} listeners.
 */
public interface FileListener extends EventListener {

	/**
	 * Called when a file upload started.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onUpload(IUpload task);

	/**
	 * Called when a file upload has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onUploaded(IUpload task);

	/**
	 * Called when a file upload has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onUploadCancelled(IUpload task);

	/**
	 * Called when a file upload caused an exception.
	 * 
	 * @param task
	 */
	public void onUploadException(IUpload task);

	/**
	 * Called when a file download started.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onDownload(IDownload task);

	/**
	 * Called when a file download has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onDownloaded(IDownload task);

	/**
	 * Called when a file download has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onDownloadCancelled(IDownload task);

	/**
	 * Called when a file download caused an exception.
	 * 
	 * @param task
	 */
	public void onDownloadException(IDownload task);

}
