package org.minecraftnauja.coloredwool.server;
/*     */ public class BlockColor extends lb
/*     */ {
	
/*     */   public boolean a(dj world, int i, int j, int k, em player)
/*     */   {
/* 136 */     if (world.B) {
/* 137 */       return true;
/*     */     }
/* 139 */     if (player.ah()) {
/* 140 */       return false;
/*     */     }
/* 142 */     fy itemstack = player.i.b();
/* 143 */     if (itemstack == null)
/* 144 */       return false;
/* 145 */     if (itemstack.a() != ItemColoredBrush.item) {
/* 146 */       return false;
/*     */     }
/* 148 */     if (colorSelection == '\001') {
/* 149 */       TileEntityColor entity = (TileEntityColor)world.b(i, j, k);
/* 150 */       entity.changeColor(world, i, j, k);
/* 151 */       entity.sendColorPacket();
/* 152 */       return true;
/*     */     }
/* 154 */     if (colorSelection == '\002') {
/* 155 */       TileEntityColor entity = (TileEntityColor)world.b(i, j, k);
/* 156 */       Packet230ModLoader packet = entity.getColorPacket();
/* 157 */       packet.packetType = mod_ColoredBlock.onBlockRightClick;
/* 158 */       ModLoaderMp.SendPacketTo(mod_ColoredBlock.instance, (dl)player, packet);
/* 159 */       return true;
/*     */     }
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */   public void b(dj world, int i, int j, int k, em player)
/*     */   {
/* 173 */     if (world.B) {
/* 174 */       return;
/*     */     }
/* 176 */     if (player.ah()) {
/* 177 */       return;
/*     */     }
/* 179 */     fy itemstack = player.i.b();
/* 180 */     if (itemstack == null)
/* 181 */       return;
/* 182 */     if (itemstack.a() != ItemColoredBrush.item) {
/* 183 */       return;
/*     */     }
/* 185 */     if (colorSelection == '\001') {
/* 186 */       TileEntityColor entity = (TileEntityColor)world.b(i, j, k);
/* 187 */       entity.addColor(world, i, j, k);
/* 188 */       entity.sendColorPacket();
/*     */     }
/*     */   }
/*     */ 
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Server (1)\
 * Qualified Name:     BlockColor
 * JD-Core Version:    0.6.2
 */