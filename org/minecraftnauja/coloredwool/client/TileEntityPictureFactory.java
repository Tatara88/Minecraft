package org.minecraftnauja.coloredwool.client;
/*     */ import java.awt.Color;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.PrintStream;

import org.minecraftnauja.coloredwool.ColoredWool;
import org.minecraftnauja.coloredwool.block.BlockColoredCloth;
/*     */ 
/*     */ public class TileEntityPictureFactory extends ow
/*     */   implements lw
/*     */ {
/*     */   protected String imageName;
/*     */   protected BufferedImage image;
/*     */   protected int imageWidth;
/*     */   protected int imageHeight;
/*     */   protected int currentLine;
/*     */   protected int currentColumn;
/*     */   protected int progressWidth;
/*     */   protected int progressHeight;
/*     */   protected iz[] dyeItemStacks;
/*     */   protected iz coalItemStack;
/*     */   public int factoryGenerationTime;
/*     */   public int factoryCookTime;
/*     */   public int factoryBurnTime;
/*     */   public int currentItemBurnTime;
/*     */   public boolean isActivated;
/*     */   public boolean isBurning;
/*     */ 
/*     */   public TileEntityPictureFactory()
/*     */   {
/*  98 */     this.imageName = "";
/*  99 */     this.dyeItemStacks = new iz[6];
/*     */   }
/*     */ 
/*     */   public static void initialise()
/*     */   {
/* 111 */     ModLoader.RegisterTileEntity(TileEntityPictureFactory.class, "Picture Factory");
/*     */   }
/*     */ 
/*     */   public int a()
/*     */   {
/* 123 */     return this.dyeItemStacks.length + 1;
/*     */   }
/*     */ 
/*     */   public iz f_(int i)
/*     */   {
/* 132 */     if (i == 6)
/* 133 */       return this.coalItemStack;
/* 134 */     if ((i >= 0) && (i < this.dyeItemStacks.length)) {
/* 135 */       return this.dyeItemStacks[i];
/*     */     }
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */   public iz a(int i, int j)
/*     */   {
/* 147 */     if (i == 6) {
/* 148 */       if (this.coalItemStack == null) {
/* 149 */         return null;
/*     */       }
/* 151 */       if (this.coalItemStack.a <= j) {
/* 152 */         iz itemstack = this.coalItemStack;
/* 153 */         this.coalItemStack = null;
/* 154 */         return itemstack;
/*     */       }
/* 156 */       iz itemstack = this.coalItemStack.a(j);
/* 157 */       if (this.coalItemStack.a == 0) {
/* 158 */         this.coalItemStack = null;
/*     */       }
/* 160 */       return itemstack;
/* 161 */     }if ((i >= 0) && (i < this.dyeItemStacks.length)) {
/* 162 */       if (this.dyeItemStacks[i] == null) {
/* 163 */         return null;
/*     */       }
/* 165 */       if (this.dyeItemStacks[i].a <= j) {
/* 166 */         iz itemstack = this.dyeItemStacks[i];
/* 167 */         this.dyeItemStacks[i] = null;
/* 168 */         return itemstack;
/*     */       }
/* 170 */       iz itemstack = this.dyeItemStacks[i].a(j);
/* 171 */       if (this.dyeItemStacks[i].a == 0) {
/* 172 */         this.dyeItemStacks[i] = null;
/*     */       }
/* 174 */       return itemstack;
/*     */     }
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */   public void a(int i, iz itemstack)
/*     */   {
/* 185 */     if (i == 6)
/* 186 */       this.coalItemStack = itemstack;
/* 187 */     else if ((i >= 0) && (i < this.dyeItemStacks.length)) {
/* 188 */       this.dyeItemStacks[i] = itemstack;
/*     */     }
/* 190 */     if ((itemstack != null) && (itemstack.a > d()))
/* 191 */       itemstack.a = d();
/*     */   }
/*     */ 
/*     */   public int d()
/*     */   {
/* 200 */     return 64;
/*     */   }
/*     */ 
/*     */   public String c()
/*     */   {
/* 208 */     return "Picture Factory";
/*     */   }
/*     */ 
/*     */   public int getBurnTimeRemainingScaled(int i)
/*     */   {
/* 217 */     if (this.currentItemBurnTime == 0) {
/* 218 */       this.currentItemBurnTime = 200;
/*     */     }
/* 220 */     return this.factoryBurnTime * i / this.currentItemBurnTime;
/*     */   }
/*     */ 
/*     */   public boolean isBurning()
/*     */   {
/* 228 */     return this.factoryBurnTime > 0;
/*     */   }
/*     */ 
/*     */   public boolean hasRedRose()
/*     */   {
/* 241 */     if (this.dyeItemStacks[0] == null) {
/* 242 */       return false;
/*     */     }
/* 244 */     return (this.dyeItemStacks[0].a().bf == gm.aU.bf) && (this.dyeItemStacks[0].i() == 1);
/*     */   }
/*     */ 
/*     */   public boolean hasCactusGreen()
/*     */   {
/* 252 */     if (this.dyeItemStacks[1] == null) {
/* 253 */       return false;
/*     */     }
/* 255 */     return (this.dyeItemStacks[1].a().bf == gm.aU.bf) && (this.dyeItemStacks[1].i() == 2);
/*     */   }
/*     */ 
/*     */   public boolean hasLapisLazuli()
/*     */   {
/* 263 */     if (this.dyeItemStacks[2] == null) {
/* 264 */       return false;
/*     */     }
/* 266 */     return (this.dyeItemStacks[2].a().bf == gm.aU.bf) && (this.dyeItemStacks[2].i() == 4);
/*     */   }
/*     */ 
/*     */   public boolean hasColoredDye()
/*     */   {
/* 274 */     if (this.dyeItemStacks[3] == null) {
/* 275 */       return false;
/*     */     }
/* 277 */     return this.dyeItemStacks[3].a().bf == ItemColoredDye.item.bf;
/*     */   }
/*     */ 
/*     */   public boolean hasWool()
/*     */   {
/* 285 */     if (this.dyeItemStacks[4] == null) {
/* 286 */       return false;
/*     */     }
/* 288 */     return this.dyeItemStacks[4].a().bf == uu.ac.bn;
/*     */   }
/*     */ 
/*     */   public boolean hasColoredWool()
/*     */   {
/* 296 */     if (this.dyeItemStacks[5] == null) {
/* 297 */       return false;
/*     */     }
/* 299 */     return this.dyeItemStacks[5].a().bf == BlockColoredCloth.blockId;
/*     */   }
/*     */ 
/*     */   public boolean[] findItemsToSmelt()
/*     */   {
/* 312 */     boolean type1 = (hasRedRose()) && (hasCactusGreen()) && (hasLapisLazuli()) && (hasWool());
/* 313 */     boolean type2 = (hasColoredDye()) && (hasWool());
/* 314 */     boolean type3 = hasColoredWool();
/* 315 */     return new boolean[] { type1, type2, type3 };
/*     */   }
/*     */ 
/*     */   public boolean canSmelt()
/*     */   {
/* 324 */     boolean[] b = findItemsToSmelt();
/* 325 */     return (b[0] != 0) || (b[1] != 0) || (b[2] != 0);
/*     */   }
/*     */ 
/*     */   public int smeltItem()
/*     */   {
/* 333 */     if (!canSmelt()) {
/* 334 */       return -1;
/*     */     }
/* 336 */     boolean[] b = findItemsToSmelt();
/* 337 */     if (b[2] != 0) {
/* 338 */       a(5, 1);
/* 339 */       return 2;
/* 340 */     }if (b[1] != 0) {
/* 341 */       a(3, 1);
/* 342 */       a(4, 1);
/* 343 */       return 1;
/*     */     }
/* 345 */     a(0, 1);
/* 346 */     a(1, 1);
/* 347 */     a(2, 1);
/* 348 */     a(4, 1);
/* 349 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean hasFuel()
/*     */   {
/* 358 */     return getItemBurnTime(this.coalItemStack) > 0;
/*     */   }
/*     */ 
/*     */   public void n_()
/*     */   {
/* 370 */     if (!this.isActivated) {
/* 371 */       return;
/*     */     }
/* 373 */     if (this.image == null) {
/* 374 */       return;
/*     */     }
/* 376 */     boolean changed = false;
/* 377 */     boolean smelt = canSmelt();
/* 378 */     if (BlockPictureFactory.dontRequireItems) {
/* 379 */       smelt = true;
/*     */     }
/* 381 */     boolean fuel = hasFuel();
/* 382 */     if (BlockPictureFactory.dontRequireFuel) {
/* 383 */       fuel = true;
/*     */     }
/*     */ 
/* 386 */     if ((this.isActivated) && (!this.isBurning) && (smelt) && (fuel)) {
/* 387 */       BlockPictureFactory.updateFactoryBlockState(true, true, this.d, this.e, this.f, this.g);
/*     */     }
/* 389 */     else if ((this.isBurning) && ((!this.isActivated) || (!fuel) || (!smelt))) {
/* 390 */       BlockPictureFactory.updateFactoryBlockState(this.isActivated, false, this.d, this.e, this.f, this.g);
/*     */     }
/* 392 */     if (this.factoryBurnTime > 0) {
/* 393 */       this.factoryBurnTime -= 1;
/*     */     }
/* 395 */     if (!ColoredWool.isMultiplayer()) {
/* 396 */       if ((this.factoryBurnTime == 0) && (smelt)) {
/* 397 */         this.factoryBurnTime = getItemBurnTime(this.coalItemStack);
/* 398 */         if (BlockPictureFactory.dontRequireFuel) {
/* 399 */           this.factoryBurnTime = 500;
/*     */         }
/* 401 */         this.currentItemBurnTime = this.factoryBurnTime;
/* 402 */         if (this.factoryBurnTime > 0) {
/* 403 */           changed = true;
/* 404 */           if (!BlockPictureFactory.dontRequireFuel) {
/* 405 */             a(6, 1);
/*     */           }
/*     */         }
/*     */       }
/* 409 */       if ((isBurning()) && (smelt)) {
/* 410 */         this.factoryCookTime += 16;
/* 411 */         if (BlockPictureFactory.instantCook) {
/* 412 */           this.factoryCookTime = 200;
/*     */         }
/* 414 */         if (this.factoryCookTime >= 200) {
/* 415 */           if (!BlockPictureFactory.dontRequireItems) {
/* 416 */             smeltItem();
/*     */           }
/* 418 */           if (generateImagePart()) {
/* 419 */             BlockPictureFactory.updateFactoryBlockState(false, false, this.d, this.e, this.f, this.g);
/* 420 */             resetImage();
/*     */           }
/* 422 */           updateProgressWidth();
/* 423 */           updateProgressHeight();
/* 424 */           changed = true;
/* 425 */           this.factoryCookTime = 0;
/*     */         }
/*     */       } else {
/* 428 */         this.factoryCookTime = 0;
/*     */       }
/* 430 */       if (!this.isBurning) {
/* 431 */         changed = true;
/* 432 */         BlockPictureFactory.updateFactoryBlockState(this.isActivated, false, this.d, this.e, this.f, this.g);
/*     */       }
/*     */     }
/* 435 */     if (changed)
/* 436 */       y_();
/*     */   }
/*     */ 
/*     */   public boolean a_(gs player)
/*     */   {
/* 446 */     if (this.d.b(this.e, this.f, this.g) != this) {
/* 447 */       return false;
/*     */     }
/* 449 */     return player.g(this.e + 0.5D, this.f + 0.5D, this.g + 0.5D) <= 64.0D;
/*     */   }
/*     */ 
/*     */   private boolean generateImagePart()
/*     */   {
/* 462 */     if ((this.currentColumn < 0) || (this.currentColumn >= this.imageWidth))
/* 463 */       return true;
/* 464 */     if ((this.currentLine < 0) || (this.currentLine >= this.imageHeight)) {
/* 465 */       return true;
/*     */     }
/* 467 */     int[] pos = nextVisiblePixel(this.currentColumn, this.currentLine);
/* 468 */     if (pos == null) {
/* 469 */       return true;
/*     */     }
/* 471 */     this.currentColumn = pos[0];
/* 472 */     this.currentLine = pos[1];
/* 473 */     int argb = pos[2];
/*     */ 
/* 475 */     TileEntityColor entity = new TileEntityColor();
/* 476 */     entity.setColor(argb >> 16 & 0xFF, argb >> 8 & 0xFF, argb & 0xFF);
/*     */ 
/* 478 */     int l = this.d.e(this.e, this.f, this.g);
/* 479 */     int x = this.e;
/* 480 */     int y = this.f;
/* 481 */     int z = this.g;
/* 482 */     if (l == 5) {
/* 483 */       x = this.e - this.imageWidth / 2 + this.currentColumn;
/* 484 */       y = this.f + 1 + (this.imageHeight - this.currentLine);
/* 485 */     } else if (l == 6) {
/* 486 */       z = this.g - this.imageWidth / 2 + this.currentColumn;
/* 487 */       y = this.f + 1 + (this.imageHeight - this.currentLine);
/* 488 */     } else if (l == 7) {
/* 489 */       x = this.e + this.imageWidth / 2 - this.currentColumn;
/* 490 */       y = this.f + 1 + (this.imageHeight - this.currentLine);
/* 491 */     } else if (l == 8) {
/* 492 */       z = this.g + this.imageWidth / 2 - this.currentColumn;
/* 493 */       y = this.f + 1 + (this.imageHeight - this.currentLine);
/* 494 */     } else if (l == 1) {
/* 495 */       z = this.g + this.imageWidth / 2 - this.currentColumn;
/* 496 */       x = this.e - 1 - (this.imageHeight - this.currentLine);
/* 497 */     } else if (l == 3) {
/* 498 */       z = this.g - this.imageWidth / 2 + this.currentColumn;
/* 499 */       x = this.e + 1 + (this.imageHeight - this.currentLine);
/* 500 */     } else if (l == 0) {
/* 501 */       x = this.e + this.imageWidth / 2 - this.currentColumn;
/* 502 */       z = this.g + 1 + (this.imageHeight - this.currentLine);
/* 503 */     } else if (l == 2) {
/* 504 */       x = this.e - this.imageWidth / 2 + this.currentColumn;
/* 505 */       z = this.g - 1 - (this.imageHeight - this.currentLine);
/*     */     }
/* 507 */     if (!blockAlreadyColored(x, y, z, entity)) {
/* 508 */       this.d.f(x, y, z, BlockColoredCloth.blockId);
/* 509 */       this.d.a(x, y, z, entity);
/* 510 */       if (this.d.a(x, y, z) != BlockColoredCloth.blockId) {
/* 511 */         return true;
/*     */       }
/*     */     }
/*     */ 
/* 515 */     this.currentColumn += 1;
/* 516 */     if (this.currentColumn >= this.imageWidth) {
/* 517 */       this.currentColumn = 0;
/* 518 */       this.currentLine -= 1;
/*     */     }
/* 520 */     return this.currentLine < 0;
/*     */   }
/*     */ 
/*     */   public boolean blockAlreadyColored(int i, int j, int k, TileEntityColor entity)
/*     */   {
/* 532 */     int id = this.d.a(i, j, k);
/* 533 */     if ((id > 0) && ((BlockPictureFactory.dontEraseAnything) || (BlockPictureFactory.dontEraseThese.contains(id + ";")))) {
/* 534 */       return true;
/*     */     }
/* 536 */     ow tmp = this.d.b(i, j, k);
/* 537 */     if (tmp == null) {
/* 538 */       return false;
/*     */     }
/* 540 */     if (!(tmp instanceof TileEntityColor)) {
/* 541 */       return false;
/*     */     }
/* 543 */     TileEntityColor tmp2 = (TileEntityColor)tmp;
/* 544 */     return tmp2.getColor().equals(entity.getColor());
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixel(int column, int line)
/*     */   {
/* 554 */     if ((column < 0) || (column >= this.imageWidth))
/* 555 */       return null;
/* 556 */     if ((line < 0) || (line >= this.imageHeight)) {
/* 557 */       return null;
/*     */     }
/* 559 */     int argb = this.image.getRGB(column, line);
/* 560 */     while ((argb >> 24 & 0xFF) < 255) {
/* 561 */       column++;
/* 562 */       if (column >= this.imageWidth) {
/* 563 */         column = 0;
/* 564 */         line--;
/*     */       }
/* 566 */       if (line < 0) {
/* 567 */         return null;
/*     */       }
/* 569 */       argb = this.image.getRGB(column, line);
/*     */     }
/* 571 */     return new int[] { column, line, argb };
/*     */   }
/*     */ 
/*     */   public void resetImage()
/*     */   {
/* 578 */     if (this.image != null)
/* 579 */       this.currentLine = (this.imageHeight - 1);
/*     */     else {
/* 581 */       this.currentLine = 0;
/*     */     }
/* 583 */     this.currentColumn = 0;
/*     */   }
/*     */ 
/*     */   public void updateProgressWidth()
/*     */   {
/* 595 */     if ((this.image == null) || (this.imageWidth < 1)) {
/* 596 */       this.progressWidth = 0;
/*     */     }
/* 598 */     this.progressWidth = ((int)(this.currentColumn / this.imageWidth * 100.0F));
/*     */   }
/*     */ 
/*     */   public void updateProgressHeight()
/*     */   {
/* 605 */     if ((this.image == null) || (this.imageHeight < 1)) {
/* 606 */       this.progressHeight = 0;
/*     */     }
/* 608 */     this.progressHeight = ((int)((this.imageHeight - (this.currentLine + 1)) / this.imageHeight * 100.0F));
/*     */   }
/*     */ 
/*     */   public int getImageProgressWidth(int i)
/*     */   {
/* 617 */     if ((this.image == null) || (this.imageWidth < 1)) {
/* 618 */       return 0;
/*     */     }
/* 620 */     return this.progressWidth * i / 100;
/*     */   }
/*     */ 
/*     */   public int getImageProgressHeight(int i)
/*     */   {
/* 629 */     if ((this.image == null) || (this.imageHeight < 1)) {
/* 630 */       return 0;
/*     */     }
/* 632 */     return this.progressHeight * i / 100;
/*     */   }
/*     */ 
/*     */   public int getCookProgressScaled(int i)
/*     */   {
/* 641 */     int p = this.factoryCookTime * i / 200;
/* 642 */     if (p < 0)
/* 643 */       return 0;
/* 644 */     if (p > 200 * i) {
/* 645 */       return 200 * i;
/*     */     }
/* 647 */     return p;
/*     */   }
/*     */ 
/*     */   protected int getItemBurnTime(iz itemstack)
/*     */   {
/* 656 */     if (itemstack == null) return 0;
/* 657 */     int i = itemstack.a().bf;
/* 658 */     if ((i < 256) && (uu.m[i].bA == ln.d)) return 300;
/* 659 */     if (i == gm.B.bf) return 100;
/* 660 */     if (i == gm.k.bf) return 1600;
/* 661 */     if (i == gm.aw.bf) return 20000;
/* 662 */     if (i == uu.z.bn) return 100;
/* 663 */     return ModLoader.AddAllFuel(i);
/*     */   }
/*     */ 
/*     */   public void b(nu nbttagcompound)
/*     */   {
/* 676 */     super.b(nbttagcompound);
/* 677 */     nbttagcompound.a("ImageName", this.imageName);
/* 678 */     nbttagcompound.a("ImageLine", this.currentLine);
/* 679 */     nbttagcompound.a("ImageColumn", this.currentColumn);
/* 680 */     nbttagcompound.a("GenerationTime", this.factoryGenerationTime);
/* 681 */     nbttagcompound.a("IsActivated", this.isActivated);
/* 682 */     nbttagcompound.a("IsBurning", this.isBurning);
/* 683 */     nbttagcompound.a("BurnTime", (short)this.factoryBurnTime);
/* 684 */     nbttagcompound.a("CookTime", (short)this.factoryCookTime);
/*     */ 
/* 686 */     sp nbttaglist = new sp();
/* 687 */     for (int i = 0; i < this.dyeItemStacks.length; i++) {
/* 688 */       if (this.dyeItemStacks[i] != null) {
/* 689 */         nu nbttagcompound1 = new nu();
/* 690 */         nbttagcompound1.a("Slot", (byte)i);
/* 691 */         this.dyeItemStacks[i].a(nbttagcompound1);
/* 692 */         nbttaglist.a(nbttagcompound1);
/*     */       }
/*     */     }
/* 695 */     if (this.coalItemStack != null) {
/* 696 */       nu nbttagcompound1 = new nu();
/* 697 */       nbttagcompound1.a("Slot", (byte)6);
/* 698 */       this.coalItemStack.a(nbttagcompound1);
/* 699 */       nbttaglist.a(nbttagcompound1);
/*     */     }
/* 701 */     nbttagcompound.a("Items", nbttaglist);
/*     */   }
/*     */ 
/*     */   public void a(nu nbttagcompound)
/*     */   {
/* 709 */     super.a(nbttagcompound);
/* 710 */     setImageToGenerate(nbttagcompound.i("ImageName"));
/* 711 */     this.currentLine = nbttagcompound.e("ImageLine");
/* 712 */     this.currentColumn = nbttagcompound.e("ImageColumn");
/* 713 */     updateProgressWidth();
/* 714 */     updateProgressHeight();
/* 715 */     this.factoryGenerationTime = nbttagcompound.e("GenerationTime");
/* 716 */     this.isActivated = nbttagcompound.m("IsActivated");
/* 717 */     this.isBurning = nbttagcompound.m("IsBurning");
/* 718 */     this.factoryBurnTime = nbttagcompound.d("BurnTime");
/* 719 */     this.factoryCookTime = nbttagcompound.d("CookTime");
/*     */ 
/* 721 */     sp nbttaglist = nbttagcompound.l("Items");
/* 722 */     this.dyeItemStacks = new iz[a()];
/* 723 */     for (int i = 0; i < nbttaglist.c(); i++) {
/* 724 */       nu nbttagcompound1 = (nu)nbttaglist.a(i);
/* 725 */       byte byte0 = nbttagcompound1.c("Slot");
/* 726 */       if (byte0 == 6) {
/* 727 */         this.coalItemStack = new iz(nbttagcompound1);
/* 728 */         System.out.println("Loaded coal stack");
/* 729 */       } else if ((byte0 >= 0) && (byte0 < this.dyeItemStacks.length)) {
/* 730 */         this.dyeItemStacks[byte0] = new iz(nbttagcompound1);
/*     */       }
/*     */     }
/* 733 */     this.currentItemBurnTime = getItemBurnTime(this.coalItemStack);
/*     */   }
/*     */ 
/*     */   public String getImageName()
/*     */   {
/* 746 */     return this.imageName;
/*     */   }
/*     */ 
/*     */   public void setImageToGenerate(String name)
/*     */   {
/* 759 */     if (name.equals(this.imageName)) {
/* 760 */       return;
/*     */     }
/* 762 */     this.imageName = name;
/* 763 */     this.currentLine = 0;
/* 764 */     this.currentColumn = 0;
/* 765 */     this.progressWidth = 0;
/* 766 */     this.progressHeight = 0;
/* 767 */     this.factoryGenerationTime = 0;
/* 768 */     if (name.equals("")) {
/* 769 */       return;
/*     */     }
/* 771 */     this.image = ColoredWool.getLocalImage(name);
/* 772 */     if (this.image == null) {
/* 773 */       return;
/*     */     }
/* 775 */     this.imageWidth = this.image.getWidth();
/* 776 */     this.imageHeight = this.image.getHeight();
/* 777 */     this.currentLine = (this.imageHeight - 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     TileEntityPictureFactory
 * JD-Core Version:    0.6.2
 */