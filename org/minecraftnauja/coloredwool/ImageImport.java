package org.minecraftnauja.coloredwool;

import java.awt.Color;
import java.awt.image.BufferedImage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.minecraftnauja.coloredwool.tileentity.TileEntityColoredWool;

public class ImageImport {
	public boolean importFinished;
	private EntityPlayer player;
	private TileEntityColoredWool entity;
	private BufferedImage image;
	private World worldObj;
	private int xOrient;
	private int yOrient;
	private int xCurrent;
	private int yCurrent;
	private int originX;
	private int originY;
	private int originZ;
	private int imageWidth;
	private int imageHeight;
	private int xxa;
	private int xya;
	private int xza;
	private int yxa;
	private int yya;
	private int yza;

	public ImageImport(EntityPlayer player, TileEntityColoredWool entity,
			BufferedImage image, int xOrient, int yOrient) {
		importFinished = false;
		this.player = player;
		this.entity = entity;
		this.image = image;
		this.xOrient = xOrient;
		this.yOrient = yOrient;
		worldObj = player.worldObj;
		xCurrent = 0;
		yCurrent = 0;
		originX = entity.xCoord;
		originY = entity.yCoord;
		originZ = entity.zCoord;
		imageHeight = image.getHeight();
		imageWidth = image.getWidth();
		findXOrientation(xOrient);
		findYOrientation(yOrient);
	}

	public void findXOrientation(int xOrient) {
		xxa = 0;
		xya = 0;
		xza = 0;
		switch (xOrient) {
		case 0:
			xxa = -1;
			break;
		case 1:
			xxa = 1;
			break;
		case 2:
			xza = -1;
			break;
		case 3:
			xza = 1;
			break;
		case 4:
			xya = 1;
			break;
		case 5:
			xya = -1;
			break;
		default:
			xxa = 1;
		}
	}

	public void findYOrientation(int yOrient) {
		yxa = 0;
		yya = 0;
		yza = 0;
		switch (yOrient) {
		case 0:
			yxa = -1;
			break;
		case 1:
			yxa = 1;
			break;
		case 2:
			yza = -1;
			break;
		case 3:
			yza = 1;
			break;
		case 4:
			yya = 1;
			break;
		case 5:
			yya = -1;
			break;
		default:
			yza = 1;
		}
	}

	public void imageTick() {
		int xOffset = 0;
		int yOffset = 0;
		int zOffset = 0;
		int aRGB = image.getRGB(xCurrent, yCurrent);
		Color pix = new Color(aRGB, true);

		if (xxa != 0)
			xOffset = xCurrent * xxa;
		else if (xya != 0)
			yOffset = xCurrent * xya;
		else if (xza != 0) {
			zOffset = xCurrent * xza;
		}

		if (yza != 0)
			zOffset = yCurrent * yza;
		else if (yxa != 0)
			xOffset = yCurrent * yxa;
		else if (yya != 0) {
			yOffset = yCurrent * yya;
		}

		if ((originY + yOffset < 0) || (originY + yOffset > 127)) {
			importFinished = true;
			return;
		}

		if (pix.getAlpha() == 255) {
			TileEntity loadTileEntity = worldObj.getBlockTileEntity(originX
					+ xOffset, originY + yOffset, originZ + zOffset);
			if ((loadTileEntity instanceof TileEntityColoredWool)) {
				TileEntityColoredWool currentBlockEntity = (TileEntityColoredWool) loadTileEntity;
				if (currentBlockEntity != null) {
					currentBlockEntity.setColor(pix.getRed(), pix.getGreen(),
							pix.getBlue());
					/*
					worldObj.markBlockForRenderUpdate(originX + xOffset,
							originY + yOffset, originZ + zOffset);
					if (ColoredWool.isMultiplayer()) {
						currentBlockEntity.sendColorPacket();
					}*/
				}
			}
		}
		xCurrent += 1;

		if (xCurrent == imageWidth) {
			xCurrent = 0;
			yCurrent += 1;
			if (yCurrent == imageHeight) {
				yCurrent = 0;
				importFinished = true;
			}
		}
	}
}