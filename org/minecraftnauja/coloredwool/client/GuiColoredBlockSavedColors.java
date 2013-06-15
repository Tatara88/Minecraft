package org.minecraftnauja.coloredwool.client;
/*     */ import java.awt.Color;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map.Entry;
/*     */ import org.lwjgl.input.Keyboard;
import org.minecraftnauja.coloredwool.ColoredWool;
/*     */ 
/*     */ public class GuiColoredBlockSavedColors extends da
/*     */ {
/*     */   private static final char PREVIOUS_COLOR_ID = '\000';
/*     */   private static final char NEXT_COLOR_ID = '\001';
/*     */   private static final char ADD_COLOR_ID = '\002';
/*     */   private static final char DELETE_COLOR_ID = '\003';
/*     */   private static final char CLEAR_COLORS_ID = '\004';
/*     */   private static final char DONE_ID = '\005';
/*     */   private ke previousButton;
/*     */   private ke nextButton;
/*     */   private ke addButton;
/*     */   private ke deleteButton;
/*     */   private ke clearButton;
/*     */   private ke doneButton;
/*     */   private GuiColoredBlockColorButton selectedColorButton;
/*     */   private int startIndex;
/*     */   private int maxColors;
/*     */   private int nbColors;
/*     */   private gs player;
/*     */   private TileEntityColor tileEntityColor;
/*     */   private Color selectedColor;
/*     */   private Color parentColor;
/*     */   private String screenTitle;
/*     */   private int updateCounter;
/*     */ 
/*     */   public GuiColoredBlockSavedColors(gs player, TileEntityColor tileEntityColor, Color color)
/*     */   {
/* 128 */     this.player = player;
/* 129 */     this.tileEntityColor = tileEntityColor;
/* 130 */     this.selectedColor = color;
/* 131 */     this.parentColor = color;
/* 132 */     this.startIndex = 0;
/*     */   }
/*     */ 
/*     */   public void b()
/*     */   {
/* 144 */     updateScreenTitle();
/* 145 */     Keyboard.enableRepeatEvents(false);
/* 146 */     this.e.clear();
/* 147 */     this.e.add(this.previousButton = new ke(0, this.c / 2 - 154, this.d - 52, 150, 20, "Previous"));
/* 148 */     this.e.add(this.addButton = new ke(2, this.c / 2 - 154, this.d - 28, 70, 20, "Add"));
/* 149 */     this.e.add(this.deleteButton = new ke(3, this.c / 2 - 74, this.d - 28, 70, 20, "Delete"));
/* 150 */     this.e.add(this.nextButton = new ke(1, this.c / 2 + 4, this.d - 52, 150, 20, "Next"));
/* 151 */     this.e.add(this.clearButton = new ke(4, this.c / 2 + 4, this.d - 28, 70, 20, "Clear"));
/* 152 */     this.e.add(this.doneButton = new ke(5, this.c / 2 + 84, this.d - 28, 70, 20, "Done"));
/* 153 */     updateColors();
/* 154 */     this.previousButton.g = ((this.maxColors > 0) && (this.startIndex > 0));
/* 155 */     this.nextButton.g = ((this.maxColors > 0) && (this.startIndex + this.nbColors < ColoredBlockSavedColors.getNbColors()));
/* 156 */     this.addButton.g = (!ColoredBlockSavedColors.containsColor(this.parentColor));
/* 157 */     this.deleteButton.g = (this.selectedColorButton != null);
/* 158 */     this.clearButton.g = (ColoredBlockSavedColors.getNbColors() > 0);
/*     */   }
/*     */ 
/*     */   protected void updateScreenTitle()
/*     */   {
/* 165 */     if (this.selectedColorButton == null) {
/* 166 */       this.screenTitle = "Saved colors";
/*     */     } else {
/* 168 */       ColoredBlockColorInformations coloredblockcolorinformations = this.selectedColorButton.color;
/* 169 */       this.screenTitle = ("Saved color 0x" + coloredblockcolorinformations.getHex());
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void updateColors()
/*     */   {
/* 177 */     int i = this.c / 2 - 154;
/* 178 */     byte byte0 = 60;
/* 179 */     int j = this.c / 2 + 154;
/* 180 */     int k = this.previousButton.d - 20;
/* 181 */     byte byte1 = 14;
/* 182 */     int l = 0;
/* 183 */     if (byte1 - 1 > 0) {
/* 184 */       l = (308 - byte1 * 20) / (byte1 - 1);
/*     */     }
/* 186 */     if (l > 10) {
/* 187 */       l = 10;
/*     */     }
/* 189 */     int i1 = (k - byte0) / 20 - 1;
/* 190 */     int j1 = 0;
/* 191 */     if (i1 - 1 > 0) {
/* 192 */       j1 = (k - byte0 - i1 * 20) / (i1 - 1);
/*     */     }
/* 194 */     if (j1 > 10) {
/* 195 */       j1 = 10;
/*     */     }
/* 197 */     this.maxColors = (byte1 * i1);
/* 198 */     int k1 = 0;
/* 199 */     int l1 = 0;
/* 200 */     int i2 = 0;
/* 201 */     Iterator iterator = ColoredBlockSavedColors.getColorsIterator();
/* 202 */     while ((iterator.hasNext()) && (i2 < this.startIndex)) {
/* 203 */       iterator.next();
/* 204 */       i2++;
/*     */     }
/* 206 */     i2 = 0;
/*     */ 
/* 208 */     while ((iterator.hasNext()) && (l1 < i1))
/*     */     {
/* 211 */       Map.Entry entry = (Map.Entry)iterator.next();
/* 212 */       ColoredBlockColorInformations color = (ColoredBlockColorInformations)entry.getValue();
/* 213 */       GuiColoredBlockColorButton button = new GuiColoredBlockColorButton(9 + i2, i + (20 + l) * k1, byte0 + (20 + j1) * l1, 20, 20, color);
/* 214 */       if (color.getColor().equals(this.selectedColor)) {
/* 215 */         this.selectedColorButton = button;
/* 216 */         button.selected = true;
/*     */       }
/* 218 */       this.e.add(button);
/* 219 */       i2++;
/* 220 */       k1++; if (k1 >= byte1) {
/* 221 */         k1 = 0;
/* 222 */         l1++;
/*     */       }
/*     */     }
/* 225 */     this.nbColors = i2;
/*     */   }
/*     */ 
/*     */   public void a()
/*     */   {
/* 232 */     super.a();
/* 233 */     this.updateCounter += 1;
/*     */   }
/*     */ 
/*     */   protected void a(ke button)
/*     */   {
/* 246 */     if (!button.g) {
/* 247 */       return;
/*     */     }
/* 249 */     if ((button instanceof GuiColoredBlockColorButton)) {
/* 250 */       selectedColor((GuiColoredBlockColorButton)button);
/* 251 */     } else if (button.f == 2) {
/* 252 */       addSelectedColor();
/* 253 */     } else if (button.f == 5) {
/* 254 */       ColoredWool.saveSavedColors();
/* 255 */       if (this.selectedColor == null) {
/* 256 */         this.selectedColor = this.parentColor;
/*     */       }
/* 258 */       ModLoader.OpenGUI(this.player, new GuiColoredBlockMenu(this.player, this.tileEntityColor, this.selectedColor));
/* 259 */     } else if (button.f == 3) {
/* 260 */       deleteSelectedColor();
/* 261 */     } else if (button.f == 4) {
/* 262 */       clearAllColors();
/* 263 */     } else if (button.f == 0) {
/* 264 */       previousColors(this.maxColors);
/* 265 */     } else if (button.f == 1) {
/* 266 */       nextColors(this.maxColors);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void addSelectedColor()
/*     */   {
/* 279 */     Color color = this.parentColor;
/* 280 */     ColoredBlockSavedColors.addColor("", color);
/* 281 */     this.selectedColor = color;
/* 282 */     b();
/*     */   }
/*     */ 
/*     */   protected void selectedColor(GuiColoredBlockColorButton button)
/*     */   {
/* 289 */     if (this.selectedColorButton != null) {
/* 290 */       this.selectedColorButton.selected = false;
/*     */     }
/* 292 */     this.selectedColorButton = button;
/* 293 */     this.selectedColorButton.selected = true;
/* 294 */     this.selectedColor = this.selectedColorButton.color.getColor();
/* 295 */     this.deleteButton.g = true;
/* 296 */     updateScreenTitle();
/*     */   }
/*     */ 
/*     */   protected void deleteSelectedColor()
/*     */   {
/* 303 */     if (this.selectedColorButton == null) {
/* 304 */       return;
/*     */     }
/* 306 */     ColoredBlockColorInformations color = this.selectedColorButton.color;
/* 307 */     ColoredBlockSavedColors.removeColor(color.getColor());
/* 308 */     this.selectedColorButton.selected = false;
/* 309 */     this.selectedColorButton = null;
/* 310 */     this.selectedColor = null;
/* 311 */     if (this.startIndex >= ColoredBlockSavedColors.getNbColors()) {
/* 312 */       this.startIndex = 0;
/*     */     }
/* 314 */     b();
/*     */   }
/*     */ 
/*     */   protected void clearAllColors()
/*     */   {
/* 321 */     if (this.selectedColorButton != null) {
/* 322 */       this.selectedColorButton.selected = false;
/* 323 */       this.selectedColorButton = null;
/* 324 */       this.selectedColor = null;
/*     */     }
/* 326 */     ColoredBlockSavedColors.clear();
/* 327 */     this.startIndex = 0;
/* 328 */     b();
/*     */   }
/*     */ 
/*     */   protected void previousColors(int i)
/*     */   {
/* 335 */     this.startIndex -= i;
/* 336 */     if (this.startIndex < 0) {
/* 337 */       this.startIndex = 0;
/*     */     }
/* 339 */     b();
/*     */   }
/*     */ 
/*     */   protected void nextColors(int i)
/*     */   {
/* 346 */     this.startIndex += i;
/* 347 */     int j = ColoredBlockSavedColors.getNbColors();
/* 348 */     if (this.startIndex > j) {
/* 349 */       this.startIndex = (j - 1);
/*     */     }
/* 351 */     b();
/*     */   }
/*     */ 
/*     */   public void a(int i, int j, float f)
/*     */   {
/* 366 */     i();
/* 367 */     a(this.g, this.screenTitle, this.c / 2, 40, 16777215);
/* 368 */     super.a(i, j, f);
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     GuiColoredBlockSavedColors
 * JD-Core Version:    0.6.2
 */