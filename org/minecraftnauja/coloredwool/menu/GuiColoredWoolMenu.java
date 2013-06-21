package org.minecraftnauja.coloredwool.menu;

import java.awt.Color;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;

import org.lwjgl.input.Keyboard;
import org.minecraftnauja.coloredwool.tileentity.TileEntityColoredWool;

/**
 * Menu for the colored wool.
 */
public class GuiColoredWoolMenu extends GuiScreen {

	/**
	 * Hexadecimal button.
	 */
	private static final int HEXA = 0;

	/**
	 * Saved colors button.
	 */
	private static final int SAVED_COLORS = 1;

	/**
	 * Last color button.
	 */
	private static final int LAST_COLOR = 2;

	/**
	 * Import image button.
	 */
	private static final int IMPORT_IMAGE = 3;

	/**
	 * Done button.
	 */
	private static final int DONE = 4;

	/**
	 * Player.
	 */
	protected EntityPlayer player;

	/**
	 * Tile entity.
	 */
	protected TileEntityColoredWool tileEntity;

	/**
	 * Last selected color.
	 */
	protected static Color lastColor;

	/**
	 * Selected color.
	 */
	protected Color selectedColor;
	protected String screenTitle;
	private int updateCounter;
	protected GuiButton lastColorButton;

	/**
	 * Initializing constructor.
	 * 
	 * @param player
	 *            player.
	 * @param tileEntity
	 *            the tile entity.
	 * @param color
	 *            selected color.
	 */
	public GuiColoredWoolMenu(EntityPlayer player,
			TileEntityColoredWool tileEntity, Color color) {
		this.player = player;
		this.tileEntity = tileEntity;
		this.selectedColor = color;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(false);
		screenTitle = "Choose how to select the color";
		buttonList.clear();
		buttonList.add(new GuiButton(HEXA, width / 2 - 100, 60, "Hexa"));
		buttonList.add(new GuiButton(SAVED_COLORS, width / 2 - 100, 85,
				"Saved colors"));
		lastColorButton = new GuiButton(LAST_COLOR, width / 2 - 100, 110,
				"Last color");
		buttonList.add(new GuiButton(IMPORT_IMAGE, width / 2 - 100, 135,
				"Import Image"));
		lastColorButton.enabled = (lastColor != null);
		buttonList.add(lastColorButton);
		buttonList.add(new GuiButton(DONE, width / 2 - 100, height / 4 + 120,
				"Done"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		if (selectedColor != null) {
			lastColor = selectedColor;
			tileEntity.sendColorToServer(selectedColor.getRGB());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateScreen() {
		super.updateScreen();
		updateCounter += 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.enabled) {
			switch (par1GuiButton.id) {
			case HEXA:
				ModLoader.openGUI(player, new GuiColoredWoolHexa(player,
						tileEntity, selectedColor));
				break;
			case SAVED_COLORS:
				ModLoader.openGUI(player, new GuiColoredWoolSavedColors(player,
						tileEntity, selectedColor));
				break;
			case LAST_COLOR:
				selectedColor = lastColor;
			case DONE:
				mc.displayGuiScreen(null);
				break;
			case IMPORT_IMAGE:
				ModLoader.openGUI(player, new GuiColoredWoolImport(player,
						tileEntity, selectedColor));
				break;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, screenTitle, width / 2, 40, 16777215);
		super.drawScreen(par1, par2, par3);
	}

}