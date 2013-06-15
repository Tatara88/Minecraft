package org.minecraftnauja.coloredwool.client;
/*     */ import java.util.Random;

import org.minecraftnauja.coloredwool.ColoredWool;

import net.minecraft.client.Minecraft;
/*     */ 
/*     */ public class BlockModelFactory extends rw
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
/*     */   public static uu blockBurning;
/*     */   public static uu blockActive;
/*     */   public static uu blockInactive;
/*     */   public static int blockBurningId;
/*     */   public static int blockActiveId;
/*     */   public static int blockInactiveId;
/*     */   public static int guiFurnaceId;
/*     */   public static final char onOpenGuiImage = '\036';
/*     */   public static final char onModifyImageName = '\037';
/*     */   public int index;
/*     */   public final boolean isActive;
/*     */   public final boolean isBurning;
/*     */ 
/*     */   protected BlockModelFactory(int i, ln material, boolean flag, boolean flag2)
/*     */   {
/* 167 */     super(i, 0, material);
/* 168 */     this.isActive = flag;
/* 169 */     this.isBurning = flag2;
/*     */   }
/*     */ 
/*     */   public static void initialise()
/*     */   {
/* 181 */     if (ColoredWool.findFreeIds) {
/* 182 */       blockBurningId = ModLoaderMp.GetFreeBlockId(-1, -1);
/* 183 */       blockActiveId = ModLoaderMp.GetFreeBlockId(getIdBurning() + 1, -1);
/* 184 */       blockInactiveId = ModLoaderMp.GetFreeBlockId(getIdActive() + 1, -1);
/*     */     } else {
/* 186 */       blockBurningId = ColoredWool.properties.get("Ids", "ModelFactoryBlockBurningId", Integer.valueOf(104)).intValue();
/* 187 */       blockActiveId = ColoredWool.properties.get("Ids", "ModelFactoryBlockActiveId", Integer.valueOf(105)).intValue();
/* 188 */       blockInactiveId = ColoredWool.properties.get("Ids", "ModelFactoryBlockInactiveId", Integer.valueOf(106)).intValue();
/* 189 */       guiFurnaceId = ColoredWool.properties.get("Ids", "GuiModelFactoryFurnaceId", Integer.valueOf(101)).intValue();
/*     */     }
/*     */ 
/* 192 */     dontEraseAnything = ColoredWool.properties.get("ModelFactory", "DontEraseAnything", Boolean.valueOf(false)).booleanValue();
/* 193 */     dontEraseThese = ColoredWool.properties.get("ModelFactory", "DontEraseTheseIds", "");
/* 194 */     dontRequireItems = ColoredWool.properties.get("ModelFactory", "DontRequireItems", Boolean.valueOf(true)).booleanValue();
/* 195 */     dontRequireFuel = ColoredWool.properties.get("ModelFactory", "DontRequireFuel", Boolean.valueOf(true)).booleanValue();
/* 196 */     instantCook = ColoredWool.properties.get("ModelFactory", "InstantCook", Boolean.valueOf(true)).booleanValue();
/*     */ 
/* 198 */     spriteTopInactive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/ModelFactory/factoryTopInactive.png");
/* 199 */     spriteTopActive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/ModelFactory/factoryTopActive.png");
/* 200 */     spriteInsideInactive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/ModelFactory/factoryInsideInactive.png");
/* 201 */     spriteInsideActive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/ModelFactory/factoryInsideActive.png");
/* 202 */     spriteInputInactive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/ModelFactory/factoryInputInactive.png");
/* 203 */     spriteInputActive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/ModelFactory/factoryInputActive.png");
/* 204 */     spriteFurnaceInactive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/ModelFactory/factoryFurnaceInactive.png");
/* 205 */     spriteFurnaceActive = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/ModelFactory/factoryFurnaceActive.png");
/* 206 */     spriteBottom = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/ModelFactory/factoryBottom.png");
/* 207 */     spriteUnused = ModLoader.addOverride("/terrain.png", "/ColoredBlockSprites/ModelFactory/factoryUnused.png");
/*     */ 
/* 209 */     blockBurning = new BlockModelFactory(blockBurningId, ln.d, true, true).c(2.5F).a(0.875F).a(uu.e).a("modelFactoryBurning");
/* 210 */     blockActive = new BlockModelFactory(blockActiveId, ln.d, true, false).c(2.5F).a(0.432F).a(uu.e).a("modelFactoryActive");
/* 211 */     blockInactive = new BlockModelFactory(blockInactiveId, ln.d, false, false).c(2.5F).a(uu.e).a("modelFactoryInactive");
/*     */ 
/* 213 */     ModLoader.RegisterBlock(blockBurning);
/* 214 */     ModLoader.RegisterBlock(blockActive);
/* 215 */     ModLoader.RegisterBlock(blockInactive);
/*     */ 
/* 217 */     ModLoader.AddName(blockBurning, "Model Factory");
/* 218 */     ModLoader.AddName(blockActive, "Model Factory");
/* 219 */     ModLoader.AddName(blockInactive, "Model Factory");
/*     */ 
/* 221 */     ModLoader.AddRecipe(new iz(blockInactive, 1), new Object[] { "ogo", "wrw", "owo", Character.valueOf('o'), gm.n, Character.valueOf('g'), uu.N, Character.valueOf('w'), uu.y, Character.valueOf('r'), gm.aA });
/*     */ 
/* 229 */     ModLoaderMp.RegisterGUI(ColoredWool.instance, guiFurnaceId);
/*     */   }
/*     */ 
/*     */   public int a(int i, int j)
/*     */   {
/* 251 */     if (i == 0) {
/* 252 */       return spriteBottom;
/*     */     }
/*     */ 
/* 255 */     int k = spriteTopInactive;
/* 256 */     if (this.isActive) {
/* 257 */       k = spriteTopActive;
/*     */     }
/* 259 */     if (j == 2) return getTextureWhenFacingEast(i);
/* 260 */     if (j == 3) return getTextureWhenFacingSouth(i);
/* 261 */     if (j == 0) return getTextureWhenFacingWest(i);
/* 262 */     if (j == 1) return getTextureWhenFacingNorth(i);
/* 263 */     if ((j >= 5) && (j <= 8)) return getTextureWhenFacingTop(i, j);
/*     */ 
/* 265 */     return getTextureUnused();
/*     */   }
/*     */ 
/*     */   public int getTextureWhenFacingTop(int i, int j)
/*     */   {
/* 275 */     if (i == 1) {
/* 276 */       return getTextureTop();
/*     */     }
/* 278 */     if (j == 5) {
/* 279 */       if (i == 5)
/* 280 */         return getTextureInput();
/* 281 */       if (i == 3)
/* 282 */         return getTextureFurnace();
/* 283 */       if (i == 4) {
/* 284 */         return getTextureInside();
/*     */       }
/* 286 */       return getTextureUnused();
/*     */     }
/* 288 */     if (j == 6) {
/* 289 */       if (i == 3)
/* 290 */         return getTextureInput();
/* 291 */       if (i == 4)
/* 292 */         return getTextureFurnace();
/* 293 */       if (i == 2) {
/* 294 */         return getTextureInside();
/*     */       }
/* 296 */       return getTextureUnused();
/*     */     }
/* 298 */     if (j == 7) {
/* 299 */       if (i == 4)
/* 300 */         return getTextureInput();
/* 301 */       if (i == 2)
/* 302 */         return getTextureFurnace();
/* 303 */       if (i == 5) {
/* 304 */         return getTextureInside();
/*     */       }
/* 306 */       return getTextureUnused();
/*     */     }
/* 308 */     if (j == 8) {
/* 309 */       if (i == 2)
/* 310 */         return getTextureInput();
/* 311 */       if (i == 5)
/* 312 */         return getTextureFurnace();
/* 313 */       if (i == 3) {
/* 314 */         return getTextureInside();
/*     */       }
/* 316 */       return getTextureUnused();
/*     */     }
/*     */ 
/* 319 */     return getTextureUnused();
/*     */   }
/*     */ 
/*     */   public int getTextureWhenFacingEast(int i)
/*     */   {
/* 329 */     if (i == 2)
/* 330 */       return getTextureTop();
/* 331 */     if (i == 5)
/* 332 */       return getTextureInput();
/* 333 */     if (i == 3)
/* 334 */       return getTextureFurnace();
/* 335 */     if (i == 4) {
/* 336 */       return getTextureInside();
/*     */     }
/* 338 */     return getTextureUnused();
/*     */   }
/*     */ 
/*     */   public int getTextureWhenFacingWest(int i)
/*     */   {
/* 348 */     if (i == 3)
/* 349 */       return getTextureTop();
/* 350 */     if (i == 4)
/* 351 */       return getTextureInput();
/* 352 */     if (i == 2)
/* 353 */       return getTextureFurnace();
/* 354 */     if (i == 5) {
/* 355 */       return getTextureInside();
/*     */     }
/* 357 */     return getTextureUnused();
/*     */   }
/*     */ 
/*     */   public int getTextureWhenFacingSouth(int i)
/*     */   {
/* 367 */     if (i == 5)
/* 368 */       return getTextureTop();
/* 369 */     if (i == 3)
/* 370 */       return getTextureInput();
/* 371 */     if (i == 4)
/* 372 */       return getTextureFurnace();
/* 373 */     if (i == 2) {
/* 374 */       return getTextureInside();
/*     */     }
/* 376 */     return getTextureUnused();
/*     */   }
/*     */ 
/*     */   public int getTextureWhenFacingNorth(int i)
/*     */   {
/* 386 */     if (i == 4)
/* 387 */       return getTextureTop();
/* 388 */     if (i == 2)
/* 389 */       return getTextureInput();
/* 390 */     if (i == 5)
/* 391 */       return getTextureFurnace();
/* 392 */     if (i == 3) {
/* 393 */       return getTextureInside();
/*     */     }
/* 395 */     return getTextureUnused();
/*     */   }
/*     */ 
/*     */   public int getTextureTop()
/*     */   {
/* 404 */     return this.isActive ? spriteTopActive : spriteTopInactive;
/*     */   }
/*     */ 
/*     */   public int getTextureInput()
/*     */   {
/* 412 */     return this.isActive ? spriteInputActive : spriteInputInactive;
/*     */   }
/*     */ 
/*     */   public int getTextureFurnace()
/*     */   {
/* 420 */     return (this.isActive) && (this.isBurning) ? spriteFurnaceActive : spriteFurnaceInactive;
/*     */   }
/*     */ 
/*     */   public int getTextureInside()
/*     */   {
/* 428 */     return spriteInsideInactive;
/*     */   }
/*     */ 
/*     */   public int getTextureUnused()
/*     */   {
/* 436 */     return spriteUnused;
/*     */   }
/*     */ 
/*     */   public void b(fd world, int i, int j, int k, Random random)
/*     */   {
/* 453 */     if ((!this.isActive) || (!this.isBurning)) {
/* 454 */       return;
/*     */     }
/* 456 */     int l = world.e(i, j, k);
/* 457 */     float f = i + 0.5F;
/* 458 */     float f1 = j + 0.0F + random.nextFloat() * 12.0F / 16.0F;
/* 459 */     float f2 = k + 0.5F;
/* 460 */     float f3 = 0.52F;
/* 461 */     float f4 = random.nextFloat() * 0.6F - 0.3F;
/* 462 */     if ((l == 3) || (l == 6)) {
/* 463 */       world.a("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 464 */       world.a("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 465 */     } else if ((l == 1) || (l == 8)) {
/* 466 */       world.a("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 467 */       world.a("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
/* 468 */     } else if ((l == 0) || (l == 7)) {
/* 469 */       world.a("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
/* 470 */       world.a("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
/* 471 */     } else if ((l == 2) || (l == 5)) {
/* 472 */       world.a("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
/* 473 */       world.a("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void a(fd world, int i, int j, int k, ls entity)
/*     */   {
/* 491 */     int l = in.b(entity.aS * 4.0F / 360.0F + 2.5D) & 0x3;
/* 492 */     if (j < (int)entity.aN - 1) {
/* 493 */       l += 5;
/*     */     }
/* 495 */     world.d(i, j, k, l);
/*     */   }
/*     */ 
/*     */   public static void updateFactoryBlockState(boolean flag, fd world, int i, int j, int k)
/*     */   {
/* 507 */     ow entity = world.b(i, j, k);
/* 508 */     if (entity == null)
/* 509 */       return;
/* 510 */     if (!(entity instanceof TileEntityModelFactory)) {
/* 511 */       return;
/*     */     }
/* 513 */     TileEntityModelFactory factory = (TileEntityModelFactory)entity;
/* 514 */     updateFactoryBlockState(factory.isActivated, flag, world, i, j, k);
/*     */   }
/*     */ 
/*     */   public static void updateFactoryBlockState(boolean flag, boolean flag2, fd world, int i, int j, int k)
/*     */   {
/* 527 */     ow entity = world.b(i, j, k);
/* 528 */     if (entity == null)
/* 529 */       return;
/* 530 */     if (!(entity instanceof TileEntityModelFactory)) {
/* 531 */       return;
/*     */     }
/* 533 */     TileEntityModelFactory factory = (TileEntityModelFactory)entity;
/* 534 */     int l = world.e(i, j, k);
/* 535 */     if (flag) {
/* 536 */       if (flag2)
/* 537 */         world.f(i, j, k, getIdBurning());
/*     */       else
/* 539 */         world.f(i, j, k, getIdActive());
/*     */     }
/*     */     else {
/* 542 */       world.f(i, j, k, getIdInactive());
/*     */     }
/* 544 */     world.d(i, j, k, l);
/* 545 */     factory.isActivated = flag;
/* 546 */     factory.isBurning = flag2;
/* 547 */     factory.j();
/* 548 */     world.a(i, j, k, factory);
/*     */   }
/*     */ 
/*     */   public boolean a(fd world, int i, int j, int k, gs player)
/*     */   {
/* 565 */     if (world.B) {
/* 566 */       return true;
/*     */     }
/* 568 */     if (j < (int)player.aN - 1) {
/* 569 */       return false;
/*     */     }
/* 571 */     int l = in.b(player.aS * 4.0F / 360.0F + 2.5D) & 0x3;
/* 572 */     int metadata = world.e(i, j, k);
/* 573 */     TileEntityModelFactory entity = (TileEntityModelFactory)world.b(i, j, k);
/* 574 */     if (entity == null) {
/* 575 */       return false;
/*     */     }
/*     */ 
/* 578 */     if (((metadata == 2) || (metadata == 5)) && (l == 3)) {
/* 579 */       ModLoader.OpenGUI(player, new GuiModelFactoryMenu(player, entity));
/* 580 */       return true;
/* 581 */     }if (((metadata == 3) || (metadata == 6)) && (l == 0)) {
/* 582 */       ModLoader.OpenGUI(player, new GuiModelFactoryMenu(player, entity));
/* 583 */       return true;
/* 584 */     }if (((metadata == 0) || (metadata == 7)) && (l == 1)) {
/* 585 */       ModLoader.OpenGUI(player, new GuiModelFactoryMenu(player, entity));
/* 586 */       return true;
/* 587 */     }if (((metadata == 1) || (metadata == 8)) && (l == 2)) {
/* 588 */       ModLoader.OpenGUI(player, new GuiModelFactoryMenu(player, entity));
/* 589 */       return true;
/*     */     }
/*     */ 
/* 592 */     if (((metadata == 2) || (metadata == 5)) && (l == 0)) {
/* 593 */       ModLoader.OpenGUI(player, new GuiModelFactoryFurnace(player.c, entity));
/* 594 */       return true;
/* 595 */     }if (((metadata == 3) || (metadata == 6)) && (l == 1)) {
/* 596 */       ModLoader.OpenGUI(player, new GuiModelFactoryFurnace(player.c, entity));
/* 597 */       return true;
/* 598 */     }if (((metadata == 0) || (metadata == 7)) && (l == 2)) {
/* 599 */       ModLoader.OpenGUI(player, new GuiModelFactoryFurnace(player.c, entity));
/* 600 */       return true;
/* 601 */     }if (((metadata == 1) || (metadata == 8)) && (l == 3)) {
/* 602 */       ModLoader.OpenGUI(player, new GuiModelFactoryFurnace(player.c, entity));
/* 603 */       return true;
/*     */     }
/* 605 */     return false;
/*     */   }
/*     */ 
/*     */   public void b(fd world, int i, int j, int k, gs player)
/*     */   {
/* 617 */     if (world.B) {
/* 618 */       return;
/*     */     }
/* 620 */     if (j < (int)player.aN - 1) {
/* 621 */       return;
/*     */     }
/* 623 */     int l = in.b(player.aS * 4.0F / 360.0F + 2.5D) & 0x3;
/* 624 */     int metadata = world.e(i, j, k);
/* 625 */     if (((metadata == 2) || (metadata == 5)) && (l == 3))
/* 626 */       updateFactoryBlockState(!this.isActive, this.isBurning, world, i, j, k);
/* 627 */     else if (((metadata == 3) || (metadata == 6)) && (l == 0))
/* 628 */       updateFactoryBlockState(!this.isActive, this.isBurning, world, i, j, k);
/* 629 */     else if (((metadata == 0) || (metadata == 7)) && (l == 1))
/* 630 */       updateFactoryBlockState(!this.isActive, this.isBurning, world, i, j, k);
/* 631 */     else if (((metadata == 1) || (metadata == 8)) && (l == 2))
/* 632 */       updateFactoryBlockState(!this.isActive, this.isBurning, world, i, j, k);
/*     */   }
/*     */ 
/*     */   public static void sendModifyImageName(TileEntityModelFactory entity, String name)
/*     */   {
/* 648 */     Packet230ModLoader packet = new Packet230ModLoader();
/* 649 */     packet.packetType = 31;
/* 650 */     packet.dataInt = new int[] { entity.e, entity.f, entity.g };
/* 651 */     packet.dataString = new String[] { name };
/* 652 */     ModLoaderMp.SendPacket(ColoredWool.instance, packet);
/*     */   }
/*     */ 
/*     */   public static void handleOpenGuiImage(Packet230ModLoader packet)
/*     */   {
/* 660 */     int[] pos = packet.dataInt;
/* 661 */     if (pos == null)
/* 662 */       return;
/* 663 */     if (pos.length != 3) {
/* 664 */       return;
/*     */     }
/* 666 */     ow entity = ModLoader.getMinecraftInstance().f.b(pos[0], pos[1], pos[2]);
/* 667 */     if (entity == null)
/* 668 */       return;
/* 669 */     if (!(entity instanceof TileEntityModelFactory)) {
/* 670 */       return;
/*     */     }
/* 672 */     gs player = ColoredWool.getPlayer();
/* 673 */     ModLoader.OpenGUI(player, new GuiModelFactoryMenu(player, (TileEntityModelFactory)entity));
/*     */   }
/*     */ 
/*     */   protected ow a_()
/*     */   {
/* 686 */     return new TileEntityModelFactory();
/*     */   }
/*     */ 
/*     */   public int a(int i, Random random)
/*     */   {
/* 696 */     return blockInactive.bn;
/*     */   }
/*     */ 
/*     */   public static int getIdBurning()
/*     */   {
/* 704 */     return blockBurningId;
/*     */   }
/*     */ 
/*     */   public static int getIdActive()
/*     */   {
/* 712 */     return blockActiveId;
/*     */   }
/*     */ 
/*     */   public static int getIdInactive()
/*     */   {
/* 720 */     return blockInactiveId;
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     BlockModelFactory
 * JD-Core Version:    0.6.2
 */