package org.minecraftnauja.secretbookshelf.rotate;

import net.minecraft.world.World;

/**
 * Interface for rotations.
 */
public interface IRotate {

	/**
	 * Gets the number of blocks to rotate on the x-axis.
	 * 
	 * @return the number of blocks to rotate on the x-axis.
	 */
	public int getWidth();

	/**
	 * Sets the number of blocks to rotate on the x-axis.
	 * 
	 * @param width
	 *            new value.
	 */
	public void setWidth(int width);

	/**
	 * Gets the number of blocks to rotate on the y-axis.
	 * 
	 * @return the number of blocks to rotate on the y-axis.
	 */
	public int getHeight();

	/**
	 * Sets the number of blocks to rotate on the y-axis.
	 * 
	 * @param width
	 *            new value.
	 */
	public void setHeight(int height);

	/**
	 * Gets the number of blocks to rotate on the z-axis.
	 * 
	 * @return the number of blocks to rotate on the z-axis.
	 */
	public int getDepth();

	/**
	 * Sets the number of blocks to rotate on the z-axis.
	 * 
	 * @param width
	 *            new value.
	 */
	public void setDepth(int depth);

	/**
	 * Applies the rotation centered on given block.
	 * 
	 * @param world
	 *            the world.
	 * @param x
	 *            block's x-coordinate.
	 * @param y
	 *            block's y-coordinate.
	 * @param z
	 *            block's z-coordinate.
	 * @param dir
	 *            block's direction.
	 * @param clockwise
	 *            if the rotation is clockwise or counterclockwise.
	 */
	public void apply(World world, int x, int y, int z, int dir,
			boolean clockwise);

}
