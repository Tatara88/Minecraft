/*    */ public class ItemColoredBrush extends ej
/*    */ {
/*    */   public static ej item;
/*    */   public static int itemId;
/*    */ 
/*    */   public ItemColoredBrush(int i)
/*    */   {
/* 38 */     super(i);
/*    */   }
/*    */ 
/*    */   public static void initialise()
/*    */   {
/* 50 */     if (mod_ColoredBlock.findFreeIds)
/* 51 */       itemId = ModLoaderMp.GetFreeItemId();
/*    */     else {
/* 53 */       itemId = mod_ColoredBlock.properties.get("Ids", "BrushItemId", Integer.valueOf(108)).intValue();
/*    */     }
/*    */ 
/* 56 */     int i = ModLoader.addOverride("/gui/items.png", "/ColoredBlockSprites/brush.png");
/* 57 */     item = new ItemColoredBrush(itemId).b(i).a("brushItem");
/*    */ 
/* 59 */     ModLoader.AddRecipe(new fy(item, 1), new Object[] { "!!!", "###", " : ", Character.valueOf('!'), na.ac, Character.valueOf('#'), na.y, Character.valueOf(':'), ej.B });
/*    */   }
/*    */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Server (1)\
 * Qualified Name:     ItemColoredBrush
 * JD-Core Version:    0.6.2
 */