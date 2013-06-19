package org.minecraftnauja.p2p.provider.event;

import org.minecraftnauja.p2p.provider.IProvider;
import org.minecraftnauja.p2p.provider.file.IFileProvider;
import org.minecraftnauja.p2p.provider.player.IPlayerProvider;

/**
 * Interface for {@code IProvider} events.
 */
public interface IProviderEvent {

	/**
	 * Gets the {@code IProvider} that caused this event.
	 * 
	 * @return the source.
	 */
	public IProvider getSource();

	/**
	 * Gets the {@code IPlayerProvider} associated to the source.
	 * 
	 * @return the {@code IPlayerProvider}.
	 */
	public IPlayerProvider getPlayerProvider();

	/**
	 * Gets the {@code IFileProvider} associated to the source.
	 * 
	 * @return the {@code IFileProvider}.
	 */
	public IFileProvider getFileProvider();

}
