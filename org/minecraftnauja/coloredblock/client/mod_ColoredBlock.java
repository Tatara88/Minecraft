/*     */ import java.awt.Color;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Properties;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.minecraft.client.Minecraft;
/*     */ 
/*     */ public final class mod_ColoredBlock extends BaseModMp
/*     */   implements ColoredBlockPropertiesHandler
/*     */ {
/*     */   public static boolean findFreeIds;
/*  39 */   public static int onBlockColorChanged = 0;
/*     */ 
/*  43 */   public static int onBlockRightClick = 1;
/*     */ 
/*  47 */   public static int onBlockPlayNote = 2;
/*     */ 
/*  51 */   public static int onBlockRequestColor = 3;
/*     */   public static mod_ColoredBlock instance;
/*     */   public static ColoredBlockProperties properties;
/*     */   public static File imagesDir;
/*  79 */   public static ImageImport imageImport = null;
/*     */   public long importTimer;
/*     */ 
/*     */   public mod_ColoredBlock()
/*     */   {
/*  94 */     instance = this;
/*  95 */     ModLoader.SetInGameHook(this, true, false);
/*     */ 
/*  97 */     properties = ColoredBlockProperties.create(this);
/*  98 */     imagesDir = new File(Minecraft.b(), "/mods/ColoredBlock/Images/");
/*  99 */     loadSettings();
/* 100 */     loadSavedColors();
/*     */ 
/* 102 */     ItemColoredDye.initialise();
/* 103 */     ItemColoredBrush.initialise();
/* 104 */     BlockColor.initialise();
/* 105 */     TileEntityColor.initialise();
/* 106 */     TileEntityPictureFactory.initialise();
/* 107 */     BlockPictureFactory.initialise();
/* 108 */     TileEntityModelFactory.initialise();
/* 109 */     BlockModelFactory.initialise();
/* 110 */     saveIds();
/*     */ 
/* 112 */     this.importTimer = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   public static void saveIds()
/*     */   {
/* 124 */     properties.set("Ids", "ColoredBlockId", Integer.valueOf(BlockColor.blockId));
/* 125 */     properties.set("Ids", "PictureFactoryBlockBurningId", Integer.valueOf(BlockPictureFactory.blockBurningId));
/* 126 */     properties.set("Ids", "PictureFactoryBlockActiveId", Integer.valueOf(BlockPictureFactory.blockActiveId));
/* 127 */     properties.set("Ids", "PictureFactoryBlockInactiveId", Integer.valueOf(BlockPictureFactory.blockInactiveId));
/* 128 */     properties.set("Ids", "ModelFactoryBlockBurningId", Integer.valueOf(BlockModelFactory.blockBurningId));
/* 129 */     properties.set("Ids", "ModelFactoryBlockActiveId", Integer.valueOf(BlockModelFactory.blockActiveId));
/* 130 */     properties.set("Ids", "ModelFactoryBlockInactiveId", Integer.valueOf(BlockModelFactory.blockInactiveId));
/* 131 */     properties.set("Ids", "DyeItemId", Integer.valueOf(ItemColoredDye.itemId));
/* 132 */     properties.set("Ids", "BrushItemId", Integer.valueOf(ItemColoredBrush.itemId));
/* 133 */     properties.set("Ids", "FindFreeIds", "0");
/* 134 */     properties.save("Ids");
/*     */   }
/*     */ 
/*     */   public String[] getPropertiesHandlers()
/*     */   {
/* 147 */     return new String[] { "Ids", "ColoredBlock", "PictureFactory", "ModelFactory", "SavedColors" };
/*     */   }
/*     */ 
/*     */   public String getPropertiesHandlersPath(String name)
/*     */   {
/* 156 */     return "/mods/ColoredBlock/" + name + ".properties";
/*     */   }
/*     */ 
/*     */   public String[][] getDefaultProperties(String name)
/*     */   {
/* 165 */     if (name.equals("Ids")) {
/* 166 */       return new String[][] { { "ColoredBlockId", "100" }, { "PictureFactoryBlockBurningId", "101" }, { "PictureFactoryBlockActiveId", "102" }, { "PictureFactoryBlockInactiveId", "103" }, { "ModelFactoryBlockBurningId", "104" }, { "ModelFactoryBlockActiveId", "105" }, { "ModelFactoryBlockInactiveId", "106" }, { "DyeItemId", "107" }, { "BrushItemId", "108" }, { "FindFreeIds", "1" } };
/*     */     }
/*     */ 
/* 178 */     if (name.equals("ColoredBlock")) {
/* 179 */       return new String[][] { { "InitColorRed", "255" }, { "InitColorGreen", "255" }, { "InitColorBlue", "255" }, { "InitColorStep", "5" }, { "MaxColorStep", "10" }, { "ColorSelection", "menu" } };
/*     */     }
/*     */ 
/* 187 */     if ((name.equals("PictureFactory")) || (name.equals("ModelFactory"))) {
/* 188 */       return new String[][] { { "DontEraseAnything", "0" }, { "DontEraseTheseIds", "" }, { "DontRequireItems", "1" }, { "DontRequireFuel", "1" }, { "InstantCook", "1" } };
/*     */     }
/*     */ 
/* 196 */     return (String[][])null;
/*     */   }
/*     */ 
/*     */   public static void loadSettings()
/*     */   {
/* 208 */     findFreeIds = properties.get("Ids", "FindFreeIds", Boolean.valueOf(true)).booleanValue();
/*     */   }
/*     */ 
/*     */   public static void loadSavedColors()
/*     */   {
/* 220 */     ColoredBlockSavedColors.loadFromProps(properties.get("SavedColors"));
/*     */   }
/*     */ 
/*     */   public static void saveSavedColors()
/*     */   {
/*     */     try
/*     */     {
/* 228 */       Properties props = properties.get("SavedColors");
/* 229 */       ColoredBlockSavedColors.saveToProps(props);
/* 230 */       properties.save("SavedColors");
/*     */     } catch (Exception exception) {
/* 232 */       System.out.println("mod_ColoredBlock: error while saving saved colors.");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static BufferedImage getLocalImage(String name)
/*     */   {
/*     */     try
/*     */     {
/* 248 */       FileInputStream fis = new FileInputStream(imagesDir.getPath() + "/" + name);
/* 249 */       BufferedImage bufferedimage = ImageIO.read(fis);
/* 250 */       fis.close();
/* 251 */       return bufferedimage; } catch (Exception exception) {
/*     */     }
/* 253 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean OnTickInGame(Minecraft minecraft)
/*     */   {
/* 266 */     if ((imageImport != null) && (System.currentTimeMillis() >= this.importTimer)) {
/* 267 */       this.importTimer = (System.currentTimeMillis() + 32L);
/* 268 */       if (imageImport.importFinished)
/* 269 */         imageImport = null;
/*     */       else {
/* 271 */         imageImport.imageTick();
/*     */       }
/*     */     }
/* 274 */     return false;
/*     */   }
/*     */ 
/*     */   public void HandlePacket(Packet230ModLoader packet)
/*     */   {
/* 287 */     if (packet.packetType == 20) {
/* 288 */       BlockPictureFactory.handleOpenGuiImage(packet);
/* 289 */       return;
/* 290 */     }if (packet.packetType == 30) {
/* 291 */       BlockModelFactory.handleOpenGuiImage(packet);
/* 292 */       return;
/*     */     }
/* 294 */     int[] ai = packet.dataInt;
/* 295 */     int i = ai[0];
/* 296 */     int j = ai[1];
/* 297 */     int k = ai[2];
/* 298 */     fd world = ModLoader.getMinecraftInstance().f;
/* 299 */     TileEntityColor tileEntityColor = (TileEntityColor)world.b(i, j, k);
/* 300 */     if (packet.packetType == onBlockColorChanged) {
/* 301 */       int l = ai[3];
/* 302 */       int j1 = ai[4];
/* 303 */       int l1 = ai[5];
/* 304 */       tileEntityColor.setColor(l, j1, l1);
/* 305 */       world.j(i, j, k);
/* 306 */     } else if (packet.packetType == onBlockRightClick) {
/* 307 */       int i1 = ai[3];
/* 308 */       int k1 = ai[4];
/* 309 */       int i2 = ai[5];
/* 310 */       dc player = ModLoader.getMinecraftInstance().h;
/* 311 */       ModLoader.OpenGUI(player, new GuiColoredBlockMenu(player, tileEntityColor, new Color(i1, k1, i2)));
/* 312 */     } else if (packet.packetType == onBlockPlayNote) {
/* 313 */       tileEntityColor.displayParticle(world, i, j, k);
/*     */     }
/*     */   }
/*     */ 
/*     */   public da HandleGUI(int type)
/*     */   {
/* 322 */     if (type == BlockPictureFactory.guiFurnaceId) {
/* 323 */       TileEntityPictureFactory entity = new TileEntityPictureFactory();
/* 324 */       return new GuiPictureFactoryFurnace(getPlayer().c, entity);
/* 325 */     }if (type == BlockModelFactory.guiFurnaceId) {
/* 326 */       TileEntityModelFactory entity = new TileEntityModelFactory();
/* 327 */       return new GuiModelFactoryFurnace(getPlayer().c, entity);
/*     */     }
/* 329 */     return null;
/*     */   }
/*     */ 
/*     */   public String Version()
/*     */   {
/* 342 */     return "1.5_01";
/*     */   }
/*     */ 
/*     */   public static boolean isMultiplayer()
/*     */   {
/* 350 */     return ModLoader.getMinecraftInstance().f.B;
/*     */   }
/*     */ 
/*     */   public static gs getPlayer()
/*     */   {
/* 358 */     return ModLoader.getMinecraftInstance().h;
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     mod_ColoredBlock
 * JD-Core Version:    0.6.2
 */