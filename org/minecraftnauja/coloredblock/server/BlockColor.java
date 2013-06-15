/*     */ public class BlockColor extends lb
/*     */ {
/*     */   public static na block;
/*     */   public static int blockId;
/*     */   public static final char SELECTION_NONE = '\000';
/*     */   public static final char SELECTION_MANUAL = '\001';
/*     */   public static final char SELECTION_MENU = '\002';
/*  48 */   public static char colorSelection = '\002';
/*     */ 
/*     */   protected BlockColor(int i, int j, hj material)
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
/*  81 */     block = new BlockColor(blockId, na.ac.bm, hj.l).c(0.8F).a(na.k).a("coloredBlock");
/*     */ 
/*  83 */     ModLoader.RegisterBlock(block);
/*     */ 
/*  85 */     ModLoader.AddShapelessRecipe(new fy(block, 1), new Object[] { new fy(ItemColoredDye.item, 1), new fy(na.ac, 1) });
/*     */     try
/*     */     {
/*  90 */       ((int[])ModLoader.getPrivateValue(pt.class, na.as, 0))[blockId] = 30;
/*  91 */       ((int[])ModLoader.getPrivateValue(pt.class, na.as, 1))[blockId] = 60;
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static char getSelectionMode()
/*     */   {
/* 100 */     String value = mod_ColoredBlock.properties.get("ColoredBlock", "ColorSelection", "menu");
/* 101 */     if (value.equals("manual")) {
/* 102 */       return '\001';
/*     */     }
/* 104 */     return !value.equals("menu") ? '\000' : '\002';
/*     */   }
/*     */ 
/*     */   public int a(int i, int j)
/*     */   {
/* 119 */     return this.bm;
/*     */   }
/*     */ 
/*     */   public boolean a(dj world, int i, int j, int k, em player)
/*     */   {
/* 136 */     if (world.B) {
/* 137 */       return true;
/*     */     }
/* 139 */     if (player.ah()) {
/* 140 */       return false;
/*     */     }
/* 142 */     fy itemstack = player.i.b();
/* 143 */     if (itemstack == null)
/* 144 */       return false;
/* 145 */     if (itemstack.a() != ItemColoredBrush.item) {
/* 146 */       return false;
/*     */     }
/* 148 */     if (colorSelection == '\001') {
/* 149 */       TileEntityColor entity = (TileEntityColor)world.b(i, j, k);
/* 150 */       entity.changeColor(world, i, j, k);
/* 151 */       entity.sendColorPacket();
/* 152 */       return true;
/*     */     }
/* 154 */     if (colorSelection == '\002') {
/* 155 */       TileEntityColor entity = (TileEntityColor)world.b(i, j, k);
/* 156 */       Packet230ModLoader packet = entity.getColorPacket();
/* 157 */       packet.packetType = mod_ColoredBlock.onBlockRightClick;
/* 158 */       ModLoaderMp.SendPacketTo(mod_ColoredBlock.instance, (dl)player, packet);
/* 159 */       return true;
/*     */     }
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */   public void b(dj world, int i, int j, int k, em player)
/*     */   {
/* 173 */     if (world.B) {
/* 174 */       return;
/*     */     }
/* 176 */     if (player.ah()) {
/* 177 */       return;
/*     */     }
/* 179 */     fy itemstack = player.i.b();
/* 180 */     if (itemstack == null)
/* 181 */       return;
/* 182 */     if (itemstack.a() != ItemColoredBrush.item) {
/* 183 */       return;
/*     */     }
/* 185 */     if (colorSelection == '\001') {
/* 186 */       TileEntityColor entity = (TileEntityColor)world.b(i, j, k);
/* 187 */       entity.addColor(world, i, j, k);
/* 188 */       entity.sendColorPacket();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected jh a_()
/*     */   {
/* 202 */     return new TileEntityColor();
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Server (1)\
 * Qualified Name:     BlockColor
 * JD-Core Version:    0.6.2
 */