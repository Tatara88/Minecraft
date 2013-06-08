package org.minecraftnauja.secretbookshelf;

import java.lang.reflect.Constructor;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;

import org.minecraftnauja.secretbookshelf.rotate.IRotate;
import org.minecraftnauja.secretbookshelf.rotate.RotateRight;

import cpw.mods.fml.common.FMLLog;

/**
 * Mod's configuration.
 */
public class Config {

	/**
	 * Block's default identifier.
	 */
	private static final int secretBookshelfDefaultID = 500;

	/**
	 * Block's identifier.
	 */
	public static int secretBookshelfID;

	/**
	 * Rotation.
	 */
	public static IRotate rotation;

	/**
	 * Selected mode of rotation.
	 */
	public static String rotateMode;

	/**
	 * Number of blocks to rotate on the x-axis.
	 */
	public static int rotateWidth;

	/**
	 * Number of blocks to rotate on the y-axis.
	 */
	public static int rotateHeight;

	/**
	 * Number of blocks to rotate on the z-axis.
	 */
	public static int rotateDepth;

	/**
	 * Loads the configuration.
	 * 
	 * @param config
	 *            the configuration.
	 */
	public static void load(Configuration config) {
		secretBookshelfID = config.get(Configuration.CATEGORY_BLOCK,
				"SecretBookshelf", secretBookshelfDefaultID).getInt();
		rotateMode = config.get("Rotate", "Mode", "Right").getString();
		rotateWidth = config.get("Rotate", "Width", 1).getInt(1);
		rotateHeight = config.get("Rotate", "Height", 3).getInt(3);
		rotateDepth = config.get("Rotate", "Depth", 1).getInt(1);
		try {
			if (!rotateMode.contains(".")) {
				// If the mode is just one word (Left, Right...).
				rotateMode = "org.minecraftnauja.secretbookshelf.rotate.Rotate"
						+ rotateMode;
			}
			// Gets the class for selected mode and creates an instance.
			Class<?> clazz = Class.forName(rotateMode);
			Constructor<?> cst = clazz.getConstructor(int.class, int.class,
					int.class);
			rotation = (IRotate) cst.newInstance(rotateWidth, rotateHeight,
					rotateDepth);
		} catch (Exception e) {
			// Any exception, default settings.
			FMLLog.log("SecretBookshelf", Level.SEVERE, e,
					"Invalid rotation mode %s", rotateMode);
			rotation = new RotateRight(1, 3, 1);
		}
	}

}
