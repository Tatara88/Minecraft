/*     */ import java.awt.Color;
/*     */ 
/*     */ public class TileEntityColor extends ow
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
/*     */   public boolean needUpdate;
/*     */ 
/*     */   public TileEntityColor()
/*     */   {
/*  67 */     this.color = 3;
/*  68 */     this.values = new int[] { initColorRed, initColorGreen, initColorBlue };
/*  69 */     this.colorStep = initColorStep;
/*  70 */     this.needUpdate = mod_ColoredBlock.isMultiplayer();
/*     */   }
/*     */ 
/*     */   public static void initialise()
/*     */   {
/*  82 */     initColorRed = mod_ColoredBlock.properties.get("ColoredBlock", "InitColorRed", Character.valueOf('ÿ')).charValue();
/*  83 */     initColorGreen = mod_ColoredBlock.properties.get("ColoredBlock", "InitColorGreen", Character.valueOf('ÿ')).charValue();
/*  84 */     initColorBlue = mod_ColoredBlock.properties.get("ColoredBlock", "InitColorBlue", Character.valueOf('ÿ')).charValue();
/*  85 */     initColorStep = mod_ColoredBlock.properties.get("ColoredBlock", "InitColorStep", Character.valueOf('\005')).charValue();
/*  86 */     maxColorStep = mod_ColoredBlock.properties.get("ColoredBlock", "MaxColorStep", Character.valueOf('\n')).charValue();
/*     */ 
/*  88 */     ModLoader.RegisterTileEntity(TileEntityColor.class, "Colored Block");
/*     */   }
/*     */ 
/*     */   public void b(nu nbttagcompound)
/*     */   {
/* 101 */     super.b(nbttagcompound);
/* 102 */     nbttagcompound.a("colorRed", this.values[0]);
/* 103 */     nbttagcompound.a("colorGreen", this.values[1]);
/* 104 */     nbttagcompound.a("colorBlue", this.values[2]);
/*     */   }
/*     */ 
/*     */   public void a(nu nbttagcompound)
/*     */   {
/* 112 */     super.a(nbttagcompound);
/* 113 */     this.values[0] = nbttagcompound.e("colorRed");
/* 114 */     this.values[1] = nbttagcompound.e("colorGreen");
/* 115 */     this.values[2] = nbttagcompound.e("colorBlue");
/* 116 */     verifyValues();
/*     */   }
/*     */ 
/*     */   public void verifyValues()
/*     */   {
/* 128 */     this.values[0] = verifyColor(this.values[0]);
/* 129 */     this.values[1] = verifyColor(this.values[1]);
/* 130 */     this.values[2] = verifyColor(this.values[2]);
/*     */   }
/*     */ 
/*     */   public int verifyColor(int i)
/*     */   {
/* 139 */     if (i < 0) {
/* 140 */       return 0;
/*     */     }
/* 142 */     return i <= 255 ? i : 255;
/*     */   }
/*     */ 
/*     */   public void changeColor(fd world, int i, int j, int k)
/*     */   {
/* 154 */     this.color = ((byte)(this.color + 1));
/* 155 */     if (this.color > 3) {
/* 156 */       this.color = 0;
/* 157 */       displayParticle(world, i, j, k);
/*     */     }
/* 159 */     y_();
/*     */   }
/*     */ 
/*     */   public void addColor(fd world, int i, int j, int k)
/*     */   {
/* 170 */     if (this.color >= 0)
/* 171 */       if (this.color < 3) {
/* 172 */         this.values[this.color] = ((char)(this.values[this.color] + 'ÿ' / this.colorStep));
/* 173 */         if (this.values[this.color] > 255) {
/* 174 */           this.values[this.color] = 0;
/* 175 */           displayParticle(world, i, j, k);
/*     */         }
/* 177 */       } else if (this.color == 3) {
/* 178 */         this.colorStep = ((char)(this.colorStep + '\001'));
/* 179 */         if (this.colorStep > maxColorStep) {
/* 180 */           this.colorStep = '\001';
/* 181 */           displayParticle(world, i, j, k);
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public void displayParticle(fd world, int i, int j, int k)
/*     */   {
/* 189 */     world.a("note", i + 0.5D, j + 1.2D, k + 0.5D, 0.0D, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */   public void sendColorPacket()
/*     */   {
/* 201 */     Packet230ModLoader packet230modloader = new Packet230ModLoader();
/* 202 */     packet230modloader.packetType = mod_ColoredBlock.onBlockColorChanged;
/* 203 */     packet230modloader.dataInt = new int[] { this.e, this.f, this.g, this.values[0], this.values[1], this.values[2] };
/* 204 */     ModLoaderMp.SendPacket(mod_ColoredBlock.instance, packet230modloader);
/*     */   }
/*     */ 
/*     */   public void requestUpdate()
/*     */   {
/* 211 */     this.needUpdate = false;
/* 212 */     Packet230ModLoader packet230modloader = new Packet230ModLoader();
/* 213 */     packet230modloader.packetType = mod_ColoredBlock.onBlockRequestColor;
/* 214 */     packet230modloader.dataInt = new int[] { this.e, this.f, this.g };
/* 215 */     ModLoaderMp.SendPacket(mod_ColoredBlock.instance, packet230modloader);
/*     */   }
/*     */ 
/*     */   public Color getColor()
/*     */   {
/* 228 */     return new Color(this.values[0], this.values[1], this.values[2]);
/*     */   }
/*     */ 
/*     */   public String getHexString()
/*     */   {
/* 236 */     String hex = Integer.toHexString(getColorMultiplier());
/* 237 */     for (int i = hex.length(); i < 6; i++) {
/* 238 */       hex = "0" + hex;
/*     */     }
/* 240 */     return hex;
/*     */   }
/*     */ 
/*     */   public int getColorMultiplier()
/*     */   {
/* 248 */     return (this.values[0] << 16) + (this.values[1] << 8) + this.values[2];
/*     */   }
/*     */ 
/*     */   public void setColor(int r, int g, int b)
/*     */   {
/* 263 */     this.values[0] = r;
/* 264 */     this.values[1] = g;
/* 265 */     this.values[2] = b;
/* 266 */     verifyValues();
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     TileEntityColor
 * JD-Core Version:    0.6.2
 */