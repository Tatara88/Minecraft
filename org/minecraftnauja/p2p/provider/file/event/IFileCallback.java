package org.minecraftnauja.p2p.provider.file.event;


/**
 * Interface for {@code IFileProvider} callbacks.
 */
public interface IFileCallback {

	/**
	 * Called when a file has been uploaded.
	 * 
	 * @param event
	 *            the event.
	 */
	public void onFileUploaded(IFileProviderEvent event);

	/**
	 * Called when uploading a file caused an exception.
	 * 
	 * @param event
	 *            the event.
	 */
	public void onFileUploadException(IFileProviderEvent event);

	/**
	 * Called when a file has been downloaded.
	 * 
	 * @param event
	 *            the event.
	 */
	public void onFileDownloaded(IFileProviderEvent event);

	/**
	 * Called when downloading a file caused an exception.
	 * 
	 * @param event
	 *            the event.
	 */
	public void onFileDownloadException(IFileProviderEvent event);

}
