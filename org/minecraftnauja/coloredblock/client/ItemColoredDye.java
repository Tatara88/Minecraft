/*    */ public class ItemColoredDye extends gm
/*    */ {
/*    */   public static gm item;
/*    */   public static int itemId;
/*    */ 
/*    */   public ItemColoredDye(int i)
/*    */   {
/* 38 */     super(i);
/*    */   }
/*    */ 
/*    */   public static void initialise()
/*    */   {
/* 50 */     if (mod_ColoredBlock.findFreeIds)
/* 51 */       itemId = ModLoaderMp.GetFreeItemId();
/*    */     else {
/* 53 */       itemId = mod_ColoredBlock.properties.get("Ids", "DyeItemId", Integer.valueOf(107)).intValue();
/*    */     }
/*    */ 
/* 56 */     int i = ModLoader.addOverride("/gui/items.png", "/ColoredBlockSprites/coloredDyePowder.png");
/* 57 */     item = new ItemColoredDye(itemId).c(i).a("dyeItem");
/*    */ 
/* 59 */     ModLoader.AddName(item, "Colored Dye");
/*    */ 
/* 61 */     ModLoader.AddShapelessRecipe(new iz(item, 1), new Object[] { new iz(gm.aU, 1, 1), new iz(gm.aU, 1, 2), new iz(gm.aU, 1, 4) });
/*    */   }
/*    */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     ItemColoredDye
 * JD-Core Version:    0.6.2
 */