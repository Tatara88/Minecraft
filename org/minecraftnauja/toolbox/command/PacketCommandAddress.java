package org.minecraftnauja.toolbox.command;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.minecraftnauja.p2p.P2P;
import org.minecraftnauja.p2p.provider.player.IPlayerProvider;
import org.minecraftnauja.p2p.provider.player.task.IGetAddress;
import org.minecraftnauja.toolbox.Toolbox;

/**
 * The address command.
 */
public class PacketCommandAddress extends PacketCommandBase<IGetAddress> {

	/**
	 * The player.
	 */
	private String player;

	/**
	 * Default constructor.
	 */
	public PacketCommandAddress() {
		super();
	}

	/**
	 * Initializing constructor.
	 * 
	 * @param player
	 *            the player.
	 */
	public PacketCommandAddress(String player) {
		super();
		this.player = player;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Command getCommand() {
		return Command.Address;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(DataOutputStream dos) throws IOException {
		dos.writeUTF(player);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void read(DataInputStream dis) throws IOException {
		player = dis.readUTF();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		chat("[Getting " + player + "'s address]");
		IPlayerProvider pp = P2P.get(P2P.CLIENT_PROVIDER).getPlayerProvider();
		pp.getAddress(Toolbox.MOD_ID, player, this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCompleted(IGetAddress task) {
		String address = task.getAddress().toString();
		if (address.startsWith("/")) {
			address = address.substring(1);
		}
		chat(player + "'s address is " + task.getAddress());
	}

}
