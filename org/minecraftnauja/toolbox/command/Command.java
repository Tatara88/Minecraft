package org.minecraftnauja.toolbox.command;

/**
 * All the commands.
 */
public enum Command {

	Ping(PacketCommandPing.class),
	Address(PacketCommandAddress.class),
	Player(PacketCommandPlayer.class);

	/**
	 * Class of the packet.
	 */
	private final Class<? extends PacketCommandBase> clazz;

	/**
	 * Initializing constructor.
	 * 
	 * @param clazz
	 *            class of the packet.
	 */
	private Command(Class<? extends PacketCommandBase> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Creates a new instance of the packet.
	 * 
	 * @return the instance.
	 * @throws IllegalAccessException
	 *             illegal access.
	 * @throws InstantiationException
	 *             cannot instantiate.
	 */
	public PacketCommandBase newInstance() throws InstantiationException,
			IllegalAccessException {
		return clazz.newInstance();
	}

}
