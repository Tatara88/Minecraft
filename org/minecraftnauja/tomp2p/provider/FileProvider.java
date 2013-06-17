package org.minecraftnauja.tomp2p.provider;

import java.io.File;
import java.io.IOException;

import net.tomp2p.futures.BaseFutureListener;
import net.tomp2p.futures.FutureDHT;

import org.minecraftnauja.p2p.provider.file.FileProviderBase;
import org.minecraftnauja.p2p.provider.file.event.FileProviderEvent;
import org.minecraftnauja.p2p.provider.file.event.IFileCallback;
import org.minecraftnauja.tomp2p.callback.GetCallback;
import org.minecraftnauja.tomp2p.callback.PutCallback;
import org.minecraftnauja.tomp2p.event.GetEvent;
import org.minecraftnauja.tomp2p.event.PutEvent;
import org.minecraftnauja.tomp2p.peer.IPeer;
import org.minecraftnauja.tomp2p.peer.PeerBase;

import com.google.common.io.Files;

/**
 * Implementation of the file provider.
 */
public class FileProvider extends FileProviderBase {

	/**
	 * Peer instance.
	 */
	private final IPeer peer;

	/**
	 * Initializing constructor.
	 * 
	 * @param peer
	 *            peer instance.
	 */
	public FileProvider(IPeer peer) {
		super();
		this.peer = peer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void upload(final String channel, final File file,
			final String name, final IFileCallback callback) {
		try {
			// Puts the file.
			peer.put(channel, name, Files.toByteArray(file)).addListener(
					new BaseFutureListener<FutureDHT>() {
						public void operationComplete(FutureDHT future)
								throws Exception {
							// File uploaded.
							if (callback != null) {
								FileProviderEvent e = new FileProviderEvent(
										FileProvider.this, channel, file, name);
								callback.onFileUploaded(e);
								fireFileUploaded(e);
							} else {
								fireFileUploaded(channel, file, name);
							}
						}

						public void exceptionCaught(Throwable t)
								throws Exception {
							// Could not upload the file.
							if (callback != null) {
								FileProviderEvent e = new FileProviderEvent(
										FileProvider.this, channel, file, name,
										t);
								callback.onFileUploadException(e);
								fireFileUploadException(e);
							} else {
								fireFileUploadException(channel, file, name, t);
							}
						}
					});
		} catch (Exception e) {
			// Could not read the file or put it.
			if (callback != null) {
				FileProviderEvent event = new FileProviderEvent(this, channel,
						file, name, e);
				callback.onFileUploadException(event);
				fireFileUploadException(event);
			} else {
				fireFileUploadException(channel, file, name, e);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void download(final String channel, final String name,
			final File file, final IFileCallback callback) {
		// Downloads the file.
		peer.get(channel, name).addListener(
				new BaseFutureListener<FutureDHT>() {
					public void operationComplete(FutureDHT future)
							throws Exception {
						// File downloaded.
						try {
							// Makes the directory.
							File f = file.getParentFile();
							if (f != null && f.isDirectory() && !f.exists()) {
								f.mkdirs();
							}
							// Writes it to disk.
							Files.write((byte[]) future.getData().getObject(),
									file);
							// Notifies listeners.
							if (callback != null) {
								FileProviderEvent e = new FileProviderEvent(
										FileProvider.this, channel, name, file);
								callback.onFileDownloaded(e);
								fireFileDownloaded(e);
							} else {
								fireFileDownloaded(channel, name, file);
							}
						} catch (Exception e) {
							// Could not write.
							if (callback != null) {
								FileProviderEvent event = new FileProviderEvent(
										FileProvider.this, channel, name, file);
								callback.onFileDownloadException(event);
								fireFileDownloadException(event);
							} else {
								fireFileDownloadException(channel, name, file,
										e);
							}
						}
					}

					public void exceptionCaught(Throwable t) throws Exception {
						// File not downloaded.
						if (callback != null) {
							FileProviderEvent e = new FileProviderEvent(
									FileProvider.this, channel, name, file, t);
							callback.onFileDownloadException(e);
							fireFileDownloadException(e);
						} else {
							fireFileDownloadException(channel, name, file, t);
						}
					}
				});
	}

}
