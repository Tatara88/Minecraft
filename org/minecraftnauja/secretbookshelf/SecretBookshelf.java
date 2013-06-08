package org.minecraftnauja.secretbookshelf;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;

import org.minecraftnauja.secretbookshelf.block.BlockSecretBookshelf;

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

@Mod(modid = "SecretBookshelf", name = "SecretBookshelf", version = "0.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SecretBookshelf {

	/**
	 * Mod's instance.
	 */
	@Instance("SecretBookshelf")
	public static SecretBookshelf instance;

	/**
	 * Block's instance.
	 */
	public static Block secretBookshelf;

	/**
	 * {@inheritDoc}
	 */
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());
		config.load();
		Config.load(config);
		config.save();
	}

	/**
	 * {@inheritDoc}
	 */
	@Init
	public void load(FMLInitializationEvent event) {
		secretBookshelf = new BlockSecretBookshelf(Config.secretBookshelfID)
				.setHardness(Block.bookShelf.blockHardness)
				.setStepSound(Block.bookShelf.stepSound)
				.setUnlocalizedName("secretBookshelf");
		// Register.
		LanguageRegistry.addName(secretBookshelf, "Secret Bookshelf");
		GameRegistry.registerBlock(secretBookshelf, "secretBookshelf");
		// Craft.
		ItemStack sbs = new ItemStack(secretBookshelf);
		ItemStack ls = new ItemStack(Block.lever);
		ItemStack bs = new ItemStack(Block.bookShelf);
		GameRegistry.addRecipe(sbs, "l", "b", 'l', ls, 'b', bs);
	}

	/**
	 * {@inheritDoc}
	 */
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
	}

}
