package org.minecraftnauja.coloredwool.menu;

import java.awt.Color;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;

import org.minecraftnauja.coloredwool.tileentity.TileEntityColoredWool;

/**
 * Menu for error when importing image.
 */
public class GuiColoredWoolImportErr extends GuiScreen {

	/**
	 * Ok button.
	 */
	private static final char OK = 0;

	/**
	 * Player.
	 */
	private final EntityPlayer player;

	/**
	 * Tile entity.
	 */
	private final TileEntityColoredWool tileEntity;

	/**
	 * Selected color.
	 */
	private Color selectedColor;

	/**
	 * Error message.
	 */
	private final String errormessage;

	/**
	 * Ok button.
	 */
	private GuiButton okbutton;

	/**
	 * Initializing constructor.
	 * 
	 * @param player
	 *            player.
	 * @param tileEntity
	 *            tile entity.
	 * @param selectedColor
	 *            selected color.
	 * @param error
	 *            error message.
	 */
	public GuiColoredWoolImportErr(EntityPlayer player,
			TileEntityColoredWool tileEntity, Color selectedColor, String error) {
		this.player = player;
		this.tileEntity = tileEntity;
		this.selectedColor = selectedColor;
		this.errormessage = error;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initGui() {
		buttonList.clear();
		buttonList.add(okbutton = new GuiButton(OK, width / 2 - 35,
				height / 4 + 120, 70, 20, "OK"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		drawCenteredString(fontRenderer, errormessage, width / 2, 40, 16777215);
		super.drawScreen(par1, par2, par3);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.enabled) {
			switch (par1GuiButton.id) {
			case OK:
				ModLoader.openGUI(player, new GuiColoredWoolMenu(player,
						tileEntity, selectedColor));
				break;
			}
		}
	}

}