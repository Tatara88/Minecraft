package org.minecraftnauja.coloredwool.menu;

import javax.swing.JOptionPane;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import org.lwjgl.input.Keyboard;
import org.minecraftnauja.coloredwool.tileentity.TileEntityPictureFactory;

public class GuiPictureFactoryImage extends GuiScreen {

	/**
	 * Done button.
	 */
	private static final char DONE = 0;

	/**
	 * Tile entity.
	 */
	private TileEntityPictureFactory entity;

	/**
	 * Name text field.
	 */
	private GuiTextField nameButton;

	/**
	 * Done button.
	 */
	private GuiButton doneButton;

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
	 * @param entity
	 *            tile entity.
	 */
	public GuiPictureFactoryImage(TileEntityPictureFactory entity) {
		super();
		this.entity = entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.screenTitle = "Choose image to generate";
		this.nameButton = new GuiTextField(fontRenderer, this.width / 2 - 100,
				60, 200, 20);
		nameButton.setText(entity.getImageName());
		this.nameButton.setEnabled(true);
		nameButton.setFocused(true);
		this.nameButton.setMaxStringLength(42);
		buttonList.clear();
		this.doneButton = new GuiButton(DONE, this.width / 2 - 100,
				this.height / 4 + 120, "Done");
		this.buttonList.add(this.doneButton);
		checkName();
	}

	/**
	 * Closes the gui.
	 */
	public void close() {
		try {
			String name = this.nameButton.getText();
			entity.sendImageToServer(name);
			this.entity.setImageToGenerate(name);
			mc.displayGuiScreen(null);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
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
			case DONE:
				close();
				break;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void keyTyped(char par1, int par2) {
		this.nameButton.textboxKeyTyped(par1, par2);
		checkName();
		if (par1 == '\r') {
			actionPerformed(doneButton);
		}
	}

	/**
	 * Checks the name.
	 * 
	 * @return if it is valid.
	 */
	public boolean checkName() {
		String s = this.nameButton.getText();
		boolean flag = s.length() > 0;
		this.doneButton.enabled = flag;
		return flag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, screenTitle, width / 2, 40, 16777215);
		nameButton.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}

}