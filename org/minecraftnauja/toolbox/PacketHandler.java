package org.minecraftnauja.toolbox;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import org.minecraftnauja.p2p.P2P;
import org.minecraftnauja.p2p.provider.packet.IPacketProvider;
import org.minecraftnauja.toolbox.command.Command;
import org.minecraftnauja.toolbox.command.PacketCommandBase;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * Handler for packets.
 */
public class PacketHandler implements IPacketHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		if (packet.channel.equals(Toolbox.MOD_ID)) {
			try {
				// Creates the packet.
				ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
				DataInputStream dis = new DataInputStream(bis);
				Command c = Command.values()[dis.readInt()];
				PacketCommandBase p = c.newInstance();
				p.read(dis);
				p.execute();
			} catch (Exception e) {
				FMLLog.log(Toolbox.MOD_ID, Level.SEVERE, e,
						"Could not read the packet");
			}
		}
	}
}
