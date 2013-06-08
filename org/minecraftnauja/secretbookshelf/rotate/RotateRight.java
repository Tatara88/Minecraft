package org.minecraftnauja.secretbookshelf.rotate;

import org.minecraftnauja.secretbookshelf.Config;

import net.minecraft.world.World;

/**
 * Rotates blocks at the right of given block.
 */
public class RotateRight extends RotateBase {

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
	public RotateRight(int width, int height, int depth) {
		super(width, height, 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDepth(int depth) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void apply(World world, int x, int y, int z, int dir,
			boolean clockwise) {
		// Translations from the block at (x, y, z) to its right and its front.
		int dX1 = 0, dZ1 = 0;
		int dX2 = 0, dZ2 = 0;
		switch (dir) {
		case 2:
			dX1--;
			dZ2--;
			break;
		case 5:
			dZ1--;
			dX2++;
			break;
		case 3:
			dX1++;
			dZ2++;
			break;
		case 4:
			dZ1++;
			dX2--;
			break;
		}
		// Swaps the translations if counterclockwise.
		if (!clockwise) {
			int i = dX1;
			dX1 = dX2;
			dX2 = i;
			i = dZ1;
			dZ1 = dZ2;
			dZ2 = i;
		}
		// Rotates the blocks.
		applyRotation(world, x, y, z, dX1, dZ1, dX2, dZ2);
	}

	/**
	 * Applies the rotation with given parameters.
	 * 
	 * @param world
	 *            the world.
	 * @param x
	 *            block's x-coordinate.
	 * @param y
	 *            block's y-coordinate.
	 * @param z
	 *            block's z-coordinate.
	 * @param dX1
	 *            translation from block's x-coordinate to source's
	 *            x-coordinate.
	 * @param dZ1
	 *            translation from block's z-coordinate to source's
	 *            z-coordinate.
	 * @param dX2
	 *            translation from block's x-coordinate to destination's
	 *            x-coordinate.
	 * @param dZ2
	 *            translation from block's z-coordinate to destination's
	 *            z-coordinate.
	 */
	protected void applyRotation(World world, int x, int y, int z, int dX1,
			int dZ1, int dX2, int dZ2) {
		// Rotates width blocks.
		int x1 = x + dX1;
		int z1 = z + dZ1;
		int x2 = x + dX2;
		int z2 = z + dZ2;
		int y1;
		for (int i = 0; i < width; ++i) {
			// Rotates height blocks.
			y1 = y - height / 2;
			for (int j = 0; j < height; ++j) {
				rotate(world, x1, z1, x2, z2, y1);
				++y1;
			}
			x1 += dX1;
			z1 += dZ1;
			x2 += dX2;
			z2 += dZ2;
		}
	}

}
