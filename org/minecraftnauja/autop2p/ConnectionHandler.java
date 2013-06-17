package org.minecraftnauja.autop2p;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Level;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;

import org.minecraftnauja.tomp2p.TomP2P;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

/**
 * Handle players connections to establish p2p connections.
 */
public class ConnectionHandler implements IConnectionHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler,
			INetworkManager manager) {
		// Sends server configuration.
		try {
			FMLLog.log(AutoP2P.MOD_ID, Level.INFO,
					"Player logged in, sending server config");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			// Localhost or not.
			String hs = ((InetSocketAddress) manager.getSocketAddress())
					.getHostString();
			if (hs.equalsIgnoreCase("localhost") || hs.equals("127.0.0.1")) {
				// Localhost, send local address.
				dos.writeUTF(AutoP2P.config.server.address);
			} else {
				// Send external address.
				dos.writeUTF(AutoP2P.config.server.externalAddress);
			}
			//
			dos.writeInt(AutoP2P.config.server.port);
			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = AutoP2P.MOD_ID;
			packet.data = bos.toByteArray();
			packet.length = bos.size();
			PacketDispatcher.sendPacketToPlayer(packet, player);
		} catch (IOException e) {
			FMLLog.log(AutoP2P.MOD_ID, Level.SEVERE, e,
					"Could not send server config to player");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clientLoggedIn(NetHandler clientHandler,
			INetworkManager manager, Packet1Login login) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String connectionReceived(NetLoginHandler netHandler,
			INetworkManager manager) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void connectionOpened(NetHandler netClientHandler, String server,
			int port, INetworkManager manager) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void connectionOpened(NetHandler netClientHandler,
			MinecraftServer server, INetworkManager manager) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void connectionClosed(INetworkManager manager) {
		// Client side, connection closed, shutdown the p2p client.
		if (AutoP2P.side == Side.CLIENT) {
			TomP2P.shutdownClient();
		}
	}

}
