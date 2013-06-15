/*     */ import java.awt.Color;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiColoredBlockImport extends da
/*     */ {
/*     */   protected gs player;
/*     */   protected TileEntityColor entity;
/*     */   protected Color selectedColor;
/*     */   private ro fileinput;
/*     */   private String fileinputstring;
/*     */   private ke startbutton;
/*     */   private ke cancelbutton;
/*     */   private ke xOrientButton;
/*     */   private ke yOrientButton;
/*     */   private int xOrient;
/*     */   private int yOrient;
/*     */ 
/*     */   public GuiColoredBlockImport(gs player, TileEntityColor entity, Color selectedColor)
/*     */   {
/*  82 */     this.player = player;
/*  83 */     this.entity = entity;
/*  84 */     this.selectedColor = selectedColor;
/*     */   }
/*     */ 
/*     */   public void b()
/*     */   {
/*  96 */     this.fileinput = new ro(this, this.g, this.c / 2 - 100, 60, 200, 20, "");
/*  97 */     this.fileinput.a = true;
/*  98 */     this.e.clear();
/*  99 */     this.e.add(this.startbutton = new ke(0, this.c / 2 - 80, this.d / 4 + 120, 70, 20, "Import"));
/* 100 */     this.e.add(this.cancelbutton = new ke(1, this.c / 2 + 10, this.d / 4 + 120, 70, 20, "Cancel"));
/* 101 */     this.e.add(this.xOrientButton = new ke(2, this.c / 2 - 110, this.d / 4 + 60, 100, 20, "North"));
/* 102 */     this.e.add(this.yOrientButton = new ke(3, this.c / 2 + 10, this.d / 4 + 60, 100, 20, "North"));
/* 103 */     Keyboard.enableRepeatEvents(true);
/*     */   }
/*     */ 
/*     */   public void a(int i, int j, float f)
/*     */   {
/* 118 */     i();
/* 119 */     a(this.g, "Type the image file name to import", this.c / 2, 40, 16777215);
/* 120 */     a(this.g, "X Orientation:", this.c / 2 - 60, this.d / 4 + 40, 16777215);
/* 121 */     a(this.g, "Y Orientation:", this.c / 2 + 60, this.d / 4 + 40, 16777215);
/* 122 */     this.fileinput.c();
/* 123 */     super.a(i, j, f);
/*     */   }
/*     */ 
/*     */   protected void a(ke guibutton)
/*     */   {
/* 136 */     switch (guibutton.f) {
/*     */     case 0:
/* 138 */       startImport();
/* 139 */       break;
/*     */     case 1:
/* 141 */       Keyboard.enableRepeatEvents(false);
/* 142 */       ModLoader.OpenGUI(this.player, new GuiColoredBlockMenu(this.player, this.entity, this.selectedColor));
/* 143 */       break;
/*     */     case 2:
/* 145 */       this.xOrient += 1;
/* 146 */       this.xOrient %= 6;
/* 147 */       String orient = getDirection(this.xOrient);
/* 148 */       if (orient == "Unknown") {
/* 149 */         this.xOrient = 0;
/* 150 */         orient = "North";
/*     */       }
/* 152 */       this.xOrientButton.e = orient;
/* 153 */       break;
/*     */     case 3:
/* 155 */       this.yOrient += 1;
/* 156 */       this.yOrient %= 6;
/* 157 */       String orient2 = getDirection(this.yOrient);
/* 158 */       if (orient2 == "Unknown") {
/* 159 */         this.yOrient = 0;
/* 160 */         orient2 = "North";
/*     */       }
/* 162 */       this.yOrientButton.e = orient2;
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void a(char c, int i)
/*     */   {
/* 172 */     if (this.fileinput.a)
/* 173 */       this.fileinput.a(c, i);
/*     */   }
/*     */ 
/*     */   private String getDirection(int orient)
/*     */   {
/* 188 */     switch (orient) {
/*     */     case 0:
/* 190 */       return "North";
/*     */     case 1:
/* 192 */       return "South";
/*     */     case 2:
/* 194 */       return "East";
/*     */     case 3:
/* 196 */       return "West";
/*     */     case 4:
/* 198 */       return "Up";
/*     */     case 5:
/* 200 */       return "Down";
/*     */     }
/* 202 */     return "Unknown";
/*     */   }
/*     */ 
/*     */   private void startImport()
/*     */   {
/* 210 */     if ((this.xOrient == this.yOrient) || ((this.xOrient == this.yOrient - 1) && (this.yOrient % 2 == 1)) || ((this.yOrient == this.xOrient - 1) && (this.xOrient % 2 == 1))) {
/* 211 */       ModLoader.OpenGUI(this.player, new GuiColoredBlockImportErr(this.player, this.entity, this.selectedColor, "ERROR: Image axis must use different orientation axes!"));
/* 212 */       return;
/*     */     }
/*     */ 
/* 215 */     this.fileinputstring = this.fileinput.a();
/* 216 */     BufferedImage image = mod_ColoredBlock.getLocalImage(this.fileinputstring);
/* 217 */     if (image == null) {
/* 218 */       ModLoader.OpenGUI(this.player, new GuiColoredBlockImportErr(this.player, this.entity, this.selectedColor, "ERROR: Image load failed."));
/* 219 */       return;
/*     */     }
/* 221 */     int test = image.getHeight();
/* 222 */     mod_ColoredBlock.imageImport = new ImageImport(this.player, this.entity, image, this.xOrient, this.yOrient);
/* 223 */     ModLoader.getMinecraftInstance().a((da)null);
/* 224 */     ModLoader.getMinecraftInstance().g();
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     GuiColoredBlockImport
 * JD-Core Version:    0.6.2
 */