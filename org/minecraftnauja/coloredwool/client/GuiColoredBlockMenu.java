package org.minecraftnauja.coloredwool.client;
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import org.lwjgl.input.Keyboard;
import org.minecraftnauja.coloredwool.ColoredWool;
/*     */ 
/*     */ public class GuiColoredBlockMenu extends da
/*     */ {
/*     */   private static final char HEXA_ID = '\000';
/*     */   private static final char SAVED_COLORS_ID = '\001';
/*     */   private static final char DONE_ID = '\002';
/*     */   private static final char LAST_COLOR_ID = '\003';
/*     */   private static final char IMPORT_IMAGE_ID = '\004';
/*     */   protected static Color lastColor;
/*     */   protected gs player;
/*     */   protected TileEntityColor tileEntityColor;
/*     */   protected Color selectedColor;
/*     */   protected String screenTitle;
/*     */   private int updateCounter;
/*     */   protected ke lastColorButton;
/*     */ 
/*     */   public GuiColoredBlockMenu(gs player, TileEntityColor tileEntityColor, Color color)
/*     */   {
/*  85 */     this.player = player;
/*  86 */     this.tileEntityColor = tileEntityColor;
/*  87 */     this.selectedColor = color;
/*     */   }
/*     */ 
/*     */   public void b()
/*     */   {
/*  99 */     Keyboard.enableRepeatEvents(false);
/* 100 */     this.screenTitle = "Choose how to select the color";
/* 101 */     this.e.clear();
/* 102 */     this.e.add(new ke(0, this.c / 2 - 100, 60, "Hexa"));
/* 103 */     this.e.add(new ke(1, this.c / 2 - 100, 85, "Saved colors"));
/* 104 */     this.lastColorButton = new ke(3, this.c / 2 - 100, 110, "Last color");
/* 105 */     this.e.add(new ke(4, this.c / 2 - 100, 135, "Import Image"));
/* 106 */     this.lastColorButton.g = (lastColor != null);
/* 107 */     this.e.add(this.lastColorButton);
/* 108 */     this.e.add(new ke(2, this.c / 2 - 100, this.d / 4 + 120, "Done"));
/*     */   }
/*     */ 
/*     */   public void close()
/*     */   {
/* 115 */     if (this.selectedColor != null) {
/* 116 */       lastColor = this.selectedColor;
/* 117 */       this.tileEntityColor.setColor(this.selectedColor.getRed(), this.selectedColor.getGreen(), this.selectedColor.getBlue());
/* 118 */       if (ColoredWool.isMultiplayer())
/* 119 */         this.tileEntityColor.sendColorPacket();
/*     */       else {
/* 121 */         this.tileEntityColor.d.j(this.tileEntityColor.e, this.tileEntityColor.f, this.tileEntityColor.g);
/*     */       }
/*     */     }
/* 124 */     this.tileEntityColor.y_();
/* 125 */     this.b.a((da)null);
/* 126 */     this.b.g();
/*     */   }
/*     */ 
/*     */   public void a()
/*     */   {
/* 133 */     super.a();
/* 134 */     this.updateCounter += 1;
/*     */   }
/*     */ 
/*     */   protected void a(ke button)
/*     */   {
/* 147 */     if (!button.g) {
/* 148 */       return;
/*     */     }
/* 150 */     if (button.f == 0) {
/* 151 */       ModLoader.OpenGUI(this.player, new GuiColoredBlockHexa(this.player, this.tileEntityColor, this.selectedColor));
/* 152 */     } else if (button.f == 1) {
/* 153 */       ModLoader.OpenGUI(this.player, new GuiColoredBlockSavedColors(this.player, this.tileEntityColor, this.selectedColor));
/* 154 */     } else if (button.f == 2) {
/* 155 */       close();
/* 156 */     } else if (button.f == 3) {
/* 157 */       this.selectedColor = lastColor;
/* 158 */       close();
/* 159 */     } else if (button.f == 4) {
/* 160 */       ModLoader.OpenGUI(this.player, new GuiColoredBlockImport(this.player, this.tileEntityColor, this.selectedColor));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void a(int i, int j, float f)
/*     */   {
/* 176 */     i();
/* 177 */     a(this.g, this.screenTitle, this.c / 2, 40, 16777215);
/* 178 */     super.a(i, j, f);
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     GuiColoredBlockMenu
 * JD-Core Version:    0.6.2
 */