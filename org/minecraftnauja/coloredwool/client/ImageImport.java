package org.minecraftnauja.coloredwool.client;

 import java.awt.Color;
import java.awt.image.BufferedImage;

import org.minecraftnauja.coloredwool.ColoredWool;

import net.minecraft.client.Minecraft;
 
 public class ImageImport
 {
   public boolean importFinished;
   private gs player;
   private TileEntityColor entity;
   private BufferedImage image;
   private fd worldObj;
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
 
   public ImageImport(g player, TileEntityColor entity, BufferedImage image, int xOrient, int yOrient)
   {
     this.importFinished = false;
     this.player = player;
     this.entity = entity;
     this.image = image;
     this.xOrient = xOrient;
     this.yOrient = yOrient;
     this.worldObj = ModLoader.getMinecraftInstance().f;
     this.xCurrent = 0;
     this.yCurrent = 0;
     this.originX = entity.e;
     this.originY = entity.f;
     this.originZ = entity.g;
     this.imageHeight = image.getHeight();
     this.imageWidth = image.getWidth();
     findXOrientation(xOrient);
     findYOrientation(yOrient);
   }
 
   public void findXOrientation(int xOrient)
   {
     this.xxa = 0;
     this.xya = 0;
     this.xza = 0;
     switch (xOrient) {
     case 0:
       this.xxa = -1;
       break;
     case 1:
       this.xxa = 1;
       break;
     case 2:
       this.xza = -1;
       break;
     case 3:
       this.xza = 1;
       break;
     case 4:
       this.xya = 1;
       break;
     case 5:
       this.xya = -1;
       break;
     default:
       this.xxa = 1;
     }
   }
 
   public void findYOrientation(int yOrient)
   {
     this.yxa = 0;
     this.yya = 0;
     this.yza = 0;
     switch (yOrient) {
     case 0:
       this.yxa = -1;
       break;
     case 1:
       this.yxa = 1;
       break;
     case 2:
       this.yza = -1;
       break;
     case 3:
       this.yza = 1;
       break;
     case 4:
       this.yya = 1;
       break;
     case 5:
       this.yya = -1;
       break;
     default:
       this.yza = 1;
     }
   }
 
   public void imageTick()
   {
     int xOffset = 0;
     int yOffset = 0;
     int zOffset = 0;
     int aRGB = this.image.getRGB(this.xCurrent, this.yCurrent);
     Color pix = new Color(aRGB, true);
 
     if (this.xxa != 0)
       xOffset = this.xCurrent * this.xxa;
     else if (this.xya != 0)
       yOffset = this.xCurrent * this.xya;
     else if (this.xza != 0) {
       zOffset = this.xCurrent * this.xza;
     }
 
     if (this.yza != 0)
       zOffset = this.yCurrent * this.yza;
     else if (this.yxa != 0)
       xOffset = this.yCurrent * this.yxa;
     else if (this.yya != 0) {
       yOffset = this.yCurrent * this.yya;
     }
 
     if ((this.originY + yOffset < 0) || (this.originY + yOffset > 127)) {
       this.importFinished = true;
       return;
     }
 
     if (pix.getAlpha() == 255) {
       ow loadTileEntity = this.worldObj.b(this.originX + xOffset, this.originY + yOffset, this.originZ + zOffset);
       if ((loadTileEntity instanceof TileEntityColor)) {
         TileEntityColor currentBlockEntity = (TileEntityColor)loadTileEntity;
         if (currentBlockEntity != null) {
           currentBlockEntity.setColor(pix.getRed(), pix.getGreen(), pix.getBlue());
           this.worldObj.b(this.originX + xOffset, this.originY + yOffset, this.originZ + zOffset, this.originX + xOffset, this.originY + yOffset, this.originZ + zOffset);
           if (ColoredWool.isMultiplayer()) {
             currentBlockEntity.sendColorPacket();
           }
         }
       }
     }
     this.xCurrent += 1;
 
     if (this.xCurrent == this.imageWidth) {
       this.xCurrent = 0;
       this.yCurrent += 1;
       if (this.yCurrent == this.imageHeight) {
         this.yCurrent = 0;
         this.importFinished = true;
       }
     }
   }
 }