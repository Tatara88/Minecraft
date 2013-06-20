package org.minecraftnauja.toolbox.command;

import java.util.logging.Level;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import org.minecraftnauja.toolbox.Toolbox;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

/**
 * Address command.
 */
public class CommandAddress extends CommandBase {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandName() {
		return "address";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCommandUsage(ICommandSender par1ICommandSender) {
		return "/" + getCommandName() + " <player>";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processCommand(ICommandSender icommandsender, String[] astring) {
		try {
			PacketCommandAddress p = null;
			if (astring.length == 1) {
				p = new PacketCommandAddress(astring[0]);
			}
			if (p != null) {
				p.write();
				PacketDispatcher.sendPacketToPlayer(p,
						(Player) getCommandSenderAsPlayer(icommandsender));
			}
		} catch (Exception e) {
			FMLLog.log(Toolbox.MOD_ID, Level.SEVERE, e, "");
		}
	}

}