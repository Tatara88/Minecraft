package org.minecraftnauja.p2p.provider.file;

import java.io.File;

import org.minecraftnauja.p2p.provider.ISubProvider;
import org.minecraftnauja.p2p.provider.event.ICallback;
import org.minecraftnauja.p2p.provider.file.event.FileListener;
import org.minecraftnauja.p2p.provider.file.task.IDownload;
import org.minecraftnauja.p2p.provider.file.task.IUpload;

/**
 * Interface for files providers.
 */
public interface IFileProvider extends ISubProvider<FileListener> {

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
	public IUpload upload(String channel, File file, String name);

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
	public IUpload upload(String channel, File file, String name,
			ICallback<IUpload> callback);

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
	public IDownload download(String channel, String name, File file);

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
	public IDownload download(String channel, String name, File file,
			ICallback<IDownload> callback);

}
