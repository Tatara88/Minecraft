package org.minecraftnauja.coloredwool.tileentity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.minecraftnauja.coloredwool.ColoredWool;
import org.minecraftnauja.coloredwool.Config.Factory;
import org.minecraftnauja.coloredwool.Packet;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;

/**
 * Factory tile entity.
 */
public abstract class TileEntityFactory extends TileEntity implements
		ISidedInventory {

	protected String imageName;
	public int factoryBurnTime;
	public int currentItemBurnTime;
	protected ItemStack[] dyeItemStacks;
	protected ItemStack coalItemStack;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSizeInventory() {
		return dyeItemStacks.length + 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemStack getStackInSlot(int par1) {
		if (par1 == 6)
			return coalItemStack;
		if ((par1 >= 0) && (par1 < dyeItemStacks.length)) {
			return dyeItemStacks[par1];
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (par1 == 6) {
			if (coalItemStack == null) {
				return null;
			}
			if (coalItemStack.stackSize <= par2) {
				ItemStack itemstack = coalItemStack;
				coalItemStack = null;
				return itemstack;
			}
			ItemStack itemstack = coalItemStack.splitStack(par2);
			if (coalItemStack.stackSize == 0) {
				coalItemStack = null;
			}
			return itemstack;
		}
		if ((par1 >= 0) && (par1 < dyeItemStacks.length)) {
			if (dyeItemStacks[par1] == null) {
				return null;
			}
			if (dyeItemStacks[par1].stackSize <= par2) {
				ItemStack itemstack = dyeItemStacks[par1];
				dyeItemStacks[par1] = null;
				return itemstack;
			}
			ItemStack itemstack = dyeItemStacks[par1].splitStack(par2);
			if (dyeItemStacks[par1].stackSize == 0) {
				dyeItemStacks[par1] = null;
			}
			return itemstack;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		if (par1 == 6)
			coalItemStack = par2ItemStack;
		else if ((par1 >= 0) && (par1 < dyeItemStacks.length)) {
			dyeItemStacks[par1] = par2ItemStack;
		}
		if ((par2ItemStack != null)
				&& (par2ItemStack.stackSize > getInventoryStackLimit()))
			par2ItemStack.stackSize = getInventoryStackLimit();
	}

	public int getBurnTimeRemainingScaled(int i) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = 200;
		}
		return factoryBurnTime * i / currentItemBurnTime;
	}

	public boolean isBurning() {
		return factoryBurnTime > 0;
	}

	public boolean hasRedRose() {
		if (dyeItemStacks[0] == null) {
			return false;
		}
		return (dyeItemStacks[0].getItem().itemID == Item.dyePowder.itemID)
				&& (dyeItemStacks[0].getItemDamage() == 1);
	}

	public boolean hasCactusGreen() {
		if (dyeItemStacks[1] == null) {
			return false;
		}
		return (dyeItemStacks[1].getItem().itemID == Item.dyePowder.itemID)
				&& (dyeItemStacks[1].getItemDamage() == 2);
	}

	public boolean hasLapisLazuli() {
		if (dyeItemStacks[2] == null) {
			return false;
		}
		return (dyeItemStacks[2].getItem().itemID == Item.dyePowder.itemID)
				&& (dyeItemStacks[2].getItemDamage() == 4);
	}

	public boolean hasColoredDye() {
		if (dyeItemStacks[3] == null) {
			return false;
		}
		return dyeItemStacks[3].getItem().itemID == ColoredWool.coloredDye.itemID;
	}

	public boolean hasWool() {
		if (dyeItemStacks[4] == null) {
			return false;
		}
		return dyeItemStacks[4].getItem().itemID == Block.cloth.blockID;
	}

	public boolean hasColoredWool() {
		if (dyeItemStacks[5] == null) {
			return false;
		}
		return dyeItemStacks[5].getItem().itemID == ColoredWool.coloredWool.blockID;
	}

	public boolean[] findItemsToSmelt() {
		boolean type1 = (hasRedRose()) && (hasCactusGreen())
				&& (hasLapisLazuli()) && (hasWool());
		boolean type2 = (hasColoredDye()) && (hasWool());
		boolean type3 = hasColoredWool();
		return new boolean[] { type1, type2, type3 };
	}

	public boolean canSmelt() {
		boolean[] b = findItemsToSmelt();
		return b[0] || b[1] || b[2];
	}

	public int smeltItem() {
		if (!canSmelt()) {
			return -1;
		}
		boolean[] b = findItemsToSmelt();
		if (b[2]) {
			decrStackSize(5, 1);
			return 2;
		}
		if (b[1]) {
			decrStackSize(3, 1);
			decrStackSize(4, 1);
			return 1;
		}
		decrStackSize(0, 1);
		decrStackSize(1, 1);
		decrStackSize(2, 1);
		decrStackSize(4, 1);
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}
		return par1EntityPlayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D,
				zCoord + 0.5D) <= 64.0D;
	}

	public boolean blockAlreadyColored(int i, int j, int k, int color) {
		int id = worldObj.getBlockId(i, j, k);
		if ((id > 0)
				&& ((getConfig().dontEraseAnything) || (getConfig().dontEraseTheseIds
						.contains(id + ";")))) {
			return true;
		}
		TileEntity tmp = worldObj.getBlockTileEntity(i, j, k);
		if (tmp == null) {
			return false;
		}
		if (!(tmp instanceof TileEntityColoredWool)) {
			return false;
		}
		TileEntityColoredWool tmp2 = (TileEntityColoredWool) tmp;
		return tmp2.color == color;
	}

	/**
	 * Gets the configuration.
	 * 
	 * @return the configuration.
	 */
	protected abstract Factory getConfig();

	/**
	 * Gets the image name.
	 * 
	 * @return the image name.
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * Sets the image name.
	 * 
	 * @param name
	 *            new name.
	 */
	public void setImageName(String name) {
		imageName = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setWorldObj(World par1World) {
		super.setWorldObj(par1World);
		if (par1World != null && !par1World.isRemote && imageName != null) {
			String name = imageName;
			imageName = null;
			setImageToGenerate(name);
		}
	}

	/**
	 * Sets the new image to generate.
	 * 
	 * @param name
	 *            new image name.
	 */
	public abstract void setImageToGenerate(String name);

	/**
	 * Sends the new selected image to players.
	 */
	public void sendImageToPlayers() {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			dos.writeInt(Packet.UpdateFactoryImageClient.ordinal());
			dos.writeInt(xCoord);
			dos.writeInt(yCoord);
			dos.writeInt(zCoord);
			dos.writeUTF(imageName);
			Packet250CustomPayload p = new Packet250CustomPayload();
			p.channel = ColoredWool.MOD_ID;
			p.data = bos.toByteArray();
			p.length = bos.size();
			PacketDispatcher.sendPacketToAllPlayers(p);
		} catch (IOException e) {
			FMLLog.log(ColoredWool.MOD_ID, Level.SEVERE, e,
					"Could not send packet");
		}
	}

	/**
	 * Sends the new selected image to server.
	 * 
	 * @param name
	 *            image name.
	 */
	public void sendImageToServer(String name) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			dos.writeInt(Packet.UpdateFactoryImageServer.ordinal());
			dos.writeInt(xCoord);
			dos.writeInt(yCoord);
			dos.writeInt(zCoord);
			dos.writeUTF(name);
			Packet250CustomPayload p = new Packet250CustomPayload();
			p.channel = ColoredWool.MOD_ID;
			p.data = bos.toByteArray();
			p.length = bos.size();
			PacketDispatcher.sendPacketToServer(p);
		} catch (IOException e) {
			FMLLog.log(ColoredWool.MOD_ID, Level.SEVERE, e,
					"Could not send packet");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public net.minecraft.network.packet.Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		readFromNBT(pkt.customParam1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void openChest() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeChest() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
		return isStackValidForSlot(par1, par2ItemStack);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

}
