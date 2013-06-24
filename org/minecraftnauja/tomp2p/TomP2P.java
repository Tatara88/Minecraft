package org.minecraftnauja.tomp2p;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;

import org.minecraftnauja.p2p.P2P;
import org.minecraftnauja.p2p.provider.event.IProviderEvent;
import org.minecraftnauja.p2p.provider.event.ProviderAdapter;
import org.minecraftnauja.tomp2p.peer.Client;
import org.minecraftnauja.tomp2p.peer.IClient;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "TomP2P", name = "TomP2P", version = "1.0.0", dependencies = "required-after:P2P")
@NetworkMod(clientSideRequired = false, serverSideRequired = false, channels = { "TomP2P" }, connectionHandler = ConnectionHandler.class, packetHandler = PacketHandler.class)
public class TomP2P {

	/**
	 * Mod identifier.
	 */
	public static final String MOD_ID = "TomP2P";

	/**
	 * Mod instance.
	 */
	@Instance("TomP2P")
	public static TomP2P instance;

	/**
	 * Mod configuration.
	 */
	public static Config config;

	/**
	 * Side.
	 */
	public static Side side;

	/**
	 * List of peers.
	 */
	private static List<Peer> peers;

	/**
	 * Player's name to peer.
	 */
	private static Map<String, Peer> nameToPeer;

	/**
	 * {@inheritDoc}
	 */
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		side = event.getSide();
		if (side == Side.CLIENT) {
			config = Config.load(event);
			Client c = new Client();
			c.addListener(new ClientListener());
			P2P.register(c);
		}
		peers = new ArrayList<Peer>();
		nameToPeer = new HashMap<String, Peer>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Init
	public void load(FMLInitializationEvent event) {
		GameRegistry.registerPlayerTracker(new PlayerTracker());
	}

	// ***************************************************
	// CLIENT
	// ***************************************************

	/**
	 * Called when the player connected to the server.
	 */
	public static synchronized void onLoggedIn() {
		FMLLog.log(MOD_ID, Level.INFO, "onLoggedIn");
		try {
			// Asks the server to join the p2p network.
			TomP2P.sendJoinPacket();
		} catch (IOException e) {
			FMLLog.log(TomP2P.MOD_ID, Level.SEVERE, e,
					"Could not send the Join packet");
		}
	}

	/**
	 * Called when the player disconnected from the server.
	 */
	public static synchronized void onLoggedOut() {
		FMLLog.log(MOD_ID, Level.INFO, "onLoggedOut");
		// Client side, connection closed, shutdown the p2p client.
		if (TomP2P.side == Side.CLIENT) {
			((IClient) P2P.get(P2P.CLIENT_PROVIDER)).stop();
		}
	}

	/**
	 * Called when the informations to join the network have been received.
	 * 
	 * @param player
	 *            player's name.
	 * @param address
	 *            address to use.
	 * @param port
	 *            port to use.
	 */
	public static synchronized void onBootstrap(final String player,
			final String address, final int port) {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					((IClient) P2P.get(P2P.CLIENT_PROVIDER)).start(player,
							address, port);
				} catch (Exception e) {
					// Try again.
					FMLLog.log(TomP2P.MOD_ID, Level.SEVERE, e,
							"Could not start the client");
					try {
						sendJoinPacket();
					} catch (IOException e1) {
						FMLLog.log(TomP2P.MOD_ID, Level.SEVERE, e1,
								"Could not send the Join packet");
					}
				}
			}
		});
		t.setPriority(Thread.NORM_PRIORITY);
		t.start();
	}

	/**
	 * Called when there is no need to join the network.
	 */
	public static synchronized void onNoBootstrap(String player) {
		try {
			((IClient) P2P.get(P2P.CLIENT_PROVIDER)).start(player);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sends the {@code Join} packet to the server.<br/>
	 * <br/>
	 * 
	 * When a player connects to a server, he asks the server to send him an
	 * address and a port to join the peer-to-peer network by sending a
	 * {@code Join} packet.<br/>
	 * <br/>
	 * 
	 * The packet contains the port of the player in the case where the network
	 * doesn't already exist so that it can be send to future players.
	 * 
	 * @throws IOException
	 *             error with IO.
	 */
	public static synchronized void sendJoinPacket() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		dos.writeInt(PacketType.Join.ordinal());
		dos.writeInt(config.port);
		Packet250CustomPayload p = new Packet250CustomPayload();
		p.channel = MOD_ID;
		p.data = bos.toByteArray();
		p.length = bos.size();
		PacketDispatcher.sendPacketToServer(p);
	}

	/**
	 * Sends the {@code Joined} packet to the server.
	 * 
	 * @throws IOException
	 *             error with IO.
	 */
	public static synchronized void sendJoinedPacket() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		dos.writeInt(PacketType.Joined.ordinal());
		dos.writeInt(config.port);
		Packet250CustomPayload p = new Packet250CustomPayload();
		p.channel = MOD_ID;
		p.data = bos.toByteArray();
		p.length = bos.size();
		PacketDispatcher.sendPacketToServer(p);
	}

	/**
	 * Sends the {@code Left} packet to the server.
	 * 
	 * @throws IOException
	 *             error with IO.
	 */
	public static synchronized void sendLeftPacket() throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		dos.writeInt(PacketType.Left.ordinal());
		Packet250CustomPayload p = new Packet250CustomPayload();
		p.channel = MOD_ID;
		p.data = bos.toByteArray();
		p.length = bos.size();
		PacketDispatcher.sendPacketToServer(p);
	}

	/**
	 * Listener for the client.
	 */
	private static class ClientListener extends ProviderAdapter {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onStarted(IProviderEvent event) {
			// Client started, notify the server.
			try {
				sendJoinedPacket();
			} catch (IOException e) {
				FMLLog.log(MOD_ID, Level.SEVERE, e,
						"Could not send the Joined packet");
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onStopped(IProviderEvent event) {
			// Client stopped, notify the server.
			try {
				sendLeftPacket();
			} catch (IOException e) {
				FMLLog.log(MOD_ID, Level.SEVERE, e,
						"Could not send the Left packet");
			}
		}

	}

	// ***************************************************
	// SERVER
	// ***************************************************

	/**
	 * Called when a player asks to join the network.
	 * 
	 * @param player
	 *            player.
	 * @param address
	 *            its address.
	 * @param port
	 *            its port.
	 */
	public static synchronized void onJoin(Player player, String address,
			int port) {
		// Gets a peer to bootstrap.
		Peer p = getBootstrapPeer();
		if (p == null) {
			// No peer available, starts the network.
			p = new Peer(address, port);
			nameToPeer.put(((EntityPlayer) player).username, p);
			peers.add(p);
			FMLLog.log(MOD_ID, Level.INFO, "Player %s started the network",
					player);
			sendNoBootstrapPacket(player);
		} else {
			// Peer available, sends informations.
			sendBootstrapPacket(player, p.getAddress(), p.getPort());
		}
	}

	/**
	 * Called when a player joined the network.
	 * 
	 * @param player
	 *            player.
	 * @param address
	 *            its address.
	 * @param port
	 *            its port.
	 */
	public static synchronized void onJoined(String player, String address,
			int port) {
		if (!nameToPeer.containsKey(player)) {
			Peer p = new Peer(address, port);
			nameToPeer.put(player, p);
			peers.add(p);
			FMLLog.log(MOD_ID, Level.INFO, "Player %s joined the network",
					player);
		}
	}

	/**
	 * Called when a player left the network.
	 * 
	 * @param player
	 *            player.
	 * @param address
	 *            its address.
	 * @param port
	 *            its port.
	 */
	public static synchronized void onLeft(String player) {
		Peer p = nameToPeer.remove(player);
		peers.remove(p);
		FMLLog.log(MOD_ID, Level.INFO, "Player %s left the network", player);
	}

	/**
	 * Gets a peer from the network that can be used by others players to join
	 * it.
	 * 
	 * @return a peer.
	 */
	public static synchronized Peer getBootstrapPeer() {
		if (peers.isEmpty()) {
			return null;
		} else {
			return peers.get((int) (Math.random() * peers.size()));
		}
	}

	/**
	 * Sends the {@code Bootstrap} packet to a player.<br/>
	 * <br/>
	 * 
	 * The packet contains the address and the port usable by a player to join
	 * the network.
	 * 
	 * @param player
	 *            player.
	 * @param address
	 *            address to use.
	 * @param port
	 *            port to use.
	 */
	public static synchronized void sendBootstrapPacket(Player player,
			String address, int port) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			dos.writeInt(PacketType.Bootstrap.ordinal());
			dos.writeUTF(address);
			dos.writeInt(port);
			Packet250CustomPayload p = new Packet250CustomPayload();
			p.channel = MOD_ID;
			p.data = bos.toByteArray();
			p.length = bos.size();
			PacketDispatcher.sendPacketToPlayer(p, player);
		} catch (IOException e) {
			FMLLog.log(MOD_ID, Level.INFO,
					"Could not send the Bootstrap packet to player %s", player);
		}
	}

	/**
	 * Sends the {@code NoBootstrap} packet to a player.<br/>
	 * <br/>
	 * 
	 * This packet is sent when the network doesn't exist and the player is the
	 * first one connected.
	 * 
	 * @param player
	 *            player.
	 */
	public static synchronized void sendNoBootstrapPacket(Player player) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			dos.writeInt(PacketType.NoBootstrap.ordinal());
			Packet250CustomPayload p = new Packet250CustomPayload();
			p.channel = MOD_ID;
			p.data = bos.toByteArray();
			p.length = bos.size();
			PacketDispatcher.sendPacketToPlayer(p, player);
		} catch (IOException e) {
			FMLLog.log(MOD_ID, Level.INFO,
					"Could not send the NoBootstrap packet to player %s",
					player);
		}
	}

}
