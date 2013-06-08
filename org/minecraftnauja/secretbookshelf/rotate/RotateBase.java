package org.minecraftnauja.secretbookshelf.rotate;

import net.minecraft.world.World;

/**
 * Base for rotations.
 */
public abstract class RotateBase implements IRotate {

	/**
	 * Number of blocks to rotate on the x-axis.
	 */
	protected int width;

	/**
	 * Number of blocks to rotate on the y-axis.
	 */
	protected int height;

	/**
	 * Number of blocks to rotate on the z-axis.
	 */
	protected int depth;

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
	public RotateBase(int width, int height, int depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getDepth() {
		return depth;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * Rotates the block at (x1, y, z1) to (x2, y, z2).
	 * 
	 * @param world
	 *            the world.
	 * @param x1
	 *            source x-coordinate.
	 * @param z1
	 *            source z-coordinate.
	 * @param x2
	 *            destination x-coordinate.
	 * @param z2
	 *            destination z-coordinate.
	 * @param y
	 *            y-coordinate.
	 */
	protected void rotate(World world, int x1, int z1, int x2, int z2, int y) {
		if (canRotate(world, x1, z1, x2, z2, y)) {
			int id = world.getBlockId(x1, y, z1);
			int metadata = world.getBlockMetadata(x1, y, z1);
			world.setBlockToAir(x1, y, z1);
			world.setBlock(x2, y, z2, id, metadata, 3);
		}
	}

	/**
	 * Indicates if the block at (x1, y, z1) can be moved to (x2, y, z2).
	 * 
	 * @param world
	 *            the world.
	 * @param x1
	 *            source x-coordinate.
	 * @param z1
	 *            source z-coordinate.
	 * @param x2
	 *            destination x-coordinate.
	 * @param z2
	 *            destination z-coordinate.
	 * @param y
	 *            y-coordinate.
	 * @return if it can be moved.
	 */
	protected boolean canRotate(World world, int x1, int z1, int x2, int z2,
			int y) {
		// Only if source block exist, doesn't have an entity and is solid.
		// Only if destination block is air or liquid.
		return world.blockExists(x1, y, z1)
				&& !world.blockHasTileEntity(x1, y, z1)
				&& world.getBlockMaterial(x1, y, z1).isSolid()
				&& (world.isAirBlock(x2, y, z2) || world.getBlockMaterial(x2,
						y, z2).isLiquid());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "RotateBase [width=" + width + ", height=" + height + ", depth="
				+ depth + "]";
	}

}
