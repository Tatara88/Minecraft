package org.minecraftnauja.klaxon;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.Configuration;

import org.minecraftnauja.klaxon.item.ItemKlaxon;
import org.minecraftnauja.p2p.P2P;
import org.minecraftnauja.p2p.provider.IProvider;
import org.minecraftnauja.p2p.provider.event.IProviderEvent;
import org.minecraftnauja.p2p.provider.event.IProviderListener;
import org.minecraftnauja.p2p.provider.event.ProviderAdapter;
import org.minecraftnauja.p2p.provider.file.IFileProvider;
import org.minecraftnauja.p2p.provider.file.event.FileAdapter;
import org.minecraftnauja.p2p.provider.file.event.FileListener;
import org.minecraftnauja.p2p.provider.file.task.IDownload;
import org.minecraftnauja.p2p.provider.file.task.IUpload;
import org.minecraftnauja.p2p.provider.player.IPlayerProvider;
import org.minecraftnauja.p2p.provider.player.event.PlayerAdapter;
import org.minecraftnauja.p2p.provider.player.task.IGetAddress;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "Klaxon", name = "Klaxon", version = "1.0.0", dependencies = "required-after:P2P")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "Klaxon" }, packetHandler = PacketHandler.class)
public class Klaxon {

	/**
	 * Mod identifier.
	 */
	public static final String MOD_ID = "Klaxon";

	/**
	 * Mod instance.
	 */
	@Instance("Klaxon")
	public static Klaxon instance;

	/**
	 * The configuration.
	 */
	public static Configuration config;

	/**
	 * File for the klaxon.
	 */
	public static File file;

	/**
	 * Listener for the provider.
	 */
	public static IProviderListener providerListener;

	/**
	 * Listener for the file provider.
	 */
	public static FileListener fileListener;

	/**
	 * Default item identifier.
	 */
	public static final int klaxonDefaultId = 5000;

	/**
	 * Item identifier.
	 */
	public static int klaxonId;

	/**
	 * Item instance.
	 */
	public static Item klaxon;

	/**
	 * Klaxons folder.
	 */
	public static File klaxons;

	/**
	 * If the client is running.
	 */
	public static boolean running;

	/**
	 * Loading klaxons.
	 */
	public static Set<String> loadingKlaxons;

	/**
	 * Loaded klaxons.
	 */
	public static Set<String> loadedKlaxons;

	/**
	 * {@inheritDoc}
	 */
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		if (event.getSide() == Side.CLIENT) {
			// Creates the klaxon folder.
			klaxons = new File(event.getModConfigurationDirectory(), "klaxons");
			if (!klaxons.exists()) {
				klaxons.mkdirs();
			}
			FMLLog.log(MOD_ID, Level.INFO, "Folder %s", klaxons);
			// Loads client configuration.
			file = new File(config.get(Configuration.CATEGORY_GENERAL,
					"Klaxon", "").getString());
			// Loading and loaded klaxons.
			loadingKlaxons = new HashSet<String>();
			loadedKlaxons = new HashSet<String>();
		}
		// Loads shared configuration.
		klaxonId = config.get(Configuration.CATEGORY_ITEM, "Klaxon",
				klaxonDefaultId).getInt(klaxonDefaultId);
		config.save();
	}

	/**
	 * {@inheritDoc}
	 */
	@Init
	public void load(final FMLInitializationEvent event) {
		klaxon = new ItemKlaxon(klaxonId);
		LanguageRegistry.addName(klaxon, "Klaxon");
		GameRegistry.addRecipe(new ItemStack(klaxon), " x ", "xox", " x ", 'x',
				new ItemStack(Item.stick), 'o', new ItemStack(Item.leather));
		if (event.getSide() == Side.CLIENT) {
			String name = "klaxon" + Minecraft.getMinecraft().session.username;
			Minecraft.getMinecraft().sndManager.addSound(name + ".ogg", file);
			FMLLog.log(MOD_ID, Level.INFO, "klaxon name %s", name);
		}
		// Adds listeners.
		if (event.getSide() == Side.CLIENT) {
			providerListener = new MyProviderListener();
			fileListener = new MyFileListener();
			IProvider p = P2P.get(P2P.CLIENT_PROVIDER);
			p.addListener(providerListener);
			p.getFileProvider().addListener(fileListener);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws IOException
	 */
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
	}

	/**
	 * Called when the client has been started.
	 * 
	 * @param event
	 *            the event.
	 */
	private static synchronized void clientStarted(IProviderEvent event) {
		running = true;
		clear();
		P2P.get(P2P.CLIENT_PROVIDER).getFileProvider()
				.addListener(fileListener);
		// Klaxon file exists.
		if (file.exists() && file.isFile()) {
			// Uploads the klaxon.
			String name = "klaxon"
					+ Minecraft.getMinecraft().thePlayer.username + "a";
			FMLLog.log(MOD_ID, Level.INFO,
					"Client started, uploading the klaxon to %s", name);
			event.getFileProvider().upload(MOD_ID, file, name);
		}
	}

	/**
	 * Called when the client has been shutdown.
	 * 
	 * @param event
	 *            the event.
	 */
	private static synchronized void clientShutdown(IProviderEvent event) {
		running = false;
		clear();
	}

	/**
	 * Clears loaded klaxons.
	 */
	private static synchronized void clear() {
		loadingKlaxons.clear();
		loadedKlaxons.clear();
		File[] files = klaxons.listFiles();
		if (files != null) {
			for (File f : files) {
				f.delete();
			}
		}
	}

	/**
	 * Gets the klaxon with given name only if needed.
	 * 
	 * @param name
	 *            klaxon's name.
	 */
	public static synchronized void getKlaxonIfNeeded(String name) {
		if (running) {
			if (loadingKlaxons.contains(name) || loadedKlaxons.contains(name)) {
				FMLLog.log(MOD_ID, Level.INFO,
						"Must get the klaxon %s if needed, already have it",
						name);
			} else {
				getKlaxon(name);
			}
		}
	}

	/**
	 * Gets the klaxon with given name.
	 * 
	 * @param name
	 *            klaxon's name.
	 */
	public static synchronized void getKlaxon(String name) {
		if (running) {
			if (!loadingKlaxons.contains(name)) {
				FMLLog.log(MOD_ID, Level.INFO, "Must get the klaxon %s", name);
				loadedKlaxons.remove(name);
				loadingKlaxons.add(name);
				File file = new File(klaxons, name + ".ogg");
				IFileProvider p = P2P.get(P2P.CLIENT_PROVIDER)
						.getFileProvider();
				p.download(MOD_ID, name, file);
			}
		}
	}

	/**
	 * Called when a klaxon has been received.
	 * 
	 * @param task
	 *            the task.
	 */
	private static synchronized void gotKlaxon(IDownload task) {
		String name = task.getName();
		File file = task.getFile();
		FMLLog.log(MOD_ID, Level.INFO, "Klaxon %s saved to %s", name,
				file.getAbsolutePath());
		// Adds the klaxon to the sound manager.
		Minecraft.getMinecraft().sndManager.addSound(name + ".ogg", file);
		// Adds to the list of loaded klaxons.
		loadedKlaxons.add(name);
		loadingKlaxons.remove(name);
	}

	/**
	 * Called when a klaxon has not been received because of an error.
	 * 
	 * @param task
	 *            the task.
	 */
	private static synchronized void gotKlaxonFailed(IDownload task) {
		String name = task.getName();
		loadingKlaxons.remove(name);
		loadedKlaxons.remove(name);
		FMLLog.log(MOD_ID, Level.SEVERE, task.getError(),
				"Failed to get the klaxon %s", name);
	}

	/**
	 * Custom listener for the provider.
	 */
	private class MyProviderListener extends ProviderAdapter {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onStarted(IProviderEvent event) {
			clientStarted(event);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onStopped(IProviderEvent event) {
			clientShutdown(event);
		}

	}

	/**
	 * Custom listener for the file provider.
	 */
	private class MyFileListener extends FileAdapter {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onUploaded(IUpload task) {
			if (task.getChannel().equals(MOD_ID)) {
				FMLLog.log(MOD_ID, Level.INFO,
						"Klaxon has been uploaded, notifying players");
				try {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					DataOutputStream dos = new DataOutputStream(bos);
					dos.writeInt(PacketType.UploadedKlaxon.ordinal());
					dos.writeUTF(task.getName());
					Packet250CustomPayload packet = new Packet250CustomPayload();
					packet.channel = MOD_ID;
					packet.data = bos.toByteArray();
					packet.length = bos.size();
					PacketDispatcher.sendPacketToServer(packet);
				} catch (IOException e) {
					FMLLog.log(MOD_ID, Level.SEVERE, e,
							"Could not notify the players");
				}
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onUploadException(IUpload task) {
			if (task.getChannel().equals(MOD_ID)) {
				FMLLog.log(MOD_ID, Level.SEVERE, task.getError(),
						"Failed to upload the klaxon");
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onDownloaded(IDownload task) {
			if (task.getChannel().equals(MOD_ID)) {
				gotKlaxon(task);
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onDownloadException(IDownload task) {
			if (task.getChannel().equals(MOD_ID)) {
				gotKlaxonFailed(task);
			}
		}
	}

}
