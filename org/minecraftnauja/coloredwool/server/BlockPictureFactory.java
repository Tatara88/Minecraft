package org.minecraftnauja.coloredwool.server;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class BlockPictureFactory extends lb
/*     */ {
/*     */   public static boolean dontEraseAnything;
/*     */   public static String dontEraseThese;
/*     */   public static boolean dontRequireItems;
/*     */   public static boolean dontRequireFuel;
/*     */   public static boolean instantCook;
/*     */   public static int spriteTopInactive;
/*     */   public static int spriteTopActive;
/*     */   public static int spriteInsideInactive;
/*     */   public static int spriteInsideActive;
/*     */   public static int spriteInputInactive;
/*     */   public static int spriteInputActive;
/*     */   public static int spriteFurnaceInactive;
/*     */   public static int spriteFurnaceActive;
/*     */   public static int spriteBottom;
/*     */   public static int spriteUnused;
/*     */   public static na blockBurning;
/*     */   public static na blockActive;
/*     */   public static na blockInactive;
/*     */   public static int blockBurningId;
/*     */   public static int blockActiveId;
/*     */   public static int blockInactiveId;
/*     */   public static int guiFurnaceId;
/*     */   public static final char onOpenGuiImage = '\024';
/*     */   public static final char onModifyImageName = '\025';
/*     */   public int index;
/*     */   public final boolean isActive;
/*     */   public final boolean isBurning;
/*     */ 
/*     */   protected BlockPictureFactory(int i, hj material, boolean flag, boolean flag2)
/*     */   {
/* 167 */     super(i, 0, material);
/* 168 */     this.isActive = flag;
/* 169 */     this.isBurning = flag2;
/*     */   }
/*     */ 
/*     */   public static void initialise()
/*     */   {
/* 181 */     if (mod_ColoredBlock.findFreeIds) {
/* 182 */       blockBurningId = ModLoaderMp.GetFreeBlockId();
/* 183 */       blockActiveId = ModLoaderMp.GetFreeBlockId(getIdBurning() + 1, -1);
/* 184 */       blockInactiveId = ModLoaderMp.GetFreeBlockId(getIdActive() + 1, -1);
/*     */     } else {
/* 186 */       blockBurningId = mod_ColoredBlock.properties.get("Ids", "PictureFactoryBlockBurningId", Integer.valueOf(101)).intValue();
/* 187 */       blockActiveId = mod_ColoredBlock.properties.get("Ids", "PictureFactoryBlockActiveId", Integer.valueOf(102)).intValue();
/* 188 */       blockInactiveId = mod_ColoredBlock.properties.get("Ids", "PictureFactoryBlockInactiveId", Integer.valueOf(103)).intValue();
/* 189 */       guiFurnaceId = mod_ColoredBlock.properties.get("Ids", "GuiPictureFactoryFurnaceId", Integer.valueOf(100)).intValue();
/*     */     }
/*     */ 
/* 192 */     dontEraseAnything = mod_ColoredBlock.properties.get("PictureFactory", "DontEraseAnything", Boolean.valueOf(false)).booleanValue();
/* 193 */     dontEraseThese = mod_ColoredBlock.properties.get("PictureFactory", "DontEraseTheseIds", "");
/* 194 */     dontRequireItems = mod_ColoredBlock.properties.get("PictureFactory", "DontRequireItems", Boolean.valueOf(true)).booleanValue();
/* 195 */     dontRequireFuel = mod_ColoredBlock.properties.get("PictureFactory", "DontRequireFuel", Boolean.valueOf(true)).booleanValue();
/* 196 */     instantCook = mod_ColoredBlock.properties.get("PictureFactory", "InstantCook", Boolean.valueOf(true)).booleanValue();
/*     */ 
/* 198 */     spriteTopInactive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/PictureFactory/factoryTopInactive.png");
/* 199 */     spriteTopActive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/PictureFactory/factoryTopActive.png");
/* 200 */     spriteInsideInactive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/PictureFactory/factoryInsideInactive.png");
/* 201 */     spriteInsideActive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/PictureFactory/factoryInsideActive.png");
/* 202 */     spriteInputInactive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/PictureFactory/factoryInputInactive.png");
/* 203 */     spriteInputActive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/PictureFactory/factoryInputActive.png");
/* 204 */     spriteFurnaceInactive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/PictureFactory/factoryFurnaceInactive.png");
/* 205 */     spriteFurnaceActive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/PictureFactory/factoryFurnaceActive.png");
/* 206 */     spriteBottom = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/PictureFactory/factoryBottom.png");
/* 207 */     spriteUnused = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/PictureFactory/factoryUnused.png");
/*     */ 
/* 209 */     blockBurning = new BlockPictureFactory(blockBurningId, hj.d, true, true).c(2.5F).a(0.875F).a(na.e).a("pictureFactoryBurning");
/* 210 */     blockActive = new BlockPictureFactory(blockActiveId, hj.d, true, false).c(2.5F).a(0.432F).a(na.e).a("pictureFactoryActive");
/* 211 */     blockInactive = new BlockPictureFactory(blockInactiveId, hj.d, false, false).c(2.5F).a(na.e).a("pictureFactoryInactive");
/*     */ 
/* 213 */     ModLoader.RegisterBlock(blockBurning);
/* 214 */     ModLoader.RegisterBlock(blockActive);
/* 215 */     ModLoader.RegisterBlock(blockInactive);
/*     */ 
/* 217 */     ModLoader.AddRecipe(new fy(blockInactive, 1), new Object[] { "igi", "wrw", "iwi", Character.valueOf('i'), ej.m, Character.valueOf('g'), na.N, Character.valueOf('w'), na.y, Character.valueOf('r'), ej.aA });
/*     */   }
/*     */ 
/*     */   public int a(int i, int j)
/*     */   {
/* 245 */     if (i == 0) {
/* 246 */       return spriteBottom;
/*     */     }
/*     */ 
/* 249 */     int k = spriteTopInactive;
/* 250 */     if (this.isActive) {
/* 251 */       k = spriteTopActive;
/*     */     }
/* 253 */     if (j == 2) return getTextureWhenFacingEast(i);
/* 254 */     if (j == 3) return getTextureWhenFacingSouth(i);
/* 255 */     if (j == 0) return getTextureWhenFacingWest(i);
/* 256 */     if (j == 1) return getTextureWhenFacingNorth(i);
/* 257 */     if ((j >= 5) && (j <= 8)) return getTextureWhenFacingTop(i, j);
/*     */ 
/* 259 */     return getTextureUnused();
/*     */   }
/*     */ 
/*     */   public int getTextureWhenFacingTop(int i, int j)
/*     */   {
/* 269 */     if (i == 1) {
/* 270 */       return getTextureTop();
/*     */     }
/* 272 */     if (j == 5) {
/* 273 */       if (i == 5)
/* 274 */         return getTextureInput();
/* 275 */       if (i == 3)
/* 276 */         return getTextureFurnace();
/* 277 */       if (i == 4) {
/* 278 */         return getTextureInside();
/*     */       }
/* 280 */       return getTextureUnused();
/*     */     }
/* 282 */     if (j == 6) {
/* 283 */       if (i == 3)
/* 284 */         return getTextureInput();
/* 285 */       if (i == 4)
/* 286 */         return getTextureFurnace();
/* 287 */       if (i == 2) {
/* 288 */         return getTextureInside();
/*     */       }
/* 290 */       return getTextureUnused();
/*     */     }
/* 292 */     if (j == 7) {
/* 293 */       if (i == 4)
/* 294 */         return getTextureInput();
/* 295 */       if (i == 2)
/* 296 */         return getTextureFurnace();
/* 297 */       if (i == 5) {
/* 298 */         return getTextureInside();
/*     */       }
/* 300 */       return getTextureUnused();
/*     */     }
/* 302 */     if (j == 8) {
/* 303 */       if (i == 2)
/* 304 */         return getTextureInput();
/* 305 */       if (i == 5)
/* 306 */         return getTextureFurnace();
/* 307 */       if (i == 3) {
/* 308 */         return getTextureInside();
/*     */       }
/* 310 */       return getTextureUnused();
/*     */     }
/*     */ 
/* 313 */     return getTextureUnused();
/*     */   }
/*     */ 
/*     */   public int getTextureWhenFacingEast(int i)
/*     */   {
/* 323 */     if (i == 2)
/* 324 */       return getTextureTop();
/* 325 */     if (i == 5)
/* 326 */       return getTextureInput();
/* 327 */     if (i == 3)
/* 328 */       return getTextureFurnace();
/* 329 */     if (i == 4) {
/* 330 */       return getTextureInside();
/*     */     }
/* 332 */     return getTextureUnused();
/*     */   }
/*     */ 
/*     */   public int getTextureWhenFacingWest(int i)
/*     */   {
/* 342 */     if (i == 3)
/* 343 */       return getTextureTop();
/* 344 */     if (i == 4)
/* 345 */       return getTextureInput();
/* 346 */     if (i == 2)
/* 347 */       return getTextureFurnace();
/* 348 */     if (i == 5) {
/* 349 */       return getTextureInside();
/*     */     }
/* 351 */     return getTextureUnused();
/*     */   }
/*     */ 
/*     */   public int getTextureWhenFacingSouth(int i)
/*     */   {
/* 361 */     if (i == 5)
/* 362 */       return getTextureTop();
/* 363 */     if (i == 3)
/* 364 */       return getTextureInput();
/* 365 */     if (i == 4)
/* 366 */       return getTextureFurnace();
/* 367 */     if (i == 2) {
/* 368 */       return getTextureInside();
/*     */     }
/* 370 */     return getTextureUnused();
/*     */   }
/*     */ 
/*     */   public int getTextureWhenFacingNorth(int i)
/*     */   {
/* 380 */     if (i == 4)
/* 381 */       return getTextureTop();
/* 382 */     if (i == 2)
/* 383 */       return getTextureInput();
/* 384 */     if (i == 5)
/* 385 */       return getTextureFurnace();
/* 386 */     if (i == 3) {
/* 387 */       return getTextureInside();
/*     */     }
/* 389 */     return getTextureUnused();
/*     */   }
/*     */ 
/*     */   public int getTextureTop()
/*     */   {
/* 398 */     return this.isActive ? spriteTopActive : spriteTopInactive;
/*     */   }
/*     */ 
/*     */   public int getTextureInput()
/*     */   {
/* 406 */     return this.isActive ? spriteInputActive : spriteInputInactive;
/*     */   }
/*     */ 
/*     */   public int getTextureFurnace()
/*     */   {
/* 414 */     return (this.isActive) && (this.isBurning) ? spriteFurnaceActive : spriteFurnaceInactive;
/*     */   }
/*     */ 
/*     */   public int getTextureInside()
/*     */   {
/* 422 */     return spriteInsideInactive;
/*     */   }
/*     */ 
/*     */   public int getTextureUnused()
/*     */   {
/* 430 */     return spriteUnused;
/*     */   }
/*     */ 
/*     */   public void a(dj world, int i, int j, int k, hl entity)
/*     */   {
/* 447 */     int l = fq.b(entity.aV * 4.0F / 360.0F + 2.5D) & 0x3;
/* 448 */     if (j < (int)entity.aQ - 1) {
/* 449 */       l += 5;
/*     */     }
/* 451 */     world.c(i, j, k, l);
/*     */   }
/*     */ 
/*     */   public static void updateFactoryBlockState(boolean flag, dj world, int i, int j, int k)
/*     */   {
/* 463 */     jh entity = world.b(i, j, k);
/* 464 */     if (entity == null)
/* 465 */       return;
/* 466 */     if (!(entity instanceof TileEntityPictureFactory)) {
/* 467 */       return;
/*     */     }
/* 469 */     TileEntityPictureFactory factory = (TileEntityPictureFactory)entity;
/* 470 */     updateFactoryBlockState(factory.isActivated, flag, world, i, j, k);
/*     */   }
/*     */ 
/*     */   public static void updateFactoryBlockState(boolean flag, boolean flag2, dj world, int i, int j, int k)
/*     */   {
/* 483 */     jh entity = world.b(i, j, k);
/* 484 */     if (entity == null)
/* 485 */       return;
/* 486 */     if (!(entity instanceof TileEntityPictureFactory)) {
/* 487 */       return;
/*     */     }
/* 489 */     TileEntityPictureFactory factory = (TileEntityPictureFactory)entity;
/* 490 */     int l = world.c(i, j, k);
/* 491 */     if (flag) {
/* 492 */       if (flag2)
/* 493 */         world.e(i, j, k, getIdBurning());
/*     */       else
/* 495 */         world.e(i, j, k, getIdActive());
/*     */     }
/*     */     else {
/* 498 */       world.e(i, j, k, getIdInactive());
/*     */     }
/* 500 */     world.c(i, j, k, l);
/* 501 */     factory.isActivated = flag;
/* 502 */     factory.isBurning = flag2;
/* 503 */     factory.j();
/* 504 */     world.a(i, j, k, factory);
/*     */   }
/*     */ 
/*     */   public boolean a(dj world, int i, int j, int k, em player)
/*     */   {
/* 521 */     if (world.B) {
/* 522 */       return true;
/*     */     }
/* 524 */     if (j < (int)player.aQ - 1) {
/* 525 */       return false;
/*     */     }
/* 527 */     int l = fq.b(player.aV * 4.0F / 360.0F + 2.5D) & 0x3;
/* 528 */     int metadata = world.c(i, j, k);
/* 529 */     TileEntityPictureFactory entity = (TileEntityPictureFactory)world.b(i, j, k);
/* 530 */     if (entity == null) {
/* 531 */       return false;
/*     */     }
/*     */ 
/* 534 */     if (((metadata == 2) || (metadata == 5)) && (l == 3)) {
/* 535 */       sendOpenGuiImage(player, i, j, k, entity);
/* 536 */       return true;
/* 537 */     }if (((metadata == 3) || (metadata == 6)) && (l == 0)) {
/* 538 */       sendOpenGuiImage(player, i, j, k, entity);
/* 539 */       return true;
/* 540 */     }if (((metadata == 0) || (metadata == 7)) && (l == 1)) {
/* 541 */       sendOpenGuiImage(player, i, j, k, entity);
/* 542 */       return true;
/* 543 */     }if (((metadata == 1) || (metadata == 8)) && (l == 2)) {
/* 544 */       sendOpenGuiImage(player, i, j, k, entity);
/* 545 */       return true;
/*     */     }
/*     */ 
/* 548 */     if (((metadata == 2) || (metadata == 5)) && (l == 0)) {
/* 549 */       ModLoader.OpenGUI(player, guiFurnaceId, player.i, new ContainerPictureFactoryFurnace(player.i, entity));
/* 550 */       return true;
/* 551 */     }if (((metadata == 3) || (metadata == 6)) && (l == 1)) {
/* 552 */       ModLoader.OpenGUI(player, guiFurnaceId, player.i, new ContainerPictureFactoryFurnace(player.i, entity));
/* 553 */       return true;
/* 554 */     }if (((metadata == 0) || (metadata == 7)) && (l == 2)) {
/* 555 */       ModLoader.OpenGUI(player, guiFurnaceId, player.i, new ContainerPictureFactoryFurnace(player.i, entity));
/* 556 */       return true;
/* 557 */     }if (((metadata == 1) || (metadata == 8)) && (l == 3)) {
/* 558 */       ModLoader.OpenGUI(player, guiFurnaceId, player.i, new ContainerPictureFactoryFurnace(player.i, entity));
/* 559 */       return true;
/*     */     }
/* 561 */     return false;
/*     */   }
/*     */ 
/*     */   public void b(dj world, int i, int j, int k, em player)
/*     */   {
/* 573 */     if (world.B) {
/* 574 */       return;
/*     */     }
/* 576 */     if (j < (int)player.aQ - 1) {
/* 577 */       return;
/*     */     }
/* 579 */     int l = fq.b(player.aV * 4.0F / 360.0F + 2.5D) & 0x3;
/* 580 */     int metadata = world.c(i, j, k);
/* 581 */     if (((metadata == 2) || (metadata == 5)) && (l == 3))
/* 582 */       updateFactoryBlockState(!this.isActive, this.isBurning, world, i, j, k);
/* 583 */     else if (((metadata == 3) || (metadata == 6)) && (l == 0))
/* 584 */       updateFactoryBlockState(!this.isActive, this.isBurning, world, i, j, k);
/* 585 */     else if (((metadata == 0) || (metadata == 7)) && (l == 1))
/* 586 */       updateFactoryBlockState(!this.isActive, this.isBurning, world, i, j, k);
/* 587 */     else if (((metadata == 1) || (metadata == 8)) && (l == 2))
/* 588 */       updateFactoryBlockState(!this.isActive, this.isBurning, world, i, j, k);
/*     */   }
/*     */ 
/*     */   public static void sendOpenGuiImage(em player, int i, int j, int k, TileEntityPictureFactory entity)
/*     */   {
/* 606 */     Packet230ModLoader packet = new Packet230ModLoader();
/* 607 */     packet.packetType = 20;
/* 608 */     packet.dataInt = new int[] { i, j, k };
/* 609 */     packet.dataString = new String[] { entity.imageName };
/* 610 */     ModLoaderMp.SendPacketTo(mod_ColoredBlock.instance, (dl)player, packet);
/*     */   }
/*     */ 
/*     */   public static void handleModifyImageName(Packet230ModLoader packet, dj world)
/*     */   {
/* 619 */     int[] pos = packet.dataInt;
/* 620 */     String[] img = packet.dataString;
/* 621 */     if ((pos == null) || (img == null))
/* 622 */       return;
/* 623 */     if ((pos.length != 3) || (img.length != 1)) {
/* 624 */       return;
/*     */     }
/* 626 */     jh entity = world.b(pos[0], pos[1], pos[2]);
/* 627 */     if (entity == null)
/* 628 */       return;
/* 629 */     if (!(entity instanceof TileEntityPictureFactory)) {
/* 630 */       return;
/*     */     }
/* 632 */     ((TileEntityPictureFactory)entity).setImageToGenerate(packet.dataString[0]);
/*     */   }
/*     */ 
/*     */   protected jh a_()
/*     */   {
/* 645 */     return new TileEntityPictureFactory();
/*     */   }
/*     */ 
/*     */   public int a(int i, Random random)
/*     */   {
/* 655 */     return blockInactive.bn;
/*     */   }
/*     */ 
/*     */   public static int getIdBurning()
/*     */   {
/* 663 */     return blockBurningId;
/*     */   }
/*     */ 
/*     */   public static int getIdActive()
/*     */   {
/* 671 */     return blockActiveId;
/*     */   }
/*     */ 
/*     */   public static int getIdInactive()
/*     */   {
/* 679 */     return blockInactiveId;
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Server (1)\
 * Qualified Name:     BlockPictureFactory
 * JD-Core Version:    0.6.2
 */