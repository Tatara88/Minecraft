package org.minecraftnauja.tomp2p;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.logging.Level;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * Handle packets.
 */
public class PacketHandler implements IPacketHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		try {
			if (packet.channel.equals(TomP2P.MOD_ID)) {
				ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
				DataInputStream dis = new DataInputStream(bis);
				PacketType p = PacketType.values()[dis.readInt()];
				p.handle(manager, player, dis);
			}
		} catch (Exception e) {
			FMLLog.log(TomP2P.MOD_ID, Level.SEVERE, e,
					"Could not handle a packet");
		}
	}

}
