/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ public final class mod_ColoredBlock extends BaseModMp
/*     */   implements ColoredBlockPropertiesHandler
/*     */ {
/*     */   public static boolean findFreeIds;
/*  36 */   public static int onBlockColorChanged = 0;
/*     */ 
/*  40 */   public static int onBlockRightClick = 1;
/*     */ 
/*  44 */   public static int onBlockPlayNote = 2;
/*     */ 
/*  48 */   public static int onBlockRequestColor = 3;
/*     */   public static mod_ColoredBlock instance;
/*     */   public static ColoredBlockProperties properties;
/*     */   public static File imagesDir;
/*     */ 
/*     */   public mod_ColoredBlock()
/*     */   {
/*  77 */     instance = this;
/*  78 */     ModLoader.SetInGameHook(this, true, false);
/*     */ 
/*  80 */     properties = ColoredBlockProperties.create(this);
/*  81 */     imagesDir = new File("mods/ColoredBlock/Images/");
/*  82 */     loadSettings();
/*     */ 
/*  84 */     ItemColoredDye.initialise();
/*  85 */     ItemColoredBrush.initialise();
/*  86 */     BlockColor.initialise();
/*  87 */     TileEntityColor.initialise();
/*  88 */     TileEntityPictureFactory.initialise();
/*  89 */     BlockPictureFactory.initialise();
/*  90 */     TileEntityModelFactory.initialise();
/*  91 */     BlockModelFactory.initialise();
/*  92 */     saveIds();
/*     */   }
/*     */ 
/*     */   public static void saveIds()
/*     */   {
/* 104 */     properties.set("Ids", "ColoredBlockId", Integer.valueOf(BlockColor.blockId));
/* 105 */     properties.set("Ids", "PictureFactoryBlockBurningId", Integer.valueOf(BlockPictureFactory.blockBurningId));
/* 106 */     properties.set("Ids", "PictureFactoryBlockActiveId", Integer.valueOf(BlockPictureFactory.blockActiveId));
/* 107 */     properties.set("Ids", "PictureFactoryBlockInactiveId", Integer.valueOf(BlockPictureFactory.blockInactiveId));
/* 108 */     properties.set("Ids", "ModelFactoryBlockBurningId", Integer.valueOf(BlockModelFactory.blockBurningId));
/* 109 */     properties.set("Ids", "ModelFactoryBlockActiveId", Integer.valueOf(BlockModelFactory.blockActiveId));
/* 110 */     properties.set("Ids", "ModelFactoryBlockInactiveId", Integer.valueOf(BlockModelFactory.blockInactiveId));
/* 111 */     properties.set("Ids", "DyeItemId", Integer.valueOf(ItemColoredDye.itemId));
/* 112 */     properties.set("Ids", "BrushItemId", Integer.valueOf(ItemColoredBrush.itemId));
/* 113 */     properties.set("Ids", "FindFreeIds", "0");
/* 114 */     properties.save("Ids");
/*     */   }
/*     */ 
/*     */   public String[] getPropertiesHandlers()
/*     */   {
/* 127 */     return new String[] { "Ids", "ColoredBlock", "PictureFactory", "ModelFactory", "SavedColors" };
/*     */   }
/*     */ 
/*     */   public String getPropertiesHandlersPath(String name)
/*     */   {
/* 136 */     return "mods/ColoredBlock/" + name + ".properties";
/*     */   }
/*     */ 
/*     */   public String[][] getDefaultProperties(String name)
/*     */   {
/* 145 */     if (name.equals("Ids")) {
/* 146 */       return new String[][] { { "ColoredBlockId", "100" }, { "PictureFactoryBlockBurningId", "101" }, { "PictureFactoryBlockActiveId", "102" }, { "PictureFactoryBlockInactiveId", "103" }, { "ModelFactoryBlockBurningId", "104" }, { "ModelFactoryBlockActiveId", "105" }, { "ModelFactoryBlockInactiveId", "106" }, { "DyeItemId", "107" }, { "BrushItemId", "108" }, { "FindFreeIds", "1" } };
/*     */     }
/*     */ 
/* 158 */     if (name.equals("ColoredBlock")) {
/* 159 */       return new String[][] { { "InitColorRed", "255" }, { "InitColorGreen", "255" }, { "InitColorBlue", "255" }, { "InitColorStep", "5" }, { "MaxColorStep", "10" }, { "ColorSelection", "menu" } };
/*     */     }
/*     */ 
/* 167 */     if ((name.equals("PictureFactory")) || (name.equals("ModelFactory"))) {
/* 168 */       return new String[][] { { "DontEraseAnything", "0" }, { "DontEraseTheseIds", "" }, { "DontRequireItems", "1" }, { "DontRequireFuel", "1" }, { "InstantCook", "1" } };
/*     */     }
/*     */ 
/* 176 */     return (String[][])null;
/*     */   }
/*     */ 
/*     */   public static void loadSettings()
/*     */   {
/* 188 */     findFreeIds = properties.get("Ids", "FindFreeIds", Boolean.valueOf(true)).booleanValue();
/*     */   }
/*     */ 
/*     */   public static BufferedImage getLocalImage(String name)
/*     */   {
/*     */     try
/*     */     {
/* 203 */       FileInputStream fis = new FileInputStream(imagesDir.getPath() + "/" + name);
/* 204 */       BufferedImage bufferedimage = ImageIO.read(fis);
/* 205 */       fis.close();
/* 206 */       return bufferedimage; } catch (Exception exception) {
/*     */     }
/* 208 */     return null;
/*     */   }
/*     */ 
/*     */   public void HandlePacket(Packet230ModLoader packet, dl player)
/*     */   {
/* 222 */     if (packet.packetType == 21) {
/* 223 */       BlockPictureFactory.handleModifyImageName(packet, player.aL);
/* 224 */       return;
/* 225 */     }if (packet.packetType == 31) {
/* 226 */       BlockModelFactory.handleModifyImageName(packet, player.aL);
/* 227 */       return;
/*     */     }
/* 229 */     int[] ai = packet.dataInt;
/* 230 */     if (ai.length < 3) return;
/* 231 */     int i = ai[0];
/* 232 */     int j = ai[1];
/* 233 */     int k = ai[2];
/* 234 */     dj worldserver = player.aL;
/* 235 */     if (worldserver == null) return;
/* 236 */     TileEntityColor tileentitycolor = (TileEntityColor)worldserver.b(i, j, k);
/* 237 */     if (tileentitycolor == null) return;
/* 238 */     if (packet.packetType == onBlockColorChanged) {
/* 239 */       if (ai.length < 6) {
/* 240 */         return;
/*     */       }
/* 242 */       int l = ai[3];
/* 243 */       int i1 = ai[4];
/* 244 */       int j1 = ai[5];
/* 245 */       tileentitycolor.setColor(l, i1, j1);
/* 246 */       tileentitycolor.sendColorPacket();
/* 247 */     } else if (packet.packetType == onBlockRequestColor) {
/* 248 */       tileentitycolor.sendColorPacketTo(player);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String Version()
/*     */   {
/* 262 */     return "1.5_01";
/*     */   }
/*     */ 
/*     */   public static boolean isSingleplayer()
/*     */   {
/* 270 */     return ModLoader.getMinecraftServerInstance().e[0].B;
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Server (1)\
 * Qualified Name:     mod_ColoredBlock
 * JD-Core Version:    0.6.2
 */