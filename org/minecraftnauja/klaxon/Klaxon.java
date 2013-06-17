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
import org.minecraftnauja.p2p.provider.file.IFileProvider;
import org.minecraftnauja.p2p.provider.file.event.FileAdapter;
import org.minecraftnauja.p2p.provider.file.event.IFileProviderEvent;
import org.minecraftnauja.tomp2p.TomP2P;
import org.minecraftnauja.tomp2p.event.ClientEvent;
import org.minecraftnauja.tomp2p.event.GetEvent;
import org.minecraftnauja.tomp2p.event.P2PAdapter;
import org.minecraftnauja.tomp2p.event.P2PListener;
import org.minecraftnauja.tomp2p.event.PeerListener;

import com.google.common.io.Files;

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

@Mod(modid = "Klaxon", name = "Klaxon", version = "1.0.0", dependencies = "required-after:AutoP2P")
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
	 * Listener for the p2p mod.
	 */
	public static P2PListener p2pListener;

	/**
	 * Listener for the peer.
	 */
	public static MyFileListener fileListener;

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
			// Adds the listeners.
			p2pListener = new MyP2PListener();
			fileListener = new MyFileListener();
			TomP2P.addListener(p2pListener);
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
	private static synchronized void clientStarted(ClientEvent event) {
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
			try {
				byte[] data = Files.toByteArray(file);
				event.getPeer().put(MOD_ID, name, data);
			} catch (IOException e) {
				FMLLog.log(MOD_ID, Level.SEVERE, e,
						"Could not upload the klaxon");
			}
		}
	}

	/**
	 * Called when the client has been shutdown.
	 * 
	 * @param event
	 *            the event.
	 */
	private static synchronized void clientShutdown(ClientEvent event) {
		running = false;
		clear();
		P2P.get(P2P.CLIENT_PROVIDER).getFileProvider()
				.removeListener(fileListener);
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
	 * @param event
	 *            the event.
	 */
	private static synchronized void gotKlaxon(IFileProviderEvent event) {
		String name = event.getName();
		File file = event.getFile();
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
	 * @param event
	 *            the event.
	 */
	private static synchronized void gotKlaxonFailed(IFileProviderEvent event) {
		String name = event.getName();
		loadingKlaxons.remove(name);
		loadedKlaxons.remove(name);
		FMLLog.log(MOD_ID, Level.SEVERE, event.getError(),
				"Failed to get the klaxon %s", name);
	}

	/**
	 * Custom listener for the P2P mod.
	 */
	private class MyP2PListener extends P2PAdapter {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onClientStarted(ClientEvent event) {
			clientStarted(event);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onClientShutdown(ClientEvent event) {
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
		public void onFileUploaded(IFileProviderEvent event) {
			if (event.getChannel().equals(MOD_ID)) {
				FMLLog.log(MOD_ID, Level.INFO,
						"Klaxon has been uploaded, notifying players");
				try {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					DataOutputStream dos = new DataOutputStream(bos);
					dos.writeInt(PacketType.UploadedKlaxon.ordinal());
					dos.writeUTF(event.getName());
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
		public void onFileUploadException(IFileProviderEvent event) {
			if (event.getChannel().equals(MOD_ID)) {
				FMLLog.log(MOD_ID, Level.SEVERE, event.getError(),
						"Failed to upload the klaxon");
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onFileDownloaded(IFileProviderEvent event) {
			if (event.getChannel().equals(MOD_ID)) {
				gotKlaxon(event);
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onFileDownloadException(IFileProviderEvent event) {
			if (event.getChannel().equals(MOD_ID)) {
				gotKlaxonFailed(event);
			}
		}
	}

}
