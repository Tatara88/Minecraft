package org.minecraftnauja.tomp2p;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import net.tomp2p.p2p.Peer;
import net.tomp2p.storage.StorageDisk;
import net.tomp2p.storage.StorageMemory;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Mod configuration.
 */
public class Config {

	/**
	 * Loads the configuration.
	 * 
	 * @param event
	 *            initialization event.
	 * @return the configuration.
	 */
	public static Config load(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(
				event.getSuggestedConfigurationFile());
		config.load();
		Config c = new Config();
		c.load(config, event);
		config.save();
		return c;
	}

	/**
	 * Address.
	 */
	public String address = "127.0.0.1";

	/**
	 * Port.
	 */
	public int port = 4563;

	/**
	 * Behind a firewall or not.
	 */
	public boolean behindFirewall = false;

	/**
	 * Storage type.
	 */
	public StorageType storageType = StorageType.Memory;

	/**
	 * Storage directory.
	 */
	public String storage = null;

	/**
	 * Loads the configuration.
	 * 
	 * @param config
	 *            the configuration.
	 * @param event
	 *            initialization event.
	 */
	private void load(Configuration config, FMLPreInitializationEvent event) {
		address = config
				.get(Configuration.CATEGORY_GENERAL, "Address", address)
				.getString();
		port = config.get(Configuration.CATEGORY_GENERAL, "Port", port).getInt(
				port);
		behindFirewall = config.get(Configuration.CATEGORY_GENERAL,
				"BehindFirewall", behindFirewall).getBoolean(behindFirewall);
		storage = config.get(Configuration.CATEGORY_GENERAL, "Storage",
				"memory").getString();
		// Storage in memory.
		if (storage.equalsIgnoreCase("memory")) {
			storageType = StorageType.Memory;
			storage = null;
		} else {
			// Storage on disk, creates the storage directory.
			storageType = StorageType.Disk;
			File f = new File(storage);
			if (!f.exists()) {
				if (!f.mkdirs()) {
					FMLLog.log(TomP2P.MOD_ID, Level.SEVERE,
							"Could not make storage directory %s", storage);
				}
			}
		}
	}

	/**
	 * Enum for the types of storage.
	 */
	public static enum StorageType {

		/**
		 * No storage.
		 */
		None {

			public void apply(Peer peer) {
			}

		},
		/**
		 * Storage on disk.
		 */
		Disk {

			public void apply(Peer peer) {
				peer.getPeerBean().setStorage(
						new StorageDisk(TomP2P.config.storage));
			}

		},
		/**
		 * Storage in memory.
		 */
		Memory {

			public void apply(Peer peer) {
				peer.getPeerBean().setStorage(new StorageMemory());
			}

		};

		/**
		 * Applies the configuration to given peer.
		 * 
		 * @param peer
		 *            the peer.
		 */
		public abstract void apply(Peer peer);

	}

}
