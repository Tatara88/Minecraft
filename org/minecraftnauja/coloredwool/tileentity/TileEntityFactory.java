package org.minecraftnauja.coloredwool.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.minecraftnauja.coloredwool.ColoredWool;
import org.minecraftnauja.coloredwool.Config.Factory;

/**
 * Factory tile entity.
 */
public abstract class TileEntityFactory extends TileEntity implements
		ISidedInventory {

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
			if (this.coalItemStack == null) {
				return null;
			}
			if (this.coalItemStack.stackSize <= par2) {
				ItemStack itemstack = this.coalItemStack;
				this.coalItemStack = null;
				return itemstack;
			}
			ItemStack itemstack = this.coalItemStack.splitStack(par2);
			if (this.coalItemStack.stackSize == 0) {
				this.coalItemStack = null;
			}
			return itemstack;
		}
		if ((par1 >= 0) && (par1 < this.dyeItemStacks.length)) {
			if (this.dyeItemStacks[par1] == null) {
				return null;
			}
			if (this.dyeItemStacks[par1].stackSize <= par2) {
				ItemStack itemstack = this.dyeItemStacks[par1];
				this.dyeItemStacks[par1] = null;
				return itemstack;
			}
			ItemStack itemstack = this.dyeItemStacks[par1].splitStack(par2);
			if (this.dyeItemStacks[par1].stackSize == 0) {
				this.dyeItemStacks[par1] = null;
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
			this.coalItemStack = par2ItemStack;
		else if ((par1 >= 0) && (par1 < this.dyeItemStacks.length)) {
			this.dyeItemStacks[par1] = par2ItemStack;
		}
		if ((par2ItemStack != null)
				&& (par2ItemStack.stackSize > getInventoryStackLimit()))
			par2ItemStack.stackSize = getInventoryStackLimit();
	}

	public int getBurnTimeRemainingScaled(int i) {
		if (this.currentItemBurnTime == 0) {
			this.currentItemBurnTime = 200;
		}
		return this.factoryBurnTime * i / this.currentItemBurnTime;
	}

	public boolean isBurning() {
		return this.factoryBurnTime > 0;
	}

	public boolean hasRedRose() {
		if (this.dyeItemStacks[0] == null) {
			return false;
		}
		return (this.dyeItemStacks[0].getItem().itemID == Item.dyePowder.itemID)
				&& (this.dyeItemStacks[0].getItemDamage() == 1);
	}

	public boolean hasCactusGreen() {
		if (this.dyeItemStacks[1] == null) {
			return false;
		}
		return (this.dyeItemStacks[1].getItem().itemID == Item.dyePowder.itemID)
				&& (this.dyeItemStacks[1].getItemDamage() == 2);
	}

	public boolean hasLapisLazuli() {
		if (this.dyeItemStacks[2] == null) {
			return false;
		}
		return (this.dyeItemStacks[2].getItem().itemID == Item.dyePowder.itemID)
				&& (this.dyeItemStacks[2].getItemDamage() == 4);
	}

	public boolean hasColoredDye() {
		if (this.dyeItemStacks[3] == null) {
			return false;
		}
		return this.dyeItemStacks[3].getItem().itemID == ColoredWool.coloredDye.itemID;
	}

	public boolean hasWool() {
		if (this.dyeItemStacks[4] == null) {
			return false;
		}
		return this.dyeItemStacks[4].getItem().itemID == Block.cloth.blockID;
	}

	public boolean hasColoredWool() {
		if (this.dyeItemStacks[5] == null) {
			return false;
		}
		return this.dyeItemStacks[5].getItem().itemID == ColoredWool.coloredWool.blockID;
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
		return par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D,
				this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	public boolean blockAlreadyColored(int i, int j, int k,
			TileEntityColoredWool entity) {
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
		return tmp2.color == entity.color;
	}

	/**
	 * Gets the configuration.
	 * 
	 * @return the configuration.
	 */
	protected abstract Factory getConfig();

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
