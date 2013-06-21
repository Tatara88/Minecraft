package org.minecraftnauja.coloredwool.client;
 import java.awt.Color;
 import java.awt.image.BufferedImage;
 import java.io.PrintStream;

import org.minecraftnauja.coloredwool.ColoredWool;
import org.minecraftnauja.coloredwool.block.BlockColoredWool;
 
 public class TileEntityPictureFactory extends ow
   implements lw
 {
   protected String imageName;
   protected BufferedImage image;
   protected int imageWidth;
   protected int imageHeight;
   protected int currentLine;
   protected int currentColumn;
   protected int progressWidth;
   protected int progressHeight;
   protected iz[] dyeItemStacks;
   protected iz coalItemStack;
   public int factoryGenerationTime;
   public int factoryCookTime;
   public int factoryBurnTime;
   public int currentItemBurnTime;
   public boolean isActivated;
   public boolean isBurning;
 
   public TileEntityPictureFactory()
   {
     this.imageName = "";
     this.dyeItemStacks = new iz[6];
   }
 
   public static void initialise()
   {
     ModLoader.RegisterTileEntity(TileEntityPictureFactory.class, "Picture Factory");
   }
 
   public int a()
   {
     return this.dyeItemStacks.length + 1;
   }
 
   public iz f_(int i)
   {
     if (i == 6)
       return this.coalItemStack;
     if ((i >= 0) && (i < this.dyeItemStacks.length)) {
       return this.dyeItemStacks[i];
     }
     return null;
   }
 
   public iz a(int i, int j)
   {
     if (i == 6) {
       if (this.coalItemStack == null) {
         return null;
       }
       if (this.coalItemStack.a <= j) {
         iz itemstack = this.coalItemStack;
         this.coalItemStack = null;
         return itemstack;
       }
       iz itemstack = this.coalItemStack.a(j);
       if (this.coalItemStack.a == 0) {
         this.coalItemStack = null;
       }
       return itemstack;
     }if ((i >= 0) && (i < this.dyeItemStacks.length)) {
       if (this.dyeItemStacks[i] == null) {
         return null;
       }
       if (this.dyeItemStacks[i].a <= j) {
         iz itemstack = this.dyeItemStacks[i];
         this.dyeItemStacks[i] = null;
         return itemstack;
       }
       iz itemstack = this.dyeItemStacks[i].a(j);
       if (this.dyeItemStacks[i].a == 0) {
         this.dyeItemStacks[i] = null;
       }
       return itemstack;
     }
     return null;
   }
 
   public void a(int i, iz itemstack)
   {
     if (i == 6)
       this.coalItemStack = itemstack;
     else if ((i >= 0) && (i < this.dyeItemStacks.length)) {
       this.dyeItemStacks[i] = itemstack;
     }
     if ((itemstack != null) && (itemstack.a > d()))
       itemstack.a = d();
   }
 
   public int d()
   {
     return 64;
   }
 
   public String c()
   {
     return "Picture Factory";
   }
 
   public int getBurnTimeRemainingScaled(int i)
   {
     if (this.currentItemBurnTime == 0) {
       this.currentItemBurnTime = 200;
     }
     return this.factoryBurnTime * i / this.currentItemBurnTime;
   }
 
   public boolean isBurning()
   {
     return this.factoryBurnTime > 0;
   }
 
   public boolean hasRedRose()
   {
     if (this.dyeItemStacks[0] == null) {
       return false;
     }
     return (this.dyeItemStacks[0].a().bf == gm.aU.bf) && (this.dyeItemStacks[0].i() == 1);
   }
 
   public boolean hasCactusGreen()
   {
     if (this.dyeItemStacks[1] == null) {
       return false;
     }
     return (this.dyeItemStacks[1].a().bf == gm.aU.bf) && (this.dyeItemStacks[1].i() == 2);
   }
 
   public boolean hasLapisLazuli()
   {
     if (this.dyeItemStacks[2] == null) {
       return false;
     }
     return (this.dyeItemStacks[2].a().bf == gm.aU.bf) && (this.dyeItemStacks[2].i() == 4);
   }
 
   public boolean hasColoredDye()
   {
     if (this.dyeItemStacks[3] == null) {
       return false;
     }
     return this.dyeItemStacks[3].a().bf == ItemColoredDye.item.bf;
   }
 
   public boolean hasWool()
   {
     if (this.dyeItemStacks[4] == null) {
       return false;
     }
     return this.dyeItemStacks[4].a().bf == uu.ac.bn;
   }
 
   public boolean hasColoredWool()
   {
     if (this.dyeItemStacks[5] == null) {
       return false;
     }
     return this.dyeItemStacks[5].a().bf == BlockColoredWool.blockId;
   }
 
   public boolean[] findItemsToSmelt()
   {
     boolean type1 = (hasRedRose()) && (hasCactusGreen()) && (hasLapisLazuli()) && (hasWool());
     boolean type2 = (hasColoredDye()) && (hasWool());
     boolean type3 = hasColoredWool();
     return new boolean[] { type1, type2, type3 };
   }
 
   public boolean canSmelt()
   {
     boolean[] b = findItemsToSmelt();
     return (b[0] != 0) || (b[1] != 0) || (b[2] != 0);
   }
 
   public int smeltItem()
   {
     if (!canSmelt()) {
       return -1;
     }
     boolean[] b = findItemsToSmelt();
     if (b[2] != 0) {
       a(5, 1);
       return 2;
     }if (b[1] != 0) {
       a(3, 1);
       a(4, 1);
       return 1;
     }
     a(0, 1);
     a(1, 1);
     a(2, 1);
     a(4, 1);
     return 0;
   }
 
   public boolean hasFuel()
   {
     return getItemBurnTime(this.coalItemStack) > 0;
   }
 
   public void n_()
   {
     if (!this.isActivated) {
       return;
     }
     if (this.image == null) {
       return;
     }
     boolean changed = false;
     boolean smelt = canSmelt();
     if (BlockPictureFactory.dontRequireItems) {
       smelt = true;
     }
     boolean fuel = hasFuel();
     if (BlockPictureFactory.dontRequireFuel) {
       fuel = true;
     }
 
     if ((this.isActivated) && (!this.isBurning) && (smelt) && (fuel)) {
       BlockPictureFactory.updateFactoryBlockState(true, true, this.d, this.e, this.f, this.g);
     }
     else if ((this.isBurning) && ((!this.isActivated) || (!fuel) || (!smelt))) {
       BlockPictureFactory.updateFactoryBlockState(this.isActivated, false, this.d, this.e, this.f, this.g);
     }
     if (this.factoryBurnTime > 0) {
       this.factoryBurnTime -= 1;
     }
     if (!ColoredWool.isMultiplayer()) {
       if ((this.factoryBurnTime == 0) && (smelt)) {
         this.factoryBurnTime = getItemBurnTime(this.coalItemStack);
         if (BlockPictureFactory.dontRequireFuel) {
           this.factoryBurnTime = 500;
         }
         this.currentItemBurnTime = this.factoryBurnTime;
         if (this.factoryBurnTime > 0) {
           changed = true;
           if (!BlockPictureFactory.dontRequireFuel) {
             a(6, 1);
           }
         }
       }
       if ((isBurning()) && (smelt)) {
         this.factoryCookTime += 16;
         if (BlockPictureFactory.instantCook) {
           this.factoryCookTime = 200;
         }
         if (this.factoryCookTime >= 200) {
           if (!BlockPictureFactory.dontRequireItems) {
             smeltItem();
           }
           if (generateImagePart()) {
             BlockPictureFactory.updateFactoryBlockState(false, false, this.d, this.e, this.f, this.g);
             resetImage();
           }
           updateProgressWidth();
           updateProgressHeight();
           changed = true;
           this.factoryCookTime = 0;
         }
       } else {
         this.factoryCookTime = 0;
       }
       if (!this.isBurning) {
         changed = true;
         BlockPictureFactory.updateFactoryBlockState(this.isActivated, false, this.d, this.e, this.f, this.g);
       }
     }
     if (changed)
       y_();
   }
 
   public boolean a_(gs player)
   {
     if (this.d.b(this.e, this.f, this.g) != this) {
       return false;
     }
     return player.g(this.e + 0.5D, this.f + 0.5D, this.g + 0.5D) <= 64.0D;
   }
 
   private boolean generateImagePart()
   {
     if ((this.currentColumn < 0) || (this.currentColumn >= this.imageWidth))
       return true;
     if ((this.currentLine < 0) || (this.currentLine >= this.imageHeight)) {
       return true;
     }
     int[] pos = nextVisiblePixel(this.currentColumn, this.currentLine);
     if (pos == null) {
       return true;
     }
     this.currentColumn = pos[0];
     this.currentLine = pos[1];
     int argb = pos[2];
 
     TileEntityColor entity = new TileEntityColor();
     entity.setColor(argb >> 16 & 0xFF, argb >> 8 & 0xFF, argb & 0xFF);
 
     int l = this.d.e(this.e, this.f, this.g);
     int x = this.e;
     int y = this.f;
     int z = this.g;
     if (l == 5) {
       x = this.e - this.imageWidth / 2 + this.currentColumn;
       y = this.f + 1 + (this.imageHeight - this.currentLine);
     } else if (l == 6) {
       z = this.g - this.imageWidth / 2 + this.currentColumn;
       y = this.f + 1 + (this.imageHeight - this.currentLine);
     } else if (l == 7) {
       x = this.e + this.imageWidth / 2 - this.currentColumn;
       y = this.f + 1 + (this.imageHeight - this.currentLine);
     } else if (l == 8) {
       z = this.g + this.imageWidth / 2 - this.currentColumn;
       y = this.f + 1 + (this.imageHeight - this.currentLine);
     } else if (l == 1) {
       z = this.g + this.imageWidth / 2 - this.currentColumn;
       x = this.e - 1 - (this.imageHeight - this.currentLine);
     } else if (l == 3) {
       z = this.g - this.imageWidth / 2 + this.currentColumn;
       x = this.e + 1 + (this.imageHeight - this.currentLine);
     } else if (l == 0) {
       x = this.e + this.imageWidth / 2 - this.currentColumn;
       z = this.g + 1 + (this.imageHeight - this.currentLine);
     } else if (l == 2) {
       x = this.e - this.imageWidth / 2 + this.currentColumn;
       z = this.g - 1 - (this.imageHeight - this.currentLine);
     }
     if (!blockAlreadyColored(x, y, z, entity)) {
       this.d.f(x, y, z, BlockColoredWool.blockId);
       this.d.a(x, y, z, entity);
       if (this.d.a(x, y, z) != BlockColoredWool.blockId) {
         return true;
       }
     }
 
     this.currentColumn += 1;
     if (this.currentColumn >= this.imageWidth) {
       this.currentColumn = 0;
       this.currentLine -= 1;
     }
     return this.currentLine < 0;
   }
 
   public boolean blockAlreadyColored(int i, int j, int k, TileEntityColor entity)
   {
     int id = this.d.a(i, j, k);
     if ((id > 0) && ((BlockPictureFactory.dontEraseAnything) || (BlockPictureFactory.dontEraseThese.contains(id + ";")))) {
       return true;
     }
     ow tmp = this.d.b(i, j, k);
     if (tmp == null) {
       return false;
     }
     if (!(tmp instanceof TileEntityColor)) {
       return false;
     }
     TileEntityColor tmp2 = (TileEntityColor)tmp;
     return tmp2.getColor().equals(entity.getColor());
   }
 
   public int[] nextVisiblePixel(int column, int line)
   {
     if ((column < 0) || (column >= this.imageWidth))
       return null;
     if ((line < 0) || (line >= this.imageHeight)) {
       return null;
     }
     int argb = this.image.getRGB(column, line);
     while ((argb >> 24 & 0xFF) < 255) {
       column++;
       if (column >= this.imageWidth) {
         column = 0;
         line--;
       }
       if (line < 0) {
         return null;
       }
       argb = this.image.getRGB(column, line);
     }
     return new int[] { column, line, argb };
   }
 
   public void resetImage()
   {
     if (this.image != null)
       this.currentLine = (this.imageHeight - 1);
     else {
       this.currentLine = 0;
     }
     this.currentColumn = 0;
   }
 
   public void updateProgressWidth()
   {
     if ((this.image == null) || (this.imageWidth < 1)) {
       this.progressWidth = 0;
     }
     this.progressWidth = ((int)(this.currentColumn / this.imageWidth * 100.0F));
   }
 
   public void updateProgressHeight()
   {
     if ((this.image == null) || (this.imageHeight < 1)) {
       this.progressHeight = 0;
     }
     this.progressHeight = ((int)((this.imageHeight - (this.currentLine + 1)) / this.imageHeight * 100.0F));
   }
 
   public int getImageProgressWidth(int i)
   {
     if ((this.image == null) || (this.imageWidth < 1)) {
       return 0;
     }
     return this.progressWidth * i / 100;
   }
 
   public int getImageProgressHeight(int i)
   {
     if ((this.image == null) || (this.imageHeight < 1)) {
       return 0;
     }
     return this.progressHeight * i / 100;
   }
 
   public int getCookProgressScaled(int i)
   {
     int p = this.factoryCookTime * i / 200;
     if (p < 0)
       return 0;
     if (p > 200 * i) {
       return 200 * i;
     }
     return p;
   }
 
   protected int getItemBurnTime(iz itemstack)
   {
     if (itemstack == null) return 0;
     int i = itemstack.a().bf;
     if ((i < 256) && (uu.m[i].bA == ln.d)) return 300;
     if (i == gm.B.bf) return 100;
     if (i == gm.k.bf) return 1600;
     if (i == gm.aw.bf) return 20000;
     if (i == uu.z.bn) return 100;
     return ModLoader.AddAllFuel(i);
   }
 
   public void b(nu nbttagcompound)
   {
     super.b(nbttagcompound);
     nbttagcompound.a("ImageName", this.imageName);
     nbttagcompound.a("ImageLine", this.currentLine);
     nbttagcompound.a("ImageColumn", this.currentColumn);
     nbttagcompound.a("GenerationTime", this.factoryGenerationTime);
     nbttagcompound.a("IsActivated", this.isActivated);
     nbttagcompound.a("IsBurning", this.isBurning);
     nbttagcompound.a("BurnTime", (short)this.factoryBurnTime);
     nbttagcompound.a("CookTime", (short)this.factoryCookTime);
 
     sp nbttaglist = new sp();
     for (int i = 0; i < this.dyeItemStacks.length; i++) {
       if (this.dyeItemStacks[i] != null) {
         nu nbttagcompound1 = new nu();
         nbttagcompound1.a("Slot", (byte)i);
         this.dyeItemStacks[i].a(nbttagcompound1);
         nbttaglist.a(nbttagcompound1);
       }
     }
     if (this.coalItemStack != null) {
       nu nbttagcompound1 = new nu();
       nbttagcompound1.a("Slot", (byte)6);
       this.coalItemStack.a(nbttagcompound1);
       nbttaglist.a(nbttagcompound1);
     }
     nbttagcompound.a("Items", nbttaglist);
   }
 
   public void a(nu nbttagcompound)
   {
     super.a(nbttagcompound);
     setImageToGenerate(nbttagcompound.i("ImageName"));
     this.currentLine = nbttagcompound.e("ImageLine");
     this.currentColumn = nbttagcompound.e("ImageColumn");
     updateProgressWidth();
     updateProgressHeight();
     this.factoryGenerationTime = nbttagcompound.e("GenerationTime");
     this.isActivated = nbttagcompound.m("IsActivated");
     this.isBurning = nbttagcompound.m("IsBurning");
     this.factoryBurnTime = nbttagcompound.d("BurnTime");
     this.factoryCookTime = nbttagcompound.d("CookTime");
 
     sp nbttaglist = nbttagcompound.l("Items");
     this.dyeItemStacks = new iz[a()];
     for (int i = 0; i < nbttaglist.c(); i++) {
       nu nbttagcompound1 = (nu)nbttaglist.a(i);
       byte byte0 = nbttagcompound1.c("Slot");
       if (byte0 == 6) {
         this.coalItemStack = new iz(nbttagcompound1);
         System.out.println("Loaded coal stack");
       } else if ((byte0 >= 0) && (byte0 < this.dyeItemStacks.length)) {
         this.dyeItemStacks[byte0] = new iz(nbttagcompound1);
       }
     }
     this.currentItemBurnTime = getItemBurnTime(this.coalItemStack);
   }
 
   public String getImageName()
   {
     return this.imageName;
   }
 
   public void setImageToGenerate(String name)
   {
     if (name.equals(this.imageName)) {
       return;
     }
     this.imageName = name;
     this.currentLine = 0;
     this.currentColumn = 0;
     this.progressWidth = 0;
     this.progressHeight = 0;
     this.factoryGenerationTime = 0;
     if (name.equals("")) {
       return;
     }
     this.image = ColoredWool.getLocalImage(name);
     if (this.image == null) {
       return;
     }
     this.imageWidth = this.image.getWidth();
     this.imageHeight = this.image.getHeight();
     this.currentLine = (this.imageHeight - 1);
   }
 }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     TileEntityPictureFactory
 * JD-Core Version:    0.6.2
 */