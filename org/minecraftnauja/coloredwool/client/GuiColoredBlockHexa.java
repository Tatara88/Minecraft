package org.minecraftnauja.coloredwool.client;
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiColoredBlockHexa extends da
/*     */ {
/*     */   private static final char DONE_ID = '\000';
/*     */   private static final String allowedCharacters = "0123456789abcdefABCDEF";
/*     */   private ro hexaInput;
/*     */   private String hexaString;
/*     */   private ke doneButton;
/*     */   protected gs player;
/*     */   protected TileEntityColor tileEntityColor;
/*     */   protected Color selectedColor;
/*     */   protected String screenTitle;
/*     */   private int updateCounter;
/*     */ 
/*     */   public GuiColoredBlockHexa(gs player, TileEntityColor tileEntityColor, Color color)
/*     */   {
/*  76 */     this.player = player;
/*  77 */     this.tileEntityColor = tileEntityColor;
/*  78 */     setSelectedColor(color);
/*     */   }
/*     */ 
/*     */   public void b()
/*     */   {
/*  90 */     this.screenTitle = "Enter an hexadecimal value";
/*  91 */     this.hexaInput = new ro(this, this.g, this.c / 2 - 100, 60, 200, 20, this.hexaString);
/*  92 */     this.hexaInput.a = true;
/*  93 */     this.hexaInput.a(6);
/*  94 */     this.e.clear();
/*  95 */     this.e.add(this.doneButton = new ke(0, this.c / 2 - 100, this.d / 4 + 120, "Done"));
/*  96 */     this.doneButton.g = false;
/*  97 */     checkHexaInput();
/*  98 */     Keyboard.enableRepeatEvents(true);
/*     */   }
/*     */ 
/*     */   public void a()
/*     */   {
/* 105 */     super.a();
/* 106 */     this.updateCounter += 1;
/*     */   }
/*     */ 
/*     */   public void checkHexaInput()
/*     */   {
/* 118 */     ((ke)this.e.get(0)).g = (this.hexaInput.a().trim().length() == 6);
/*     */   }
/*     */ 
/*     */   public String validateHexaString(String hex)
/*     */   {
/* 127 */     String tmp = hex;
/* 128 */     for (int i = tmp.length(); i < 6; i++) {
/* 129 */       tmp = "0" + tmp;
/*     */     }
/* 131 */     return tmp;
/*     */   }
/*     */ 
/*     */   protected void a(ke button)
/*     */   {
/* 144 */     if (!button.g) {
/* 145 */       return;
/*     */     }
/* 147 */     if (button.f == 0) {
/* 148 */       Keyboard.enableRepeatEvents(false);
/* 149 */       ModLoader.OpenGUI(this.player, new GuiColoredBlockMenu(this.player, this.tileEntityColor, getSelectedColor()));
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void a(char c, int i)
/*     */   {
/* 159 */     if (("0123456789abcdefABCDEF".indexOf(c) >= 0) || (i == 14) || (c == '\r')) {
/* 160 */       this.hexaInput.a(c, i);
/* 161 */       checkHexaInput();
/* 162 */       if (c == '\r')
/* 163 */         a((ke)this.e.get(0));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void a(int i, int j, float f)
/*     */   {
/* 180 */     i();
/* 181 */     a(this.g, this.screenTitle, this.c / 2, 40, 16777215);
/* 182 */     this.hexaInput.c();
/* 183 */     super.a(i, j, f);
/*     */   }
/*     */ 
/*     */   public Color getSelectedColor()
/*     */   {
/* 196 */     String hex = this.hexaInput.a();
/* 197 */     hex = validateHexaString(hex);
/* 198 */     this.selectedColor = Color.decode("0x" + hex);
/* 199 */     return this.selectedColor;
/*     */   }
/*     */ 
/*     */   public void setSelectedColor(Color color)
/*     */   {
/* 212 */     int rgb = ((char)color.getRed() << '\020') + ((char)color.getGreen() << '\b') + (char)color.getBlue();
/* 213 */     String hex = Integer.toHexString(rgb);
/* 214 */     this.hexaString = validateHexaString(hex);
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     GuiColoredBlockHexa
 * JD-Core Version:    0.6.2
 */