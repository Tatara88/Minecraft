package org.minecraftnauja.toolbox;

import org.minecraftnauja.p2p.provider.packet.IPacket;
import org.minecraftnauja.p2p.provider.packet.event.PacketAdapter;
import org.minecraftnauja.toolbox.packet.PacketBase;

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
		if (packet.getChannel().equals(Toolbox.MOD_ID)) {
			((PacketBase) packet).handle();
		}
	}

}