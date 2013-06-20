package org.minecraftnauja.p2p.provider.packet.task;

import org.minecraftnauja.p2p.provider.packet.IPacket;
import org.minecraftnauja.p2p.provider.packet.IPacketProvider;
import org.minecraftnauja.p2p.task.ITask;

/**
 * Interface for tasks from the {@code IPacketProvider}.
 */
public interface IPacketTask extends ITask<IPacketProvider> {

	/**
	 * Gets the packet.
	 * 
	 * @return the packet.
	 */
	public IPacket getPacket();

}
