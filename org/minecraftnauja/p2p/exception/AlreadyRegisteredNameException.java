package org.minecraftnauja.p2p.exception;

import org.minecraftnauja.p2p.provider.IProvider;

/**
 * Exception thrown when a provider name is already registered.
 */
public final class AlreadyRegisteredNameException extends RuntimeException {

	/**
	 * Generates the exception message.
	 * 
	 * @param register
	 *            the provider to register.
	 * @param name
	 *            the name.
	 * @param registered
	 *            the already registered provider.
	 * @return the message.
	 */
	private static String message(IProvider register, String name,
			IProvider registered) {
		return "Trying to register the provider " + register + " with name "
				+ name + " already taken by the provider " + registered;
	}

	/**
	 * The provider to register.
	 */
	private final IProvider register;

	/**
	 * The name.
	 */
	private final String name;

	/**
	 * The already registered provider.
	 */
	private final IProvider registered;

	/**
	 * Initializing constructor.
	 * 
	 * @param register
	 *            the provider to register.
	 * @param name
	 *            the name.
	 * @param registered
	 *            the already registered provider.
	 */
	public AlreadyRegisteredNameException(IProvider register, String name,
			IProvider registered) {
		super(message(register, name, registered));
		this.register = register;
		this.name = name;
		this.registered = registered;
	}

	/**
	 * Gets the provider to register.
	 * 
	 * @return the provider to register.
	 */
	public IProvider getRegister() {
		return register;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the already registered provider.
	 * 
	 * @return the already registered provider.
	 */
	public IProvider getRegistered() {
		return registered;
	}

}
