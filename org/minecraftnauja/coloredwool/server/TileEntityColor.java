package org.minecraftnauja.coloredwool.server;
/*     */ import java.awt.Color;
/*     */ 
/*     */ public class TileEntityColor extends jh
/*     */ {
/*  18 */   public static char initColorRed = 'ÿ';
/*     */ 
/*  22 */   public static char initColorGreen = 'ÿ';
/*     */ 
/*  26 */   public static char initColorBlue = 'ÿ';
/*     */ 
/*  30 */   public static char initColorStep = '\005';
/*     */ 
/*  34 */   public static char maxColorStep = '\n';
/*     */   public byte color;
/*     */   public int[] values;
/*     */   public char colorStep;
/*     */ 
/*     */   public TileEntityColor()
/*     */   {
/*  63 */     this.color = 3;
/*  64 */     this.values = new int[] { initColorRed, initColorGreen, initColorBlue };
/*  65 */     this.colorStep = initColorStep;
/*     */   }
/*     */ 
/*     */   public static void initialise()
/*     */   {
/*  77 */     initColorRed = mod_ColoredBlock.properties.get("ColoredBlock", "InitColorRed", Character.valueOf('ÿ')).charValue();
/*  78 */     initColorGreen = mod_ColoredBlock.properties.get("ColoredBlock", "InitColorGreen", Character.valueOf('ÿ')).charValue();
/*  79 */     initColorBlue = mod_ColoredBlock.properties.get("ColoredBlock", "InitColorBlue", Character.valueOf('ÿ')).charValue();
/*  80 */     initColorStep = mod_ColoredBlock.properties.get("ColoredBlock", "InitColorStep", Character.valueOf('\005')).charValue();
/*  81 */     maxColorStep = mod_ColoredBlock.properties.get("ColoredBlock", "MaxColorStep", Character.valueOf('\n')).charValue();
/*     */ 
/*  83 */     ModLoader.RegisterTileEntity(TileEntityColor.class, "Colored Block");
/*     */   }
/*     */ 
/*     */   public void b(iq nbttagcompound)
/*     */   {
/*  96 */     super.b(nbttagcompound);
/*  97 */     nbttagcompound.a("colorRed", this.values[0]);
/*  98 */     nbttagcompound.a("colorGreen", this.values[1]);
/*  99 */     nbttagcompound.a("colorBlue", this.values[2]);
/*     */   }
/*     */ 
/*     */   public void a(iq nbttagcompound)
/*     */   {
/* 107 */     super.a(nbttagcompound);
/* 108 */     this.values[0] = nbttagcompound.e("colorRed");
/* 109 */     this.values[1] = nbttagcompound.e("colorGreen");
/* 110 */     this.values[2] = nbttagcompound.e("colorBlue");
/* 111 */     verifyValues();
/*     */   }
/*     */ 
/*     */   public void verifyValues()
/*     */   {
/* 123 */     this.values[0] = verifyColor(this.values[0]);
/* 124 */     this.values[1] = verifyColor(this.values[1]);
/* 125 */     this.values[2] = verifyColor(this.values[2]);
/*     */   }
/*     */ 
/*     */   public int verifyColor(int i)
/*     */   {
/* 134 */     if (i < 0) {
/* 135 */       return 0;
/*     */     }
/* 137 */     return i <= 255 ? i : 255;
/*     */   }
/*     */ 
/*     */   public void changeColor(dj world, int i, int j, int k)
/*     */   {
/* 149 */     this.color = ((byte)(this.color + 1));
/* 150 */     if (this.color > 3) {
/* 151 */       this.color = 0;
/* 152 */       sendPlayNote();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addColor(dj world, int i, int j, int k)
/*     */   {
/* 164 */     if (this.color >= 0)
/* 165 */       if (this.color < 3) {
/* 166 */         this.values[this.color] = ((char)(this.values[this.color] + 'ÿ' / this.colorStep));
/* 167 */         if (this.values[this.color] > 255) {
/* 168 */           this.values[this.color] = 0;
/* 169 */           sendPlayNote();
/*     */         }
/* 171 */       } else if (this.color == 3) {
/* 172 */         this.colorStep = ((char)(this.colorStep + '\001'));
/* 173 */         if (this.colorStep > maxColorStep) {
/* 174 */           this.colorStep = '\001';
/* 175 */           sendPlayNote();
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public void displayParticle(dj world, int i, int j, int k)
/*     */   {
/* 189 */     world.a("note", i + 0.5D, j + 1.2D, k + 0.5D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */   public void sendColorPacket()
/*     */   {
/* 201 */     ModLoaderMp.SendPacketToAll(mod_ColoredBlock.instance, getColorPacket());
/*     */   }
/*     */ 
/*     */   public Packet230ModLoader getColorPacket()
/*     */   {
/* 208 */     Packet230ModLoader packet = new Packet230ModLoader();
/* 209 */     packet.packetType = mod_ColoredBlock.onBlockColorChanged;
/* 210 */     packet.dataInt = new int[] { this.e, this.f, this.g, this.values[0], this.values[1], this.values[2] };
/* 211 */     return packet;
/*     */   }
/*     */ 
/*     */   public void sendColorPacketTo(dl player)
/*     */   {
/* 219 */     Packet230ModLoader packet = new Packet230ModLoader();
/* 220 */     packet.packetType = mod_ColoredBlock.onBlockColorChanged;
/* 221 */     packet.dataInt = new int[] { this.e, this.f, this.g, this.values[0], this.values[1], this.values[2] };
/* 222 */     ModLoaderMp.SendPacketTo(mod_ColoredBlock.instance, player, packet);
/*     */   }
/*     */ 
/*     */   public void sendPlayNote()
/*     */   {
/* 229 */     Packet230ModLoader packet = new Packet230ModLoader();
/* 230 */     packet.packetType = mod_ColoredBlock.onBlockPlayNote;
/* 231 */     packet.dataInt = new int[] { this.e, this.f, this.g };
/* 232 */     ModLoaderMp.SendPacketToAll(mod_ColoredBlock.instance, packet);
/*     */   }
/*     */ 
/*     */   public Color getColor()
/*     */   {
/* 245 */     return new Color(this.values[0], this.values[1], this.values[2]);
/*     */   }
/*     */ 
/*     */   public String getHexString()
/*     */   {
/* 253 */     String hex = Integer.toHexString(getColorMultiplier());
/* 254 */     for (int i = hex.length(); i < 6; i++) {
/* 255 */       hex = "0" + hex;
/*     */     }
/* 257 */     return hex;
/*     */   }
/*     */ 
/*     */   public int getColorMultiplier()
/*     */   {
/* 265 */     return (this.values[0] << 16) + (this.values[1] << 8) + this.values[2];
/*     */   }
/*     */ 
/*     */   public void setColor(int i, int j, int k)
/*     */   {
/* 280 */     this.values[0] = i;
/* 281 */     this.values[1] = j;
/* 282 */     this.values[2] = k;
/* 283 */     verifyValues();
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Server (1)\
 * Qualified Name:     TileEntityColor
 * JD-Core Version:    0.6.2
 */