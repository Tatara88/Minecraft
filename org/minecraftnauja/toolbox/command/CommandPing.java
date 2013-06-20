package org.minecraftnauja.toolbox.command;

import java.util.logging.Level;

import org.minecraftnauja.toolbox.Toolbox;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
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
		try {
			PacketCommandPing p = null;
			if (astring.length == 1) {
				p = new PacketCommandPing(astring[0]);
			} else if (astring.length == 2) {
				p = new PacketCommandPing(astring[0], parseInt(icommandsender,
						astring[1]));
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