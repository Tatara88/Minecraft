package org.minecraftnauja.ping;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.logging.Level;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

/**
 * Ping command.
 */
public class CommandPing extends CommandBase {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandName() {
		return "ping";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandUsage(ICommandSender par1ICommandSender) {
		return "/" + getCommandName() + " <player> <time>";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		if (astring.length >= 1 && astring.length <= 2) {
			try {
				// When a player does the /ping command, send him this packet.
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				DataOutputStream dos = new DataOutputStream(bos);
				dos.writeInt(astring.length);
				dos.writeUTF(astring[0]);
				if (astring.length >= 2) {
					dos.writeInt(parseIntWithMin(icommandsender, astring[1], 0));
				}
				Packet250CustomPayload packet = new Packet250CustomPayload();
				packet.channel = Ping.MOD_ID;
				packet.data = bos.toByteArray();
				packet.length = bos.size();
				PacketDispatcher.sendPacketToPlayer(packet,
						(Player) getCommandSenderAsPlayer(icommandsender));
			} catch (Exception e) {
				FMLLog.log(Ping.MOD_ID, Level.SEVERE, e,
						"Could not send the packet");
			}
		}
	}

}