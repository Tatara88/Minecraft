package org.minecraftnauja.autop2p;

import java.io.IOException;
import java.util.logging.Level;

import org.minecraftnauja.tomp2p.TomP2P;
import org.minecraftnauja.tomp2p.config.ServerConfig;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.Mod.ServerStopped;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "AutoP2P", name = "AutoP2P", version = "1.0.0", dependencies = "required-after:P2P")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = { "AutoP2P" }, connectionHandler = ConnectionHandler.class, packetHandler = PacketHandler.class)
public class AutoP2P {

	/**
	 * Mod identifier.
	 */
	public static final String MOD_ID = "AutoP2P";

	/**
	 * Mod instance.
	 */
	@Instance("AutoP2P")
	public static AutoP2P instance;

	/**
	 * Mod configuration.
	 */
	public static Config config;

	/**
	 * Side.
	 */
	public static Side side;

	/**
	 * {@inheritDoc}
	 */
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		config = Config.load(event);
		side = event.getSide();
	}

	/**
	 * {@inheritDoc}
	 */
	@Init
	public void load(final FMLInitializationEvent event) throws IOException {
	}

	/**
	 * {@inheritDoc}
	 */
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
	}

	/**
	 * {@inheritDoc}
	 */
	@ServerStarted
	public void serverStarted(FMLServerStartedEvent event) {
		// Server side, server started, starts the peer.
		FMLLog.log(MOD_ID, Level.INFO, "Minecraft server started");
		try {
			ServerConfig sc = new ServerConfig(config.server.address,
					config.server.externalAddress, config.server.port,
					config.server.behindFirewall, config.server.storageType,
					config.server.storage);
			TomP2P.startServer("Server", sc);
		} catch (IOException e) {
			FMLLog.log(MOD_ID, Level.SEVERE, e,
					"Could not start the p2p server");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@ServerStopped
	public void serverStopped(FMLServerStoppedEvent event) {
		// Server side, server stopped, stops the peer.
		FMLLog.log(MOD_ID, Level.INFO, "Minecraft server stopped");
		TomP2P.shutdownServer();
	}

}
