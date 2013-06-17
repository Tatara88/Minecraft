package org.minecraftnauja.p2p.provider.file;

import java.io.File;

import org.minecraftnauja.p2p.provider.file.event.IFileCallback;
import org.minecraftnauja.p2p.provider.file.event.IFileListener;

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
	public void addListener(IFileListener listener);

	/**
	 * Removes given listener.
	 * 
	 * @param listener
	 *            the listener to remove.
	 */
	public void removeListener(IFileListener listener);

	/**
	 * Uploads given file.
	 * 
	 * @param channel
	 *            channel to use.
	 * @param file
	 *            file to upload.
	 * @param name
	 *            name of uploaded file.
	 */
	public void upload(String channel, File file, String name);

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
	 *            callback function.
	 */
	public void upload(String channel, File file, String name,
			IFileCallback callback);

	/**
	 * Downloads file with given name.
	 * 
	 * @param channel
	 *            channel to use.
	 * @param name
	 *            name of file to download.
	 * @param file
	 *            file to create.
	 */
	public void download(String channel, String name, File file);

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
	 *            callback function.
	 */
	public void download(String channel, String name, File file,
			IFileCallback callback);

}
