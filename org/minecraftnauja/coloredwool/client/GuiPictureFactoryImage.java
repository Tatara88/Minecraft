package org.minecraftnauja.coloredwool.client;
/*     */ import java.util.List;
/*     */ import javax.swing.JOptionPane;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import org.lwjgl.input.Keyboard;
import org.minecraftnauja.coloredwool.ColoredWool;
/*     */ 
/*     */ public class GuiPictureFactoryImage extends da
/*     */ {
/*     */   private static final char DONE_ID = '\000';
/*     */   private TileEntityPictureFactory entity;
/*     */   private ro nameButton;
/*     */   private ke doneButton;
/*     */   private String screenTitle;
/*     */   private int updateCounter;
/*     */ 
/*     */   public GuiPictureFactoryImage(TileEntityPictureFactory entity)
/*     */   {
/*  61 */     this.entity = entity;
/*     */   }
/*     */ 
/*     */   public void b()
/*     */   {
/*  73 */     Keyboard.enableRepeatEvents(true);
/*  74 */     this.screenTitle = "Choose image to generate";
/*  75 */     this.nameButton = new ro(this, this.g, this.c / 2 - 100, 60, 200, 20, this.entity.getImageName());
/*  76 */     this.nameButton.a = true;
/*  77 */     this.nameButton.a(42);
/*  78 */     this.e.clear();
/*  79 */     this.doneButton = new ke(0, this.c / 2 - 100, this.d / 4 + 120, "Done");
/*  80 */     this.e.add(this.doneButton);
/*  81 */     checkName();
/*     */   }
/*     */ 
/*     */   public void close()
/*     */   {
/*     */     try
/*     */     {
/*  89 */       String name = this.nameButton.a();
/*  90 */       if (ColoredWool.isMultiplayer())
/*  91 */         BlockPictureFactory.sendModifyImageName(this.entity, name);
/*     */       else {
/*  93 */         this.entity.setImageToGenerate(name);
/*     */       }
/*  95 */       this.entity.y_();
/*  96 */       this.b.a((da)null);
/*  97 */       this.b.g();
/*     */     } catch (Exception e) {
/*  99 */       JOptionPane.showMessageDialog(null, e.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void a()
/*     */   {
/* 107 */     super.a();
/* 108 */     this.updateCounter += 1;
/*     */   }
/*     */ 
/*     */   protected void a(ke button)
/*     */   {
/* 121 */     if (!button.g) {
/* 122 */       return;
/*     */     }
/* 124 */     if (button.f == 0)
/* 125 */       close();
/*     */   }
/*     */ 
/*     */   protected void a(char c, int i)
/*     */   {
/* 135 */     this.nameButton.a(c, i);
/* 136 */     checkName();
/* 137 */     if (c == '\r')
/* 138 */       a((ke)this.e.get(0));
/*     */   }
/*     */ 
/*     */   public boolean checkName()
/*     */   {
/* 152 */     String s = this.nameButton.a();
/* 153 */     boolean flag = s.length() > 0;
/* 154 */     this.doneButton.g = flag;
/* 155 */     return flag;
/*     */   }
/*     */ 
/*     */   public void a(int i, int j, float f)
/*     */   {
/* 165 */     i();
/* 166 */     a(this.g, this.screenTitle, this.c / 2, 40, 16777215);
/* 167 */     this.nameButton.c();
/* 168 */     super.a(i, j, f);
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     GuiPictureFactoryImage
 * JD-Core Version:    0.6.2
 */