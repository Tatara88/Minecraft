/*     */ import java.awt.Color;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class TileEntityPictureFactory extends jh
/*     */   implements hp
/*     */ {
/*     */   protected String imageName;
/*     */   protected BufferedImage image;
/*     */   protected int imageWidth;
/*     */   protected int imageHeight;
/*     */   protected int currentLine;
/*     */   protected int currentColumn;
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
/*     */   public TileEntityPictureFactory()
/*     */   {
/*  98 */     this.imageName = "";
/*  99 */     this.dyeItemStacks = new fy[6];
/* 100 */     j();
/*     */   }
/*     */ 
/*     */   public static void initialise()
/*     */   {
/* 112 */     ModLoader.RegisterTileEntity(TileEntityPictureFactory.class, "Picture Factory");
/*     */   }
/*     */ 
/*     */   public int a()
/*     */   {
/* 124 */     return this.dyeItemStacks.length + 1;
/*     */   }
/*     */ 
/*     */   public fy d_(int i)
/*     */   {
/* 133 */     if (i == 6)
/* 134 */       return this.coalItemStack;
/* 135 */     if ((i >= 0) && (i < this.dyeItemStacks.length)) {
/* 136 */       return this.dyeItemStacks[i];
/*     */     }
/* 138 */     return null;
/*     */   }
/*     */ 
/*     */   public fy a(int i, int j)
/*     */   {
/* 148 */     if (i == 6) {
/* 149 */       if (this.coalItemStack == null) {
/* 150 */         return null;
/*     */       }
/* 152 */       if (this.coalItemStack.a <= j) {
/* 153 */         fy itemstack = this.coalItemStack;
/* 154 */         this.coalItemStack = null;
/* 155 */         return itemstack;
/*     */       }
/* 157 */       fy itemstack = this.coalItemStack.a(j);
/* 158 */       if (this.coalItemStack.a == 0) {
/* 159 */         this.coalItemStack = null;
/*     */       }
/* 161 */       return itemstack;
/* 162 */     }if ((i >= 0) && (i < this.dyeItemStacks.length)) {
/* 163 */       if (this.dyeItemStacks[i] == null) {
/* 164 */         return null;
/*     */       }
/* 166 */       if (this.dyeItemStacks[i].a <= j) {
/* 167 */         fy itemstack = this.dyeItemStacks[i];
/* 168 */         this.dyeItemStacks[i] = null;
/* 169 */         return itemstack;
/*     */       }
/* 171 */       fy itemstack = this.dyeItemStacks[i].a(j);
/* 172 */       if (this.dyeItemStacks[i].a == 0) {
/* 173 */         this.dyeItemStacks[i] = null;
/*     */       }
/* 175 */       return itemstack;
/*     */     }
/* 177 */     return null;
/*     */   }
/*     */ 
/*     */   public void a(int i, fy itemstack)
/*     */   {
/* 186 */     if (i == 6)
/* 187 */       this.coalItemStack = itemstack;
/* 188 */     else if ((i >= 0) && (i < this.dyeItemStacks.length)) {
/* 189 */       this.dyeItemStacks[i] = itemstack;
/*     */     }
/* 191 */     if ((itemstack != null) && (itemstack.a > d()))
/* 192 */       itemstack.a = d();
/*     */   }
/*     */ 
/*     */   public int d()
/*     */   {
/* 201 */     return 64;
/*     */   }
/*     */ 
/*     */   public String c()
/*     */   {
/* 209 */     return "Picture Factory";
/*     */   }
/*     */ 
/*     */   public boolean isBurning()
/*     */   {
/* 217 */     return this.factoryBurnTime > 0;
/*     */   }
/*     */ 
/*     */   public boolean hasRedRose()
/*     */   {
/* 230 */     if (this.dyeItemStacks[0] == null) {
/* 231 */       return false;
/*     */     }
/* 233 */     return (this.dyeItemStacks[0].a().bf == ej.aU.bf) && (this.dyeItemStacks[0].h() == 1);
/*     */   }
/*     */ 
/*     */   public boolean hasCactusGreen()
/*     */   {
/* 241 */     if (this.dyeItemStacks[1] == null) {
/* 242 */       return false;
/*     */     }
/* 244 */     return (this.dyeItemStacks[1].a().bf == ej.aU.bf) && (this.dyeItemStacks[1].h() == 2);
/*     */   }
/*     */ 
/*     */   public boolean hasLapisLazuli()
/*     */   {
/* 252 */     if (this.dyeItemStacks[2] == null) {
/* 253 */       return false;
/*     */     }
/* 255 */     return (this.dyeItemStacks[2].a().bf == ej.aU.bf) && (this.dyeItemStacks[2].h() == 4);
/*     */   }
/*     */ 
/*     */   public boolean hasColoredDye()
/*     */   {
/* 263 */     if (this.dyeItemStacks[3] == null) {
/* 264 */       return false;
/*     */     }
/* 266 */     return this.dyeItemStacks[3].a().bf == ItemColoredDye.item.bf;
/*     */   }
/*     */ 
/*     */   public boolean hasWool()
/*     */   {
/* 274 */     if (this.dyeItemStacks[4] == null) {
/* 275 */       return false;
/*     */     }
/* 277 */     return this.dyeItemStacks[4].a().bf == na.ac.bn;
/*     */   }
/*     */ 
/*     */   public boolean hasColoredWool()
/*     */   {
/* 285 */     if (this.dyeItemStacks[5] == null) {
/* 286 */       return false;
/*     */     }
/* 288 */     return this.dyeItemStacks[5].a().bf == BlockColor.blockId;
/*     */   }
/*     */ 
/*     */   public boolean[] findItemsToSmelt()
/*     */   {
/* 301 */     boolean type1 = (hasRedRose()) && (hasCactusGreen()) && (hasLapisLazuli()) && (hasWool());
/* 302 */     boolean type2 = (hasColoredDye()) && (hasWool());
/* 303 */     boolean type3 = hasColoredWool();
/* 304 */     return new boolean[] { type1, type2, type3 };
/*     */   }
/*     */ 
/*     */   public boolean canSmelt()
/*     */   {
/* 313 */     boolean[] b = findItemsToSmelt();
/* 314 */     return (b[0] != 0) || (b[1] != 0) || (b[2] != 0);
/*     */   }
/*     */ 
/*     */   public int smeltItem()
/*     */   {
/* 322 */     if (!canSmelt()) {
/* 323 */       return -1;
/*     */     }
/* 325 */     boolean[] b = findItemsToSmelt();
/* 326 */     if (b[2] != 0) {
/* 327 */       a(5, 1);
/* 328 */       return 2;
/* 329 */     }if (b[1] != 0) {
/* 330 */       a(3, 1);
/* 331 */       a(4, 1);
/* 332 */       return 1;
/*     */     }
/* 334 */     a(0, 1);
/* 335 */     a(1, 1);
/* 336 */     a(2, 1);
/* 337 */     a(4, 1);
/* 338 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean hasFuel()
/*     */   {
/* 347 */     return getItemBurnTime(this.coalItemStack) > 0;
/*     */   }
/*     */ 
/*     */   public void g_()
/*     */   {
/* 359 */     if (!this.isActivated) {
/* 360 */       return;
/*     */     }
/* 362 */     if (this.image == null) {
/* 363 */       return;
/*     */     }
/* 365 */     boolean changed = false;
/* 366 */     boolean smelt = canSmelt();
/* 367 */     if (BlockPictureFactory.dontRequireItems) {
/* 368 */       smelt = true;
/*     */     }
/* 370 */     boolean fuel = hasFuel();
/* 371 */     if (BlockPictureFactory.dontRequireFuel) {
/* 372 */       fuel = true;
/*     */     }
/*     */ 
/* 375 */     if ((this.isActivated) && (!this.isBurning) && (smelt) && (fuel)) {
/* 376 */       BlockPictureFactory.updateFactoryBlockState(true, true, this.d, this.e, this.f, this.g);
/*     */     }
/* 378 */     else if ((this.isBurning) && ((!this.isActivated) || (!fuel) || (!smelt))) {
/* 379 */       BlockPictureFactory.updateFactoryBlockState(this.isActivated, false, this.d, this.e, this.f, this.g);
/*     */     }
/* 381 */     if (this.factoryBurnTime > 0) {
/* 382 */       this.factoryBurnTime -= 1;
/*     */     }
/* 384 */     if (!this.d.B) {
/* 385 */       if ((this.factoryBurnTime == 0) && (smelt)) {
/* 386 */         this.factoryBurnTime = getItemBurnTime(this.coalItemStack);
/* 387 */         if (BlockPictureFactory.dontRequireFuel) {
/* 388 */           this.factoryBurnTime = 500;
/*     */         }
/* 390 */         this.currentItemBurnTime = this.factoryBurnTime;
/* 391 */         if (this.factoryBurnTime > 0) {
/* 392 */           changed = true;
/* 393 */           if (!BlockPictureFactory.dontRequireFuel) {
/* 394 */             a(6, 1);
/*     */           }
/*     */         }
/*     */       }
/* 398 */       if ((isBurning()) && (smelt)) {
/* 399 */         this.factoryCookTime += 16;
/* 400 */         if (BlockPictureFactory.instantCook) {
/* 401 */           this.factoryCookTime = 200;
/*     */         }
/* 403 */         if (this.factoryCookTime >= 200) {
/* 404 */           if (!BlockPictureFactory.dontRequireItems) {
/* 405 */             smeltItem();
/*     */           }
/* 407 */           if (generateImagePart()) {
/* 408 */             BlockPictureFactory.updateFactoryBlockState(false, false, this.d, this.e, this.f, this.g);
/* 409 */             resetImage();
/*     */           }
/* 411 */           updateProgressWidth();
/* 412 */           updateProgressHeight();
/* 413 */           changed = true;
/* 414 */           this.factoryCookTime = 0;
/*     */         }
/*     */       } else {
/* 417 */         this.factoryCookTime = 0;
/*     */       }
/* 419 */       if (!this.isBurning) {
/* 420 */         changed = true;
/* 421 */         BlockPictureFactory.updateFactoryBlockState(this.isActivated, false, this.d, this.e, this.f, this.g);
/*     */       }
/*     */     }
/* 424 */     if (changed)
/* 425 */       i();
/*     */   }
/*     */ 
/*     */   public boolean a_(em player)
/*     */   {
/* 435 */     if (this.d.b(this.e, this.f, this.g) != this) {
/* 436 */       return false;
/*     */     }
/* 438 */     return player.e(this.e + 0.5D, this.f + 0.5D, this.g + 0.5D) <= 64.0D;
/*     */   }
/*     */ 
/*     */   private boolean generateImagePart()
/*     */   {
/* 451 */     if ((this.currentColumn < 0) || (this.currentColumn >= this.imageWidth))
/* 452 */       return true;
/* 453 */     if ((this.currentLine < 0) || (this.currentLine >= this.imageHeight)) {
/* 454 */       return true;
/*     */     }
/* 456 */     int[] pos = nextVisiblePixel(this.currentColumn, this.currentLine);
/* 457 */     if (pos == null) {
/* 458 */       return true;
/*     */     }
/* 460 */     this.currentColumn = pos[0];
/* 461 */     this.currentLine = pos[1];
/* 462 */     int argb = pos[2];
/*     */ 
/* 464 */     TileEntityColor entity = new TileEntityColor();
/* 465 */     entity.setColor(argb >> 16 & 0xFF, argb >> 8 & 0xFF, argb & 0xFF);
/*     */ 
/* 467 */     int l = this.d.c(this.e, this.f, this.g);
/* 468 */     int x = this.e;
/* 469 */     int y = this.f;
/* 470 */     int z = this.g;
/* 471 */     if (l == 5) {
/* 472 */       x = this.e - this.imageWidth / 2 + this.currentColumn;
/* 473 */       y = this.f + 1 + (this.imageHeight - this.currentLine);
/* 474 */     } else if (l == 6) {
/* 475 */       z = this.g - this.imageWidth / 2 + this.currentColumn;
/* 476 */       y = this.f + 1 + (this.imageHeight - this.currentLine);
/* 477 */     } else if (l == 7) {
/* 478 */       x = this.e + this.imageWidth / 2 - this.currentColumn;
/* 479 */       y = this.f + 1 + (this.imageHeight - this.currentLine);
/* 480 */     } else if (l == 8) {
/* 481 */       z = this.g + this.imageWidth / 2 - this.currentColumn;
/* 482 */       y = this.f + 1 + (this.imageHeight - this.currentLine);
/* 483 */     } else if (l == 1) {
/* 484 */       z = this.g + this.imageWidth / 2 - this.currentColumn;
/* 485 */       x = this.e - 1 - (this.imageHeight - this.currentLine);
/* 486 */     } else if (l == 3) {
/* 487 */       z = this.g - this.imageWidth / 2 + this.currentColumn;
/* 488 */       x = this.e + 1 + (this.imageHeight - this.currentLine);
/* 489 */     } else if (l == 0) {
/* 490 */       x = this.e + this.imageWidth / 2 - this.currentColumn;
/* 491 */       z = this.g + 1 + (this.imageHeight - this.currentLine);
/* 492 */     } else if (l == 2) {
/* 493 */       x = this.e - this.imageWidth / 2 + this.currentColumn;
/* 494 */       z = this.g - 1 - (this.imageHeight - this.currentLine);
/*     */     }
/* 496 */     if (!blockAlreadyColored(x, y, z, entity)) {
/* 497 */       this.d.e(x, y, z, BlockColor.blockId);
/* 498 */       this.d.a(x, y, z, entity);
/* 499 */       if (this.d.a(x, y, z) != BlockColor.blockId) {
/* 500 */         return true;
/*     */       }
/*     */     }
/*     */ 
/* 504 */     this.currentColumn += 1;
/* 505 */     if (this.currentColumn >= this.imageWidth) {
/* 506 */       this.currentColumn = 0;
/* 507 */       this.currentLine -= 1;
/*     */     }
/* 509 */     return this.currentLine < 0;
/*     */   }
/*     */ 
/*     */   public boolean blockAlreadyColored(int i, int j, int k, TileEntityColor entity)
/*     */   {
/* 521 */     int id = this.d.a(i, j, k);
/* 522 */     if ((id > 0) && ((BlockPictureFactory.dontEraseAnything) || (BlockPictureFactory.dontEraseThese.contains(id + ";")))) {
/* 523 */       return true;
/*     */     }
/* 525 */     jh tmp = this.d.b(i, j, k);
/* 526 */     if (tmp == null) {
/* 527 */       return false;
/*     */     }
/* 529 */     if (!(tmp instanceof TileEntityColor)) {
/* 530 */       return false;
/*     */     }
/* 532 */     TileEntityColor tmp2 = (TileEntityColor)tmp;
/* 533 */     return tmp2.getColor().equals(entity.getColor());
/*     */   }
/*     */ 
/*     */   public int[] nextVisiblePixel(int column, int line)
/*     */   {
/* 543 */     if ((column < 0) || (column >= this.imageWidth))
/* 544 */       return null;
/* 545 */     if ((line < 0) || (line >= this.imageHeight)) {
/* 546 */       return null;
/*     */     }
/* 548 */     int argb = this.image.getRGB(column, line);
/* 549 */     while ((argb >> 24 & 0xFF) < 255) {
/* 550 */       column++;
/* 551 */       if (column >= this.imageWidth) {
/* 552 */         column = 0;
/* 553 */         line--;
/*     */       }
/* 555 */       if (line < 0) {
/* 556 */         return null;
/*     */       }
/* 558 */       argb = this.image.getRGB(column, line);
/*     */     }
/* 560 */     return new int[] { column, line, argb };
/*     */   }
/*     */ 
/*     */   public void resetImage()
/*     */   {
/* 567 */     if (this.image != null)
/* 568 */       this.currentLine = (this.imageHeight - 1);
/*     */     else {
/* 570 */       this.currentLine = 0;
/*     */     }
/* 572 */     this.currentColumn = 0;
/*     */   }
/*     */ 
/*     */   public void updateProgressWidth()
/*     */   {
/* 584 */     if ((this.image == null) || (this.imageWidth < 1)) {
/* 585 */       this.progressWidth = 0;
/*     */     }
/* 587 */     this.progressWidth = ((int)(this.currentColumn / this.imageWidth * 100.0F));
/*     */   }
/*     */ 
/*     */   public void updateProgressHeight()
/*     */   {
/* 594 */     if ((this.image == null) || (this.imageHeight < 1)) {
/* 595 */       this.progressHeight = 0;
/*     */     }
/* 597 */     this.progressHeight = ((int)((this.imageHeight - (this.currentLine + 1)) / this.imageHeight * 100.0F));
/*     */   }
/*     */ 
/*     */   protected int getItemBurnTime(fy itemstack)
/*     */   {
/* 606 */     if (itemstack == null) return 0;
/* 607 */     int i = itemstack.a().bf;
/* 608 */     if ((i < 256) && (na.m[i].bA == hj.d)) return 300;
/* 609 */     if (i == ej.B.bf) return 100;
/* 610 */     if (i == ej.k.bf) return 1600;
/* 611 */     if (i == ej.aw.bf) return 20000;
/* 612 */     if (i == na.z.bn) return 100;
/* 613 */     return ModLoader.AddAllFuel(i);
/*     */   }
/*     */ 
/*     */   public void b(iq nbttagcompound)
/*     */   {
/* 626 */     super.b(nbttagcompound);
/* 627 */     nbttagcompound.a("ImageName", this.imageName);
/* 628 */     nbttagcompound.a("ImageLine", this.currentLine);
/* 629 */     nbttagcompound.a("ImageColumn", this.currentColumn);
/* 630 */     nbttagcompound.a("GenerationTime", this.factoryGenerationTime);
/* 631 */     nbttagcompound.a("IsActivated", this.isActivated);
/* 632 */     nbttagcompound.a("IsBurning", this.isBurning);
/* 633 */     nbttagcompound.a("BurnTime", (short)this.factoryBurnTime);
/* 634 */     nbttagcompound.a("CookTime", (short)this.factoryCookTime);
/*     */ 
/* 636 */     lr nbttaglist = new lr();
/* 637 */     for (int i = 0; i < this.dyeItemStacks.length; i++) {
/* 638 */       if (this.dyeItemStacks[i] != null) {
/* 639 */         iq nbttagcompound1 = new iq();
/* 640 */         nbttagcompound1.a("Slot", (byte)i);
/* 641 */         this.dyeItemStacks[i].a(nbttagcompound1);
/* 642 */         nbttaglist.a(nbttagcompound1);
/*     */       }
/*     */     }
/* 645 */     if (this.coalItemStack != null) {
/* 646 */       iq nbttagcompound1 = new iq();
/* 647 */       nbttagcompound1.a("Slot", (byte)6);
/* 648 */       this.coalItemStack.a(nbttagcompound1);
/* 649 */       nbttaglist.a(nbttagcompound1);
/*     */     }
/* 651 */     nbttagcompound.a("Items", nbttaglist);
/*     */   }
/*     */ 
/*     */   public void a(iq nbttagcompound)
/*     */   {
/* 659 */     super.a(nbttagcompound);
/* 660 */     setImageToGenerate(nbttagcompound.i("ImageName"));
/* 661 */     this.currentLine = nbttagcompound.e("ImageLine");
/* 662 */     this.currentColumn = nbttagcompound.e("ImageColumn");
/* 663 */     updateProgressWidth();
/* 664 */     updateProgressHeight();
/* 665 */     this.factoryGenerationTime = nbttagcompound.e("GenerationTime");
/* 666 */     this.isActivated = nbttagcompound.m("IsActivated");
/* 667 */     this.isBurning = nbttagcompound.m("IsBurning");
/* 668 */     this.factoryBurnTime = nbttagcompound.d("BurnTime");
/* 669 */     this.factoryCookTime = nbttagcompound.d("CookTime");
/*     */ 
/* 671 */     lr nbttaglist = nbttagcompound.l("Items");
/* 672 */     this.dyeItemStacks = new fy[a()];
/* 673 */     for (int i = 0; i < nbttaglist.c(); i++) {
/* 674 */       iq nbttagcompound1 = (iq)nbttaglist.a(i);
/* 675 */       byte byte0 = nbttagcompound1.c("Slot");
/* 676 */       if (byte0 == 6) {
/* 677 */         this.coalItemStack = new fy(nbttagcompound1);
/* 678 */         System.out.println("Loaded coal stack");
/* 679 */       } else if ((byte0 >= 0) && (byte0 < this.dyeItemStacks.length)) {
/* 680 */         this.dyeItemStacks[byte0] = new fy(nbttagcompound1);
/*     */       }
/*     */     }
/* 683 */     this.currentItemBurnTime = getItemBurnTime(this.coalItemStack);
/*     */   }
/*     */ 
/*     */   public String getImageName()
/*     */   {
/* 696 */     return this.imageName;
/*     */   }
/*     */ 
/*     */   public void setImageToGenerate(String name)
/*     */   {
/* 709 */     if (name.equals(this.imageName)) {
/* 710 */       return;
/*     */     }
/* 712 */     this.imageName = name;
/* 713 */     this.currentLine = 0;
/* 714 */     this.currentColumn = 0;
/* 715 */     this.progressWidth = 0;
/* 716 */     this.progressHeight = 0;
/* 717 */     if (name.equals("")) {
/* 718 */       return;
/*     */     }
/* 720 */     this.image = mod_ColoredBlock.getLocalImage(name);
/* 721 */     if (this.image == null) {
/* 722 */       return;
/*     */     }
/* 724 */     this.imageWidth = this.image.getWidth();
/* 725 */     this.imageHeight = this.image.getHeight();
/* 726 */     this.currentLine = (this.imageHeight - 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Server (1)\
 * Qualified Name:     TileEntityPictureFactory
 * JD-Core Version:    0.6.2
 */