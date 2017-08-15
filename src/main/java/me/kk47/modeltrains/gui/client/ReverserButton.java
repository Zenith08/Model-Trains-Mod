package me.kk47.modeltrains.gui.client;

import me.kk47.modeltrains.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ReverserButton extends GuiButton {

	private static final ResourceLocation TEXTURE = new ResourceLocation(Data.MODID + ":textures/gui/controllergui.png");

	private final int buttonMode;
	public boolean isSelected = false;

	public ReverserButton(int buttonId, int x, int y, String buttonText) {
		super(buttonId, x, y, buttonText);
		buttonMode = buttonId;
	}

	public ReverserButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		buttonMode = buttonId;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
		if(this.visible){
			mc.getTextureManager().bindTexture(TEXTURE);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			
			int textureXmod = 0;
			int textureYmod = 0;
			
			if(!isSelected) //If the button is selected
				textureXmod+=42; //Modify the texture to reflect this
			if(buttonMode == 0) //If the button is the reverse button
				textureYmod+=15; //Modify texture to contain text
			//Now render based on it
			this.drawTexturedModalRect(this.x, this.y, 20+textureXmod, 174+textureYmod, 41, 14);
			
		}
	}
}
