package org.minecraftnauja.toolbox.packet;

import net.minecraft.client.Minecraft;

/**
 * Base for packets.
 */
public abstract class PacketBase extends
		org.minecraftnauja.p2p.provider.packet.PacketBase {

	/**
	 * Handle the packet.
	 */
	public abstract void handle();

	/**
	 * Adds a message to the chat.
	 * 
	 * @param msg
	 *            the message.
	 */
	public void chat(String msg) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(msg);
	}

}
