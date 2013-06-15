package org.minecraftnauja.coloredwool.client;
 import java.awt.Color;
 
 public class TileEntityColored extends ow
 {
   public static char initColorRed = 'ÿ';
 
   public static char initColorGreen = 'ÿ';
 
   public static char initColorBlue = 'ÿ';
 
   public static char initColorStep = '\005';
 
   public static char maxColorStep = '\n';
   public byte color;
   public int[] values;
   public char colorStep;
   public boolean needUpdate;
 
   public TileEntityColored()
   {
     this.color = 3;
     this.values = new int[] { initColorRed, initColorGreen, initColorBlue };
     this.colorStep = initColorStep;
     this.needUpdate = mod_ColoredBlock.isMultiplayer();
   }
 
   public static void initialise()
   {
     initColorRed = mod_ColoredBlock.properties.get("ColoredBlock", "InitColorRed", Character.valueOf('ÿ')).charValue();
     initColorGreen = mod_ColoredBlock.properties.get("ColoredBlock", "InitColorGreen", Character.valueOf('ÿ')).charValue();
     initColorBlue = mod_ColoredBlock.properties.get("ColoredBlock", "InitColorBlue", Character.valueOf('ÿ')).charValue();
     initColorStep = mod_ColoredBlock.properties.get("ColoredBlock", "InitColorStep", Character.valueOf('\005')).charValue();
     maxColorStep = mod_ColoredBlock.properties.get("ColoredBlock", "MaxColorStep", Character.valueOf('\n')).charValue();
 
     ModLoader.RegisterTileEntity(TileEntityColor.class, "Colored Block");
   }
 
   public void b(nu nbttagcompound)
   {
     super.b(nbttagcompound);
     nbttagcompound.a("colorRed", this.values[0]);
     nbttagcompound.a("colorGreen", this.values[1]);
     nbttagcompound.a("colorBlue", this.values[2]);
   }
 
   public void a(nu nbttagcompound)
   {
     super.a(nbttagcompound);
     this.values[0] = nbttagcompound.e("colorRed");
     this.values[1] = nbttagcompound.e("colorGreen");
     this.values[2] = nbttagcompound.e("colorBlue");
     verifyValues();
   }
 
   public void verifyValues()
   {
     this.values[0] = verifyColor(this.values[0]);
     this.values[1] = verifyColor(this.values[1]);
     this.values[2] = verifyColor(this.values[2]);
   }
 
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
 
   public int getColorMultiplier()
   {
     return (this.values[0] << 16) + (this.values[1] << 8) + this.values[2];
   }
 
   public void setColor(int r, int g, int b)
   {
     this.values[0] = r;
     this.values[1] = g;
     this.values[2] = b;
     verifyValues();
   }
 }