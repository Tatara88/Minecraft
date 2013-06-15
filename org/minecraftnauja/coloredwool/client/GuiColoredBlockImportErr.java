package org.minecraftnauja.coloredwool.client;
/*    */ import java.awt.Color;
/*    */ import java.util.List;
/*    */ 
/*    */ public class GuiColoredBlockImportErr extends da
/*    */ {
/*    */   protected gs field_27516_entityplayer;
/*    */   protected TileEntityColor field_27520_tileentitycolor;
/*    */   protected Color field_27522_selectedColor;
/*    */   String errormessage;
/*    */   private ke okbutton;
/*    */ 
/*    */   public GuiColoredBlockImportErr(gs entityplayer, TileEntityColor tileentitycolor, Color selectedColor, String error)
/*    */   {
/* 15 */     this.field_27516_entityplayer = entityplayer;
/* 16 */     this.field_27520_tileentitycolor = tileentitycolor;
/* 17 */     this.field_27522_selectedColor = selectedColor;
/* 18 */     this.errormessage = error;
/*    */   }
/*    */ 
/*    */   public void b()
/*    */   {
/* 24 */     this.e.clear();
/* 25 */     this.e.add(this.okbutton = new ke(1, this.c / 2 - 35, this.d / 4 + 120, 70, 20, "OK"));
/*    */   }
/*    */ 
/*    */   public void a(int i, int j, float f)
/*    */   {
/* 30 */     i();
/* 31 */     a(this.g, this.errormessage, this.c / 2, 40, 16777215);
/* 32 */     super.a(i, j, f);
/*    */   }
/*    */ 
/*    */   protected void a(ke guibutton)
/*    */   {
/* 37 */     switch (guibutton.f)
/*    */     {
/*    */     case 1:
/* 40 */       ModLoader.OpenGUI(this.field_27516_entityplayer, new GuiColoredBlockMenu(this.field_27516_entityplayer, this.field_27520_tileentitycolor, this.field_27522_selectedColor));
/* 41 */       break;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     GuiColoredBlockImportErr
 * JD-Core Version:    0.6.2
 */