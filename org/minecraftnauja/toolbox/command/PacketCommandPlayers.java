package org.minecraftnauja.toolbox.command;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;

import org.minecraftnauja.p2p.P2P;
import org.minecraftnauja.p2p.provider.player.IPlayerProvider;
import org.minecraftnauja.p2p.provider.player.task.IGetAddress;
import org.minecraftnauja.p2p.provider.player.task.IGetPlayers;
import org.minecraftnauja.toolbox.Toolbox;

/**
 * The players command.
 */
public class PacketCommandPlayers extends PacketCommandBase<IGetPlayers> {

	/**
	 * The address.
	 */
	private InetAddress address;

	/**
	 * Default constructor.
	 */
	public PacketCommandPlayers() {
		super();
	}

	/**
	 * Initializing constructor.
	 * 
	 * @param address
	 *            the address.
	 */
	public PacketCommandPlayers(InetAddress address) {
		super();
		this.address = address;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Command getCommand() {
		return Command.Players;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(DataOutputStream dos) throws IOException {
		dos.writeUTF(address.getHostAddress());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void read(DataInputStream dis) throws IOException {
		address = InetAddress.getByName(dis.readUTF());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		chat("[Getting names of players at address " + address + "]");
		IPlayerProvider pp = P2P.get(P2P.CLIENT_PROVIDER).getPlayerProvider();
		pp.getPlayers(Toolbox.MOD_ID, address, this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCompleted(IGetPlayers task) {
		chat("at address " + task.getAddress() + " there is "
				+ Arrays.toString(task.getPlayers()));
	}

}
