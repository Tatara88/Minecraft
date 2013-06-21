package org.minecraftnauja.coloredwool.client;
 import java.awt.Color;
 
 public class TileEntityColored extends ow
 {

 
   public int verifyColor(int i)
   {
     if (i < 0) {
       return 0;
     }
     return i <= 255 ? i : 255;
   }
 
   public void changeColor(fd world, int i, int j, int k)
   {
     this.color = ((byte)(this.color + 1));
     if (this.color > 3) {
       this.color = 0;
       displayParticle(world, i, j, k);
     }
     y_();
   }
 
   public void addColor(fd world, int i, int j, int k)
   {
     if (this.color >= 0)
       if (this.color < 3) {
         this.values[this.color] = ((char)(this.values[this.color] + 'ÿ' / this.colorStep));
         if (this.values[this.color] > 255) {
           this.values[this.color] = 0;
           displayParticle(world, i, j, k);
         }
       } else if (this.color == 3) {
         this.colorStep = ((char)(this.colorStep + '\001'));
         if (this.colorStep > maxColorStep) {
           this.colorStep = '\001';
           displayParticle(world, i, j, k);
         }
       }
   }
 
   public void displayParticle(fd world, int i, int j, int k)
   {
     world.a("note", i + 0.5D, j + 1.2D, k + 0.5D, 0.0D, 0.0D, 0.0D);
   }
 
   public void sendColorPacket()
   {
     Packet230ModLoader packet230modloader = new Packet230ModLoader();
     packet230modloader.packetType = mod_ColoredBlock.onBlockColorChanged;
     packet230modloader.dataInt = new int[] { this.e, this.f, this.g, this.values[0], this.values[1], this.values[2] };
     ModLoaderMp.SendPacket(mod_ColoredBlock.instance, packet230modloader);
   }
 
   public void requestUpdate()
   {
     this.needUpdate = false;
     Packet230ModLoader packet230modloader = new Packet230ModLoader();
     packet230modloader.packetType = mod_ColoredBlock.onBlockRequestColor;
     packet230modloader.dataInt = new int[] { this.e, this.f, this.g };
     ModLoaderMp.SendPacket(mod_ColoredBlock.instance, packet230modloader);
   }
 
   public Color getColor()
   {
     return new Color(this.values[0], this.values[1], this.values[2]);
   }
 
   public String getHexString()
   {
     String hex = Integer.toHexString(getColorMultiplier());
     for (int i = hex.length(); i < 6; i++) {
       hex = "0" + hex;
     }
     return hex;
   }
   
   public void setColor(int r, int g, int b)
   {
     this.values[0] = r;
     this.values[1] = g;
     this.values[2] = b;
     verifyValues();
   }
 }