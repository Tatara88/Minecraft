package org.minecraftnauja.toolbox.command;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;

import org.minecraftnauja.p2p.P2P;
import org.minecraftnauja.p2p.provider.player.IPlayerProvider;
import org.minecraftnauja.p2p.provider.player.task.IGetAddress;
import org.minecraftnauja.p2p.provider.player.task.IGetPlayer;
import org.minecraftnauja.toolbox.Toolbox;

/**
 * The player command.
 */
public class PacketCommandPlayer extends PacketCommandBase<IGetPlayer> {

	/**
	 * The address.
	 */
	private InetAddress address;

	/**
	 * Default constructor.
	 */
	public PacketCommandPlayer() {
		super();
	}

	/**
	 * Initializing constructor.
	 * 
	 * @param address
	 *            the address.
	 */
	public PacketCommandPlayer(InetAddress address) {
		super();
		this.address = address;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Command getCommand() {
		return Command.Player;
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
		chat("[Getting name of player at address " + address + "]");
		IPlayerProvider pp = P2P.get(P2P.CLIENT_PROVIDER).getPlayerProvider();
		pp.getPlayer(Toolbox.MOD_ID, address, this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCompleted(IGetPlayer task) {
		chat("at address " + task.getAddress() + " there is "
				+ task.getPlayer());
	}

}
