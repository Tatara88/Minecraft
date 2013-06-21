package org.minecraftnauja.coloredwool;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

import org.minecraftnauja.coloredwool.tileentity.TileEntityColoredWool;

/**
 * Custom packets.
 */
public enum Packet {

	UpdateColoredWoolServer {

		public void handle(Packet250CustomPayload packet, DataInputStream dis,
				EntityPlayer player) throws IOException {
			TileEntity e = player.worldObj.getBlockTileEntity(dis.readInt(),
					dis.readInt(), dis.readInt());
			if (e != null && e instanceof TileEntityColoredWool) {
				TileEntityColoredWool w = (TileEntityColoredWool) e;
				w.color = (dis.readInt() & 0xFFFFFF);
				w.sendColorToPlayers();
			}
		}

	},

	UpdateColoredWoolClient {

		public void handle(Packet250CustomPayload packet, DataInputStream dis,
				EntityPlayer player) throws IOException {
			TileEntity e = player.worldObj.getBlockTileEntity(dis.readInt(),
					dis.readInt(), dis.readInt());
			if (e != null && e instanceof TileEntityColoredWool) {
				TileEntityColoredWool w = (TileEntityColoredWool) e;
				w.color = (dis.readInt() & 0xFFFFFF);
				player.worldObj.markBlockForRenderUpdate(e.xCoord, e.yCoord,
						e.zCoord);
			}
		}

	};

	/**
	 * Handles the packet.
	 * 
	 * @param packet
	 *            the packet.
	 * @param dis
	 *            input stream.
	 * @param player
	 *            the player.
	 * @throws IOException
	 *             error with IO.
	 */
	public abstract void handle(Packet250CustomPayload packet,
			DataInputStream dis, EntityPlayer player) throws IOException;

}
