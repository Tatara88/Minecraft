package org.minecraftnauja.p2p.provider.file;

import java.io.File;

import org.minecraftnauja.p2p.provider.event.ICallback;
import org.minecraftnauja.p2p.provider.file.event.FileListener;
import org.minecraftnauja.p2p.provider.file.task.IFileDownload;
import org.minecraftnauja.p2p.provider.file.task.IFileUpload;

/**
 * Interface for files providers.
 */
public interface IFileProvider {

	/**
	 * Adds given listener.
	 * 
	 * @param listener
	 *            the listener to add.
	 */
	public void addListener(FileListener listener);

	/**
	 * Removes given listener.
	 * 
	 * @param listener
	 *            the listener to remove.
	 */
	public void removeListener(FileListener listener);

	/**
	 * Uploads given file.
	 * 
	 * @param channel
	 *            channel to use.
	 * @param file
	 *            file to upload.
	 * @param name
	 *            name of uploaded file.
	 * @return the task.
	 */
	public IFileUpload upload(String channel, File file, String name);

	/**
	 * Uploads given file.
	 * 
	 * @param channel
	 *            channel to use.
	 * @param file
	 *            file to upload.
	 * @param name
	 *            name of uploaded file.
	 * @param callback
	 *            a callback.
	 * @return the task.
	 */
	public IFileUpload upload(String channel, File file, String name,
			ICallback<IFileUpload> callback);

	/**
	 * Downloads file with given name.
	 * 
	 * @param channel
	 *            channel to use.
	 * @param name
	 *            name of file to download.
	 * @param file
	 *            file to create.
	 * @return the task.
	 */
	public IFileDownload download(String channel, String name, File file);

	/**
	 * Downloads file with given name.
	 * 
	 * @param channel
	 *            channel to use.
	 * @param name
	 *            name of file to download.
	 * @param file
	 *            file to create.
	 * @param callback
	 *            a callback.
	 * @return the task.
	 */
	public IFileDownload download(String channel, String name, File file,
			ICallback<IFileDownload> callback);

}
