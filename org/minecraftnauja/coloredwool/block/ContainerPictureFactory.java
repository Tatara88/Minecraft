package org.minecraftnauja.coloredwool.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import org.minecraftnauja.coloredwool.tileentity.TileEntityPictureFactory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Container for the picture factory.
 */
public class ContainerPictureFactory extends Container {

	/**
	 * Instance of the factory.
	 */
	private TileEntityPictureFactory factory;

	/**
	 * Progress.
	 */
	private int progressWidth;

	/**
	 * Progress.
	 */
	private int progressHeight;

	/**
	 * Cooking time.
	 */
	private int cookTime;

	/**
	 * Burning time.
	 */
	private int burnTime;

	/**
	 * Initializing constructor.
	 * 
	 * @param inventory
	 *            player's inventory.
	 * @param factory
	 *            instance of the factory.
	 */
	public ContainerPictureFactory(InventoryPlayer inventory,
			TileEntityPictureFactory factory) {
		cookTime = 0;
		burnTime = 0;
		progressWidth = 0;
		progressHeight = 0;
		this.factory = factory;

		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 2; k++) {
				addSlotToContainer(new Slot(factory, k + i * 2, 8 + k * 18,
						17 + i * 18));
			}
		}
		addSlotToContainer(new Slot(factory, 6, 80, 35));

		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
				addSlotToContainer(new Slot(inventory, k + i * 9 + 9,
						8 + k * 18, 84 + i * 18));
			}

		}

		for (int j = 0; j < 9; j++) {
			addSlotToContainer(new Slot(inventory, j, 8 + j * 18, 142));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) crafters.get(i);
			if (cookTime != factory.factoryCookTime)
				icrafting.sendProgressBarUpdate(this, 0,
						factory.factoryCookTime);
			else if (progressWidth != factory.progressWidth)
				icrafting.sendProgressBarUpdate(this, 1, factory.progressWidth);
			else if (progressHeight != factory.progressHeight)
				icrafting
						.sendProgressBarUpdate(this, 2, factory.progressHeight);
			else if (burnTime != factory.factoryBurnTime) {
				icrafting.sendProgressBarUpdate(this, 3,
						factory.factoryBurnTime);
			}
		}

		cookTime = factory.factoryCookTime;
		burnTime = factory.factoryBurnTime;
		progressWidth = factory.progressWidth;
		progressHeight = factory.progressHeight;
	}

	/**
	 * {@inheritDoc}
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int par1, int par2) {
		switch (par1) {
		case 0:
			factory.factoryCookTime = par2;
			break;
		case 1:
			factory.progressWidth = par2;
			break;
		case 2:
			factory.progressHeight = par2;
			break;
		case 3:
			factory.factoryBurnTime = par2;
			break;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		if (factory == null) {
			return false;
		}
		return factory.isUseableByPlayer(par1EntityPlayer);
	}

}