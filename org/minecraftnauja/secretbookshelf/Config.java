package org.minecraftnauja.secretbookshelf;

import net.minecraftforge.common.Configuration;

/**
 * Mod's configuration.
 */
public class Config {

	/**
	 * Block's default identifier.
	 */
	private static int secretBookshelfDefaultID = 500;

	/**
	 * Block's identifier.
	 */
	public static int secretBookshelfID;

	/**
	 * Rotate blocks at the left of the bookshelf.
	 */
	public static boolean rotateLeft;

	/**
	 * Rotate blocks at the right of the bookshelf.
	 */
	public static boolean rotateRight;

	/**
	 * Loads the configuration.
	 * 
	 * @param config
	 *            the configuration.
	 */
	public static void load(Configuration config) {
		secretBookshelfID = config.get(Configuration.CATEGORY_BLOCK,
				"SecretBookshelf", secretBookshelfDefaultID).getInt();
		rotateLeft = config.get("Rotate", "Left", false).getBoolean(false);
		rotateRight = config.get("Rotate", "Right", true).getBoolean(true);
	}

}
