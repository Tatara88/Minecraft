package org.minecraftnauja.klaxon;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

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
		if (packet.channel.equals(Klaxon.MOD_ID)) {
			try {
				ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
				DataInputStream dis = new DataInputStream(bis);
				int id = dis.readInt();
				String name = dis.readUTF();
				switch (PacketType.values()[id]) {
				case UploadedKlaxon:
					try {
						FMLLog.log(
								Klaxon.MOD_ID,
								Level.INFO,
								"Player uploaded a klaxon %s, notifies all players",
								name);
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						DataOutputStream dos = new DataOutputStream(bos);
						dos.writeInt(PacketType.UpdateKlaxon.ordinal());
						dos.writeUTF(name);
						packet.data = bos.toByteArray();
						packet.length = bos.size();
						PacketDispatcher.sendPacketToAllPlayers(packet);
					} catch (IOException e) {
						FMLLog.log(Klaxon.MOD_ID, Level.SEVERE, e,
								"Could not send the packet to all players");
					}
					break;
				case UpdateKlaxon:
					Klaxon.getKlaxon(name);
					break;
				case PlayKlaxon:
					Klaxon.getKlaxonIfNeeded(name);
					break;
				}
			} catch (IOException e) {
				FMLLog.log(Klaxon.MOD_ID, Level.SEVERE, e,
						"Could not read the packet");
			}
		}
	}

}
