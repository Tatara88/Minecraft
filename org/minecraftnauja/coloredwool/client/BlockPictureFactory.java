package org.minecraftnauja.coloredwool.client;

import java.util.Random;

import org.minecraftnauja.coloredwool.ColoredWool;

import net.minecraft.client.Minecraft;

public class BlockPictureFactory extends rw {

	public static void sendModifyImageName(TileEntityPictureFactory entity,
			String name) {
		Packet230ModLoader packet = new Packet230ModLoader();
		packet.packetType = 21;
		packet.dataInt = new int[] { entity.e, entity.f, entity.g };
		packet.dataString = new String[] { name };
		ModLoaderMp.SendPacket(ColoredWool.instance, packet);
	}

	public static void handleOpenGuiImage(Packet230ModLoader packet) {
		int[] pos = packet.dataInt;
		if (pos == null)
			return;
		if (pos.length != 3) {
			return;
		}
		ow entity = ModLoader.getMinecraftInstance().f
				.b(pos[0], pos[1], pos[2]);
		if (entity == null)
			return;
		if (!(entity instanceof TileEntityPictureFactory)) {
			return;
		}
		gs player = ColoredWool.getPlayer();
		ModLoader.OpenGUI(player, new GuiPictureFactoryImage(
				(TileEntityPictureFactory) entity));
	}

	public int a(int i, Random random) {
		return blockInactive.bn;
	}
	
}