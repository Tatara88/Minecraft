package org.minecraftnauja.tomp2p;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

/**
 * Handle players connections to establish p2p connections.
 */
public class ConnectionHandler implements IConnectionHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clientLoggedIn(NetHandler clientHandler,
			INetworkManager manager, Packet1Login login) {
		TomP2P.onLoggedIn();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void connectionClosed(INetworkManager manager) {
		TomP2P.onLoggedOut();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler,
			INetworkManager manager) {
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

}
