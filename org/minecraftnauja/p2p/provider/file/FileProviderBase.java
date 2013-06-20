package org.minecraftnauja.p2p.provider.file;

import java.io.File;

import javax.swing.event.EventListenerList;

import org.minecraftnauja.p2p.provider.file.event.FileListener;
import org.minecraftnauja.p2p.provider.file.task.IDownload;
import org.minecraftnauja.p2p.provider.file.task.IUpload;

/**
 * Base for files providers.
 */
public abstract class FileProviderBase implements IFileProvider {

	/**
	 * Listeners.
	 */
	private final EventListenerList listeners;

	/**
	 * Default constructor.
	 */
	public FileProviderBase() {
		listeners = new EventListenerList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addListener(FileListener listener) {
		listeners.add(FileListener.class, listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeListener(FileListener listener) {
		listeners.remove(FileListener.class, listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUpload upload(String channel, File file, String name) {
		return upload(channel, file, name, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IDownload download(String channel, String name, File file) {
		return download(channel, name, file, null);
	}

	/**
	 * Notifies listeners that a file upload started.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireUpload(IUpload task) {
		for (FileListener l : listeners.getListeners(FileListener.class)) {
			l.onUpload(task);
		}
	}

	/**
	 * Notifies listeners that a file upload has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireUploaded(IUpload task) {
		for (FileListener l : listeners.getListeners(FileListener.class)) {
			l.onUploaded(task);
		}
	}

	/**
	 * Notifies listeners that a file upload has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireUploadCancelled(IUpload task) {
		for (FileListener l : listeners.getListeners(FileListener.class)) {
			l.onUploadCancelled(task);
		}
	}

	/**
	 * Notifies listeners that a file upload caused an exception.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireUploadException(IUpload task) {
		for (FileListener l : listeners.getListeners(FileListener.class)) {
			l.onUploadException(task);
		}
	}

	/**
	 * Notifies listeners that a file download started.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireDownload(IDownload task) {
		for (FileListener l : listeners.getListeners(FileListener.class)) {
			l.onDownload(task);
		}
	}

	/**
	 * Notifies listeners that a file download has been completed.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireDownloaded(IDownload task) {
		for (FileListener l : listeners.getListeners(FileListener.class)) {
			l.onDownloaded(task);
		}
	}

	/**
	 * Notifies listeners that a file download has been cancelled.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireDownloadCancelled(IDownload task) {
		for (FileListener l : listeners.getListeners(FileListener.class)) {
			l.onDownloadCancelled(task);
		}
	}

	/**
	 * Notifies listeners that a file download caused an exception.
	 * 
	 * @param task
	 *            the task.
	 */
	protected void fireDownloadException(IDownload task) {
		for (FileListener l : listeners.getListeners(FileListener.class)) {
			l.onDownloadException(task);
		}
	}

}
