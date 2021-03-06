package org.minecraftnauja.toolbox.command;

import java.net.InetAddress;
import java.util.logging.Level;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import org.minecraftnauja.toolbox.Toolbox;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

/**
 * Player command.
 */
public class CommandPlayers extends CommandBase {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandName() {
		return "players";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandUsage(ICommandSender par1ICommandSender) {
		return "/" + getCommandName() + " <address>";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		try {
			PacketCommandPlayers p = null;
			if (astring.length == 1) {
				p = new PacketCommandPlayers(InetAddress.getByName(astring[0]));
			}
			if (p != null) {
				PacketDispatcher.sendPacketToPlayer(p.write(),
						(Player) getCommandSenderAsPlayer(icommandsender));
			}
		} catch (Exception e) {
			FMLLog.log(Toolbox.MOD_ID, Level.SEVERE, e, "");
		}
	}

}