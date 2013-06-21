package org.minecraftnauja.coloredwool.menu;

import java.awt.Color;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;

import org.lwjgl.input.Keyboard;
import org.minecraftnauja.coloredwool.tileentity.TileEntityColoredWool;

/**
 * Menu for hexadecimal color.
 */
public class GuiColoredWoolHexa extends GuiScreen {

	/**
	 * Done button.
	 */
	private static final char DONE = 0;

	/**
	 * Allowed characters.
	 */
	private static final String allowedCharacters = "0123456789abcdefABCDEF";

	/**
	 * Text field.
	 */
	private GuiTextField hexaInput;

	/**
	 * Input string.
	 */
	private String hexaString;

	/**
	 * Done button.
	 */
	private GuiButton doneButton;

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
	 * Screen title.
	 */
	private String screenTitle;

	/**
	 * Update counter.
	 */
	private int updateCounter;

	/**
	 * Initializing constructor.
	 * 
	 * @param player
	 *            player.
	 * @param tileEntity
	 *            tile entity.
	 * @param color
	 *            selected color.
	 */
	public GuiColoredWoolHexa(EntityPlayer player,
			TileEntityColoredWool tileEntity, Color color) {
		super();
		this.player = player;
		this.tileEntity = tileEntity;
		setSelectedColor(color);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initGui() {
		screenTitle = "Enter an hexadecimal value";
		hexaInput = new GuiTextField(fontRenderer, width / 2 - 100, 60, 200, 20);
		hexaInput.setText(hexaString);
		hexaInput.setEnabled(true);
		hexaInput.setFocused(true);
		hexaInput.setMaxStringLength(6);
		buttonList.clear();
		doneButton = new GuiButton(DONE, width / 2 - 100, height / 4 + 120,
				"Done");
		buttonList.add(doneButton);
		doneButton.enabled = false;
		checkHexaInput();
		Keyboard.enableRepeatEvents(true);
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
	 * Checks the input.
	 */
	private void checkHexaInput() {
		doneButton.enabled = (hexaInput.getText().trim().length() == 6);
	}

	/**
	 * Validates the input.
	 * 
	 * @param hex
	 *            the input.
	 * @return valid input.
	 */
	private String validateHexaString(String hex) {
		String tmp = hex;
		for (int i = tmp.length(); i < 6; i++) {
			tmp = "0" + tmp;
		}
		return tmp;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.enabled) {
			if (par1GuiButton.id == DONE) {
				Keyboard.enableRepeatEvents(false);
				ModLoader.openGUI(player, new GuiColoredWoolMenu(player,
						tileEntity, getSelectedColor()));
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void keyTyped(char par1, int par2) {
		if (("0123456789abcdefABCDEF".indexOf(par1) >= 0) || (par2 == 14)) {
			hexaInput.textboxKeyTyped(par1, par2);
			checkHexaInput();
		} else if (par2 == 1) {
			actionPerformed(doneButton);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, screenTitle, width / 2, 40, 16777215);
		hexaInput.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}

	/**
	 * Gets the selected color.
	 * 
	 * @return the selected color.
	 */
	public Color getSelectedColor() {
		String hex = hexaInput.getText();
		hex = validateHexaString(hex);
		selectedColor = Color.decode("0x" + hex);
		return selectedColor;
	}

	/**
	 * Sets the selected color.
	 * 
	 * @param color
	 *            new value.
	 */
	public void setSelectedColor(Color color) {
		int rgb = ((char) color.getRed() << '\020')
				+ ((char) color.getGreen() << '\b') + (char) color.getBlue();
		String hex = Integer.toHexString(rgb);
		hexaString = validateHexaString(hex);
	}

}