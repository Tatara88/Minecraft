package org.minecraftnauja.coloredwool.block;

import java.util.Random;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import org.minecraftnauja.coloredwool.tileentity.TileEntityFactory;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Base for the factories.
 */
public abstract class BlockFactory extends BlockContainer {

	/**
	 * Prevents factory inventory to be dropped.
	 */
	protected static boolean keepFactoryInventory = false;

	/**
	 * Random generator.
	 */
	private final Random factoryRand = new Random();

	/**
	 * Its state.
	 */
	private final FactoryState state;

	/**
	 * Icon for bottom side.
	 */
	@SideOnly(Side.CLIENT)
	private Icon iconBottom;

	/**
	 * Icon for furnace side.
	 */
	@SideOnly(Side.CLIENT)
	private Icon iconFurnace;

	/**
	 * Icon for inside side.
	 */
	@SideOnly(Side.CLIENT)
	private Icon iconInside;

	/**
	 * Icon for input side.
	 */
	@SideOnly(Side.CLIENT)
	private Icon iconInput;

	/**
	 * Icon for top side.
	 */
	@SideOnly(Side.CLIENT)
	private Icon iconTop;

	/**
	 * Icon for unused side.
	 */
	@SideOnly(Side.CLIENT)
	private Icon iconUnused;

	/**
	 * Initializing constructor.
	 * 
	 * @param par1
	 *            block identifier.
	 * @param state
	 *            its state.
	 */
	protected BlockFactory(int par1, FactoryState state) {
		super(par1, Block.furnaceIdle.blockMaterial);
		this.state = state;
	}

	/**
	 * Gets this block identifier when burning.
	 * 
	 * @return this block identifier when burning.
	 */
	protected abstract int getBurningId();

	/**
	 * Gets this block identifier when active.
	 * 
	 * @return this block identifier when active.
	 */
	protected abstract int getActiveId();

	/**
	 * Gets this block identifier when idle.
	 * 
	 * @return this block identifier when idle.
	 */
	protected abstract int getIdleId();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return getIdleId();
	}

	/**
	 * {@inheritDoc}
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2) {
		if (par1 == 0) {
			return iconBottom;
		}
		if (par2 == 2)
			return getIconWhenFacingEast(par1);
		if (par2 == 3)
			return getIconWhenFacingSouth(par1);
		if (par2 == 0)
			return getIconWhenFacingWest(par1);
		if (par2 == 1)
			return getIconWhenFacingNorth(par1);
		if ((par2 >= 5) && (par2 <= 8))
			return getIconWhenFacingTop(par1, par2);
		return iconUnused;
	}

	/**
	 * Gets the icon when facing top.
	 * 
	 * @param par1
	 * @param par2
	 * @return
	 */
	@SideOnly(Side.CLIENT)
	public Icon getIconWhenFacingTop(int par1, int par2) {
		if (par1 == 1) {
			return iconTop;
		}
		if (par2 == 5) {
			if (par1 == 5)
				return iconInput;
			if (par1 == 3)
				return iconFurnace;
			if (par1 == 4) {
				return iconInside;
			}
			return iconUnused;
		}
		if (par2 == 6) {
			if (par1 == 3)
				return iconInput;
			if (par1 == 4)
				return iconFurnace;
			if (par1 == 2) {
				return iconInside;
			}
			return iconUnused;
		}
		if (par2 == 7) {
			if (par1 == 4)
				return iconInput;
			if (par1 == 2)
				return iconFurnace;
			if (par1 == 5) {
				return iconInside;
			}
			return iconUnused;
		}
		if (par2 == 8) {
			if (par1 == 2)
				return iconInput;
			if (par1 == 5)
				return iconFurnace;
			if (par1 == 3) {
				return iconInside;
			}
			return iconUnused;
		}

		return iconUnused;
	}

	/**
	 * Gets the icon when facing east.
	 * 
	 * @param par1
	 * @return
	 */
	@SideOnly(Side.CLIENT)
	public Icon getIconWhenFacingEast(int par1) {
		if (par1 == 2)
			return iconTop;
		if (par1 == 5)
			return iconInput;
		if (par1 == 3)
			return iconFurnace;
		if (par1 == 4) {
			return iconInside;
		}
		return iconUnused;
	}

	/**
	 * Gets the icon when facing west.
	 * 
	 * @param par1
	 * @return
	 */
	@SideOnly(Side.CLIENT)
	public Icon getIconWhenFacingWest(int par1) {
		if (par1 == 3)
			return iconTop;
		if (par1 == 4)
			return iconInput;
		if (par1 == 2)
			return iconFurnace;
		if (par1 == 5) {
			return iconInside;
		}
		return iconUnused;
	}

	/**
	 * Gets the icon when facing south.
	 * 
	 * @param par1
	 * @return
	 */
	@SideOnly(Side.CLIENT)
	public Icon getIconWhenFacingSouth(int par1) {
		if (par1 == 5)
			return iconTop;
		if (par1 == 3)
			return iconInput;
		if (par1 == 4)
			return iconFurnace;
		if (par1 == 2) {
			return iconInside;
		}
		return iconUnused;
	}

	/**
	 * Gets the icon when facing north.
	 * 
	 * @param par1
	 * @return
	 */
	@SideOnly(Side.CLIENT)
	public Icon getIconWhenFacingNorth(int par1) {
		if (par1 == 4)
			return iconTop;
		if (par1 == 2)
			return iconInput;
		if (par1 == 5)
			return iconFurnace;
		if (par1 == 3) {
			return iconInside;
		}
		return iconUnused;
	}

	/**
	 * {@inheritDoc}
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		String prefix = "ColoredWool:" + getIconPrefix() + "Factory";
		iconBottom = par1IconRegister.registerIcon(prefix + "Bottom");
		iconFurnace = par1IconRegister.registerIcon(prefix + "Furnace"
				+ (state == FactoryState.Burning ? "Active" : "Idle"));
		iconInput = par1IconRegister.registerIcon(prefix + "Input"
				+ state.getActiveSuffix());
		iconInside = par1IconRegister.registerIcon(prefix + "Inside"
				+ state.getActiveSuffix());
		iconTop = par1IconRegister.registerIcon(prefix + "Top"
				+ state.getActiveSuffix());
		iconUnused = par1IconRegister.registerIcon(prefix + "Unused");
	}

	/**
	 * Gets the prefix for each icon of this factory.
	 * 
	 * @return the prefix.
	 */
	protected abstract String getIconPrefix();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (par1World.isRemote) {
			return true;
		} else {
			TileEntityFactory tef = (TileEntityFactory) par1World
					.getBlockTileEntity(par2, par3, par4);
			if (tef != null) {
				// TODO display gui.
			}
			return true;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World par1World, int par2, int par3,
			int par4, Random par5Random) {
		if (state == FactoryState.Burning) {
			int l = par1World.getBlockMetadata(par2, par3, par4);
			float f = (float) par2 + 0.5F;
			float f1 = (float) par3 + 0.0F + par5Random.nextFloat() * 6.0F
					/ 16.0F;
			float f2 = (float) par4 + 0.5F;
			float f3 = 0.52F;
			float f4 = par5Random.nextFloat() * 0.6F - 0.3F;
			if ((l == 3) || (l == 6)) {
				par1World.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D,
						0.0D, 0.0D);
				par1World.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D,
						0.0D, 0.0D);
			} else if ((l == 1) || (l == 8)) {
				par1World.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D,
						0.0D, 0.0D);
				par1World.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D,
						0.0D, 0.0D);
			} else if ((l == 0) || (l == 7)) {
				par1World.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D,
						0.0D, 0.0D);
				par1World.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D,
						0.0D, 0.0D);
			} else if ((l == 2) || (l == 5)) {
				par1World.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D,
						0.0D, 0.0D);
				par1World.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D,
						0.0D, 0.0D);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLiving par5EntityLiving, ItemStack par6ItemStack) {
		int l = 0;
		switch (MathHelper
				.floor_double((double) (par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) {
		case 0:
			l = 2;
			break;
		case 1:
			l = 3;
			break;
		case 2:
			l = 0;
			break;
		case 3:
			l = 1;
			break;
		}
		if (par3 < (int) par5EntityLiving.posY - 1) {
			l += 5;
		}
		par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4,
			int par5, int par6) {
		if (!keepFactoryInventory) {
			TileEntityFactory tef = (TileEntityFactory) par1World
					.getBlockTileEntity(par2, par3, par4);
			if (tef != null) {
				for (int j1 = 0; j1 < tef.getSizeInventory(); ++j1) {
					ItemStack itemstack = tef.getStackInSlot(j1);
					if (itemstack != null) {
						float f = factoryRand.nextFloat() * 0.8F + 0.1F;
						float f1 = factoryRand.nextFloat() * 0.8F + 0.1F;
						float f2 = factoryRand.nextFloat() * 0.8F + 0.1F;
						while (itemstack.stackSize > 0) {
							int k1 = factoryRand.nextInt(21) + 10;
							if (k1 > itemstack.stackSize) {
								k1 = itemstack.stackSize;
							}
							itemstack.stackSize -= k1;
							EntityItem entityitem = new EntityItem(par1World,
									(double) ((float) par2 + f),
									(double) ((float) par3 + f1),
									(double) ((float) par4 + f2),
									new ItemStack(itemstack.itemID, k1,
											itemstack.getItemDamage()));
							if (itemstack.hasTagCompound()) {
								entityitem.getEntityItem().setTagCompound(
										(NBTTagCompound) itemstack
												.getTagCompound().copy());
							}
							float f3 = 0.05F;
							entityitem.motionX = (double) ((float) factoryRand
									.nextGaussian() * f3);
							entityitem.motionY = (double) ((float) factoryRand
									.nextGaussian() * f3 + 0.2F);
							entityitem.motionZ = (double) ((float) factoryRand
									.nextGaussian() * f3);
							par1World.spawnEntityInWorld(entityitem);
						}
					}
				}
				par1World.func_96440_m(par2, par3, par4, par5);
			}
		}
		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3,
			int par4, int par5) {
		return Container.calcRedstoneFromInventory((IInventory) par1World
				.getBlockTileEntity(par2, par3, par4));
	}

	/**
	 * {@inheritDoc}
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public int idPicked(World par1World, int par2, int par3, int par4) {
		return getIdleId();
	}

}
