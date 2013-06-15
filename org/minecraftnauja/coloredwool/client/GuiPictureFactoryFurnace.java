package org.minecraftnauja.coloredwool.client;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class GuiPictureFactoryFurnace extends id
/*    */ {
/*    */   private TileEntityPictureFactory factory;
/*    */ 
/*    */   public GuiPictureFactoryFurnace(ix inventoryplayer, TileEntityPictureFactory factory)
/*    */   {
/* 31 */     super(new ContainerPictureFactoryFurnace(inventoryplayer, factory));
/* 32 */     this.factory = factory;
/*    */   }
/*    */ 
/*    */   protected void k()
/*    */   {
/* 44 */     this.g.b("Picture Factory", 48, 6, 4210752);
/* 45 */     this.g.b("Inventory", 8, this.i - 96 + 2, 4210752);
/*    */   }
/*    */ 
/*    */   protected void a(float f)
/*    */   {
/* 53 */     int i = this.b.p.b("/ColoredBlockSprites/PictureFactory/guiPictureFactory.png");
/* 54 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 55 */     this.b.p.b(i);
/* 56 */     int j = (this.c - this.a) / 2;
/* 57 */     int k = (this.d - this.i) / 2;
/* 58 */     b(j, k, 0, 0, this.a, this.i);
/*    */ 
/* 60 */     if (this.factory.isBurning()) {
/* 61 */       int l = this.factory.getBurnTimeRemainingScaled(12);
/* 62 */       b(j + 81, k + 19 + 12 - l, 176, 12 - l, 14, l + 2);
/*    */     }
/*    */ 
/* 65 */     int i1 = this.factory.getCookProgressScaled(29);
/* 66 */     if (i1 > 0) {
/* 67 */       if (i1 >= 14)
/* 68 */         b(j + 61, k + 41, 176, 14, 14, 4);
/*    */       else {
/* 70 */         b(j + 61, k + 41, 176, 14, i1, 4);
/*    */       }
/* 72 */       b(j + 100, k + 35, 176, 18, i1 - 14, 16);
/*    */     }
/*    */ 
/* 75 */     i1 = this.factory.getImageProgressHeight(52);
/* 76 */     if (i1 > 0) {
/* 77 */       b(j + 134, k + 17 + 52 - i1, 176, 34 + (52 - i1), 34, i1);
/*    */     }
/* 79 */     if (i1 < 52) {
/* 80 */       int i2 = this.factory.getImageProgressWidth(34);
/* 81 */       if ((i2 > 0) && (i1 >= 0))
/* 82 */         b(j + 134, k + 17 + 51 - i1, 176, 85 - i1, i2, 1);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     GuiPictureFactoryFurnace
 * JD-Core Version:    0.6.2
 */