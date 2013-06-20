package org.minecraftnauja.toolbox.command;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.minecraftnauja.p2p.P2P;
import org.minecraftnauja.p2p.provider.packet.IPacketProvider;
import org.minecraftnauja.p2p.provider.packet.task.ISendTo;
import org.minecraftnauja.toolbox.Toolbox;
import org.minecraftnauja.toolbox.packet.PacketPing;

/**
 * The ping command.
 */
public class PacketCommandPing extends PacketCommandBase<ISendTo> {

	/**
	 * The player.
	 */
	private String player;

	/**
	 * How many times.
	 */
	private int time;

	/**
	 * Default constructor.
	 */
	public PacketCommandPing() {
		super();
	}

	/**
	 * Initializing constructor.
	 * 
	 * @param player
	 *            the player.
	 */
	public PacketCommandPing(String player) {
		this(player, 1);
	}

	/**
	 * Initializing constructor.
	 * 
	 * @param player
	 *            the player.
	 * @param time
	 *            how many times.
	 */
	public PacketCommandPing(String player, int time) {
		super();
		this.player = player;
		this.time = Math.max(time, 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Command getCommand() {
		return Command.Ping;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(DataOutputStream dos) throws IOException {
		dos.writeUTF(player);
		dos.writeInt(time);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void read(DataInputStream dis) throws IOException {
		player = dis.readUTF();
		time = dis.readInt();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		chat("[Sending ping to " + player + "]");
		IPacketProvider pp = P2P.get(P2P.CLIENT_PROVIDER).getPacketProvider();
		pp.sendTo(Toolbox.MOD_ID, new PacketPing(time), player, this);
	}

}
