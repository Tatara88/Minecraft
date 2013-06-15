/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ public class GuiColoredBlockColorButton extends ke
/*    */ {
/*    */   public ColoredBlockColorInformations color;
/*    */   public final int xColor;
/*    */   public final int yColor;
/*    */   public final int widthColor;
/*    */   public final int heightColor;
/*    */   public boolean selected;
/*    */ 
/*    */   public GuiColoredBlockColorButton(int i, int j, int k, int w, int h, ColoredBlockColorInformations color)
/*    */   {
/* 56 */     super(i, j, k, w, h, "");
/* 57 */     this.color = color;
/* 58 */     this.xColor = (this.c + this.a / 6);
/* 59 */     this.yColor = (this.d + this.b / 6);
/* 60 */     this.widthColor = (this.a - this.a / 3);
/* 61 */     this.heightColor = (this.b - this.b / 3);
/* 62 */     this.selected = false;
/*    */   }
/*    */ 
/*    */   protected int a(boolean flag)
/*    */   {
/* 76 */     if (this.selected) {
/* 77 */       return 2;
/*    */     }
/* 79 */     return super.a(flag);
/*    */   }
/*    */ 
/*    */   public void a(Minecraft minecraft, int i, int j)
/*    */   {
/* 95 */     super.a(minecraft, i, j);
/* 96 */     a(this.xColor, this.yColor, this.xColor + this.widthColor, this.yColor + this.heightColor, this.color.getColor().getRGB());
/*    */   }
/*    */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     GuiColoredBlockColorButton
 * JD-Core Version:    0.6.2
 */