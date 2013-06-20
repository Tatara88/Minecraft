package org.minecraftnauja.ping;

import net.minecraft.client.Minecraft;

import org.minecraftnauja.p2p.provider.packet.IPacket;
import org.minecraftnauja.p2p.provider.packet.event.PacketAdapter;

/**
 * Custom listener for the packet provider.
 */
public class MyPacketListener extends PacketAdapter {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onReceived(IPacket packet) {
		// Only handle packets on the MOD_ID channel.
		if (packet.getChannel().equals(Ping.MOD_ID)) {
			// Received the PacketPing.
			PacketPing p = (PacketPing) packet;
			String sender = p.getSender();
			String address = p.getAddress().toString();
			if (!address.startsWith("/")) {
				address = '/' + address;
			}
			p.pong = !p.pong;
			if (p.pong) {
				// If pong is true, we are the one pinged.
				Minecraft.getMinecraft().thePlayer.addChatMessage('[' + sender
						+ address + "] ping");
				if (Ping.running) {
					// Provider still running, respond to ping.
					p.getSource().sendTo(Ping.MOD_ID, p, sender);
				}
			} else {
				// If pong is false, we are the one pinging.
				Minecraft.getMinecraft().thePlayer.addChatMessage('[' + sender
						+ address + "] pong");
				p.time--;
				if (Ping.running && p.time >= 1) {
					// Provider still running, ping again.
					p.getSource().sendTo(Ping.MOD_ID, p, sender);
				}
			}
		}
	}

}