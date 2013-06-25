package org.minecraftnauja.tomp2p;

import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;

/**
 * Enums for packets types.
 */
public enum PacketType {

	/**
	 * Client wanting to join the p2p network, server-side.
	 */
	Join {

		public void handle(INetworkManager manager, Player player,
				DataInputStream dis) throws IOException {
			String address = dis.readUTF();
			int port = dis.readInt();
			TomP2P.onJoin(player, address, port);
		}

	},

	/**
	 * Server sending informations to join the network, client-side.
	 */
	Bootstrap {

		public void handle(INetworkManager manager, Player player,
				DataInputStream dis) throws IOException {
			String username = dis.readUTF();
			String address = dis.readUTF();
			int port = dis.readInt();
			TomP2P.onBootstrap(((EntityPlayer) player).username, username,
					address, port);
		}

	},

	/**
	 * Network is not already set up, client-side.
	 */
	NoBootstrap {

		public void handle(INetworkManager manager, Player player,
				DataInputStream dis) throws IOException {
			TomP2P.onNoBootstrap(((EntityPlayer) player).username);
		}

	},

	/**
	 * Notifies the server that a client joined the network, server-side.
	 */
	Joined {

		public void handle(INetworkManager manager, Player player,
				DataInputStream dis) throws IOException {
			String address = dis.readUTF();
			int port = dis.readInt();
			TomP2P.onJoined(((EntityPlayer) player).username, address, port);
		}

	},

	/**
	 * Notifies the server that a client left the network, server-side.
	 */
	Left {

		public void handle(INetworkManager manager, Player player,
				DataInputStream dis) throws IOException {
			TomP2P.onLeft(((EntityPlayer) player).username);
		}

	};

	/**
	 * Handles received packet.
	 * 
	 * @param manager
	 *            network manager.
	 * @param player
	 *            sender.
	 * @param dis
	 *            packet data.
	 * @throws IOException
	 *             error with IO.
	 */
	public abstract void handle(INetworkManager manager, Player player,
			DataInputStream dis) throws IOException;

}
