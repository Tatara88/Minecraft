package org.minecraftnauja.coloredwool.tileentity;

import java.awt.image.BufferedImage;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;

import org.minecraftnauja.coloredwool.ColoredWool;
import org.minecraftnauja.coloredwool.Config.Factory;
import org.minecraftnauja.coloredwool.block.BlockPictureFactory;

/**
 * Picture factory tile entity.
 */
public class TileEntityPictureFactory extends TileEntityFactory {

	protected String imageName;
	protected BufferedImage image;
	protected int imageWidth;
	protected int imageHeight;
	protected int currentLine;
	protected int currentColumn;
	public int progressWidth;
	public int progressHeight;
	public int factoryGenerationTime;
	public int factoryCookTime;
	public boolean isBurning;
	public boolean isActivated;

	/**
	 * Default constructor.
	 */
	public TileEntityPictureFactory() {
		imageName = "";
		dyeItemStacks = new ItemStack[6];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInvName() {
		return "Picture Factory";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateEntity() {
		if (!isActivated) {
			return;
		}
		if (image == null) {
			return;
		}
		boolean changed = false;
		boolean smelt = canSmelt();
		if (ColoredWool.config.pictureFactory.dontRequireItems) {
			smelt = true;
		}
		boolean fuel = TileEntityFurnace.isItemFuel(coalItemStack);
		if (ColoredWool.config.pictureFactory.dontRequireFuel) {
			fuel = true;
		}

		if (isActivated && !isBurning && (smelt) && (fuel)) {
			BlockPictureFactory.updateFactoryBlockState(true, true, worldObj,
					xCoord, yCoord, zCoord);
		} else if (isBurning && ((!isActivated) || (!fuel) || (!smelt))) {
			BlockPictureFactory.updateFactoryBlockState(isActivated, false,
					worldObj, xCoord, yCoord, zCoord);
		}
		if (factoryBurnTime > 0) {
			factoryBurnTime -= 1;
		}
		if (!worldObj.isRemote) {
			if ((factoryBurnTime == 0) && (smelt)) {
				factoryBurnTime = TileEntityFurnace
						.getItemBurnTime(coalItemStack);
				if (ColoredWool.config.pictureFactory.dontRequireFuel) {
					factoryBurnTime = 500;
				}
				currentItemBurnTime = factoryBurnTime;
				if (factoryBurnTime > 0) {
					changed = true;
					if (!ColoredWool.config.pictureFactory.dontRequireFuel) {
						decrStackSize(6, 1);
					}
				}
			}
			if ((isBurning()) && (smelt)) {
				factoryCookTime += 16;
				if (ColoredWool.config.pictureFactory.instantCook) {
					factoryCookTime = 200;
				}
				if (factoryCookTime >= 200) {
					if (!ColoredWool.config.pictureFactory.dontRequireItems) {
						smeltItem();
					}
					if (generateImagePart()) {
						BlockPictureFactory.updateFactoryBlockState(false,
								false, worldObj, xCoord, yCoord, zCoord);
						resetImage();
					}
					updateProgressWidth();
					updateProgressHeight();
					changed = true;
					factoryCookTime = 0;
				}
			} else {
				factoryCookTime = 0;
			}
			if (!isBurning) {
				changed = true;
				BlockPictureFactory.updateFactoryBlockState(isActivated, false,
						worldObj, xCoord, yCoord, zCoord);
			}
		}
		if (changed)
			onInventoryChanged();
	}

	private boolean generateImagePart() {
		if ((currentColumn < 0) || (currentColumn >= imageWidth))
			return true;
		if ((currentLine < 0) || (currentLine >= imageHeight)) {
			return true;
		}
		int[] pos = nextVisiblePixel(currentColumn, currentLine);
		if (pos == null) {
			return true;
		}
		currentColumn = pos[0];
		currentLine = pos[1];
		int argb = pos[2];

		TileEntityColoredWool entity = new TileEntityColoredWool();
		entity.setColor(argb >> 16 & 0xFF, argb >> 8 & 0xFF, argb & 0xFF);

		int l = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		int x = xCoord;
		int y = yCoord;
		int z = zCoord;
		if (l == 5) {
			x = xCoord - imageWidth / 2 + currentColumn;
			y = yCoord + 1 + (imageHeight - currentLine);
		} else if (l == 6) {
			z = zCoord - imageWidth / 2 + currentColumn;
			y = yCoord + 1 + (imageHeight - currentLine);
		} else if (l == 7) {
			x = xCoord + imageWidth / 2 - currentColumn;
			y = yCoord + 1 + (imageHeight - currentLine);
		} else if (l == 8) {
			z = zCoord + imageWidth / 2 - currentColumn;
			y = yCoord + 1 + (imageHeight - currentLine);
		} else if (l == 1) {
			z = zCoord + imageWidth / 2 - currentColumn;
			x = xCoord - 1 - (imageHeight - currentLine);
		} else if (l == 3) {
			z = zCoord - imageWidth / 2 + currentColumn;
			x = xCoord + 1 + (imageHeight - currentLine);
		} else if (l == 0) {
			x = xCoord + imageWidth / 2 - currentColumn;
			z = zCoord + 1 + (imageHeight - currentLine);
		} else if (l == 2) {
			x = xCoord - imageWidth / 2 + currentColumn;
			z = zCoord - 1 - (imageHeight - currentLine);
		}
		if (!blockAlreadyColored(x, y, z, entity)) {
			worldObj.setBlock(x, y, z, ColoredWool.coloredWool.blockID);
			worldObj.setBlockTileEntity(x, y, z, entity);
			if (worldObj.getBlockId(x, y, z) != ColoredWool.coloredWool.blockID) {
				return true;
			}
		}

		currentColumn += 1;
		if (currentColumn >= imageWidth) {
			currentColumn = 0;
			currentLine -= 1;
		}
		return currentLine < 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Factory getConfig() {
		return ColoredWool.config.pictureFactory;
	}

	public int[] nextVisiblePixel(int column, int line) {
		if ((column < 0) || (column >= imageWidth))
			return null;
		if ((line < 0) || (line >= imageHeight)) {
			return null;
		}
		int argb = image.getRGB(column, line);
		while ((argb >> 24 & 0xFF) < 255) {
			column++;
			if (column >= imageWidth) {
				column = 0;
				line--;
			}
			if (line < 0) {
				return null;
			}
			argb = image.getRGB(column, line);
		}
		return new int[] { column, line, argb };
	}

	public void resetImage() {
		if (image != null)
			currentLine = (imageHeight - 1);
		else {
			currentLine = 0;
		}
		currentColumn = 0;
	}

	public void updateProgressWidth() {
		if ((image == null) || (imageWidth < 1)) {
			progressWidth = 0;
		}
		progressWidth = ((int) (currentColumn / imageWidth * 100.0F));
	}

	public void updateProgressHeight() {
		if ((image == null) || (imageHeight < 1)) {
			progressHeight = 0;
		}
		progressHeight = ((int) ((imageHeight - (currentLine + 1))
				/ imageHeight * 100.0F));
	}

	public int getImageProgressWidth(int i) {
		if ((image == null) || (imageWidth < 1)) {
			return 0;
		}
		return progressWidth * i / 100;
	}

	public int getImageProgressHeight(int i) {
		if ((image == null) || (imageHeight < 1)) {
			return 0;
		}
		return progressHeight * i / 100;
	}

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
	 * {@inheritDoc}
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setString("ImageName", imageName);
		par1NBTTagCompound.setInteger("ImageLine", currentLine);
		par1NBTTagCompound.setInteger("ImageColumn", currentColumn);
		par1NBTTagCompound.setInteger("GenerationTime", factoryGenerationTime);
		par1NBTTagCompound.setBoolean("IsActivated", isActivated);
		par1NBTTagCompound.setBoolean("IsBurning", isBurning);
		par1NBTTagCompound.setShort("BurnTime", (short) factoryBurnTime);
		par1NBTTagCompound.setShort("CookTime", (short) factoryCookTime);

		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < dyeItemStacks.length; i++) {
			if (dyeItemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				dyeItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		if (coalItemStack != null) {
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			nbttagcompound1.setByte("Slot", (byte) 6);
			coalItemStack.writeToNBT(nbttagcompound1);
			nbttaglist.appendTag(nbttagcompound1);
		}
		par1NBTTagCompound.setTag("Items", nbttaglist);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		setImageToGenerate(par1NBTTagCompound.getString("ImageName"));
		currentLine = par1NBTTagCompound.getInteger("ImageLine");
		currentColumn = par1NBTTagCompound.getInteger("ImageColumn");
		updateProgressWidth();
		updateProgressHeight();
		factoryGenerationTime = par1NBTTagCompound.getInteger("GenerationTime");
		isActivated = par1NBTTagCompound.getBoolean("IsActivated");
		isBurning = par1NBTTagCompound.getBoolean("IsBurning");
		factoryBurnTime = par1NBTTagCompound.getShort("BurnTime");
		factoryCookTime = par1NBTTagCompound.getShort("CookTime");

		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		dyeItemStacks = new ItemStack[getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
					.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");
			if (b0 == 6) {
				coalItemStack = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			} else if ((b0 >= 0) && (b0 < dyeItemStacks.length)) {
				dyeItemStacks[b0] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		currentItemBurnTime = TileEntityFurnace.getItemBurnTime(coalItemStack);
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageToGenerate(String name) {
		if (name.equals(imageName)) {
			return;
		}
		imageName = name;
		currentLine = 0;
		currentColumn = 0;
		progressWidth = 0;
		progressHeight = 0;
		factoryGenerationTime = 0;
		if (name.equals("")) {
			return;
		}
		image = ColoredWool.getLocalImage(name);
		if (image == null) {
			return;
		}
		imageWidth = image.getWidth();
		imageHeight = image.getHeight();
		currentLine = (imageHeight - 1);
	}

}
