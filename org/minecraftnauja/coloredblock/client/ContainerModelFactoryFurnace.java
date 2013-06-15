/*     */ import java.util.List;
/*     */ 
/*     */ public class ContainerModelFactoryFurnace extends dw
/*     */ {
/*     */   private TileEntityModelFactory factory;
/*     */   private int progressWidth;
/*     */   private int progressHeight;
/*     */   private int cookTime;
/*     */   private int burnTime;
/*     */ 
/*     */   public ContainerModelFactoryFurnace(ix inventory, TileEntityModelFactory factory)
/*     */   {
/*  47 */     this.cookTime = 0;
/*  48 */     this.burnTime = 0;
/*  49 */     this.progressWidth = 0;
/*  50 */     this.progressHeight = 0;
/*  51 */     this.factory = factory;
/*     */ 
/*  53 */     for (int i = 0; i < 3; i++) {
/*  54 */       for (int k = 0; k < 2; k++) {
/*  55 */         a(new gp(factory, k + i * 2, 8 + k * 18, 17 + i * 18));
/*     */       }
/*     */     }
/*  58 */     a(new gp(factory, 6, 80, 35));
/*     */ 
/*  60 */     for (int i = 0; i < 3; i++) {
/*  61 */       for (int k = 0; k < 9; k++) {
/*  62 */         a(new gp(inventory, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  67 */     for (int j = 0; j < 9; j++)
/*  68 */       a(new gp(inventory, j, 8 + j * 18, 142));
/*     */   }
/*     */ 
/*     */   public void a()
/*     */   {
/*  81 */     super.a();
/*  82 */     for (int i = 0; i < this.g.size(); i++) {
/*  83 */       ec icrafting = (ec)this.g.get(i);
/*  84 */       if (this.cookTime != this.factory.factoryCookTime)
/*  85 */         icrafting.a(this, 0, this.factory.factoryCookTime);
/*  86 */       else if (this.progressWidth != this.factory.progressWidth)
/*  87 */         icrafting.a(this, 1, this.factory.progressWidth);
/*  88 */       else if (this.progressHeight != this.factory.progressHeight)
/*  89 */         icrafting.a(this, 2, this.factory.progressHeight);
/*  90 */       else if (this.burnTime != this.factory.factoryBurnTime) {
/*  91 */         icrafting.a(this, 3, this.factory.factoryBurnTime);
/*     */       }
/*     */     }
/*     */ 
/*  95 */     this.cookTime = this.factory.factoryCookTime;
/*  96 */     this.burnTime = this.factory.factoryBurnTime;
/*  97 */     this.progressWidth = this.factory.progressWidth;
/*  98 */     this.progressHeight = this.factory.progressHeight;
/*     */   }
/*     */ 
/*     */   public void a(int i, int j)
/*     */   {
/* 105 */     if (i == 0)
/* 106 */       this.factory.factoryCookTime = j;
/* 107 */     else if (i == 1)
/* 108 */       this.factory.progressWidth = j;
/* 109 */     else if (i == 2)
/* 110 */       this.factory.progressHeight = j;
/* 111 */     else if (i == 3)
/* 112 */       this.factory.factoryBurnTime = j;
/*     */   }
/*     */ 
/*     */   public boolean b(gs player)
/*     */   {
/* 122 */     if (this.factory == null) {
/* 123 */       return false;
/*     */     }
/* 125 */     return this.factory.a_(player);
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     ContainerModelFactoryFurnace
 * JD-Core Version:    0.6.2
 */