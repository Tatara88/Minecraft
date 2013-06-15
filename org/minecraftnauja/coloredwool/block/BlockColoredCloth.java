package org.minecraftnauja.coloredwool.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import org.minecraftnauja.coloredwool.ColoredWool;
import org.minecraftnauja.coloredwool.client.GuiColoredBlockMenu;
import org.minecraftnauja.coloredwool.client.TileEntityColored;
import org.minecraftnauja.coloredwool.server.TileEntityColor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Colored cloth block.
 */
public class BlockColoredCloth extends Block {

	/**
	 * Initializing constructor.
	 * 
	 * @param par1
	 *            block identifier.
	 */
	public BlockColoredCloth(int par1) {
		super(par1, Block.cloth.blockMaterial);
		setCreativeTab(Block.cloth.getCreativeTabToDisplayOn());
	}

	/**
	 * {@inheritDoc}
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIcon(int par1, int par2) {
		return Block.cloth.getIcon(par1, par2);
	}

	/*
	 * public int b(xp iblockaccess, int i, int j, int k) { TileEntityColor
	 * tileentitycolor = (TileEntityColor) iblockaccess.b(i, j, k); if
	 * ((ColoredBlock.isMultiplayer()) && (tileentitycolor.needUpdate)) {
	 * tileentitycolor.requestUpdate(); } return
	 * tileentitycolor.getColorMultiplier(); }
	 * 
	 * public boolean a(fd world, int i, int j, int k, gs player) { if (world.B)
	 * { return true; } if (player.t()) { return false; } iz itemstack =
	 * player.c.b(); if (itemstack == null) return false; if (itemstack.a() !=
	 * ItemColoredBrush.item) { return false; } if (colorSelection == '\001') {
	 * TileEntityColor tileentitycolor = (TileEntityColor) world .b(i, j, k);
	 * tileentitycolor.changeColor(world, i, j, k); return true; } if
	 * (colorSelection == '\002') { TileEntityColor tileentitycolor1 =
	 * (TileEntityColor) world.b(i, j, k); ModLoader.OpenGUI(player, new
	 * GuiColoredBlockMenu(player, tileentitycolor1,
	 * tileentitycolor1.getColor())); return true; } return false; }
	 * 
	 * public void b(fd world, int i, int j, int k, gs player) { if (world.B) {
	 * return; } if (player.t()) { return; } iz itemstack = player.c.b(); if
	 * (itemstack == null) return; if (itemstack.a() != ItemColoredBrush.item) {
	 * return; } if (colorSelection == '\001') { TileEntityColor tileentitycolor
	 * = (TileEntityColor) world .b(i, j, k); tileentitycolor.addColor(world, i,
	 * j, k); world.j(i, j, k); } }
	 */

}