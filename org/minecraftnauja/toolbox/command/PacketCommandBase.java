package org.minecraftnauja.toolbox.command;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.network.packet.Packet250CustomPayload;

import org.minecraftnauja.p2p.provider.event.ICallback;
import org.minecraftnauja.p2p.task.ITask;
import org.minecraftnauja.toolbox.Toolbox;

/**
 * Base for packets.
 */
public abstract class PacketCommandBase<T extends ITask> extends
		Packet250CustomPayload implements ICallback<T> {

	/**
	 * Gets the command.
	 * 
	 * @return the command.
	 */
	public abstract Command getCommand();

	/**
	 * Writes the packet.
	 * 
	 * @throws IOException
	 *             error with IO.
	 */
	public void write() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		dos.writeInt(getCommand().ordinal());
		write(dos);
		channel = Toolbox.MOD_ID;
		data = bos.toByteArray();
		length = bos.size();
	}

	/**
	 * Writes the packet.
	 * 
	 * @param dos
	 *            output stream.
	 * @throws IOException
	 *             error with IO.
	 */

	public abstract void write(DataOutputStream dos) throws IOException;

	/**
	 * Reads the packet.
	 * 
	 * @param dis
	 *            input stream.
	 * @throws IOException
	 *             error with IO.
	 */
	public abstract void read(DataInputStream dis) throws IOException;

	/**
	 * Executes the command.
	 */
	public abstract void execute();

	public void chat(String msg) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(msg);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onException(T task) {
		chat("[Failed]");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onStarted(T task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCompleted(T task) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCancelled(T task) {
	}

}
