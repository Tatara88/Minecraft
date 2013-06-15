/*     */ public class BlockColor extends rw
/*     */ {
/*     */   public static uu block;
/*     */   public static int blockId;
/*     */   public static final char SELECTION_NONE = '\000';
/*     */   public static final char SELECTION_MANUAL = '\001';
/*     */   public static final char SELECTION_MENU = '\002';
/*  48 */   public static char colorSelection = '\002';
/*     */ 
/*     */   protected BlockColor(int i, int j, ln material)
/*     */   {
/*  62 */     super(i, j, material);
/*     */   }
/*     */ 
/*     */   public static void initialise()
/*     */   {
/*  74 */     colorSelection = getSelectionMode();
/*  75 */     if (mod_ColoredBlock.findFreeIds)
/*  76 */       blockId = ModLoaderMp.GetFreeBlockId();
/*     */     else {
/*  78 */       blockId = mod_ColoredBlock.properties.get("Ids", "ColoredBlockId", Integer.valueOf(100)).intValue();
/*     */     }
/*     */ 
/*  81 */     block = new BlockColor(blockId, uu.ac.bm, ln.l).c(0.8F).a(uu.k).a("coloredBlock");
/*     */ 
/*  83 */     ModLoader.RegisterBlock(block);
/*     */ 
/*  85 */     ModLoader.AddName(block, "Colored Block");
/*     */ 
/*  87 */     ModLoader.AddShapelessRecipe(new iz(block, 1), new Object[] { new iz(ItemColoredDye.item, 1), new iz(uu.ac, 1) });
/*     */     try
/*     */     {
/*  92 */       ((int[])ModLoader.getPrivateValue(yq.class, uu.as, 0))[blockId] = 30;
/*  93 */       ((int[])ModLoader.getPrivateValue(yq.class, uu.as, 1))[blockId] = 60;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static char getSelectionMode()
/*     */   {
/* 102 */     String value = mod_ColoredBlock.properties.get("ColoredBlock", "ColorSelection", "menu");
/* 103 */     if (value.equals("manual")) {
/* 104 */       return '\001';
/*     */     }
/* 106 */     return !value.equals("menu") ? '\000' : '\002';
/*     */   }
/*     */ 
/*     */   public int a(int i, int j)
/*     */   {
/* 121 */     return this.bm;
/*     */   }
/*     */ 
/*     */   public int b(xp iblockaccess, int i, int j, int k)
/*     */   {
/* 132 */     TileEntityColor tileentitycolor = (TileEntityColor)iblockaccess.b(i, j, k);
/* 133 */     if ((mod_ColoredBlock.isMultiplayer()) && (tileentitycolor.needUpdate)) {
/* 134 */       tileentitycolor.requestUpdate();
/*     */     }
/* 136 */     return tileentitycolor.getColorMultiplier();
/*     */   }
/*     */ 
/*     */   public boolean a(fd world, int i, int j, int k, gs player)
/*     */   {
/* 153 */     if (world.B) {
/* 154 */       return true;
/*     */     }
/* 156 */     if (player.t()) {
/* 157 */       return false;
/*     */     }
/* 159 */     iz itemstack = player.c.b();
/* 160 */     if (itemstack == null)
/* 161 */       return false;
/* 162 */     if (itemstack.a() != ItemColoredBrush.item) {
/* 163 */       return false;
/*     */     }
/* 165 */     if (colorSelection == '\001') {
/* 166 */       TileEntityColor tileentitycolor = (TileEntityColor)world.b(i, j, k);
/* 167 */       tileentitycolor.changeColor(world, i, j, k);
/* 168 */       return true;
/* 169 */     }if (colorSelection == '\002') {
/* 170 */       TileEntityColor tileentitycolor1 = (TileEntityColor)world.b(i, j, k);
/* 171 */       ModLoader.OpenGUI(player, new GuiColoredBlockMenu(player, tileentitycolor1, tileentitycolor1.getColor()));
/* 172 */       return true;
/*     */     }
/* 174 */     return false;
/*     */   }
/*     */ 
/*     */   public void b(fd world, int i, int j, int k, gs player)
/*     */   {
/* 186 */     if (world.B) {
/* 187 */       return;
/*     */     }
/* 189 */     if (player.t()) {
/* 190 */       return;
/*     */     }
/* 192 */     iz itemstack = player.c.b();
/* 193 */     if (itemstack == null)
/* 194 */       return;
/* 195 */     if (itemstack.a() != ItemColoredBrush.item) {
/* 196 */       return;
/*     */     }
/* 198 */     if (colorSelection == '\001') {
/* 199 */       TileEntityColor tileentitycolor = (TileEntityColor)world.b(i, j, k);
/* 200 */       tileentitycolor.addColor(world, i, j, k);
/* 201 */       world.j(i, j, k);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected ow a_()
/*     */   {
/* 215 */     return new TileEntityColor();
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     BlockColor
 * JD-Core Version:    0.6.2
 */