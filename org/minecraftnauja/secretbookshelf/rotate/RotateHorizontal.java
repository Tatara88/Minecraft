package org.minecraftnauja.secretbookshelf.rotate;

import net.minecraft.world.World;

/**
 * Rotates blocks at the left and right of given block.
 */
public class RotateHorizontal extends RotateBase {

	/**
	 * Rotation for blocks at the left.
	 */
	private RotateLeft rotateLeft;

	/**
	 * Rotation for blocks at the right.
	 */
	private RotateRight rotateRight;

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
	public RotateHorizontal(int width, int height, int depth) {
		super(width, height, 1);
		--width;
		rotateLeft = new RotateLeft(width / 2, height, depth);
		rotateRight = new RotateRight(width - (width / 2), height, depth);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setWidth(int width) {
		super.setWidth(width);
		--width;
		rotateLeft.setWidth(width / 2);
		rotateRight.setWidth(width - (width / 2));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHeight(int height) {
		super.setHeight(height);
		rotateLeft.setHeight(height);
		rotateRight.setHeight(height);
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
		rotateLeft.apply(world, x, y, z, dir, clockwise);
		rotateRight.apply(world, x, y, z, dir, clockwise);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "RotateHorizontal [rotateLeft=" + rotateLeft + ", rotateRight="
				+ rotateRight + ", toString()=" + super.toString() + "]";
	}

}
