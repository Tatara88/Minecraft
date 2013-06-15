/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiModelFactoryImage extends da
/*     */ {
/*     */   private static final char DONE_ID = '\000';
/*     */   private static final char CANCEL_ID = '\001';
/*     */   private gs player;
/*     */   private TileEntityModelFactory entity;
/*     */   private char type;
/*     */   private ro nameButton;
/*     */   private ke doneButton;
/*     */   private ke cancelButton;
/*     */   private String screenTitle;
/*     */   private int updateCounter;
/*     */ 
/*     */   public GuiModelFactoryImage(gs player, TileEntityModelFactory entity, char type)
/*     */   {
/*  77 */     this.player = player;
/*  78 */     this.entity = entity;
/*  79 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public void b()
/*     */   {
/*  91 */     Keyboard.enableRepeatEvents(true);
/*  92 */     this.screenTitle = "Choose model to generate";
/*  93 */     this.nameButton = new ro(this, this.g, this.c / 2 - 100, 60, 200, 20, this.entity.getImageName());
/*  94 */     this.nameButton.a = true;
/*  95 */     this.nameButton.a(42);
/*  96 */     this.e.clear();
/*  97 */     this.doneButton = new ke(0, this.c / 2 - 105, this.d / 4 + 120, 90, 20, "Done");
/*  98 */     this.cancelButton = new ke(1, this.c / 2 + 15, this.d / 4 + 120, 90, 20, "Cancel");
/*  99 */     this.e.add(this.doneButton);
/* 100 */     this.e.add(this.cancelButton);
/* 101 */     checkName();
/*     */   }
/*     */ 
/*     */   public void close()
/*     */   {
/* 108 */     String name = this.nameButton.a();
/* 109 */     if (mod_ColoredBlock.isMultiplayer())
/* 110 */       BlockModelFactory.sendModifyImageName(this.entity, name);
/*     */     else {
/* 112 */       this.entity.setImageToGenerate(name);
/*     */     }
/* 114 */     this.b.a((da)null);
/* 115 */     this.b.g();
/*     */   }
/*     */ 
/*     */   public void a()
/*     */   {
/* 122 */     super.a();
/* 123 */     this.updateCounter += 1;
/*     */   }
/*     */ 
/*     */   protected void a(ke button)
/*     */   {
/* 136 */     if (!button.g) {
/* 137 */       return;
/*     */     }
/* 139 */     if (button.f == 0)
/* 140 */       close();
/* 141 */     else if (button.f == 1)
/* 142 */       ModLoader.OpenGUI(this.player, new GuiModelFactoryMenu(this.player, this.entity));
/*     */   }
/*     */ 
/*     */   protected void a(char c, int i)
/*     */   {
/* 152 */     this.nameButton.a(c, i);
/* 153 */     checkName();
/* 154 */     if (c == '\r')
/* 155 */       a((ke)this.e.get(0));
/*     */   }
/*     */ 
/*     */   public boolean checkName()
/*     */   {
/* 169 */     String s = this.nameButton.a();
/* 170 */     boolean flag = s.length() > 0;
/* 171 */     this.doneButton.g = flag;
/* 172 */     return flag;
/*     */   }
/*     */ 
/*     */   public void a(int i, int j, float f)
/*     */   {
/* 182 */     i();
/* 183 */     a(this.g, this.screenTitle, this.c / 2, 40, 16777215);
/* 184 */     this.nameButton.c();
/* 185 */     super.a(i, j, f);
/*     */   }
/*     */ }

/* Location:           C:\Users\jeremy\Downloads\ColoredBlock v0.7 - 1.7.3 Client (1)\
 * Qualified Name:     GuiModelFactoryImage
 * JD-Core Version:    0.6.2
 */