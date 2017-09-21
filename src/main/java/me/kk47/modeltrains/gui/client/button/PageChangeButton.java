package me.kk47.modeltrains.gui.client.button;

import me.kk47.modeltrains.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class PageChangeButton extends GuiButton {

	private static final ResourceLocation TEXTURE = new ResourceLocation(Data.MODID + ":textures/gui/printergui.png");
	private static final int PRESS_TIMER_MAX = 30;

	private int pressTimer = 0;
	private boolean isUpButton;

	public PageChangeButton(int buttonId, int x, int y, String buttonText, boolean isUpButton) {
		super(buttonId, x, y, buttonText);
		this.isUpButton = isUpButton;
	}

	public PageChangeButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
		pressTimer--;
		if(pressTimer < 0) {
			pressTimer = 0;
		}
		if(this.visible){
			mc.getTextureManager().bindTexture(TEXTURE);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);

			if(isUpButton) {
				if(pressTimer == 0) { //Up
					//Actual x and y then texture u v and lastly width and height
					this.drawTexturedModalRect(this.x, this.y, 178, 2, 43, 16); //Up un-pressed
				}else {
					//Actual x and y then texture u v and lastly width and height
					this.drawTexturedModalRect(this.x, this.y, 178, 20, 43, 16); //Up Pressed
				}
			}else {
				if(pressTimer == 0) { //Down
					//Actual x and y then texture u v and lastly width and height
					this.drawTexturedModalRect(this.x, this.y, 178, 38, 43, 16); //Down Un-Pressed
				}else {
					//Actual x and y then texture u v and lastly width and height
					this.drawTexturedModalRect(this.x, this.y, 178, 56, 43, 16); //Down Pressed
				}
			}

		}
	}

	public void buttonPressed() {
		pressTimer = PRESS_TIMER_MAX;
	}
}
