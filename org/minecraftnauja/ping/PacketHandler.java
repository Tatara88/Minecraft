package org.minecraftnauja.ping;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

import org.minecraftnauja.p2p.P2P;
import org.minecraftnauja.p2p.provider.packet.IPacketProvider;

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
		if (packet.channel.equals(Ping.MOD_ID)) {
			try {
				ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
				DataInputStream dis = new DataInputStream(bis);
				int n = dis.readInt();
				String name = dis.readUTF();
				int time = n == 1 ? 1 : dis.readInt();
				if (time >= 1 && Ping.running) {
					Minecraft.getMinecraft().thePlayer
							.addChatMessage("[Sending ping to " + player + "]");
					IPacketProvider p = P2P.get(P2P.CLIENT_PROVIDER)
							.getPacketProvider();
					p.sendTo(Ping.MOD_ID, new PacketPing(time), name);
				}
			} catch (IOException e) {
				FMLLog.log(Ping.MOD_ID, Level.SEVERE, e,
						"Could not read the packet");
			}
		}
	}
}
