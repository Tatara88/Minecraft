package org.minecraftnauja.coloredwool.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.minecraftnauja.coloredwool.ColoredWool;
import org.minecraftnauja.coloredwool.tileentity.TileEntityModelFactory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Model factory block.
 */
public class BlockModelFactory extends BlockFactory {

	/**
	 * Initializing constructor.
	 * 
	 * @param par1
	 *            block identifier.
	 * @param state
	 *            its state.
	 */
	public BlockModelFactory(int par1, FactoryState state) {
		super(par1, state);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getBurningId() {
		return ColoredWool.modelFactoryBurning.blockID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getActiveId() {
		return ColoredWool.modelFactoryActive.blockID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getIdleId() {
		return ColoredWool.modelFactoryIdle.blockID;
	}

	/**
	 * {@inheritDoc}
	 */
	@SideOnly(Side.CLIENT)
	@Override
	protected String getIconPrefix() {
		return "model";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityModelFactory();
	}

	/**
	 * Updates the factory state depending on whether or not it is burning.
	 * 
	 * @param burning
	 *            active state.
	 * @param world
	 *            the world.
	 * @param x
	 *            x-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @param z
	 *            z-coordinate.
	 */
	public static void updateFurnaceBlockState(boolean active, World par1World,
			int x, int y, int z) {
		int l = par1World.getBlockMetadata(x, y, z);
		TileEntity tileentity = par1World.getBlockTileEntity(x, y, z);
		keepFactoryInventory = true;
		if (active) {
			par1World
					.setBlock(x, y, z, ColoredWool.modelFactoryBurning.blockID);
		} else {
			par1World.setBlock(x, y, z, ColoredWool.modelFactoryIdle.blockID);
		}
		keepFactoryInventory = false;
		par1World.setBlockMetadataWithNotify(x, y, z, l, 2);
		if (tileentity != null) {
			tileentity.validate();
			par1World.setBlockTileEntity(x, y, z, tileentity);
		}
	}

}
