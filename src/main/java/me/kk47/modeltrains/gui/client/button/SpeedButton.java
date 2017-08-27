package me.kk47.modeltrains.gui.client.button;

import me.kk47.modeltrains.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class SpeedButton extends GuiButton {

	private static final ResourceLocation TEXTURE = new ResourceLocation(Data.MODID + ":textures/gui/controllergui.png");

	public boolean isSelected = false;

	public SpeedButton(int buttonId, int x, int y, String buttonText) {
		super(buttonId, x, y, buttonText);
	}

	public SpeedButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
		if(this.visible){
			mc.getTextureManager().bindTexture(TEXTURE);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			//TODO enable/disable
			if(isSelected){
				this.drawTexturedModalRect(this.x, this.y, 10, 174, 9, 9);
			}else{
				this.drawTexturedModalRect(this.x, this.y, 0, 174, 9, 9);
			}
		}
	}
}
