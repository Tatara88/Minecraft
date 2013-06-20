package org.minecraftnauja.ping;

import net.minecraft.client.Minecraft;

import org.minecraftnauja.p2p.P2P;
import org.minecraftnauja.p2p.provider.IProvider;
import org.minecraftnauja.p2p.provider.event.IProviderEvent;
import org.minecraftnauja.p2p.provider.event.ProviderAdapter;
import org.minecraftnauja.p2p.provider.packet.IPacket;
import org.minecraftnauja.p2p.provider.packet.event.PacketAdapter;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;

/**
 * Mod allowing to ping others players having this mod.
 * 
 * <br/>
 * <br/>
 * When a player does the "/ping &lt;name&gt; &lt;time&gt;" command, the server
 * sends him a {@code Packet250CustomPayload} to initiate the process. When this
 * packet is received, the player sends a {@code PacketPing} to the player
 * selected in the command. When the other player receives the packet, it
 * displays "[Sender/address] ping" in its chat and he sends back the packet.
 * When the player receives the packet, it displays "[Sender/address] pong" in
 * its chat. This process is repeated <i>time</i> times.
 * 
 * <br/>
 * <br/>
 * All the {@code PacketPing} are sent via the peer-to-peer network. So, this
 * mod is intended to allow players to check if the network is working and if
 * they are connected.
 */
@Mod(modid = "Ping", name = "Ping", version = "1.0.0", dependencies = "required-after:P2P")
@NetworkMod(clientSideRequired = false, serverSideRequired = false, channels = { "Ping" }, packetHandler = PacketHandler.class)
public class Ping {

	/**
	 * Mod identifier.
	 */
	public static final String MOD_ID = "Ping";

	/**
	 * Mod instance.
	 */
	@Instance("Ping")
	public static Ping instance;

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
