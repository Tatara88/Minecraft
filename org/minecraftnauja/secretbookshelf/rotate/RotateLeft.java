package org.minecraftnauja.secretbookshelf.rotate;

import net.minecraft.world.World;

/**
 * Rotates blocks at the left of given block.
 */
public class RotateLeft extends RotateRight {

	/**
	 * Initializing constructor.
	 * 
	 * @param width
	 *            number of blocks to rotate on the x-axis.
	 * @param height
	 *            number of blocks to rotate on the y-axis.
	 * @param depth
	 *            number of blocks to rotate on the z-axis.
	 */
	public RotateLeft(int width, int height, int depth) {
		super(width, height, depth);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyRotation(World world, int x, int y, int z, int dX1,
			int dZ1, int dX2, int dZ2) {
		super.applyRotation(world, x, y, z, -dX1, -dZ1, -dX2, -dZ2);
	}

}
