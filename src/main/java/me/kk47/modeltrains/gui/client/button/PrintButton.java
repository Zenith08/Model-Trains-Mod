package me.kk47.modeltrains.gui.client.button;

import me.kk47.modeltrains.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class PrintButton extends GuiButton{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Data.MODID + ":textures/gui/printergui.png");
	
	private boolean isPrinting = false;
	
	public PrintButton(int buttonId, int x, int y, String buttonText) {
		super(buttonId, x, y, buttonText);
	}

	public PrintButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
		mc.getTextureManager().bindTexture(TEXTURE);
		this.drawTexturedModalRect(this.x, this.y, 178+39*printing(), 74, 37, 78);
	}
	
	public void setPrinting(boolean printing) {
		this.isPrinting = printing;
	}
	
	private int printing() {
		if(isPrinting) {
			return 1;
		}else {
			return 0;
		}
	}
}
