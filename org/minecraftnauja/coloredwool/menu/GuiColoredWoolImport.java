package org.minecraftnauja.coloredwool.menu;

import java.awt.Color;
import java.awt.image.BufferedImage;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;

import org.lwjgl.input.Keyboard;
import org.minecraftnauja.coloredwool.ColoredWool;
import org.minecraftnauja.coloredwool.ImageImport;
import org.minecraftnauja.coloredwool.tileentity.TileEntityColoredWool;

/**
 * Menu for importing images.
 */
public class GuiColoredWoolImport extends GuiScreen {

	/**
	 * Start button.
	 */
	private static final char START = 0;

	/**
	 * Cancel button.
	 */
	private static final char CANCEL = 1;

	/**
	 * X-orientation.
	 */
	private static final char X_ORIENTATION = 2;

	/**
	 * Y-orientation.
	 */
	private static final char Y_ORIENTATION = 3;

	/**
	 * Player.
	 */
	protected EntityPlayer player;

	/**
	 * Tile tileEntity.
	 */
	protected TileEntityColoredWool tileEntity;

	/**
	 * Selected color.
	 */
	protected Color selectedColor;

	/**
	 * Text field.
	 */
	private GuiTextField fileInput;

	/**
	 * Input string.
	 */
	private String fileInputString;

	/**
	 * Start button.
	 */
	private GuiButton startButton;

	/**
	 * Cancel button.
	 */
	private GuiButton cancelButton;

	/**
	 * X-orientation button.
	 */
	private GuiButton xOrientButton;

	/**
	 * Y-orientation button.
	 */
	private GuiButton yOrientButton;

	/**
	 * X-orientation.
	 */
	private int xOrient;

	/**
	 * Y-orientation.
	 */
	private int yOrient;

	/**
	 * Initializing constructor.
	 * 
	 * @param player
	 *            player.
	 * @param tileEntity
	 *            the tile tileEntity.
	 * @param color
	 *            selected color.
	 */
	public GuiColoredWoolImport(EntityPlayer player,
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
		fileInput = new GuiTextField(fontRenderer, width / 2 - 100, 60, 200, 20);
		fileInput.setEnabled(true);
		fileInput.setFocused(true);
		fileInput.setText("");
		buttonList.clear();
		buttonList.add(startButton = new GuiButton(START, width / 2 - 80,
				height / 4 + 120, 70, 20, "Import"));
		buttonList.add(cancelButton = new GuiButton(CANCEL, width / 2 + 10,
				height / 4 + 120, 70, 20, "Cancel"));
		buttonList.add(xOrientButton = new GuiButton(X_ORIENTATION,
				width / 2 - 110, height / 4 + 60, 100, 20, "North"));
		buttonList.add(yOrientButton = new GuiButton(Y_ORIENTATION,
				width / 2 + 10, height / 4 + 60, 100, 20, "North"));
		Keyboard.enableRepeatEvents(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, "Type the image file name to import",
				width / 2, 40, 16777215);
		drawCenteredString(fontRenderer, "X Orientation:", width / 2 - 60,
				height / 4 + 40, 16777215);
		drawCenteredString(fontRenderer, "Y Orientation:", width / 2 + 60,
				height / 4 + 40, 16777215);
		fileInput.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.enabled) {
			switch (par1GuiButton.id) {
			case START:
				startImport();
				break;
			case CANCEL:
				Keyboard.enableRepeatEvents(false);
				ModLoader.openGUI(player, new GuiColoredWoolMenu(player,
						tileEntity, selectedColor));
				break;
			case X_ORIENTATION:
				xOrient += 1;
				xOrient %= 6;
				String orient = getDirection(xOrient);
				if (orient == "Unknown") {
					xOrient = 0;
					orient = "North";
				}
				xOrientButton.displayString = orient;
				break;
			case Y_ORIENTATION:
				yOrient += 1;
				yOrient %= 6;
				String orient2 = getDirection(yOrient);
				if (orient2 == "Unknown") {
					yOrient = 0;
					orient2 = "North";
				}
				yOrientButton.displayString = orient2;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void keyTyped(char par1, int par2) {
		if (fileInput.isFocused()) {
			fileInput.textboxKeyTyped(par1, par2);
		}
	}

	/**
	 * Gets the name of given orientation.
	 * 
	 * @param orient
	 *            the orientation.
	 * @return the name.
	 */
	private String getDirection(int orient) {
		switch (orient) {
		case 0:
			return "North";
		case 1:
			return "South";
		case 2:
			return "East";
		case 3:
			return "West";
		case 4:
			return "Up";
		case 5:
			return "Down";
		}
		return "Unknown";
	}

	/**
	 * Starts the import.
	 */
	private void startImport() {
		if ((xOrient == yOrient)
				|| ((xOrient == yOrient - 1) && (yOrient % 2 == 1))
				|| ((yOrient == xOrient - 1) && (xOrient % 2 == 1))) {
			ModLoader.openGUI(player, new GuiColoredWoolImportErr(player,
					tileEntity, selectedColor,
					"ERROR: Image axis must use different orientation axes!"));
			return;
		}

		fileInputString = fileInput.getText();
		BufferedImage image = ColoredWool.getLocalImage(fileInputString);
		if (image == null) {
			ModLoader.openGUI(player, new GuiColoredWoolImportErr(player,
					tileEntity, selectedColor, "ERROR: Image load failed."));
			return;
		}
		int test = image.getHeight();
		ColoredWool.imageImport = new ImageImport(player, tileEntity, image,
				xOrient, yOrient);
		mc.displayGuiScreen(null);
	}

}