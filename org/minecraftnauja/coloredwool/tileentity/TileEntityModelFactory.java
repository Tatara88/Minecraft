package org.minecraftnauja.coloredwool.tileentity;

import java.awt.image.BufferedImage;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

import org.minecraftnauja.coloredwool.ColoredWool;
import org.minecraftnauja.coloredwool.Config.Factory;
import org.minecraftnauja.coloredwool.block.BlockModelFactory;

/**
 * Picture factory tile entity.
 */
public class TileEntityModelFactory extends TileEntityFactory {

	protected BufferedImage imageTop;
	protected BufferedImage imageBottom;
	protected BufferedImage imageLeft;
	protected BufferedImage imageRight;
	protected BufferedImage imageFront;
	protected BufferedImage imageBack;
	protected int imageWidth;
	protected int imageHeight;
	protected int imageDepth;
	protected int currentX;
	protected int currentY;
	protected int currentZ;
	public int progressWidth;
	public int progressHeight;
	public int factoryGenerationTime;
	public int factoryCookTime;
	public int factoryBurnTime;
	public int currentItemBurnTime;
	public boolean isActivated;
	public boolean isBurning;

	/**
	 * Default constructor.
	 */
	public TileEntityModelFactory() {
		imageName = "";
		dyeItemStacks = new ItemStack[6];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInvName() {
		return "Model Factory";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateEntity() {
		if (!isActivated) {
			return;
		}
		if (imageTop == null) {
			return;
		}
		boolean changed = false;
		boolean smelt = canSmelt();
		if (ColoredWool.config.modelFactory.dontRequireItems) {
			smelt = true;
		}
		boolean fuel = TileEntityFurnace.isItemFuel(coalItemStack);
		if (ColoredWool.config.modelFactory.dontRequireFuel) {
			fuel = true;
		}

		if ((isActivated) && (!isBurning) && (smelt) && (fuel)) {
			BlockModelFactory.updateFactoryBlockState(true, true, worldObj,
					xCoord, yCoord, zCoord);
		} else if ((isBurning) && ((!isActivated) || (!fuel) || (!smelt))) {
			BlockModelFactory.updateFactoryBlockState(isActivated, false,
					worldObj, xCoord, yCoord, zCoord);
		}
		if (factoryBurnTime > 0) {
			factoryBurnTime -= 1;
		}
		if (!worldObj.isRemote) {
			if ((factoryBurnTime == 0) && (smelt)) {
				factoryBurnTime = TileEntityFurnace
						.getItemBurnTime(coalItemStack);
				if (ColoredWool.config.modelFactory.dontRequireFuel) {
					factoryBurnTime = 500;
				}
				currentItemBurnTime = factoryBurnTime;
				if (factoryBurnTime > 0) {
					changed = true;
					if (!ColoredWool.config.modelFactory.dontRequireFuel) {
						decrStackSize(6, 1);
					}
				}
			}
			if ((isBurning()) && (smelt)) {
				factoryCookTime += 16;
				if (ColoredWool.config.modelFactory.instantCook) {
					factoryCookTime = 200;
				}
				if (factoryCookTime >= 200) {
					if (!ColoredWool.config.modelFactory.dontRequireItems) {
						smeltItem();
					}
					if (generateImagePart()) {
						BlockModelFactory.updateFactoryBlockState(false, false,
								worldObj, xCoord, yCoord, zCoord);
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
				BlockModelFactory.updateFactoryBlockState(isActivated, false,
						worldObj, xCoord, yCoord, zCoord);
			}
		}
		if (changed)
			onInventoryChanged();
	}

	private boolean generateImagePart() {
		if ((currentX < 0) || (currentX >= imageWidth))
			return true;
		if ((currentY < 0) || (currentY >= imageHeight))
			return true;
		if ((currentZ < 0) || (currentZ >= imageDepth)) {
			return true;
		}
		int[] pos = nextVisiblePixel(currentX, currentY, currentZ);
		if (pos == null) {
			return true;
		}
		currentX = pos[0];
		currentY = pos[1];
		currentZ = pos[2];
		int rgb = pos[3] & 0xFFFFFF;

		int l = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		int x = xCoord;
		int y = yCoord + 1;
		int z = zCoord;
		if (l == 5) {
			x = xCoord - imageWidth / 2 + currentX;
			y = yCoord + 1 + (imageHeight - currentY);
			z = zCoord + imageDepth / 2 - currentZ;
		} else if (l == 6) {
			x = xCoord - imageDepth / 2 + currentZ;
			y = yCoord + 1 + (imageHeight - currentY);
			z = zCoord - imageWidth / 2 + currentX;
		} else if (l == 7) {
			x = xCoord + imageWidth / 2 - currentX;
			y = yCoord + 1 + (imageHeight - currentY);
			z = zCoord - imageDepth / 2 + currentZ;
		} else if (l == 8) {
			x = xCoord + imageDepth / 2 - currentZ;
			y = yCoord + 1 + (imageHeight - currentY);
			z = zCoord + imageWidth / 2 - currentX;
		} else if (l == 1) {
			x = xCoord - 2 - currentZ;
			y = yCoord + imageHeight / 2 - currentY;
			z = zCoord + imageWidth / 2 - currentX;
		} else if (l == 3) {
			x = xCoord + 2 + currentZ;
			y = yCoord + imageHeight / 2 - currentY;
			z = zCoord - imageWidth / 2 + currentX;
		} else if (l == 0) {
			x = xCoord + imageWidth / 2 - currentX;
			y = yCoord + imageHeight / 2 - currentY;
			z = zCoord + 2 + currentZ;
		} else if (l == 2) {
			x = xCoord - imageWidth / 2 + currentX;
			y = yCoord + imageHeight / 2 - currentY;
			z = zCoord - 2 - currentZ;
		}

		if (!blockAlreadyColored(x, y, z, rgb)) {
			TileEntityColoredWool t = null;
			TileEntity e = worldObj.getBlockTileEntity(x, y, z);
			if (e != null && e instanceof TileEntityColoredWool) {
				t = (TileEntityColoredWool) e;
			} else {
				t = new TileEntityColoredWool();
				worldObj.setBlock(x, y, z, ColoredWool.coloredWool.blockID);
				if (worldObj.getBlockId(x, y, z) != ColoredWool.coloredWool.blockID) {
					return true;
				} else {
					worldObj.setBlockTileEntity(x, y, z, t);
				}
			}
			t.color = rgb;
			t.sendColorToPlayers();
		}

		currentZ -= 1;
		if (currentZ < 0) {
			currentZ = (imageDepth - 1);
			currentX += 1;
			if (currentX >= imageWidth) {
				currentX = 0;
				currentY -= 1;
				if (currentY < 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Factory getConfig() {
		return ColoredWool.config.modelFactory;
	}

	public int[] nextVisiblePixel(int x, int y, int z) {
		if ((x < 0) || (x >= imageWidth))
			return null;
		if ((y < 0) || (y >= imageHeight))
			return null;
		if ((z < 0) || (z >= imageDepth)) {
			return null;
		}

		if (z == 0)
			return nextVisiblePixelOnFront(x, y, z);
		if (z == imageDepth - 1) {
			return nextVisiblePixelOnBack(x, y, z);
		}
		return nextVisiblePixelOnSide(x, y, z);
	}

	public int[] nextVisiblePixelOnFront(int x, int y, int z) {
		int argb = imageFront.getRGB(x, y);
		if ((argb >> 24 & 0xFF) == 255) {
			return new int[] { x, y, z, argb };
		}
		x++;
		if (x >= imageWidth) {
			x = 0;
			y--;
			if (y < 0) {
				return null;
			}
		}
		return nextVisiblePixelOnBack(x, y, imageDepth - 1);
	}

	public int[] nextVisiblePixelOnBack(int x, int y, int z) {
		int argb = imageBack.getRGB(imageWidth - (x + 1), y);
		if ((argb >> 24 & 0xFF) == 255) {
			return new int[] { x, y, z, argb };
		}
		return nextVisiblePixelOnFront(x, y, 0);
	}

	public int[] nextVisiblePixelOnSide(int x, int y, int z) {
		if (x == 0)
			return nextVisiblePixelOnLeft(x, y, z);
		if (x == imageWidth - 1)
			return nextVisiblePixelOnRight(x, y, z);
		if (y == 0)
			return nextVisiblePixelOnTop(x, y, z);
		if (y == imageHeight - 1) {
			return nextVisiblePixelOnBottom(x, y, z);
		}
		return nextVisiblePixel(x, y, 0);
	}

	public int[] nextVisiblePixelOnTop(int x, int y, int z) {
		int argb = imageTop.getRGB(x, imageDepth - (z + 1));
		while ((argb >> 24 & 0xFF) < 255) {
			z--;
			if (z < 0) {
				z = imageDepth - 1;
				x++;
				if (x >= imageWidth) {
					return null;
				}
			}
			argb = imageTop.getRGB(x, imageDepth - (z + 1));
		}
		return new int[] { x, y, z, argb };
	}

	public int[] nextVisiblePixelOnBottom(int x, int y, int z) {
		int argb = imageBottom.getRGB(x, z);
		while ((argb >> 24 & 0xFF) < 255) {
			z--;
			if (z < 0) {
				z = imageDepth - 1;
				x++;
				if (x >= imageWidth) {
					return nextVisiblePixel(0, y - 1, z);
				}
			}
			argb = imageBottom.getRGB(x, z);
		}
		return new int[] { x, y, z, argb };
	}

	public int[] nextVisiblePixelOnLeft(int x, int y, int z) {
		int argb = imageLeft.getRGB(imageDepth - (z + 1), y);
		while ((argb >> 24 & 0xFF) < 255) {
			z--;
			if (z < 0) {
				return nextVisiblePixel(x + 1, y, imageDepth - 1);
			}
			argb = imageLeft.getRGB(imageDepth - (z + 1), y);
		}
		return new int[] { x, y, z, argb };
	}

	public int[] nextVisiblePixelOnRight(int x, int y, int z) {
		int argb = imageRight.getRGB(z, y);
		while ((argb >> 24 & 0xFF) < 255) {
			z--;
			if (z < 0) {
				return nextVisiblePixel(0, y - 1, imageDepth - 1);
			}
			argb = imageRight.getRGB(z, y);
		}
		return new int[] { x, y, z, argb };
	}

	public void resetImage() {
		if (imageTop != null) {
			currentY = (imageHeight - 1);
			currentZ = (imageDepth - 1);
		} else {
			currentY = 0;
			currentZ = 0;
		}
		currentX = 0;
		progressWidth = 0;
		progressHeight = 0;
	}

	public void updateProgressWidth() {
		if ((imageTop == null) || (imageWidth < 1)) {
			progressWidth = 0;
		}
		progressWidth = ((int) (currentX / imageWidth * 100.0F));
	}

	public void updateProgressHeight() {
		if ((imageTop == null) || (imageHeight < 1)) {
			progressHeight = 0;
		}
		progressHeight = ((int) ((imageHeight - (currentY + 1)) / imageHeight * 100.0F));
	}

	public int getImageProgressWidth(int i) {
		if ((imageTop == null) || (imageWidth < 1)) {
			return 0;
		}
		return progressWidth * i / 100;
	}

	public int getImageProgressHeight(int i) {
		if ((imageTop == null) || (imageHeight < 1)) {
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
		par1NBTTagCompound.setInteger("CurrentX", currentX);
		par1NBTTagCompound.setInteger("CurrentY", currentY);
		par1NBTTagCompound.setInteger("CurrentZ", currentZ);
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
		currentX = par1NBTTagCompound.getInteger("CurrentX");
		currentY = par1NBTTagCompound.getInteger("CurrentY");
		currentZ = par1NBTTagCompound.getInteger("CurrentZ");
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
			byte byte0 = nbttagcompound1.getByte("Slot");
			if (byte0 == 6)
				coalItemStack = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			else if ((byte0 >= 0) && (byte0 < dyeItemStacks.length)) {
				dyeItemStacks[byte0] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		currentItemBurnTime = TileEntityFurnace.getItemBurnTime(coalItemStack);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setImageToGenerate(String name) {
		if (name.equals(imageName)) {
			return;
		}
		imageName = name;
		currentX = 0;
		currentY = 0;
		currentZ = 0;
		progressWidth = 0;
		progressHeight = 0;
		factoryGenerationTime = 0;
		if (name.equals("")) {
			return;
		}

		imageTop = ColoredWool.getLocalImage(name + "/top.png");
		if (imageTop == null)
			return;
		imageBottom = ColoredWool.getLocalImage(name + "/bottom.png");
		if (imageBottom == null)
			return;
		imageLeft = ColoredWool.getLocalImage(name + "/left.png");
		if (imageLeft == null)
			return;
		imageRight = ColoredWool.getLocalImage(name + "/right.png");
		if (imageRight == null)
			return;
		imageFront = ColoredWool.getLocalImage(name + "/front.png");
		if (imageFront == null)
			return;
		imageBack = ColoredWool.getLocalImage(name + "/back.png");
		if (imageBack == null)
			return;

		imageWidth = imageFront.getWidth();
		imageHeight = imageFront.getHeight();
		imageDepth = imageLeft.getWidth();
		currentY = (imageHeight - 1);
		currentZ = (imageDepth - 1);
	}

}
