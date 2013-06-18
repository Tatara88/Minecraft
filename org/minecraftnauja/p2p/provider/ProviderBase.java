package org.minecraftnauja.p2p.provider;

import javax.swing.event.EventListenerList;

import org.minecraftnauja.p2p.provider.event.IProviderEvent;
import org.minecraftnauja.p2p.provider.event.IProviderListener;
import org.minecraftnauja.p2p.provider.event.ProviderEvent;

/**
 * Base for providers.
 */
public abstract class ProviderBase implements IProvider {

	/**
	 * Listeners.
	 */
	private EventListenerList listeners;

	/**
	 * Default constructor.
	 */
	public ProviderBase() {
		listeners = new EventListenerList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addListener(IProviderListener listener) {
		listeners.add(IProviderListener.class, listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeListener(IProviderListener listener) {
		listeners.remove(IProviderListener.class, listener);
	}

	/**
	 * Notifies listeners that the provider is being started.
	 */
	protected void fireStarting() {
		IProviderEvent e = null;
		for (IProviderListener l : listeners
				.getListeners(IProviderListener.class)) {
			if (e == null)
				e = new ProviderEvent(this);
			l.onStarting(e);
		}
	}

	/**
	 * Notifies listeners that the provider is being started.
	 * 
	 * @param event
	 *            the event.
	 */
	protected void fireStarting(IProviderEvent event) {
		for (IProviderListener l : listeners
				.getListeners(IProviderListener.class)) {
			l.onStarting(event);
		}
	}

	/**
	 * Notifies listeners that the provider started.
	 */
	protected void fireStarted() {
		IProviderEvent e = null;
		for (IProviderListener l : listeners
				.getListeners(IProviderListener.class)) {
			if (e == null)
				e = new ProviderEvent(this);
			l.onStarted(e);
		}
	}

	/**
	 * Notifies listeners that the provider started.
	 * 
	 * @param event
	 *            the event.
	 */
	protected void fireStarted(IProviderEvent event) {
		for (IProviderListener l : listeners
				.getListeners(IProviderListener.class)) {
			l.onStarted(event);
		}
	}

	/**
	 * Notifies listeners that the provider is being stopped.
	 */
	protected void fireStopping() {
		IProviderEvent e = null;
		for (IProviderListener l : listeners
				.getListeners(IProviderListener.class)) {
			if (e == null)
				e = new ProviderEvent(this);
			l.onStopping(e);
		}
	}

	/**
	 * Notifies listeners that the provider is being stopped.
	 * 
	 * @param event
	 *            the event.
	 */
	protected void fireStopping(IProviderEvent event) {
		for (IProviderListener l : listeners
				.getListeners(IProviderListener.class)) {
			l.onStopping(event);
		}
	}

	/**
	 * Notifies listeners that the provider stopped.
	 */
	protected void fireStopped() {
		IProviderEvent e = null;
		for (IProviderListener l : listeners
				.getListeners(IProviderListener.class)) {
			if (e == null)
				e = new ProviderEvent(this);
			l.onStopped(e);
		}
	}

	/**
	 * Notifies listeners that the provider stopped.
	 * 
	 * @param event
	 *            the event.
	 */
	protected void fireStopped(IProviderEvent event) {
		for (IProviderListener l : listeners
				.getListeners(IProviderListener.class)) {
			l.onStopped(event);
		}
	}

}
