package org.minecraftnauja.coloredwool.client;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiModelFactoryMenu extends da
/*     */ {
/*     */   private static final char FLAT_MODEL_ID = '\000';
/*     */   private static final char CANCEL_ID = '\001';
/*     */   private static final char FLAT_MODEL = '\000';
/*     */   private gs player;
/*     */   private TileEntityModelFactory entity;
/*     */   private String screenTitle;
/*     */   private int updateCounter;
/*     */ 
/*     */   public GuiModelFactoryMenu(gs player, TileEntityModelFactory entity)
/*     */   {
/*  66 */     this.player = player;
/*  67 */     this.entity = entity;
/*     */   }
/*     */ 
/*     */   public void b()
/*     */   {
/*  79 */     Keyboard.enableRepeatEvents(false);
/*  80 */     this.screenTitle = "Choose a model";
/*  81 */     this.e.clear();
/*  82 */     this.e.add(new ke(0, this.c / 2 - 100, 60, "Flat model"));
/*  83 */     this.e.add(new ke(1, this.c / 2 - 100, this.d / 4 + 120, "Cancel"));
/*     */   }
/*     */ 
/*     */   public void close()
/*     */   {
/*  90 */     this.b.a((da)null);
/*  91 */     this.b.g();
/*     */   }
/*     */ 
/*     */   public void a()
/*     */   {
/*  98 */     super.a();
/*  99 */     this.updateCounter += 1;
/*     */   }
/*     */ 
/*     */   protected void a(ke button)
/*     */   {
/* 112 */     if (!button.g) {
/* 113 */       return;
/*     */     }
/* 115 */     if (button.f == 0)
/* 116 */       ModLoader.OpenGUI(this.player, new GuiModelFactoryImage(this.player, this.entity, '\000'));
/* 117 */     else if (button.f == 1)
/* 118 */       close();
/*     */   }
/*     */ 
/*     */   public void a(int i, int j, float f)
/*     */   {
/* 134 */     i();
/* 135 */     a(this.g, this.screenTitle, this.c / 2, 40, 16777215);
/* 136 */     super.a(i, j, f);
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     GuiModelFactoryMenu
 * JD-Core Version:    0.6.2
 */