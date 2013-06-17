package org.minecraftnauja.p2p.provider.file;

import java.io.File;

import javax.swing.event.EventListenerList;

import org.minecraftnauja.p2p.provider.file.event.FileProviderEvent;
import org.minecraftnauja.p2p.provider.file.event.IFileListener;
import org.minecraftnauja.p2p.provider.file.event.IFileProviderEvent;

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
	public void addListener(IFileListener listener) {
		listeners.add(IFileListener.class, listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeListener(IFileListener listener) {
		listeners.remove(IFileListener.class, listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void upload(String channel, File file, String name) {
		upload(channel, file, name, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void download(String channel, String name, File file) {
		download(channel, name, file, null);
	}

	/**
	 * Notifies listeners that a file has been uploaded.
	 * 
	 * @param channel
	 *            the channel.
	 * @param file
	 *            the file.
	 * @param name
	 *            the name.
	 */
	protected void fireFileUploaded(String channel, File file, String name) {
		IFileProviderEvent e = null;
		for (IFileListener l : listeners.getListeners(IFileListener.class)) {
			if (e == null)
				e = new FileProviderEvent(this, channel, name, file);
			l.onFileUploaded(e);
		}
	}

	/**
	 * Notifies listeners that a file has been uploaded.
	 * 
	 * @param event
	 *            the event.
	 */
	protected void fireFileUploaded(IFileProviderEvent event) {
		for (IFileListener l : listeners.getListeners(IFileListener.class)) {
			l.onFileUploaded(event);
		}
	}

	/**
	 * Notifies listeners that uploading a file caused an exception.
	 * 
	 * @param channel
	 *            the channel.
	 * @param file
	 *            the file.
	 * @param name
	 *            the name.
	 * @param error
	 *            the exception.
	 */
	protected void fireFileUploadException(String channel, File file,
			String name, Throwable error) {
		IFileProviderEvent e = null;
		for (IFileListener l : listeners.getListeners(IFileListener.class)) {
			if (e == null)
				e = new FileProviderEvent(this, channel, name, file, error);
			l.onFileUploadException(e);
		}
	}

	/**
	 * Notifies listeners that uploading a file caused an exception.
	 * 
	 * @param event
	 *            the event.
	 */
	protected void fireFileUploadException(IFileProviderEvent event) {
		for (IFileListener l : listeners.getListeners(IFileListener.class)) {
			l.onFileUploadException(event);
		}
	}

	/**
	 * Notifies listeners that a file has been downloaded.
	 * 
	 * @param channel
	 *            the channel.
	 * @param name
	 *            the name.
	 * @param file
	 *            the file.
	 */
	protected void fireFileDownloaded(String channel, String name, File file) {
		IFileProviderEvent e = null;
		for (IFileListener l : listeners.getListeners(IFileListener.class)) {
			if (e == null)
				e = new FileProviderEvent(this, channel, name, file);
			l.onFileDownloaded(e);
		}
	}

	/**
	 * Notifies listeners that a file has been downloaded.
	 * 
	 * @param event
	 *            the event.
	 */
	protected void fireFileDownloaded(IFileProviderEvent event) {
		for (IFileListener l : listeners.getListeners(IFileListener.class)) {
			l.onFileDownloaded(event);
		}
	}

	/**
	 * Notifies listeners that downloading a file caused an exception.
	 * 
	 * @param channel
	 *            the channel.
	 * @param name
	 *            the name.
	 * @param file
	 *            the file.
	 * @param error
	 *            the exception.
	 */
	protected void fireFileDownloadException(String channel, String name,
			File file, Throwable error) {
		IFileProviderEvent e = null;
		for (IFileListener l : listeners.getListeners(IFileListener.class)) {
			if (e == null)
				e = new FileProviderEvent(this, channel, name, file, error);
			l.onFileDownloadException(e);
		}
	}

	/**
	 * Notifies listeners that downloading a file caused an exception.
	 * 
	 * @param event
	 *            the event.
	 */
	protected void fireFileDownloadException(IFileProviderEvent event) {
		for (IFileListener l : listeners.getListeners(IFileListener.class)) {
			l.onFileDownloadException(event);
		}
	}

}
