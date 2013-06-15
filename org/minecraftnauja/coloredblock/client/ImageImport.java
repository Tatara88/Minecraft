/*     */ import java.awt.Color;
/*     */ import java.awt.image.BufferedImage;
/*     */ import net.minecraft.client.Minecraft;
/*     */ 
/*     */ public class ImageImport
/*     */ {
/*     */   public boolean importFinished;
/*     */   private gs player;
/*     */   private TileEntityColor entity;
/*     */   private BufferedImage image;
/*     */   private fd worldObj;
/*     */   private int xOrient;
/*     */   private int yOrient;
/*     */   private int xCurrent;
/*     */   private int yCurrent;
/*     */   private int originX;
/*     */   private int originY;
/*     */   private int originZ;
/*     */   private int imageWidth;
/*     */   private int imageHeight;
/*     */   private int xxa;
/*     */   private int xya;
/*     */   private int xza;
/*     */   private int yxa;
/*     */   private int yya;
/*     */   private int yza;
/*     */ 
/*     */   public ImageImport(gs player, TileEntityColor entity, BufferedImage image, int xOrient, int yOrient)
/*     */   {
/* 116 */     this.importFinished = false;
/* 117 */     this.player = player;
/* 118 */     this.entity = entity;
/* 119 */     this.image = image;
/* 120 */     this.xOrient = xOrient;
/* 121 */     this.yOrient = yOrient;
/* 122 */     this.worldObj = ModLoader.getMinecraftInstance().f;
/* 123 */     this.xCurrent = 0;
/* 124 */     this.yCurrent = 0;
/* 125 */     this.originX = entity.e;
/* 126 */     this.originY = entity.f;
/* 127 */     this.originZ = entity.g;
/* 128 */     this.imageHeight = image.getHeight();
/* 129 */     this.imageWidth = image.getWidth();
/* 130 */     findXOrientation(xOrient);
/* 131 */     findYOrientation(yOrient);
/*     */   }
/*     */ 
/*     */   public void findXOrientation(int xOrient)
/*     */   {
/* 144 */     this.xxa = 0;
/* 145 */     this.xya = 0;
/* 146 */     this.xza = 0;
/* 147 */     switch (xOrient) {
/*     */     case 0:
/* 149 */       this.xxa = -1;
/* 150 */       break;
/*     */     case 1:
/* 152 */       this.xxa = 1;
/* 153 */       break;
/*     */     case 2:
/* 155 */       this.xza = -1;
/* 156 */       break;
/*     */     case 3:
/* 158 */       this.xza = 1;
/* 159 */       break;
/*     */     case 4:
/* 161 */       this.xya = 1;
/* 162 */       break;
/*     */     case 5:
/* 164 */       this.xya = -1;
/* 165 */       break;
/*     */     default:
/* 167 */       this.xxa = 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void findYOrientation(int yOrient)
/*     */   {
/* 176 */     this.yxa = 0;
/* 177 */     this.yya = 0;
/* 178 */     this.yza = 0;
/* 179 */     switch (yOrient) {
/*     */     case 0:
/* 181 */       this.yxa = -1;
/* 182 */       break;
/*     */     case 1:
/* 184 */       this.yxa = 1;
/* 185 */       break;
/*     */     case 2:
/* 187 */       this.yza = -1;
/* 188 */       break;
/*     */     case 3:
/* 190 */       this.yza = 1;
/* 191 */       break;
/*     */     case 4:
/* 193 */       this.yya = 1;
/* 194 */       break;
/*     */     case 5:
/* 196 */       this.yya = -1;
/* 197 */       break;
/*     */     default:
/* 199 */       this.yza = 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void imageTick()
/*     */   {
/* 212 */     int xOffset = 0;
/* 213 */     int yOffset = 0;
/* 214 */     int zOffset = 0;
/* 215 */     int aRGB = this.image.getRGB(this.xCurrent, this.yCurrent);
/* 216 */     Color pix = new Color(aRGB, true);
/*     */ 
/* 218 */     if (this.xxa != 0)
/* 219 */       xOffset = this.xCurrent * this.xxa;
/* 220 */     else if (this.xya != 0)
/* 221 */       yOffset = this.xCurrent * this.xya;
/* 222 */     else if (this.xza != 0) {
/* 223 */       zOffset = this.xCurrent * this.xza;
/*     */     }
/*     */ 
/* 226 */     if (this.yza != 0)
/* 227 */       zOffset = this.yCurrent * this.yza;
/* 228 */     else if (this.yxa != 0)
/* 229 */       xOffset = this.yCurrent * this.yxa;
/* 230 */     else if (this.yya != 0) {
/* 231 */       yOffset = this.yCurrent * this.yya;
/*     */     }
/*     */ 
/* 234 */     if ((this.originY + yOffset < 0) || (this.originY + yOffset > 127)) {
/* 235 */       this.importFinished = true;
/* 236 */       return;
/*     */     }
/*     */ 
/* 239 */     if (pix.getAlpha() == 255) {
/* 240 */       ow loadTileEntity = this.worldObj.b(this.originX + xOffset, this.originY + yOffset, this.originZ + zOffset);
/* 241 */       if ((loadTileEntity instanceof TileEntityColor)) {
/* 242 */         TileEntityColor currentBlockEntity = (TileEntityColor)loadTileEntity;
/* 243 */         if (currentBlockEntity != null) {
/* 244 */           currentBlockEntity.setColor(pix.getRed(), pix.getGreen(), pix.getBlue());
/* 245 */           this.worldObj.b(this.originX + xOffset, this.originY + yOffset, this.originZ + zOffset, this.originX + xOffset, this.originY + yOffset, this.originZ + zOffset);
/* 246 */           if (mod_ColoredBlock.isMultiplayer()) {
/* 247 */             currentBlockEntity.sendColorPacket();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 252 */     this.xCurrent += 1;
/*     */ 
/* 254 */     if (this.xCurrent == this.imageWidth) {
/* 255 */       this.xCurrent = 0;
/* 256 */       this.yCurrent += 1;
/* 257 */       if (this.yCurrent == this.imageHeight) {
/* 258 */         this.yCurrent = 0;
/* 259 */         this.importFinished = true;
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     ImageImport
 * JD-Core Version:    0.6.2
 */