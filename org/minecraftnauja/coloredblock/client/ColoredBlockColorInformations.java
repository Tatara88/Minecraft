/*    */ import java.awt.Color;
/*    */ 
/*    */ public final class ColoredBlockColorInformations
/*    */ {
/*    */   private String name;
/*    */   private String hex;
/*    */   private int rgb;
/*    */   private Color color;
/*    */ 
/*    */   public ColoredBlockColorInformations(String name, String hex, int rgb, Color color)
/*    */   {
/* 45 */     this.name = name;
/* 46 */     this.hex = hex;
/* 47 */     this.rgb = rgb;
/* 48 */     this.color = color;
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 61 */     return this.name;
/*    */   }
/*    */ 
/*    */   public String getHex()
/*    */   {
/* 69 */     return this.hex;
/*    */   }
/*    */ 
/*    */   public int getRGB()
/*    */   {
/* 77 */     return this.rgb;
/*    */   }
/*    */ 
/*    */   public Color getColor()
/*    */   {
/* 86 */     return this.color;
/*    */   }
/*    */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     ColoredBlockColorInformations
 * JD-Core Version:    0.6.2
 */