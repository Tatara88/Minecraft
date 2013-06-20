package org.minecraftnauja.toolbox;

import org.minecraftnauja.p2p.P2P;
import org.minecraftnauja.p2p.provider.IProvider;
import org.minecraftnauja.p2p.provider.event.IProviderEvent;
import org.minecraftnauja.p2p.provider.event.ProviderAdapter;
import org.minecraftnauja.toolbox.command.CommandAddress;
import org.minecraftnauja.toolbox.command.CommandPing;
import org.minecraftnauja.toolbox.command.CommandPlayer;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "Toolbox", name = "Toolbox", version = "1.0.0", dependencies = "required-after:P2P")
@NetworkMod(clientSideRequired = false, serverSideRequired = false, channels = { "Toolbox" }, packetHandler = PacketHandler.class)
public class Toolbox {

	/**
	 * Mod identifier.
	 */
	public static final String MOD_ID = "Toolbox";

	/**
	 * Mod instance.
	 */
	@Instance("Toolbox")
	public static Toolbox instance;

	/**
	 * If the provider is running.
	 */
	public static boolean running;

	/**
	 * Mod starting.
	 */
	@Init
	public void load(final FMLInitializationEvent event) {
		if (event.getSide() == Side.CLIENT) {
			// Client side, add listeners to the provider.
			IProvider p = P2P.get(P2P.CLIENT_PROVIDER);
			p.addListener(new MyProviderListener());
			p.getPacketProvider().addListener(new MyPacketListener());
		}
	}

	/**
	 * Server starting.
	 */
	@ServerStarting
	public void serverLoad(FMLServerStartingEvent event) {
		// Register the ping command.
		event.registerServerCommand(new CommandPing());
		event.registerServerCommand(new CommandAddress());
		event.registerServerCommand(new CommandPlayer());
	}

	/**
	 * Custom listener for the provider.
	 */
	private static class MyProviderListener extends ProviderAdapter {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onStarted(IProviderEvent event) {
			running = true;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onStopped(IProviderEvent event) {
			running = false;
		}

	}

}
