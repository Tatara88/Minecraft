package org.minecraftnauja.coloredwool;

import java.awt.Color;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class SavedColors {

	private static final Map savedColors = new HashMap();

	private static int nbColors = 0;

	public static boolean addColor(String name, Color color) {
		if ((name == null) || (color == null)) {
			return false;
		}
		int rgb = ((char) color.getRed() << '\020')
				+ ((char) color.getGreen() << '\b') + (char) color.getBlue();
		String hex = Integer.toHexString(rgb);
		ColorInformations coloredblockcolorinformations = new ColorInformations(
				name, hex, rgb, color);
		if (savedColors.put(color, coloredblockcolorinformations) == null) {
			nbColors += 1;
		}
		return true;
	}

	public static boolean addColor(String name, String hex) {
		if ((name == null) || (hex == null)) {
			return false;
		}
		Color color = Color.decode("0x" + hex);
		int rgb = ((char) color.getRed() << '\020')
				+ ((char) color.getGreen() << '\b') + (char) color.getBlue();
		ColorInformations coloredblockcolorinformations = new ColorInformations(
				name, hex, rgb, color);
		if (savedColors.put(color, coloredblockcolorinformations) == null) {
			nbColors += 1;
		}
		return true;
	}

	public static boolean removeColor(Color color) {
		if (savedColors.remove(color) != null) {
			nbColors -= 1;
			return true;
		}
		return false;
	}

	public static boolean containsColor(Color color) {
		if (color == null) {
			return false;
		}
		return savedColors.containsKey(color);
	}

	public static void clear() {
		savedColors.clear();
		nbColors = 0;
	}

	public static int getNbColors() {
		return nbColors;
	}

	public static Iterator getColorsIterator() {
		return savedColors.entrySet().iterator();
	}

	public static void saveToProps(Properties properties) {
		try {
			int i = 0;
			Iterator iterator = getColorsIterator();
			while (iterator.hasNext()) {
				Map.Entry entry = (Map.Entry) iterator.next();
				ColorInformations coloredblockcolorinformations = (ColorInformations) entry
						.getValue();
				properties.put("Color." + i,
						coloredblockcolorinformations.getHex());
				i++;
			}
		} catch (Exception exception) {
			System.out.println("SavedColors: error while saving colors.");
		}
	}

	public static void loadFromProps(Properties properties) {
		try {
			Enumeration enumeration = properties.keys();
			while (enumeration.hasMoreElements()) {
				String name = (String) enumeration.nextElement();
				loadElement(properties, name);
			}
		} catch (Exception exception) {
			System.out.println("SavedColors: error while loading colors.");
		}
	}

	private static void loadElement(Properties properties, String name) {
		try {
			if (!name.startsWith("Color.")) {
				return;
			}
			int pos = name.indexOf(46);
			String number = name.substring(pos + 1, name.length());
			String hex = (String) properties.get(name);
			addColor(number, hex);
		} catch (Exception exception) {
			System.out.println("SavedColors: error with key \"" + name + "\".");
		}
	}

}