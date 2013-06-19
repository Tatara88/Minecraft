package org.minecraftnauja.p2p.provider.file.event;

import java.util.EventListener;

import org.minecraftnauja.p2p.provider.file.task.IFileDownload;
import org.minecraftnauja.p2p.provider.file.task.IFileUpload;

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
	public void onUpload(IFileUpload task);

	/**
	 * Called when a file upload has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onUploaded(IFileUpload task);

	/**
	 * Called when a file upload has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onUploadCancelled(IFileUpload task);

	/**
	 * Called when a file upload caused an exception.
	 * 
	 * @param task
	 */
	public void onUploadException(IFileUpload task);

	/**
	 * Called when a file download started.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onDownload(IFileDownload task);

	/**
	 * Called when a file download has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onDownloaded(IFileDownload task);

	/**
	 * Called when a file download has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	public void onDownloadCancelled(IFileDownload task);

	/**
	 * Called when a file download caused an exception.
	 * 
	 * @param task
	 */
	public void onDownloadException(IFileDownload task);

}
