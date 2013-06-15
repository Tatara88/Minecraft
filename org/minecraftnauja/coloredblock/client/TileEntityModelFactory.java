/*     */ import java.awt.Color;
/*     */ import java.awt.image.BufferedImage;
/*     */ 
/*     */ public class TileEntityModelFactory extends ow
/*     */   implements lw
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
/*     */   protected iz[] dyeItemStacks;
/*     */   protected iz coalItemStack;
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
/* 127 */     this.dyeItemStacks = new iz[6];
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
/*     */   public iz f_(int i)
/*     */   {
/* 160 */     if (i == 6)
/* 161 */       return this.coalItemStack;
/* 162 */     if ((i >= 0) && (i < this.dyeItemStacks.length)) {
/* 163 */       return this.dyeItemStacks[i];
/*     */     }
/* 165 */     return null;
/*     */   }
/*     */ 
/*     */   public iz a(int i, int j)
/*     */   {
/* 175 */     if (i == 6) {
/* 176 */       if (this.coalItemStack == null) {
/* 177 */         return null;
/*     */       }
/* 179 */       if (this.coalItemStack.a <= j) {
/* 180 */         iz itemstack = this.coalItemStack;
/* 181 */         this.coalItemStack = null;
/* 182 */         return itemstack;
/*     */       }
/* 184 */       iz itemstack = this.coalItemStack.a(j);
/* 185 */       if (this.coalItemStack.a == 0) {
/* 186 */         this.coalItemStack = null;
/*     */       }
/* 188 */       return itemstack;
/* 189 */     }if ((i >= 0) && (i < this.dyeItemStacks.length)) {
/* 190 */       if (this.dyeItemStacks[i] == null) {
/* 191 */         return null;
/*     */       }
/* 193 */       if (this.dyeItemStacks[i].a <= j) {
/* 194 */         iz itemstack = this.dyeItemStacks[i];
/* 195 */         this.dyeItemStacks[i] = null;
/* 196 */         return itemstack;
/*     */       }
/* 198 */       iz itemstack = this.dyeItemStacks[i].a(j);
/* 199 */       if (this.dyeItemStacks[i].a == 0) {
/* 200 */         this.dyeItemStacks[i] = null;
/*     */       }
/* 202 */       return itemstack;
/*     */     }
/* 204 */     return null;
/*     */   }
/*     */ 
/*     */   public void a(int i, iz itemstack)
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
/*     */   public int getBurnTimeRemainingScaled(int i)
/*     */   {
/* 245 */     if (this.currentItemBurnTime == 0) {
/* 246 */       this.currentItemBurnTime = 200;
/*     */     }
/* 248 */     return this.factoryBurnTime * i / this.currentItemBurnTime;
/*     */   }
/*     */ 
/*     */   public boolean isBurning()
/*     */   {
/* 256 */     return this.factoryBurnTime > 0;
/*     */   }
/*     */ 
/*     */   public boolean hasRedRose()
/*     */   {
/* 269 */     if (this.dyeItemStacks[0] == null) {
/* 270 */       return false;
/*     */     }
/* 272 */     return (this.dyeItemStacks[0].a().bf == gm.aU.bf) && (this.dyeItemStacks[0].i() == 1);
/*     */   }
/*     */ 
/*     */   public boolean hasCactusGreen()
/*     */   {
/* 280 */     if (this.dyeItemStacks[1] == null) {
/* 281 */       return false;
/*     */     }
/* 283 */     return (this.dyeItemStacks[1].a().bf == gm.aU.bf) && (this.dyeItemStacks[1].i() == 2);
/*     */   }
/*     */ 
/*     */   public boolean hasLapisLazuli()
/*     */   {
/* 291 */     if (this.dyeItemStacks[2] == null) {
/* 292 */       return false;
/*     */     }
/* 294 */     return (this.dyeItemStacks[2].a().bf == gm.aU.bf) && (this.dyeItemStacks[2].i() == 4);
/*     */   }
/*     */ 
/*     */   public boolean hasColoredDye()
/*     */   {
/* 302 */     if (this.dyeItemStacks[3] == null) {
/* 303 */       return false;
/*     */     }
/* 305 */     return this.dyeItemStacks[3].a().bf == ItemColoredDye.item.bf;
/*     */   }
/*     */ 
/*     */   public boolean hasWool()
/*     */   {
/* 313 */     if (this.dyeItemStacks[4] == null) {
/* 314 */       return false;
/*     */     }
/* 316 */     return this.dyeItemStacks[4].a().bf == uu.ac.bn;
/*     */   }
/*     */ 
/*     */   public boolean hasColoredWool()
/*     */   {
/* 324 */     if (this.dyeItemStacks[5] == null) {
/* 325 */       return false;
/*     */     }
/* 327 */     return this.dyeItemStacks[5].a().bf == BlockColor.blockId;
/*     */   }
/*     */ 
/*     */   public boolean[] findItemsToSmelt()
/*     */   {
/* 340 */     boolean type1 = (hasRedRose()) && (hasCactusGreen()) && (hasLapisLazuli()) && (hasWool());
/* 341 */     boolean type2 = (hasColoredDye()) && (hasWool());
/* 342 */     boolean type3 = hasColoredWool();
/* 343 */     return new boolean[] { type1, type2, type3 };
/*     */   }
/*     */ 
/*     */   public boolean canSmelt()
/*     */   {
/* 352 */     boolean[] b = findItemsToSmelt();
/* 353 */     return (b[0] != 0) || (b[1] != 0) || (b[2] != 0);
/*     */   }
/*     */ 
/*     */   public int smeltItem()
/*     */   {
/* 361 */     if (!canSmelt()) {
/* 362 */       return -1;
/*     */     }
/* 364 */     boolean[] b = findItemsToSmelt();
/* 365 */     if (b[2] != 0) {
/* 366 */       a(5, 1);
/* 367 */       return 2;
/* 368 */     }if (b[1] != 0) {
/* 369 */       a(3, 1);
/* 370 */       a(4, 1);
/* 371 */       return 1;
/*     */     }
/* 373 */     a(0, 1);
/* 374 */     a(1, 1);
/* 375 */     a(2, 1);
/* 376 */     a(4, 1);
/* 377 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean hasFuel()
/*     */   {
/* 386 */     return getItemBurnTime(this.coalItemStack) > 0;
/*     */   }
/*     */ 
/*     */   public void n_()
/*     */   {
/* 398 */     if (!this.isActivated) {
/* 399 */       return;
/*     */     }
/* 401 */     if (this.imageTop == null) {
/* 402 */       return;
/*     */     }
/* 404 */     boolean changed = false;
/* 405 */     boolean smelt = canSmelt();
/* 406 */     if (BlockModelFactory.dontRequireItems) {
/* 407 */       smelt = true;
/*     */     }
/* 409 */     boolean fuel = hasFuel();
/* 410 */     if (BlockModelFactory.dontRequireFuel) {
/* 411 */       fuel = true;
/*     */     }
/*     */ 
/* 414 */     if ((this.isActivated) && (!this.isBurning) && (smelt) && (fuel)) {
/* 415 */       BlockModelFactory.updateFactoryBlockState(true, true, this.d, this.e, this.f, this.g);
/*     */     }
/* 417 */     else if ((this.isBurning) && ((!this.isActivated) || (!fuel) || (!smelt))) {
/* 418 */       BlockModelFactory.updateFactoryBlockState(this.isActivated, false, this.d, this.e, this.f, this.g);
/*     */     }
/* 420 */     if (this.factoryBurnTime > 0) {
/* 421 */       this.factoryBurnTime -= 1;
/*     */     }
/* 423 */     if (!mod_ColoredBlock.isMultiplayer()) {
/* 424 */       if ((this.factoryBurnTime == 0) && (smelt)) {
/* 425 */         this.factoryBurnTime = getItemBurnTime(this.coalItemStack);
/* 426 */         if (BlockModelFactory.dontRequireFuel) {
/* 427 */           this.factoryBurnTime = 500;
/*     */         }
/* 429 */         this.currentItemBurnTime = this.factoryBurnTime;
/* 430 */         if (this.factoryBurnTime > 0) {
/* 431 */           changed = true;
/* 432 */           if (!BlockModelFactory.dontRequireFuel) {
/* 433 */             a(6, 1);
/*     */           }
/*     */         }
/*     */       }
/* 437 */       if ((isBurning()) && (smelt)) {
/* 438 */         this.factoryCookTime += 16;
/* 439 */         if (BlockModelFactory.instantCook) {
/* 440 */           this.factoryCookTime = 200;
/*     */         }
/* 442 */         if (this.factoryCookTime >= 200) {
/* 443 */           if (!BlockModelFactory.dontRequireItems) {
/* 444 */             smeltItem();
/*     */           }
/* 446 */           if (generateImagePart()) {
/* 447 */             BlockModelFactory.updateFactoryBlockState(false, false, this.d, this.e, this.f, this.g);
/* 448 */             resetImage();
/*     */           }
/* 450 */           updateProgressWidth();
/* 451 */           updateProgressHeight();
/* 452 */           changed = true;
/* 453 */           this.factoryCookTime = 0;
/*     */         }
/*     */       } else {
/* 456 */         this.factoryCookTime = 0;
/*     */       }
/* 458 */       if (!this.isBurning) {
/* 459 */         changed = true;
/* 460 */         BlockModelFactory.updateFactoryBlockState(this.isActivated, false, this.d, this.e, this.f, this.g);
/*     */       }
/*     */     }
/* 463 */     if (changed)
/* 464 */       y_();
/*     */   }
/*     */ 
/*     */   public boolean a_(gs player)
/*     */   {
/* 474 */     if (this.d.b(this.e, this.f, this.g) != this) {
/* 475 */       return false;
/*     */     }
/* 477 */     return player.g(this.e + 0.5D, this.f + 0.5D, this.g + 0.5D) <= 64.0D;
/*     */   }
/*     */ 
/*     */   private boolean generateImagePart()
/*     */   {
/* 490 */     if ((this.currentX < 0) || (this.currentX >= this.imageWidth))
/* 491 */       return true;
/* 492 */     if ((this.currentY < 0) || (this.currentY >= this.imageHeight))
/* 493 */       return true;
/* 494 */     if ((this.currentZ < 0) || (this.currentZ >= this.imageDepth)) {
/* 495 */       return true;
/*     */     }
/* 497 */     int[] pos = nextVisiblePixel(this.currentX, this.currentY, this.currentZ);
/* 498 */     if (pos == null) {
/* 499 */       return true;
/*     */     }
/* 501 */     this.currentX = pos[0];
/* 502 */     this.currentY = pos[1];
/* 503 */     this.currentZ = pos[2];
/* 504 */     int argb = pos[3];
/*     */ 
/* 506 */     TileEntityColor entity = new TileEntityColor();
/* 507 */     entity.setColor(argb >> 16 & 0xFF, argb >> 8 & 0xFF, argb & 0xFF);
/*     */ 
/* 509 */     int l = this.d.e(this.e, this.f, this.g);
/* 510 */     int x = this.e;
/* 511 */     int y = this.f + 1;
/* 512 */     int z = this.g;
/* 513 */     if (l == 5) {
/* 514 */       x = this.e - this.imageWidth / 2 + this.currentX;
/* 515 */       y = this.f + 1 + (this.imageHeight - this.currentY);
/* 516 */       z = this.g + this.imageDepth / 2 - this.currentZ;
/* 517 */     } else if (l == 6) {
/* 518 */       x = this.e - this.imageDepth / 2 + this.currentZ;
/* 519 */       y = this.f + 1 + (this.imageHeight - this.currentY);
/* 520 */       z = this.g - this.imageWidth / 2 + this.currentX;
/* 521 */     } else if (l == 7) {
/* 522 */       x = this.e + this.imageWidth / 2 - this.currentX;
/* 523 */       y = this.f + 1 + (this.imageHeight - this.currentY);
/* 524 */       z = this.g - this.imageDepth / 2 + this.currentZ;
/* 525 */     } else if (l == 8) {
/* 526 */       x = this.e + this.imageDepth / 2 - this.currentZ;
/* 527 */       y = this.f + 1 + (this.imageHeight - this.currentY);
/* 528 */       z = this.g + this.imageWidth / 2 - this.currentX;
/* 529 */     } else if (l == 1) {
/* 530 */       x = this.e - 2 - this.currentZ;
/* 531 */       y = this.f + this.imageHeight / 2 - this.currentY;
/* 532 */       z = this.g + this.imageWidth / 2 - this.currentX;
/* 533 */     } else if (l == 3) {
/* 534 */       x = this.e + 2 + this.currentZ;
/* 535 */       y = this.f + this.imageHeight / 2 - this.currentY;
/* 536 */       z = this.g - this.imageWidth / 2 + this.currentX;
/* 537 */     } else if (l == 0) {
/* 538 */       x = this.e + this.imageWidth / 2 - this.currentX;
/* 539 */       y = this.f + this.imageHeight / 2 - this.currentY;
/* 540 */       z = this.g + 2 + this.currentZ;
/* 541 */     } else if (l == 2) {
/* 542 */       x = this.e - this.imageWidth / 2 + this.currentX;
/* 543 */       y = this.f + this.imageHeight / 2 - this.currentY;
/* 544 */       z = this.g - 2 - this.currentZ;
/*     */     }
/*     */ 
/* 547 */     if (!blockAlreadyColored(x, y, z, entity)) {
/* 548 */       this.d.f(x, y, z, BlockColor.blockId);
/* 549 */       this.d.a(x, y, z, entity);
/* 550 */       if (this.d.a(x, y, z) != BlockColor.blockId) {
/* 551 */         return true;
/*     */       }
/*     */     }
/*     */ 
/* 555 */     this.currentZ -= 1;
/* 556 */     if (this.currentZ < 0) {
/* 557 */       this.currentZ = (this.imageDepth - 1);
/* 558 */       this.currentX += 1;
/* 559 */       if (this.currentX >= this.imageWidth) {
/* 560 */         this.currentX = 0;
/* 561 */         this.currentY -= 1;
/* 562 */         if (this.currentY < 0) {
/* 563 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 567 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean blockAlreadyColored(int i, int j, int k, TileEntityColor entity)
/*     */   {
/* 579 */     int id = this.d.a(i, j, k);
/* 580 */     if ((id > 0) && ((BlockModelFactory.dontEraseAnything) || (BlockModelFactory.dontEraseThese.contains(id + ";")))) {
/* 581 */       return true;
/*     */     }
/* 583 */     ow tmp = this.d.b(i, j, k);
/* 584 */     if (tmp == null) {
/* 585 */       return false;
/*     */     }
/* 587 */     if (!(tmp instanceof TileEntityColor)) {
/* 588 */       return false;
/*     */     }
/* 590 */     TileEntityColor tmp2 = (TileEntityColor)tmp;
/* 591 */     return tmp2.getColor().equals(entity.getColor());
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixel(int x, int y, int z)
/*     */   {
/* 602 */     if ((x < 0) || (x >= this.imageWidth))
/* 603 */       return null;
/* 604 */     if ((y < 0) || (y >= this.imageHeight))
/* 605 */       return null;
/* 606 */     if ((z < 0) || (z >= this.imageDepth)) {
/* 607 */       return null;
/*     */     }
/*     */ 
/* 610 */     if (z == 0)
/* 611 */       return nextVisiblePixelOnFront(x, y, z);
/* 612 */     if (z == this.imageDepth - 1) {
/* 613 */       return nextVisiblePixelOnBack(x, y, z);
/*     */     }
/* 615 */     return nextVisiblePixelOnSide(x, y, z);
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnFront(int x, int y, int z)
/*     */   {
/* 627 */     int argb = this.imageFront.getRGB(x, y);
/* 628 */     if ((argb >> 24 & 0xFF) == 255) {
/* 629 */       return new int[] { x, y, z, argb };
/*     */     }
/* 631 */     x++;
/* 632 */     if (x >= this.imageWidth) {
/* 633 */       x = 0;
/* 634 */       y--;
/* 635 */       if (y < 0) {
/* 636 */         return null;
/*     */       }
/*     */     }
/* 639 */     return nextVisiblePixelOnBack(x, y, this.imageDepth - 1);
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnBack(int x, int y, int z)
/*     */   {
/* 651 */     int argb = this.imageBack.getRGB(this.imageWidth - (x + 1), y);
/* 652 */     if ((argb >> 24 & 0xFF) == 255) {
/* 653 */       return new int[] { x, y, z, argb };
/*     */     }
/* 655 */     return nextVisiblePixelOnFront(x, y, 0);
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnSide(int x, int y, int z)
/*     */   {
/* 667 */     if (x == 0)
/* 668 */       return nextVisiblePixelOnLeft(x, y, z);
/* 669 */     if (x == this.imageWidth - 1)
/* 670 */       return nextVisiblePixelOnRight(x, y, z);
/* 671 */     if (y == 0)
/* 672 */       return nextVisiblePixelOnTop(x, y, z);
/* 673 */     if (y == this.imageHeight - 1) {
/* 674 */       return nextVisiblePixelOnBottom(x, y, z);
/*     */     }
/* 676 */     return nextVisiblePixel(x, y, 0);
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnTop(int x, int y, int z)
/*     */   {
/* 688 */     int argb = this.imageTop.getRGB(x, this.imageDepth - (z + 1));
/* 689 */     while ((argb >> 24 & 0xFF) < 255) {
/* 690 */       z--;
/* 691 */       if (z < 0) {
/* 692 */         z = this.imageDepth - 1;
/* 693 */         x++;
/* 694 */         if (x >= this.imageWidth) {
/* 695 */           return null;
/*     */         }
/*     */       }
/* 698 */       argb = this.imageTop.getRGB(x, this.imageDepth - (z + 1));
/*     */     }
/* 700 */     return new int[] { x, y, z, argb };
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnBottom(int x, int y, int z)
/*     */   {
/* 711 */     int argb = this.imageBottom.getRGB(x, z);
/* 712 */     while ((argb >> 24 & 0xFF) < 255) {
/* 713 */       z--;
/* 714 */       if (z < 0) {
/* 715 */         z = this.imageDepth - 1;
/* 716 */         x++;
/* 717 */         if (x >= this.imageWidth) {
/* 718 */           return nextVisiblePixel(0, y - 1, z);
/*     */         }
/*     */       }
/* 721 */       argb = this.imageBottom.getRGB(x, z);
/*     */     }
/* 723 */     return new int[] { x, y, z, argb };
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnLeft(int x, int y, int z)
/*     */   {
/* 734 */     int argb = this.imageLeft.getRGB(this.imageDepth - (z + 1), y);
/* 735 */     while ((argb >> 24 & 0xFF) < 255) {
/* 736 */       z--;
/* 737 */       if (z < 0) {
/* 738 */         return nextVisiblePixel(x + 1, y, this.imageDepth - 1);
/*     */       }
/* 740 */       argb = this.imageLeft.getRGB(this.imageDepth - (z + 1), y);
/*     */     }
/* 742 */     return new int[] { x, y, z, argb };
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixelOnRight(int x, int y, int z)
/*     */   {
/* 753 */     int argb = this.imageRight.getRGB(z, y);
/* 754 */     while ((argb >> 24 & 0xFF) < 255) {
/* 755 */       z--;
/* 756 */       if (z < 0) {
/* 757 */         return nextVisiblePixel(0, y - 1, this.imageDepth - 1);
/*     */       }
/* 759 */       argb = this.imageRight.getRGB(z, y);
/*     */     }
/* 761 */     return new int[] { x, y, z, argb };
/*     */   }
/*     */ 
/*     */   public void resetImage()
/*     */   {
/* 768 */     if (this.imageTop != null) {
/* 769 */       this.currentY = (this.imageHeight - 1);
/* 770 */       this.currentZ = (this.imageDepth - 1);
/*     */     } else {
/* 772 */       this.currentY = 0;
/* 773 */       this.currentZ = 0;
/*     */     }
/* 775 */     this.currentX = 0;
/* 776 */     this.progressWidth = 0;
/* 777 */     this.progressHeight = 0;
/*     */   }
/*     */ 
/*     */   public void updateProgressWidth()
/*     */   {
/* 789 */     if ((this.imageTop == null) || (this.imageWidth < 1)) {
/* 790 */       this.progressWidth = 0;
/*     */     }
/* 792 */     this.progressWidth = ((int)(this.currentX / this.imageWidth * 100.0F));
/*     */   }
/*     */ 
/*     */   public void updateProgressHeight()
/*     */   {
/* 799 */     if ((this.imageTop == null) || (this.imageHeight < 1)) {
/* 800 */       this.progressHeight = 0;
/*     */     }
/* 802 */     this.progressHeight = ((int)((this.imageHeight - (this.currentY + 1)) / this.imageHeight * 100.0F));
/*     */   }
/*     */ 
/*     */   public int getImageProgressWidth(int i)
/*     */   {
/* 811 */     if ((this.imageTop == null) || (this.imageWidth < 1)) {
/* 812 */       return 0;
/*     */     }
/* 814 */     return this.progressWidth * i / 100;
/*     */   }
/*     */ 
/*     */   public int getImageProgressHeight(int i)
/*     */   {
/* 823 */     if ((this.imageTop == null) || (this.imageHeight < 1)) {
/* 824 */       return 0;
/*     */     }
/* 826 */     return this.progressHeight * i / 100;
/*     */   }
/*     */ 
/*     */   public int getCookProgressScaled(int i)
/*     */   {
/* 835 */     int p = this.factoryCookTime * i / 200;
/* 836 */     if (p < 0)
/* 837 */       return 0;
/* 838 */     if (p > 200 * i) {
/* 839 */       return 200 * i;
/*     */     }
/* 841 */     return p;
/*     */   }
/*     */ 
/*     */   protected int getItemBurnTime(iz itemstack)
/*     */   {
/* 850 */     if (itemstack == null) return 0;
/* 851 */     int i = itemstack.a().bf;
/* 852 */     if ((i < 256) && (uu.m[i].bA == ln.d)) return 300;
/* 853 */     if (i == gm.B.bf) return 100;
/* 854 */     if (i == gm.k.bf) return 1600;
/* 855 */     if (i == gm.aw.bf) return 20000;
/* 856 */     if (i == uu.z.bn) return 100;
/* 857 */     return ModLoader.AddAllFuel(i);
/*     */   }
/*     */ 
/*     */   public void b(nu nbttagcompound)
/*     */   {
/* 870 */     super.b(nbttagcompound);
/* 871 */     nbttagcompound.a("ImageName", this.imageName);
/* 872 */     nbttagcompound.a("CurrentX", this.currentX);
/* 873 */     nbttagcompound.a("CurrentY", this.currentY);
/* 874 */     nbttagcompound.a("CurrentZ", this.currentZ);
/* 875 */     nbttagcompound.a("GenerationTime", this.factoryGenerationTime);
/* 876 */     nbttagcompound.a("IsActivated", this.isActivated);
/* 877 */     nbttagcompound.a("IsBurning", this.isBurning);
/* 878 */     nbttagcompound.a("BurnTime", (short)this.factoryBurnTime);
/* 879 */     nbttagcompound.a("CookTime", (short)this.factoryCookTime);
/*     */ 
/* 881 */     sp nbttaglist = new sp();
/* 882 */     for (int i = 0; i < this.dyeItemStacks.length; i++) {
/* 883 */       if (this.dyeItemStacks[i] != null) {
/* 884 */         nu nbttagcompound1 = new nu();
/* 885 */         nbttagcompound1.a("Slot", (byte)i);
/* 886 */         this.dyeItemStacks[i].a(nbttagcompound1);
/* 887 */         nbttaglist.a(nbttagcompound1);
/*     */       }
/*     */     }
/* 890 */     if (this.coalItemStack != null) {
/* 891 */       nu nbttagcompound1 = new nu();
/* 892 */       nbttagcompound1.a("Slot", (byte)6);
/* 893 */       this.coalItemStack.a(nbttagcompound1);
/* 894 */       nbttaglist.a(nbttagcompound1);
/*     */     }
/* 896 */     nbttagcompound.a("Items", nbttaglist);
/*     */   }
/*     */ 
/*     */   public void a(nu nbttagcompound)
/*     */   {
/* 904 */     super.a(nbttagcompound);
/* 905 */     setImageToGenerate(nbttagcompound.i("ImageName"));
/* 906 */     this.currentX = nbttagcompound.e("CurrentX");
/* 907 */     this.currentY = nbttagcompound.e("CurrentY");
/* 908 */     this.currentZ = nbttagcompound.e("CurrentZ");
/* 909 */     updateProgressWidth();
/* 910 */     updateProgressHeight();
/* 911 */     this.factoryGenerationTime = nbttagcompound.e("GenerationTime");
/* 912 */     this.isActivated = nbttagcompound.m("IsActivated");
/* 913 */     this.isBurning = nbttagcompound.m("IsBurning");
/* 914 */     this.factoryBurnTime = nbttagcompound.d("BurnTime");
/* 915 */     this.factoryCookTime = nbttagcompound.d("CookTime");
/*     */ 
/* 917 */     sp nbttaglist = nbttagcompound.l("Items");
/* 918 */     this.dyeItemStacks = new iz[a()];
/* 919 */     for (int i = 0; i < nbttaglist.c(); i++) {
/* 920 */       nu nbttagcompound1 = (nu)nbttaglist.a(i);
/* 921 */       byte byte0 = nbttagcompound1.c("Slot");
/* 922 */       if (byte0 == 6)
/* 923 */         this.coalItemStack = new iz(nbttagcompound1);
/* 924 */       else if ((byte0 >= 0) && (byte0 < this.dyeItemStacks.length)) {
/* 925 */         this.dyeItemStacks[byte0] = new iz(nbttagcompound1);
/*     */       }
/*     */     }
/* 928 */     this.currentItemBurnTime = getItemBurnTime(this.coalItemStack);
/*     */   }
/*     */ 
/*     */   public String getImageName()
/*     */   {
/* 941 */     return this.imageName;
/*     */   }
/*     */ 
/*     */   public void setImageToGenerate(String name)
/*     */   {
/* 954 */     if (name.equals(this.imageName)) {
/* 955 */       return;
/*     */     }
/* 957 */     this.imageName = name;
/* 958 */     this.currentX = 0;
/* 959 */     this.currentY = 0;
/* 960 */     this.currentZ = 0;
/* 961 */     this.progressWidth = 0;
/* 962 */     this.progressHeight = 0;
/* 963 */     this.factoryGenerationTime = 0;
/* 964 */     if (name.equals("")) {
/* 965 */       return;
/*     */     }
/*     */ 
/* 968 */     this.imageTop = mod_ColoredBlock.getLocalImage(name + "/top.png");
/* 969 */     if (this.imageTop == null) return;
/* 970 */     this.imageBottom = mod_ColoredBlock.getLocalImage(name + "/bottom.png");
/* 971 */     if (this.imageBottom == null) return;
/* 972 */     this.imageLeft = mod_ColoredBlock.getLocalImage(name + "/left.png");
/* 973 */     if (this.imageLeft == null) return;
/* 974 */     this.imageRight = mod_ColoredBlock.getLocalImage(name + "/right.png");
/* 975 */     if (this.imageRight == null) return;
/* 976 */     this.imageFront = mod_ColoredBlock.getLocalImage(name + "/front.png");
/* 977 */     if (this.imageFront == null) return;
/* 978 */     this.imageBack = mod_ColoredBlock.getLocalImage(name + "/back.png");
/* 979 */     if (this.imageBack == null) return;
/*     */ 
/* 981 */     this.imageWidth = this.imageFront.getWidth();
/* 982 */     this.imageHeight = this.imageFront.getHeight();
/* 983 */     this.imageDepth = this.imageLeft.getWidth();
/* 984 */     this.currentY = (this.imageHeight - 1);
/* 985 */     this.currentZ = (this.imageDepth - 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     TileEntityModelFactory
 * JD-Core Version:    0.6.2
 */