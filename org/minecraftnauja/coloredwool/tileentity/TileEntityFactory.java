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
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

import org.minecraftnauja.coloredwool.ColoredWool;
import org.minecraftnauja.coloredwool.Config.Factory;
import org.minecraftnauja.coloredwool.Packet;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Factory tile entity.
 */
public abstract class TileEntityFactory extends TileEntity implements
		ISidedInventory {

	/**
	 * Index of red rose.
	 */
	private static final int RED_ROSE = 0;

	/**
	 * Index of cactus green.
	 */
	private static final int CACTUS_GREEN = 1;

	/**
	 * Index of lapis lazuli.
	 */
	private static final int LAPIS_LAZULI = 2;

	/**
	 * Index of colored dye.
	 */
	private static final int COLORED_DYE = 3;

	/**
	 * Index of wool.
	 */
	private static final int WOOL = 4;

	/**
	 * Index of colored wool.
	 */
	private static final int COLORED_WOOL = 5;

	/**
	 * Index of coal.
	 */
	private static final int COAL = 6;

	/**
	 * Slots for items.
	 */
	private static final int[] SLOTS_ITEMS = new int[] { RED_ROSE,
			CACTUS_GREEN, LAPIS_LAZULI, COLORED_DYE, WOOL, COLORED_WOOL };

	/**
	 * Slots for coal.
	 */
	private static final int[] SLOTS_COAL = new int[] { COAL };

	private String invName;
	protected String imageName;
	public int factoryBurnTime;
	public int factoryCookTime;
	public int currentItemBurnTime;

	/**
	 * Item stacks holding items.
	 */
	protected ItemStack[] factoryItemStacks = new ItemStack[7];

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSizeInventory() {
		return factoryItemStacks.length;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemStack getStackInSlot(int par1) {
		return factoryItemStacks[par1];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (factoryItemStacks[par1] != null) {
			ItemStack itemstack;
			if (factoryItemStacks[par1].stackSize <= par2) {
				itemstack = factoryItemStacks[par1];
				factoryItemStacks[par1] = null;
				return itemstack;
			} else {
				itemstack = factoryItemStacks[par1].splitStack(par2);
				if (factoryItemStacks[par1].stackSize == 0) {
					factoryItemStacks[par1] = null;
				}
				return itemstack;
			}
		} else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (factoryItemStacks[par1] != null) {
			ItemStack itemstack = factoryItemStacks[par1];
			factoryItemStacks[par1] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
		factoryItemStacks[par1] = par2ItemStack;
		if (par2ItemStack != null
				&& par2ItemStack.stackSize > getInventoryStackLimit()) {
			par2ItemStack.stackSize = getInventoryStackLimit();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInvName() {
		return isInvNameLocalized() ? invName : "container.furnace";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isInvNameLocalized() {
		return invName != null && invName.length() > 0;
	}

	/**
	 * Sets the inventory name.
	 * 
	 * @param invName
	 *            new name.
	 */
	public void setInvName(String invName) {
		this.invName = invName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	/**
	 * Returns the cooking progression.
	 * 
	 * @return the cooking progression.
	 */
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int i) {
		int p = factoryCookTime * i / 200;
		if (p < 0)
			return 0;
		if (p > 200 * i) {
			return 200 * i;
		}
		return p;
	}

	/**
	 * Returns the remaining burn time.
	 * 
	 * @return the remaining burn time.
	 */
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int i) {
		if (currentItemBurnTime == 0) {
			currentItemBurnTime = 200;
		}
		return factoryBurnTime * i / currentItemBurnTime;
	}

	/**
	 * Indicates if the factory is burning.
	 * 
	 * @return if the factory is burning.
	 */
	public boolean isBurning() {
		return factoryBurnTime > 0;
	}

	/**
	 * Indicates if the factory can smelt the items.
	 * 
	 * @return if the factory can smelt the items.
	 */
	private boolean canSmelt() {
		return (hasRedRose() && hasCactusGreen() && hasLapisLazuli() && hasWool())
				|| (hasColoredDye() && hasWool()) || hasColoredWool();
	}

	/**
	 * Indicates if the furnace has a red rose.
	 * 
	 * @return if the furnace has a red rose.
	 */
	private boolean hasRedRose() {
		if (factoryItemStacks[RED_ROSE] == null) {
			return false;
		}
		return (factoryItemStacks[RED_ROSE].getItem().itemID == Item.dyePowder.itemID)
				&& (factoryItemStacks[RED_ROSE].getItemDamage() == 1);
	}

	/**
	 * Indicates if the furnace has a cactus green.
	 * 
	 * @return if the furnace has a cactus green.
	 */
	private boolean hasCactusGreen() {
		if (factoryItemStacks[CACTUS_GREEN] == null) {
			return false;
		}
		return (factoryItemStacks[CACTUS_GREEN].getItem().itemID == Item.dyePowder.itemID)
				&& (factoryItemStacks[CACTUS_GREEN].getItemDamage() == 2);
	}

	/**
	 * Indicates if the furnace has a lapis lazuli.
	 * 
	 * @return if the furnace has a lapis lazuli.
	 */
	private boolean hasLapisLazuli() {
		if (factoryItemStacks[LAPIS_LAZULI] == null) {
			return false;
		}
		return (factoryItemStacks[LAPIS_LAZULI].getItem().itemID == Item.dyePowder.itemID)
				&& (factoryItemStacks[LAPIS_LAZULI].getItemDamage() == 4);
	}

	/**
	 * Indicates if the furnace has a colored dye.
	 * 
	 * @return if the furnace has a colored dye.
	 */
	private boolean hasColoredDye() {
		if (factoryItemStacks[COLORED_DYE] == null) {
			return false;
		}
		return factoryItemStacks[COLORED_DYE].getItem().itemID == ColoredWool.coloredDye.itemID;
	}

	/**
	 * Indicates if the furnace has a wool.
	 * 
	 * @return if the furnace has a wool.
	 */
	private boolean hasWool() {
		if (factoryItemStacks[WOOL] == null) {
			return false;
		}
		return factoryItemStacks[WOOL].getItem().itemID == Block.cloth.blockID;
	}

	/**
	 * Indicates if the furnace has a colored wool.
	 * 
	 * @return if the furnace has a colored wool.
	 */
	private boolean hasColoredWool() {
		if (factoryItemStacks[COLORED_WOOL] == null) {
			return false;
		}
		return factoryItemStacks[COLORED_WOOL].getItem().itemID == ColoredWool.coloredWool.blockID;
	}

	/**
	 * Uses one combination of items.
	 */
	private void smeltItem() {
		if (canSmelt()) {
			if (hasRedRose() && hasCactusGreen() && hasLapisLazuli()
					&& hasWool()) {
				useItem(RED_ROSE);
				useItem(CACTUS_GREEN);
				useItem(LAPIS_LAZULI);
				useItem(WOOL);
			} else if (hasColoredDye() && hasWool()) {
				useItem(COLORED_DYE);
				useItem(WOOL);
			} else if (hasColoredWool()) {
				useItem(COLORED_WOOL);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
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
	public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack) {
		if (par1 == COAL) {
			return TileEntityFurnace.isItemFuel(par2ItemStack);
		} else {
			return true;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return par1 == 0 ? SLOTS_COAL : (par1 == 1 ? SLOTS_ITEMS : SLOTS_COAL);
		// TODO
	}

	/**
	 * Uses one of the items at given index.
	 * 
	 * @param index
	 *            index in the furnace.
	 */
	private void useItem(int index) {
		factoryItemStacks[index].stackSize--;
		if (factoryItemStacks[index].stackSize <= 0) {
			factoryItemStacks[index] = null;
		}
	}

	public boolean[] findItemsToSmelt() {
		boolean type1 = (hasRedRose()) && (hasCactusGreen())
				&& (hasLapisLazuli()) && (hasWool());
		boolean type2 = (hasColoredDye()) && (hasWool());
		boolean type3 = hasColoredWool();
		return new boolean[] { type1, type2, type3 };
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
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
		return isStackValidForSlot(par1, par2ItemStack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}

}
