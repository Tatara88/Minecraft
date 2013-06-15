/*     */ import java.awt.Color;
/*     */ import java.awt.image.BufferedImage;
/*     */ 
/*     */ public class TileEntityModelFactory extends jh
/*     */   implements hp
/*     */ {
/*     */   protected String imageName;
/*     */   protected BufferedImage imageTop;
/*     */   protected BufferedImage imageBottom;
/*     */   protected BufferedImage imageLeft;
/*     */   protected BufferedImage imageRight;
/*     */   protected BufferedImage imageFront;
/*     */   protected BufferedImage imageBack;
/*     */   protected int imageWidth;
/*     */   protected int imageHeight;
/*     */   protected int imageDepth;
/*     */   protected int currentX;
/*     */   protected int currentY;
/*     */   protected int currentZ;
/*     */   protected int progressWidth;
/*     */   protected int progressHeight;
/*     */   protected fy[] dyeItemStacks;
/*     */   protected fy coalItemStack;
/*     */   public int factoryGenerationTime;
/*     */   public int factoryCookTime;
/*     */   public int factoryBurnTime;
/*     */   public int currentItemBurnTime;
/*     */   public boolean isActivated;
/*     */   public boolean isBurning;
/*     */ 
/*     */   public TileEntityModelFactory()
/*     */   {
/* 126 */     this.imageName = "";
/* 127 */     this.dyeItemStacks = new fy[6];
/*     */   }
/*     */ 
/*     */   public static void initialise()
/*     */   {
/* 139 */     ModLoader.RegisterTileEntity(TileEntityModelFactory.class, "Model Factory");
/*     */   }
/*     */ 
/*     */   public int a()
/*     */   {
/* 151 */     return this.dyeItemStacks.length + 1;
/*     */   }
/*     */ 
/*     */   public fy d_(int i)
/*     */   {
/* 160 */     if (i == 6)
/* 161 */       return this.coalItemStack;
/* 162 */     if ((i >= 0) && (i < this.dyeItemStacks.length)) {
/* 163 */       return this.dyeItemStacks[i];
/*     */     }
/* 165 */     return null;
/*     */   }
/*     */ 
/*     */   public fy a(int i, int j)
/*     */   {
/* 175 */     if (i == 6) {
/* 176 */       if (this.coalItemStack == null) {
/* 177 */         return null;
/*     */       }
/* 179 */       if (this.coalItemStack.a <= j) {
/* 180 */         fy itemstack = this.coalItemStack;
/* 181 */         this.coalItemStack = null;
/* 182 */         return itemstack;
/*     */       }
/* 184 */       fy itemstack = this.coalItemStack.a(j);
/* 185 */       if (this.coalItemStack.a == 0) {
/* 186 */         this.coalItemStack = null;
/*     */       }
/* 188 */       return itemstack;
/* 189 */     }if ((i >= 0) && (i < this.dyeItemStacks.length)) {
/* 190 */       if (this.dyeItemStacks[i] == null) {
/* 191 */         return null;
/*     */       }
/* 193 */       if (this.dyeItemStacks[i].a <= j) {
/* 194 */         fy itemstack = this.dyeItemStacks[i];
/* 195 */         this.dyeItemStacks[i] = null;
/* 196 */         return itemstack;
/*     */       }
/* 198 */       fy itemstack = this.dyeItemStacks[i].a(j);
/* 199 */       if (this.dyeItemStacks[i].a == 0) {
/* 200 */         this.dyeItemStacks[i] = null;
/*     */       }
/* 202 */       return itemstack;
/*     */     }
/* 204 */     return null;
/*     */   }
/*     */ 
/*     */   public void a(int i, fy itemstack)
/*     */   {
/* 213 */     if (i == 6)
/* 214 */       this.coalItemStack = itemstack;
/* 215 */     else if ((i >= 0) && (i < this.dyeItemStacks.length)) {
/* 216 */       this.dyeItemStacks[i] = itemstack;
/*     */     }
/* 218 */     if ((itemstack != null) && (itemstack.a > d()))
/* 219 */       itemstack.a = d();
/*     */   }
/*     */ 
/*     */   public int d()
/*     */   {
/* 228 */     return 64;
/*     */   }
/*     */ 
/*     */   public String c()
/*     */   {
/* 236 */     return "Picture Factory";
/*     */   }
/*     */ 
/*     */   public boolean isBurning()
/*     */   {
/* 244 */     return this.factoryBurnTime > 0;
/*     */   }
/*     */ 
/*     */   public boolean hasRedRose()
/*     */   {
/* 257 */     if (this.dyeItemStacks[0] == null) {
/* 258 */       return false;
/*     */     }
/* 260 */     return (this.dyeItemStacks[0].a().bf == ej.aU.bf) && (this.dyeItemStacks[0].h() == 1);
/*     */   }
/*     */ 
/*     */   public boolean hasCactusGreen()
/*     */   {
/* 268 */     if (this.dyeItemStacks[1] == null) {
/* 269 */       return false;
/*     */     }
/* 271 */     return (this.dyeItemStacks[1].a().bf == ej.aU.bf) && (this.dyeItemStacks[1].h() == 2);
/*     */   }
/*     */ 
/*     */   public boolean hasLapisLazuli()
/*     */   {
/* 279 */     if (this.dyeItemStacks[2] == null) {
/* 280 */       return false;
/*     */     }
/* 282 */     return (this.dyeItemStacks[2].a().bf == ej.aU.bf) && (this.dyeItemStacks[2].h() == 4);
/*     */   }
/*     */ 
/*     */   public boolean hasColoredDye()
/*     */   {
/* 290 */     if (this.dyeItemStacks[3] == null) {
/* 291 */       return false;
/*     */     }
/* 293 */     return this.dyeItemStacks[3].a().bf == ItemColoredDye.item.bf;
/*     */   }
/*     */ 
/*     */   public boolean hasWool()
/*     */   {
/* 301 */     if (this.dyeItemStacks[4] == null) {
/* 302 */       return false;
/*     */     }
/* 304 */     return this.dyeItemStacks[4].a().bf == na.ac.bn;
/*     */   }
/*     */ 
/*     */   public boolean hasColoredWool()
/*     */   {
/* 312 */     if (this.dyeItemStacks[5] == null) {
/* 313 */       return false;
/*     */     }
/* 315 */     return this.dyeItemStacks[5].a().bf == BlockColor.blockId;
/*     */   }
/*     */ 
/*     */   public boolean[] findItemsToSmelt()
/*     */   {
/* 328 */     boolean type1 = (hasRedRose()) && (hasCactusGreen()) && (hasLapisLazuli()) && (hasWool());
/* 329 */     boolean type2 = (hasColoredDye()) && (hasWool());
/* 330 */     boolean type3 = hasColoredWool();
/* 331 */     return new boolean[] { type1, type2, type3 };
/*     */   }
/*     */ 
/*     */   public boolean canSmelt()
/*     */   {
/* 340 */     boolean[] b = findItemsToSmelt();
/* 341 */     return (b[0] != 0) || (b[1] != 0) || (b[2] != 0);
/*     */   }
/*     */ 
/*     */   public int smeltItem()
/*     */   {
/* 349 */     if (!canSmelt()) {
/* 350 */       return -1;
/*     */     }
/* 352 */     boolean[] b = findItemsToSmelt();
/* 353 */     if (b[2] != 0) {
/* 354 */       a(5, 1);
/* 355 */       return 2;
/* 356 */     }if (b[1] != 0) {
/* 357 */       a(3, 1);
/* 358 */       a(4, 1);
/* 359 */       return 1;
/*     */     }
/* 361 */     a(0, 1);
/* 362 */     a(1, 1);
/* 363 */     a(2, 1);
/* 364 */     a(4, 1);
/* 365 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean hasFuel()
/*     */   {
/* 374 */     return getItemBurnTime(this.coalItemStack) > 0;
/*     */   }
/*     */ 
/*     */   public void g_()
/*     */   {
/* 386 */     if (!this.isActivated) {
/* 387 */       return;
/*     */     }
/* 389 */     if (this.imageTop == null) {
/* 390 */       return;
/*     */     }
/* 392 */     boolean changed = false;
/* 393 */     boolean smelt = canSmelt();
/* 394 */     if (BlockModelFactory.dontRequireItems) {
/* 395 */       smelt = true;
/*     */     }
/* 397 */     boolean fuel = hasFuel();
/* 398 */     if (BlockModelFactory.dontRequireFuel) {
/* 399 */       fuel = true;
/*     */     }
/*     */ 
/* 402 */     if ((this.isActivated) && (!this.isBurning) && (smelt) && (fuel)) {
/* 403 */       BlockModelFactory.updateFactoryBlockState(true, true, this.d, this.e, this.f, this.g);
/*     */     }
/* 405 */     else if ((this.isBurning) && ((!this.isActivated) || (!fuel) || (!smelt))) {
/* 406 */       BlockModelFactory.updateFactoryBlockState(this.isActivated, false, this.d, this.e, this.f, this.g);
/*     */     }
/* 408 */     if (this.factoryBurnTime > 0) {
/* 409 */       this.factoryBurnTime -= 1;
/*     */     }
/* 411 */     if (!mod_ColoredBlock.isSingleplayer()) {
/* 412 */       if ((this.factoryBurnTime == 0) && (smelt)) {
/* 413 */         this.factoryBurnTime = getItemBurnTime(this.coalItemStack);
/* 414 */         if (BlockModelFactory.dontRequireFuel) {
/* 415 */           this.factoryBurnTime = 500;
/*     */         }
/* 417 */         this.currentItemBurnTime = this.factoryBurnTime;
/* 418 */         if (this.factoryBurnTime > 0) {
/* 419 */           changed = true;
/* 420 */           if (!BlockModelFactory.dontRequireFuel) {
/* 421 */             a(6, 1);
/*     */           }
/*     */         }
/*     */       }
/* 425 */       if ((isBurning()) && (smelt)) {
/* 426 */         this.factoryCookTime += 16;
/* 427 */         if (BlockModelFactory.instantCook) {
/* 428 */           this.factoryCookTime = 200;
/*     */         }
/* 430 */         if (this.factoryCookTime >= 200) {
/* 431 */           if (!BlockModelFactory.dontRequireItems) {
/* 432 */             smeltItem();
/*     */           }
/* 434 */           if (generateImagePart()) {
/* 435 */             BlockModelFactory.updateFactoryBlockState(false, false, this.d, this.e, this.f, this.g);
/* 436 */             resetImage();
/*     */           }
/* 438 */           updateProgressWidth();
/* 439 */           updateProgressHeight();
/* 440 */           changed = true;
/* 441 */           this.factoryCookTime = 0;
/*     */         }
/*     */       } else {
/* 444 */         this.factoryCookTime = 0;
/*     */       }
/* 446 */       if (!this.isBurning) {
/* 447 */         changed = true;
/* 448 */         BlockModelFactory.updateFactoryBlockState(this.isActivated, false, this.d, this.e, this.f, this.g);
/*     */       }
/*     */     }
/* 451 */     if (changed)
/* 452 */       i();
/*     */   }
/*     */ 
/*     */   public boolean a_(em player)
/*     */   {
/* 462 */     if (this.d.b(this.e, this.f, this.g) != this) {
/* 463 */       return false;
/*     */     }
/* 465 */     return player.e(this.e + 0.5D, this.f + 0.5D, this.g + 0.5D) <= 64.0D;
/*     */   }
/*     */ 
/*     */   private boolean generateImagePart()
/*     */   {
/* 478 */     if ((this.currentX < 0) || (this.currentX >= this.imageWidth))
/* 479 */       return true;
/* 480 */     if ((this.currentY < 0) || (this.currentY >= this.imageHeight))
/* 481 */       return true;
/* 482 */     if ((this.currentZ < 0) || (this.currentZ >= this.imageDepth)) {
/* 483 */       return true;
/*     */     }
/* 485 */     int[] pos = nextVisiblePixel(this.currentX, this.currentY, this.currentZ);
/* 486 */     if (pos == null) {
/* 487 */       return true;
/*     */     }
/* 489 */     this.currentX = pos[0];
/* 490 */     this.currentY = pos[1];
/* 491 */     this.currentZ = pos[2];
/* 492 */     int argb = pos[3];
/*     */ 
/* 494 */     TileEntityColor entity = new TileEntityColor();
/* 495 */     entity.setColor(argb >> 16 & 0xFF, argb >> 8 & 0xFF, argb & 0xFF);
/*     */ 
/* 497 */     int l = this.d.c(this.e, this.f, this.g);
/* 498 */     int x = this.e;
/* 499 */     int y = this.f + 1;
/* 500 */     int z = this.g;
/* 501 */     if (l == 5) {
/* 502 */       x = this.e - this.imageWidth / 2 + this.currentX;
/* 503 */       y = this.f + 1 + (this.imageHeight - this.currentY);
/* 504 */       z = this.g + this.imageDepth / 2 - this.currentZ;
/* 505 */     } else if (l == 6) {
/* 506 */       x = this.e - this.imageDepth / 2 + this.currentZ;
/* 507 */       y = this.f + 1 + (this.imageHeight - this.currentY);
/* 508 */       z = this.g - this.imageWidth / 2 + this.currentX;
/* 509 */     } else if (l == 7) {
/* 510 */       x = this.e + this.imageWidth / 2 - this.currentX;
/* 511 */       y = this.f + 1 + (this.imageHeight - this.currentY);
/* 512 */       z = this.g - this.imageDepth / 2 + this.currentZ;
/* 513 */     } else if (l == 8) {
/* 514 */       x = this.e + this.imageDepth / 2 - this.currentZ;
/* 515 */       y = this.f + 1 + (this.imageHeight - this.currentY);
/* 516 */       z = this.g + this.imageWidth / 2 - this.currentX;
/* 517 */     } else if (l == 1) {
/* 518 */       x = this.e - 2 - this.currentZ;
/* 519 */       y = this.f + this.imageHeight / 2 - this.currentY;
/* 520 */       z = this.g + this.imageWidth / 2 - this.currentX;
/* 521 */     } else if (l == 3) {
/* 522 */       x = this.e + 2 + this.currentZ;
/* 523 */       y = this.f + this.imageHeight / 2 - this.currentY;
/* 524 */       z = this.g - this.imageWidth / 2 + this.currentX;
/* 525 */     } else if (l == 0) {
/* 526 */       x = this.e + this.imageWidth / 2 - this.currentX;
/* 527 */       y = this.f + this.imageHeight / 2 - this.currentY;
/* 528 */       z = this.g + 2 + this.currentZ;
/* 529 */     } else if (l == 2) {
/* 530 */       x = this.e - this.imageWidth / 2 + this.currentX;
/* 531 */       y = this.f + this.imageHeight / 2 - this.currentY;
/* 532 */       z = this.g - 2 - this.currentZ;
/*     */     }
/*     */ 
/* 535 */     if (!blockAlreadyColored(x, y, z, entity)) {
/* 536 */       this.d.e(x, y, z, BlockColor.blockId);
/* 537 */       this.d.a(x, y, z, entity);
/* 538 */       if (this.d.a(x, y, z) != BlockColor.blockId) {
/* 539 */         return true;
/*     */       }
/*     */     }
/*     */ 
/* 543 */     this.currentZ -= 1;
/* 544 */     if (this.currentZ < 0) {
/* 545 */       this.currentZ = (this.imageDepth - 1);
/* 546 */       this.currentX += 1;
/* 547 */       if (this.currentX >= this.imageWidth) {
/* 548 */         this.currentX = 0;
/* 549 */         this.currentY -= 1;
/* 550 */         if (this.currentY < 0) {
/* 551 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 555 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean blockAlreadyColored(int i, int j, int k, TileEntityColor entity)
/*     */   {
/* 567 */     int id = this.d.a(i, j, k);
/* 568 */     if ((id > 0) && ((BlockModelFactory.dontEraseAnything) || (BlockModelFactory.dontEraseThese.contains(id + ";")))) {
/* 569 */       return true;
/*     */     }
/* 571 */     jh tmp = this.d.b(i, j, k);
/* 572 */     if (tmp == null) {
/* 573 */       return false;
/*     */     }
/* 575 */     if (!(tmp instanceof TileEntityColor)) {
/* 576 */       return false;
/*     */     }
/* 578 */     TileEntityColor tmp2 = (TileEntityColor)tmp;
/* 579 */     return tmp2.getColor().equals(entity.getColor());
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixel(int x, int y, int z)
/*     */   {
/* 590 */     if ((x < 0) || (x >= this.imageWidth))
/* 591 */       return null;
/* 592 */     if ((y < 0) || (y >= this.imageHeight))
/* 593 */       return null;
/* 594 */     if ((z < 0) || (z >= this.imageDepth)) {
/* 595 */       return null;
/*     */     }
/*     */ 
/* 598 */     if (z == 0)
/* 599 */       return nextVisiblePixelOnFront(x, y, z);
/* 600 */     if (z == this.imageDepth - 1) {
/* 601 */       return nextVisiblePixelOnBack(x, y, z);
/*     */     }
/* 603 */     return nextVisiblePixelOnSide(x, y, z);
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnFront(int x, int y, int z)
/*     */   {
/* 615 */     int argb = this.imageFront.getRGB(x, y);
/* 616 */     if ((argb >> 24 & 0xFF) == 255) {
/* 617 */       return new int[] { x, y, z, argb };
/*     */     }
/* 619 */     x++;
/* 620 */     if (x >= this.imageWidth) {
/* 621 */       x = 0;
/* 622 */       y--;
/* 623 */       if (y < 0) {
/* 624 */         return null;
/*     */       }
/*     */     }
/* 627 */     return nextVisiblePixelOnBack(x, y, this.imageDepth - 1);
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnBack(int x, int y, int z)
/*     */   {
/* 639 */     int argb = this.imageBack.getRGB(this.imageWidth - (x + 1), y);
/* 640 */     if ((argb >> 24 & 0xFF) == 255) {
/* 641 */       return new int[] { x, y, z, argb };
/*     */     }
/* 643 */     return nextVisiblePixelOnFront(x, y, 0);
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnSide(int x, int y, int z)
/*     */   {
/* 655 */     if (x == 0)
/* 656 */       return nextVisiblePixelOnLeft(x, y, z);
/* 657 */     if (x == this.imageWidth - 1)
/* 658 */       return nextVisiblePixelOnRight(x, y, z);
/* 659 */     if (y == 0)
/* 660 */       return nextVisiblePixelOnTop(x, y, z);
/* 661 */     if (y == this.imageHeight - 1) {
/* 662 */       return nextVisiblePixelOnBottom(x, y, z);
/*     */     }
/* 664 */     return nextVisiblePixel(x, y, 0);
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnTop(int x, int y, int z)
/*     */   {
/* 676 */     int argb = this.imageTop.getRGB(x, this.imageDepth - (z + 1));
/* 677 */     while ((argb >> 24 & 0xFF) < 255) {
/* 678 */       z--;
/* 679 */       if (z < 0) {
/* 680 */         z = this.imageDepth - 1;
/* 681 */         x++;
/* 682 */         if (x >= this.imageWidth) {
/* 683 */           return null;
/*     */         }
/*     */       }
/* 686 */       argb = this.imageTop.getRGB(x, this.imageDepth - (z + 1));
/*     */     }
/* 688 */     return new int[] { x, y, z, argb };
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnBottom(int x, int y, int z)
/*     */   {
/* 699 */     int argb = this.imageBottom.getRGB(x, z);
/* 700 */     while ((argb >> 24 & 0xFF) < 255) {
/* 701 */       z--;
/* 702 */       if (z < 0) {
/* 703 */         z = this.imageDepth - 1;
/* 704 */         x++;
/* 705 */         if (x >= this.imageWidth) {
/* 706 */           return nextVisiblePixel(0, y - 1, z);
/*     */         }
/*     */       }
/* 709 */       argb = this.imageBottom.getRGB(x, z);
/*     */     }
/* 711 */     return new int[] { x, y, z, argb };
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnLeft(int x, int y, int z)
/*     */   {
/* 722 */     int argb = this.imageLeft.getRGB(this.imageDepth - (z + 1), y);
/* 723 */     while ((argb >> 24 & 0xFF) < 255) {
/* 724 */       z--;
/* 725 */       if (z < 0) {
/* 726 */         return nextVisiblePixel(x + 1, y, this.imageDepth - 1);
/*     */       }
/* 728 */       argb = this.imageLeft.getRGB(this.imageDepth - (z + 1), y);
/*     */     }
/* 730 */     return new int[] { x, y, z, argb };
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnRight(int x, int y, int z)
/*     */   {
/* 741 */     int argb = this.imageRight.getRGB(z, y);
/* 742 */     while ((argb >> 24 & 0xFF) < 255) {
/* 743 */       z--;
/* 744 */       if (z < 0) {
/* 745 */         return nextVisiblePixel(0, y - 1, this.imageDepth - 1);
/*     */       }
/* 747 */       argb = this.imageRight.getRGB(z, y);
/*     */     }
/* 749 */     return new int[] { x, y, z, argb };
/*     */   }
/*     */ 
/*     */   public void resetImage()
/*     */   {
/* 756 */     if (this.imageTop != null) {
/* 757 */       this.currentY = (this.imageHeight - 1);
/* 758 */       this.currentZ = (this.imageDepth - 1);
/*     */     } else {
/* 760 */       this.currentY = 0;
/* 761 */       this.currentZ = 0;
/*     */     }
/* 763 */     this.currentX = 0;
/*     */   }
/*     */ 
/*     */   public void updateProgressWidth()
/*     */   {
/* 775 */     if ((this.imageTop == null) || (this.imageWidth < 1)) {
/* 776 */       this.progressWidth = 0;
/*     */     }
/* 778 */     this.progressWidth = ((int)(this.currentX / this.imageWidth * 100.0F));
/*     */   }
/*     */ 
/*     */   public void updateProgressHeight()
/*     */   {
/* 785 */     if ((this.imageTop == null) || (this.imageHeight < 1)) {
/* 786 */       this.progressHeight = 0;
/*     */     }
/* 788 */     this.progressHeight = ((int)((this.imageHeight - (this.currentY + 1)) / this.imageHeight * 100.0F));
/*     */   }
/*     */ 
/*     */   protected int getItemBurnTime(fy itemstack)
/*     */   {
/* 797 */     if (itemstack == null) return 0;
/* 798 */     int i = itemstack.a().bf;
/* 799 */     if ((i < 256) && (na.m[i].bA == hj.d)) return 300;
/* 800 */     if (i == ej.B.bf) return 100;
/* 801 */     if (i == ej.k.bf) return 1600;
/* 802 */     if (i == ej.aw.bf) return 20000;
/* 803 */     if (i == na.z.bn) return 100;
/* 804 */     return ModLoader.AddAllFuel(i);
/*     */   }
/*     */ 
/*     */   public void b(iq nbttagcompound)
/*     */   {
/* 817 */     super.b(nbttagcompound);
/* 818 */     nbttagcompound.a("ImageName", this.imageName);
/* 819 */     nbttagcompound.a("CurrentX", this.currentX);
/* 820 */     nbttagcompound.a("CurrentY", this.currentY);
/* 821 */     nbttagcompound.a("CurrentZ", this.currentZ);
/* 822 */     nbttagcompound.a("GenerationTime", this.factoryGenerationTime);
/* 823 */     nbttagcompound.a("IsActivated", this.isActivated);
/* 824 */     nbttagcompound.a("IsBurning", this.isBurning);
/* 825 */     nbttagcompound.a("BurnTime", (short)this.factoryBurnTime);
/* 826 */     nbttagcompound.a("CookTime", (short)this.factoryCookTime);
/*     */ 
/* 828 */     lr nbttaglist = new lr();
/* 829 */     for (int i = 0; i < this.dyeItemStacks.length; i++) {
/* 830 */       if (this.dyeItemStacks[i] != null) {
/* 831 */         iq nbttagcompound1 = new iq();
/* 832 */         nbttagcompound1.a("Slot", (byte)i);
/* 833 */         this.dyeItemStacks[i].a(nbttagcompound1);
/* 834 */         nbttaglist.a(nbttagcompound1);
/*     */       }
/*     */     }
/* 837 */     if (this.coalItemStack != null) {
/* 838 */       iq nbttagcompound1 = new iq();
/* 839 */       nbttagcompound1.a("Slot", (byte)6);
/* 840 */       this.coalItemStack.a(nbttagcompound1);
/* 841 */       nbttaglist.a(nbttagcompound1);
/*     */     }
/* 843 */     nbttagcompound.a("Items", nbttaglist);
/*     */   }
/*     */ 
/*     */   public void a(iq nbttagcompound)
/*     */   {
/* 851 */     super.a(nbttagcompound);
/* 852 */     setImageToGenerate(nbttagcompound.i("ImageName"));
/* 853 */     this.currentX = nbttagcompound.e("CurrentX");
/* 854 */     this.currentY = nbttagcompound.e("CurrentY");
/* 855 */     this.currentZ = nbttagcompound.e("CurrentZ");
/* 856 */     updateProgressWidth();
/* 857 */     updateProgressHeight();
/* 858 */     this.factoryGenerationTime = nbttagcompound.e("GenerationTime");
/* 859 */     this.isActivated = nbttagcompound.m("IsActivated");
/* 860 */     this.isBurning = nbttagcompound.m("IsBurning");
/* 861 */     this.factoryBurnTime = nbttagcompound.d("BurnTime");
/* 862 */     this.factoryCookTime = nbttagcompound.d("CookTime");
/*     */ 
/* 864 */     lr nbttaglist = nbttagcompound.l("Items");
/* 865 */     this.dyeItemStacks = new fy[a()];
/* 866 */     for (int i = 0; i < nbttaglist.c(); i++) {
/* 867 */       iq nbttagcompound1 = (iq)nbttaglist.a(i);
/* 868 */       byte byte0 = nbttagcompound1.c("Slot");
/* 869 */       if (byte0 == 6)
/* 870 */         this.coalItemStack = new fy(nbttagcompound1);
/* 871 */       else if ((byte0 >= 0) && (byte0 < this.dyeItemStacks.length)) {
/* 872 */         this.dyeItemStacks[byte0] = new fy(nbttagcompound1);
/*     */       }
/*     */     }
/* 875 */     this.currentItemBurnTime = getItemBurnTime(this.coalItemStack);
/*     */   }
/*     */ 
/*     */   public String getImageName()
/*     */   {
/* 888 */     return this.imageName;
/*     */   }
/*     */ 
/*     */   public void setImageToGenerate(String name)
/*     */   {
/* 901 */     if (name.equals(this.imageName)) {
/* 902 */       return;
/*     */     }
/* 904 */     this.imageName = name;
/* 905 */     this.currentX = 0;
/* 906 */     this.currentY = 0;
/* 907 */     this.currentZ = 0;
/* 908 */     this.progressWidth = 0;
/* 909 */     this.progressHeight = 0;
/* 910 */     if (name.equals("")) {
/* 911 */       return;
/*     */     }
/*     */ 
/* 914 */     this.imageTop = mod_ColoredBlock.getLocalImage(name + "/top.png");
/* 915 */     if (this.imageTop == null) return;
/* 916 */     this.imageBottom = mod_ColoredBlock.getLocalImage(name + "/bottom.png");
/* 917 */     if (this.imageBottom == null) return;
/* 918 */     this.imageLeft = mod_ColoredBlock.getLocalImage(name + "/left.png");
/* 919 */     if (this.imageLeft == null) return;
/* 920 */     this.imageRight = mod_ColoredBlock.getLocalImage(name + "/right.png");
/* 921 */     if (this.imageRight == null) return;
/* 922 */     this.imageFront = mod_ColoredBlock.getLocalImage(name + "/front.png");
/* 923 */     if (this.imageFront == null) return;
/* 924 */     this.imageBack = mod_ColoredBlock.getLocalImage(name + "/back.png");
/* 925 */     if (this.imageBack == null) return;
/*     */ 
/* 927 */     this.imageWidth = this.imageFront.getWidth();
/* 928 */     this.imageHeight = this.imageFront.getHeight();
/* 929 */     this.imageDepth = this.imageLeft.getWidth();
/* 930 */     this.currentY = (this.imageHeight - 1);
/* 931 */     this.currentZ = (this.imageDepth - 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Server (1)\
 * Qualified Name:     TileEntityModelFactory
 * JD-Core Version:    0.6.2
 */