package org.minecraftnauja.p2p.provider.event;

import java.util.EventObject;

import org.minecraftnauja.p2p.provider.IProvider;
import org.minecraftnauja.p2p.provider.file.IFileProvider;
import org.minecraftnauja.p2p.provider.player.IPlayerProvider;

/**
 * Base for {@code IProvider} events.
 */
public class ProviderEvent extends EventObject implements IProviderEvent {

	/**
	 * Initializing constructor.
	 * 
	 * @param source
	 *            the source.
	 */
	public ProviderEvent(IProvider source) {
		super(source);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IProvider getSource() {
		return (IProvider) super.getSource();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPlayerProvider getPlayerProvider() {
		return getSource().getPlayerProvider();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFileProvider getFileProvider() {
		return getSource().getFileProvider();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "ProviderEvent [source=" + source + "]";
	}

}
