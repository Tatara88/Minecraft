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
			if ((i & 8) != 0) {
				par1World.setBlockMetadataWithNotify(par2, par3, par4, (i & 7),
						3);
				par1World.playSoundEffect((double) par2 + 0.5D,
						(double) par3 + 0.5D, (double) par4 + 0.5D,
						"random.click", 0.3F, 0.6F);
				Config.rotation
						.apply(par1World, par2, par3, par4, i & 7, false);
			} else {
				par1World.setBlockMetadataWithNotify(par2, par3, par4,
						(i & 7) | 8, 3);
				par1World.playSoundEffect((double) par2 + 0.5D,
						(double) par3 + 0.5D, (double) par4 + 0.5D,
						"random.click", 0.3F, 0.5F);
				Config.rotation.apply(par1World, par2, par3, par4, i & 7, true);
			}
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4, blockID);
		}
		return true;
	}

}
