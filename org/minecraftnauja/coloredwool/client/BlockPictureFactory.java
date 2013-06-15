package org.minecraftnauja.coloredwool.client;

import java.util.Random;

import org.minecraftnauja.coloredwool.ColoredWool;

import net.minecraft.client.Minecraft;

public class BlockPictureFactory extends rw {

	public void a(fd world, int i, int j, int k, ls entity) {
		int l = in.b(entity.aS * 4.0F / 360.0F + 2.5D) & 0x3;
		if (j < (int) entity.aN - 1) {
			l += 5;
		}
		world.d(i, j, k, l);
	}

	public static void updateFactoryBlockState(boolean flag, fd world, int i,
			int j, int k) {
		ow entity = world.b(i, j, k);
		if (entity == null)
			return;
		if (!(entity instanceof TileEntityPictureFactory)) {
			return;
		}
		TileEntityPictureFactory factory = (TileEntityPictureFactory) entity;
		updateFactoryBlockState(factory.isActivated, flag, world, i, j, k);
	}

	public static void updateFactoryBlockState(boolean flag, boolean flag2,
			fd world, int i, int j, int k) {
		ow entity = world.b(i, j, k);
		if (entity == null)
			return;
		if (!(entity instanceof TileEntityPictureFactory)) {
			return;
		}
		TileEntityPictureFactory factory = (TileEntityPictureFactory) entity;
		int l = world.e(i, j, k);
		if (flag) {
			if (flag2)
				world.f(i, j, k, getIdBurning());
			else
				world.f(i, j, k, getIdActive());
		} else {
			world.f(i, j, k, getIdInactive());
		}
		world.d(i, j, k, l);
		factory.isActivated = flag;
		factory.isBurning = flag2;
		factory.j();
		world.a(i, j, k, factory);
	}

	public boolean a(fd world, int i, int j, int k, gs player) {
		if (world.B) {
			return true;
		}
		if (j < (int) player.aN - 1) {
			return false;
		}
		int l = in.b(player.aS * 4.0F / 360.0F + 2.5D) & 0x3;
		int metadata = world.e(i, j, k);
		TileEntityPictureFactory entity = (TileEntityPictureFactory) world.b(i,
				j, k);
		if (entity == null) {
			return false;
		}

		if (((metadata == 2) || (metadata == 5)) && (l == 3)) {
			ModLoader.OpenGUI(player, new GuiPictureFactoryImage(entity));
			return true;
		}
		if (((metadata == 3) || (metadata == 6)) && (l == 0)) {
			ModLoader.OpenGUI(player, new GuiPictureFactoryImage(entity));
			return true;
		}
		if (((metadata == 0) || (metadata == 7)) && (l == 1)) {
			ModLoader.OpenGUI(player, new GuiPictureFactoryImage(entity));
			return true;
		}
		if (((metadata == 1) || (metadata == 8)) && (l == 2)) {
			ModLoader.OpenGUI(player, new GuiPictureFactoryImage(entity));
			return true;
		}

		if (((metadata == 2) || (metadata == 5)) && (l == 0)) {
			ModLoader.OpenGUI(player, new GuiPictureFactoryFurnace(player.c,
					entity));
			return true;
		}
		if (((metadata == 3) || (metadata == 6)) && (l == 1)) {
			ModLoader.OpenGUI(player, new GuiPictureFactoryFurnace(player.c,
					entity));
			return true;
		}
		if (((metadata == 0) || (metadata == 7)) && (l == 2)) {
			ModLoader.OpenGUI(player, new GuiPictureFactoryFurnace(player.c,
					entity));
			return true;
		}
		if (((metadata == 1) || (metadata == 8)) && (l == 3)) {
			ModLoader.OpenGUI(player, new GuiPictureFactoryFurnace(player.c,
					entity));
			return true;
		}
		return false;
	}

	public void b(fd world, int i, int j, int k, gs player) {
		if (world.B) {
			return;
		}
		if (j < (int) player.aN - 1) {
			return;
		}
		int l = in.b(player.aS * 4.0F / 360.0F + 2.5D) & 0x3;
		int metadata = world.e(i, j, k);
		if (((metadata == 2) || (metadata == 5)) && (l == 3))
			updateFactoryBlockState(!this.isActive, this.isBurning, world, i,
					j, k);
		else if (((metadata == 3) || (metadata == 6)) && (l == 0))
			updateFactoryBlockState(!this.isActive, this.isBurning, world, i,
					j, k);
		else if (((metadata == 0) || (metadata == 7)) && (l == 1))
			updateFactoryBlockState(!this.isActive, this.isBurning, world, i,
					j, k);
		else if (((metadata == 1) || (metadata == 8)) && (l == 2))
			updateFactoryBlockState(!this.isActive, this.isBurning, world, i,
					j, k);
	}

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