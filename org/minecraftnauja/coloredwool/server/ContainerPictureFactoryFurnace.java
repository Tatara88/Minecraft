package org.minecraftnauja.coloredwool.server;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ContainerPictureFactoryFurnace extends cl
/*     */ {
/*     */   private TileEntityPictureFactory factory;
/*     */   private int progressWidth;
/*     */   private int progressHeight;
/*     */   private int cookTime;
/*     */   private int burnTime;
/*     */ 
/*     */   public ContainerPictureFactoryFurnace(fx inventory, TileEntityPictureFactory factory)
/*     */   {
/*  47 */     this.cookTime = 0;
/*  48 */     this.burnTime = 0;
/*  49 */     this.progressWidth = 0;
/*  50 */     this.progressHeight = 0;
/*  51 */     this.factory = factory;
/*     */ 
/*  53 */     for (int i = 0; i < 3; i++) {
/*  54 */       for (int k = 0; k < 2; k++) {
/*  55 */         a(new el(factory, k + i * 2, 8 + k * 18, 17 + i * 18));
/*     */       }
/*     */     }
/*  58 */     a(new el(factory, 6, 80, 35));
/*     */ 
/*  60 */     for (int i = 0; i < 3; i++) {
/*  61 */       for (int k = 0; k < 9; k++) {
/*  62 */         a(new el(inventory, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  67 */     for (int j = 0; j < 9; j++)
/*  68 */       a(new el(inventory, j, 8 + j * 18, 142));
/*     */   }
/*     */ 
/*     */   public void a(cp icrafting)
/*     */   {
/*  82 */     super.a(icrafting);
/*  83 */     icrafting.a(this, 0, this.factory.factoryCookTime);
/*  84 */     icrafting.a(this, 1, this.factory.progressWidth);
/*  85 */     icrafting.a(this, 2, this.factory.progressHeight);
/*  86 */     icrafting.a(this, 3, this.factory.factoryBurnTime);
/*     */   }
/*     */ 
/*     */   public void a()
/*     */   {
/*  93 */     super.a();
/*  94 */     for (int i = 0; i < this.g.size(); i++) {
/*  95 */       cp icrafting = (cp)this.g.get(i);
/*  96 */       if (this.cookTime != this.factory.factoryCookTime)
/*  97 */         icrafting.a(this, 0, this.factory.factoryCookTime);
/*  98 */       else if (this.progressWidth != this.factory.progressWidth)
/*  99 */         icrafting.a(this, 1, this.factory.progressWidth);
/* 100 */       else if (this.progressHeight != this.factory.progressHeight)
/* 101 */         icrafting.a(this, 2, this.factory.progressHeight);
/* 102 */       else if (this.burnTime != this.factory.factoryBurnTime) {
/* 103 */         icrafting.a(this, 3, this.factory.factoryBurnTime);
/*     */       }
/*     */     }
/*     */ 
/* 107 */     this.cookTime = this.factory.factoryCookTime;
/* 108 */     this.burnTime = this.factory.factoryBurnTime;
/* 109 */     this.progressWidth = this.factory.progressWidth;
/* 110 */     this.progressHeight = this.factory.progressHeight;
/*     */   }
/*     */ 
/*     */   public boolean b(em player)
/*     */   {
/* 119 */     return this.factory.a_(player);
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Server (1)\
 * Qualified Name:     ContainerPictureFactoryFurnace
 * JD-Core Version:    0.6.2
 */