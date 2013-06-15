package org.minecraftnauja.coloredwool;

import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.minecraftnauja.coloredwool.block.BlockColoredCloth;
import org.minecraftnauja.coloredwool.block.BlockModelFactory;
import org.minecraftnauja.coloredwool.block.BlockPictureFactory;
import org.minecraftnauja.coloredwool.block.FactoryState;
import org.minecraftnauja.coloredwool.item.ItemColoredBrush;
import org.minecraftnauja.coloredwool.item.ItemColoredDye;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "ColoredWool", name = "ColoredWool", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class ColoredWool {

	/**
	 * Mod identifier.
	 */
	public static final String MOD_ID = "ColoredWool";

	/**
	 * Mod instance.
	 */
	@Instance("ColoredWool")
	public static ColoredWool instance;

	/**
	 * Mod configuration.
	 */
	public static Config config;

	/**
	 * Colored wool instance.
	 */
	public static Block coloredWool;

	/**
	 * Burning picture factory instance.
	 */
	public static Block pictureFactoryBurning;

	/**
	 * Active picture factory instance.
	 */
	public static Block pictureFactoryActive;

	/**
	 * Idle picture factory instance.
	 */
	public static Block pictureFactoryIdle;

	/**
	 * Burning model factory instance.
	 */
	public static Block modelFactoryBurning;

	/**
	 * Active model factory instance.
	 */
	public static Block modelFactoryActive;

	/**
	 * Idle model factory instance.
	 */
	public static Block modelFactoryIdle;

	/**
	 * Colored dye instance.
	 */
	public static Item coloredDye;

	/**
	 * Colored brush instance.
	 */
	public static Item coloredBrush;

	/**
	 * {@inheritDoc}
	 */
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		config = Config.load(event);
	}

	/**
	 * {@inheritDoc}
	 */
	@Init
	public void load(final FMLInitializationEvent event) throws IOException {
		// Colored wool.
		coloredWool = new BlockColoredCloth(config.ids.coloredWoolId)
				.setHardness(Block.cloth.blockHardness)
				.setStepSound(Block.cloth.stepSound)
				.setUnlocalizedName("coloredWool");
		// Picture factory.
		pictureFactoryBurning = new BlockPictureFactory(
				config.ids.pictureFactoryBurningId, FactoryState.Burning)
				.setHardness(Block.furnaceBurning.blockHardness)
				.setStepSound(Block.furnaceBurning.stepSound)
				.setLightValue(0.875F).setUnlocalizedName("pictureFactory");
		pictureFactoryActive = new BlockPictureFactory(
				config.ids.pictureFactoryActiveId, FactoryState.Active)
				.setHardness(Block.furnaceIdle.blockHardness)
				.setStepSound(Block.furnaceIdle.stepSound)
				.setLightValue(0.4375F).setUnlocalizedName("pictureFactory");
		pictureFactoryIdle = new BlockPictureFactory(
				config.ids.pictureFactoryIdleId, FactoryState.Idle)
				.setHardness(pictureFactoryActive.blockHardness)
				.setStepSound(pictureFactoryActive.stepSound)
				.setUnlocalizedName("pictureFactory")
				.setCreativeTab(CreativeTabs.tabDecorations);
		// Model factory.
		modelFactoryBurning = new BlockModelFactory(
				config.ids.modelFactoryBurningId, FactoryState.Burning)
				.setHardness(pictureFactoryBurning.blockHardness)
				.setStepSound(pictureFactoryBurning.stepSound)
				.setLightValue(0.875F).setUnlocalizedName("modelFactory");
		modelFactoryActive = new BlockModelFactory(
				config.ids.modelFactoryActiveId, FactoryState.Active)
				.setHardness(pictureFactoryActive.blockHardness)
				.setStepSound(pictureFactoryActive.stepSound)
				.setLightValue(0.4375F).setUnlocalizedName("modelFactory");
		modelFactoryIdle = new BlockModelFactory(config.ids.modelFactoryIdleId,
				FactoryState.Idle)
				.setHardness(pictureFactoryIdle.blockHardness)
				.setStepSound(pictureFactoryIdle.stepSound)
				.setUnlocalizedName("modelFactory")
				.setCreativeTab(CreativeTabs.tabDecorations);
		// Items.
		coloredDye = new ItemColoredDye(config.ids.coloredDyeId)
				.setUnlocalizedName("coloredDye");
		coloredBrush = new ItemColoredBrush(config.ids.coloredBrushId)
				.setUnlocalizedName("coloredBrush");
		// Register.
		LanguageRegistry.addName(coloredWool, "Colored Wool");
		GameRegistry.registerBlock(coloredWool, "coloredWool");
		LanguageRegistry.addName(pictureFactoryBurning, "Picture Factory");
		GameRegistry.registerBlock(pictureFactoryBurning,
				"pictureFactoryBurning");
		LanguageRegistry.addName(pictureFactoryActive, "Picture Factory");
		GameRegistry
				.registerBlock(pictureFactoryActive, "pictureFactoryActive");
		LanguageRegistry.addName(pictureFactoryIdle, "Picture Factory");
		GameRegistry.registerBlock(pictureFactoryIdle, "pictureFactoryIdle");
		LanguageRegistry.addName(modelFactoryBurning, "Model Factory");
		GameRegistry.registerBlock(modelFactoryBurning, "modelFactoryBurning");
		LanguageRegistry.addName(modelFactoryActive, "Model Factory");
		GameRegistry.registerBlock(modelFactoryActive, "modelFactoryActive");
		LanguageRegistry.addName(modelFactoryIdle, "Model Factory");
		GameRegistry.registerBlock(modelFactoryIdle, "modelFactoryIdle");
		LanguageRegistry.addName(coloredDye, "Colored Dye");
		LanguageRegistry.addName(coloredBrush, "Colored Brush");
		// Craft.
		ItemStack cds = new ItemStack(coloredDye);
		ItemStack pls = new ItemStack(Block.planks);
		ItemStack cls = new ItemStack(Block.cloth);
		ItemStack iis = new ItemStack(Item.ingotIron);
		ItemStack igs = new ItemStack(Item.ingotGold);
		ItemStack gls = new ItemStack(Block.glass);
		ItemStack rds = new ItemStack(Item.redstone);
		GameRegistry.addShapelessRecipe(new ItemStack(coloredWool), cds, cls);
		GameRegistry.addShapelessRecipe(cds,
				new ItemStack(Item.dyePowder, 1, 1), new ItemStack(
						Item.dyePowder, 1, 2), new ItemStack(Item.dyePowder, 1,
						4));
		GameRegistry.addRecipe(new ItemStack(coloredBrush), "xxx", "yyy",
				" z ", 'x', cls, 'y', pls, 'z', new ItemStack(Item.stick));
		GameRegistry.addRecipe(new ItemStack(pictureFactoryIdle), "xyx", "zuz",
				"xzx", 'x', iis, 'y', gls, 'z', pls, 'u', rds);
		GameRegistry.addRecipe(new ItemStack(modelFactoryIdle), "xyx", "zuz",
				"xzx", 'x', igs, 'y', gls, 'z', pls, 'u', rds);

	}

	/**
	 * {@inheritDoc}
	 */
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
	}

	/*
	 * public static boolean findFreeIds; public static int onBlockColorChanged
	 * = 0;
	 * 
	 * public static int onBlockRightClick = 1;
	 * 
	 * public static int onBlockPlayNote = 2;
	 * 
	 * public static int onBlockRequestColor = 3; public static ColoredBlock
	 * instance; public static ColoredBlockProperties properties; public static
	 * File imagesDir; public static ImageImport imageImport = null; public long
	 * importTimer;
	 * 
	 * public ColoredBlock() { instance = this; ModLoader.SetInGameHook(this,
	 * true, false);
	 * 
	 * properties = ColoredBlockProperties.create(this); imagesDir = new
	 * File(Minecraft.b(), "/mods/ColoredBlock/Images/"); loadSettings();
	 * loadSavedColors();
	 * 
	 * ItemColoredDye.initialise(); ItemColoredBrush.initialise();
	 * BlockColoredCloth.initialise(); TileEntityColor.initialise();
	 * TileEntityPictureFactory.initialise(); BlockPictureFactory.initialise();
	 * TileEntityModelFactory.initialise(); BlockModelFactory.initialise();
	 * saveIds();
	 * 
	 * this.importTimer = System.currentTimeMillis(); }
	 * 
	 * public static void loadSavedColors() {
	 * ColoredBlockSavedColors.loadFromProps(properties.get("SavedColors")); }
	 * 
	 * public static void saveSavedColors() { try { Properties props =
	 * properties.get("SavedColors");
	 * ColoredBlockSavedColors.saveToProps(props);
	 * properties.save("SavedColors"); } catch (Exception exception) {
	 * System.out
	 * .println("mod_ColoredBlock: error while saving saved colors."); } }
	 * 
	 * public static BufferedImage getLocalImage(String name) { try {
	 * FileInputStream fis = new FileInputStream(imagesDir.getPath() + "/" +
	 * name); BufferedImage bufferedimage = ImageIO.read(fis); fis.close();
	 * return bufferedimage; } catch (Exception exception) { } return null; }
	 * 
	 * public boolean OnTickInGame(Minecraft minecraft) { if ((imageImport !=
	 * null) && (System.currentTimeMillis() >= this.importTimer)) {
	 * this.importTimer = (System.currentTimeMillis() + 32L); if
	 * (imageImport.importFinished) imageImport = null; else {
	 * imageImport.imageTick(); } } return false; }
	 * 
	 * public void HandlePacket(Packet230ModLoader packet) { if
	 * (packet.packetType == 20) {
	 * BlockPictureFactory.handleOpenGuiImage(packet); return; } if
	 * (packet.packetType == 30) { BlockModelFactory.handleOpenGuiImage(packet);
	 * return; } int[] ai = packet.dataInt; int i = ai[0]; int j = ai[1]; int k
	 * = ai[2]; fd world = ModLoader.getMinecraftInstance().f; TileEntityColor
	 * tileEntityColor = (TileEntityColor) world.b(i, j, k); if
	 * (packet.packetType == onBlockColorChanged) { int l = ai[3]; int j1 =
	 * ai[4]; int l1 = ai[5]; tileEntityColor.setColor(l, j1, l1); world.j(i, j,
	 * k); } else if (packet.packetType == onBlockRightClick) { int i1 = ai[3];
	 * int k1 = ai[4]; int i2 = ai[5]; dc player =
	 * ModLoader.getMinecraftInstance().h; ModLoader.OpenGUI(player, new
	 * GuiColoredBlockMenu(player, tileEntityColor, new Color(i1, k1, i2))); }
	 * else if (packet.packetType == onBlockPlayNote) {
	 * tileEntityColor.displayParticle(world, i, j, k); } }
	 * 
	 * public da HandleGUI(int type) { if (type ==
	 * BlockPictureFactory.guiFurnaceId) { TileEntityPictureFactory entity = new
	 * TileEntityPictureFactory(); return new
	 * GuiPictureFactoryFurnace(getPlayer().c, entity); } if (type ==
	 * BlockModelFactory.guiFurnaceId) { TileEntityModelFactory entity = new
	 * TileEntityModelFactory(); return new
	 * GuiModelFactoryFurnace(getPlayer().c, entity); } return null; }
	 * 
	 * public static boolean isMultiplayer() { return
	 * ModLoader.getMinecraftInstance().f.B; }
	 * 
	 * public static gs getPlayer() { return ModLoader.getMinecraftInstance().h;
	 * }
	 */
}