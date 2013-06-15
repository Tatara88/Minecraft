package org.minecraftnauja.coloredwool.client;
/*     */ import java.awt.Color;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class ColoredBlockSavedColors
/*     */ {
/*  24 */   private static final Map savedColors = new HashMap();
/*     */ 
/*  28 */   private static int nbColors = 0;
/*     */ 
/*     */   public static boolean addColor(String name, Color color)
/*     */   {
/*  53 */     if ((name == null) || (color == null)) {
/*  54 */       return false;
/*     */     }
/*  56 */     int rgb = ((char)color.getRed() << '\020') + ((char)color.getGreen() << '\b') + (char)color.getBlue();
/*  57 */     String hex = Integer.toHexString(rgb);
/*  58 */     ColoredBlockColorInformations coloredblockcolorinformations = new ColoredBlockColorInformations(name, hex, rgb, color);
/*  59 */     if (savedColors.put(color, coloredblockcolorinformations) == null) {
/*  60 */       nbColors += 1;
/*     */     }
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean addColor(String name, String hex)
/*     */   {
/*  72 */     if ((name == null) || (hex == null)) {
/*  73 */       return false;
/*     */     }
/*  75 */     Color color = Color.decode("0x" + hex);
/*  76 */     int rgb = ((char)color.getRed() << '\020') + ((char)color.getGreen() << '\b') + (char)color.getBlue();
/*  77 */     ColoredBlockColorInformations coloredblockcolorinformations = new ColoredBlockColorInformations(name, hex, rgb, color);
/*  78 */     if (savedColors.put(color, coloredblockcolorinformations) == null) {
/*  79 */       nbColors += 1;
/*     */     }
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean removeColor(Color color)
/*     */   {
/*  90 */     if (savedColors.remove(color) != null) {
/*  91 */       nbColors -= 1;
/*  92 */       return true;
/*     */     }
/*  94 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean containsColor(Color color)
/*     */   {
/* 104 */     if (color == null) {
/* 105 */       return false;
/*     */     }
/* 107 */     return savedColors.containsKey(color);
/*     */   }
/*     */ 
/*     */   public static void clear()
/*     */   {
/* 115 */     savedColors.clear();
/* 116 */     nbColors = 0;
/*     */   }
/*     */ 
/*     */   public static int getNbColors()
/*     */   {
/* 129 */     return nbColors;
/*     */   }
/*     */ 
/*     */   public static Iterator getColorsIterator()
/*     */   {
/* 137 */     return savedColors.entrySet().iterator();
/*     */   }
/*     */ 
/*     */   public static void saveToProps(Properties properties)
/*     */   {
/*     */     try
/*     */     {
/* 151 */       int i = 0;
/* 152 */       Iterator iterator = getColorsIterator();
/* 153 */       while (iterator.hasNext()) {
/* 154 */         Map.Entry entry = (Map.Entry)iterator.next();
/* 155 */         ColoredBlockColorInformations coloredblockcolorinformations = (ColoredBlockColorInformations)entry.getValue();
/* 156 */         properties.put("Color." + i, coloredblockcolorinformations.getHex());
/* 157 */         i++;
/*     */       }
/*     */     } catch (Exception exception) {
/* 160 */       System.out.println("SavedColors: error while saving colors.");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void loadFromProps(Properties properties)
/*     */   {
/*     */     try
/*     */     {
/* 170 */       Enumeration enumeration = properties.keys();
/* 171 */       while (enumeration.hasMoreElements()) {
/* 172 */         String name = (String)enumeration.nextElement();
/* 173 */         loadElement(properties, name);
/*     */       }
/*     */     } catch (Exception exception) {
/* 176 */       System.out.println("SavedColors: error while loading colors.");
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void loadElement(Properties properties, String name)
/*     */   {
/*     */     try
/*     */     {
/* 187 */       if (!name.startsWith("Color.")) {
/* 188 */         return;
/*     */       }
/* 190 */       int pos = name.indexOf(46);
/* 191 */       String number = name.substring(pos + 1, name.length());
/* 192 */       String hex = (String)properties.get(name);
/* 193 */       addColor(number, hex);
/*     */     } catch (Exception exception) {
/* 195 */       System.out.println("SavedColors: error with key \"" + name + "\".");
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     ColoredBlockSavedColors
 * JD-Core Version:    0.6.2
 */