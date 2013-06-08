package org.minecraftnauja.secretbookshelf.block;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.minecraftnauja.secretbookshelf.Config;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSecretBookshelf extends Block {

	/**
	 * Initialising constructor.
	 * 
	 * @param par1
	 *            block's identifier.
	 */
	public BlockSecretBookshelf(int par1) {
		super(par1, Block.bookShelf.blockMaterial);
		setCreativeTab(CreativeTabs.tabRedstone);
	}

	/**
	 * {@inheritDoc}
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2) {
		return Block.bookShelf.getIcon(par1, par2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLiving par5EntityLiving, ItemStack par6ItemStack) {
		switch (MathHelper
				.floor_double((double) (par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) {
		case 0:
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
			break;
		case 1:
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
			break;
		case 2:
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
			break;
		case 3:
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
			break;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4,
			int par5, int par6) {
		if ((par6 & 8) != 0) {
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4, blockID);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean canProvidePower() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2,
			int par3, int par4, int par5) {
		return (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) != 0 ? 15
				: 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2,
			int par3, int par4, int par5) {
		return isProvidingWeakPower(par1IBlockAccess, par2, par3, par4, par5);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {
		if (!par1World.isRemote) {
			int i = par1World.getBlockMetadata(par2, par3, par4);
			System.out.print(i + " => ");
			if ((i & 8) != 0) {
				par1World.setBlockMetadataWithNotify(par2, par3, par4, (i & 7),
						3);
				System.out.println((i & 7) + " (deactivated)");
				par1World.playSoundEffect((double) par2 + 0.5D,
						(double) par3 + 0.5D, (double) par4 + 0.5D,
						"random.click", 0.3F, 0.6F);
				Config.rotation.apply(par1World, par2, par3, par4, i & 7, false);
			} else {
				par1World.setBlockMetadataWithNotify(par2, par3, par4,
						(i & 7) | 8, 3);
				System.out.println(((i & 7) | 8) + " (activated)");
				par1World.playSoundEffect((double) par2 + 0.5D,
						(double) par3 + 0.5D, (double) par4 + 0.5D,
						"random.click", 0.3F, 0.5F);
				Config.rotation.apply(par1World, par2, par3, par4, i & 7, true);
			}
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4, blockID);
		}
		return true;
	}

	/**
	 * Rotates the block at (x1, y, z1) to (x2, y, z2).
	 * 
	 * @param world
	 *            the world.
	 * @param x1
	 *            source x-coordinate.
	 * @param z1
	 *            source z-coordinate.
	 * @param x2
	 *            destination x-coordinate.
	 * @param z2
	 *            destination z-coordinate.
	 * @param y
	 *            y-coordinate.
	 */
	private void rotate(World world, int x1, int z1, int x2, int z2, int y) {
		if (canRotate(world, x1, z1, x2, z2, y)) {
			int id = world.getBlockId(x1, y, z1);
			int metadata = world.getBlockMetadata(x1, y, z1);
			world.setBlockToAir(x1, y, z1);
			world.setBlock(x2, y, z2, id, metadata, 3);
		}
	}

	/**
	 * Indicates if the block at (x1, y, z1) can be moved to (x2, y, z2).
	 * 
	 * @param world
	 *            the world.
	 * @param x1
	 *            source x-coordinate.
	 * @param z1
	 *            source z-coordinate.
	 * @param x2
	 *            destination x-coordinate.
	 * @param z2
	 *            destination z-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @return if it can be moved.
	 */
	private boolean canRotate(World world, int x1, int z1, int x2, int z2, int y) {
		// Only if source block exist, doesn't have an entity and is solid.
		// Only if destination block is air or liquid.
		return world.blockExists(x1, y, z1)
				&& !world.blockHasTileEntity(x1, y, z1)
				&& world.getBlockMaterial(x1, y, z1).isSolid()
				&& (world.isAirBlock(x2, y, z2) || world.getBlockMaterial(x2,
						y, z2).isLiquid());
	}

}
