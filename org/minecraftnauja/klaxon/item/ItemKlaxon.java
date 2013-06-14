package org.minecraftnauja.klaxon.item;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;

import org.minecraftnauja.klaxon.Klaxon;
import org.minecraftnauja.klaxon.PacketType;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;

/**
 * Klaxon item.
 */
public class ItemKlaxon extends Item {

	/**
	 * Klaxon volume.
	 */
	private final float volume = 1.5F;

	/**
	 * Initializing constructor.
	 * 
	 * @param id
	 *            item identifier.
	 */
	public ItemKlaxon(int id) {
		super(id);
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("klaxon");

		FMLLog.log(Klaxon.MOD_ID, Level.INFO, "klaxon id %d", itemID);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerIcons(IconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon("Klaxon:klaxon");
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unused")
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world,
			EntityPlayer entityPlayer) {
		if (!world.isRemote) {
			FMLLog.log(Klaxon.MOD_ID, Level.INFO,
					"Klaxon activated, playing klaxon%s", entityPlayer.username);
			world.playSoundAtEntity(entityPlayer, "klaxon"
					+ entityPlayer.username + "a", volume, 1.0F);
			try {
				// Notifies players around that a klaxon is playing.
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				DataOutputStream dos = new DataOutputStream(bos);
				dos.writeInt(PacketType.PlayKlaxon.ordinal());
				dos.writeUTF("klaxon" + entityPlayer.username + "a");
				Packet250CustomPayload packet = new Packet250CustomPayload();
				packet.channel = Klaxon.MOD_ID;
				packet.data = bos.toByteArray();
				packet.length = bos.size();
				PacketDispatcher.sendPacketToAllAround(entityPlayer.posX,
						entityPlayer.posY - (double) entityPlayer.yOffset,
						entityPlayer.posZ,
						volume > 1.0F ? (double) (16.0F * volume) : 16.0D,
						world.provider.dimensionId, packet);
			} catch (IOException e) {
				FMLLog.log(Klaxon.MOD_ID, Level.SEVERE, e,
						"Could not send packet to players");
			}
		}
		return itemStack;
	}

}
