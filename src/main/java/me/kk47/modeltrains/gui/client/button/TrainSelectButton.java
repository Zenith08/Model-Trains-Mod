package me.kk47.modeltrains.gui.client.button;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.gui.client.GuiPrinter3D;
import me.kk47.modeltrains.items.trains.TrainRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class TrainSelectButton extends GuiButton {

	private static final ResourceLocation TEXTURE = new ResourceLocation(Data.MODID + ":textures/gui/printergui.png");

	private int trainID;
	private boolean selected;
	private GuiPrinter3D gui;

	public TrainSelectButton(int buttonId, int x, int y, String buttonText, int trainID, GuiPrinter3D gui) {
		super(buttonId, x, y, buttonText);
		this.trainID = trainID;
		selected = false;
		this.gui = gui;
	}

	public TrainSelectButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, int trainID) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.trainID = trainID;
		selected = false;
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
		if(this.visible){
			mc.getTextureManager().bindTexture(TEXTURE);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);

			//Actual x and y then texture u v and lastly width and height
			this.drawTexturedModalRect(this.x, this.y, (91*isSelected()), 216, 89, 20);
			//TODO draw train image and name
			gui.getFontRenderer().drawString(I18n.format(TrainRegistry.getTrain(trainID).getUnlocalizedName() + ".name"), this.x+10, this.y+6, 10000000);
	//		this.drawModalRectWithCustomSizedTexture(mouseX, mouseY, u, v, width, height, textureWidth, textureHeight); TODO Implement with Item Texture
			/*		GL11.glPushMatrix();
			GL11.glScalef(0.7F, 0.7F, 0.7F);
			this.drawString(gui.getFontRenderer(), I18n.format(TrainRegistry.getTrain(trainID).getUnlocalizedName() + ".name"), this.x+40, this.y+20, 90000);
			GL11.glPopMatrix();*/
		}
	}

	private int isSelected() {
		if(selected) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/**@param Selected - True if this is the selected button*/
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the trainID
	 */
	public int getTrainID() {
		return trainID;
	}

	/**
	 * @param trainID the trainID to set
	 */
	public void setTrainID(int trainID) {
		this.trainID = trainID;
	}

}
