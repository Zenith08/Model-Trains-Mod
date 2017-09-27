package me.kk47.modeltrains.gui.client.button;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.gui.client.GuiPrinter3D;
import me.kk47.modeltrains.items.trains.TrainRegistry;
import me.kk47.modeltrains.math.ScrollingName;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class TrainSelectButton extends GuiButton {

	private static final ResourceLocation TEXTURE = new ResourceLocation(Data.MODID + ":textures/gui/printergui.png");

	private int trainID;
	private ItemStack stack;
	private boolean selected;
	private GuiPrinter3D gui;

	ScrollingName trainName;
	private static final int TEXT_SCROLL_TIME = 25;
	private int scrollText = 20;

	public TrainSelectButton(int buttonId, int x, int y, String buttonText, int trainID, GuiPrinter3D gui) {
		super(buttonId, x, y, buttonText);
		this.trainID = trainID;
		selected = false;
		this.gui = gui;
		trainName = new ScrollingName(I18n.format(TrainRegistry.getTrain(trainID).asItem().getUnlocalizedName() + ".name"), 12);
		stack = new ItemStack(TrainRegistry.getTrain(trainID).asItem());
	}

	public TrainSelectButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, int trainID) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.trainID = trainID;
		selected = false;
		trainName = new ScrollingName(I18n.format(TrainRegistry.getTrain(trainID).asItem().getUnlocalizedName() + ".name"), 12);
		stack = new ItemStack(TrainRegistry.getTrain(trainID).asItem());
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
		if(this.visible){
			mc.getTextureManager().bindTexture(TEXTURE);
			//			GlStateManager.enableBlend();
			//		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			//	GlStateManager.blendFunc(770, 771);

			//Actual x and y then texture u v and lastly width and height
			this.drawTexturedModalRect(this.x, this.y, (91*isSelected()), 216, 89, 20);
			if(trainID >= 0) {
				//Draws the name so that it is scrolling and no longer than the text box
				//				gui.getFontRenderer().drawString(I18n.format(TrainRegistry.getTrain(trainID).getUnlocalizedName() + ".name"), this.x+20, this.y+6, 10000000);
				scrollText--;
				if(scrollText == 0) {
					trainName.getNext();
					scrollText = TEXT_SCROLL_TIME;
				}
				gui.getFontRenderer().drawString(trainName.getCurrent(), this.x+20, this.y+6, 10000000);

				//Draws the image of the train
				if(stack != null) {
					mc.getRenderItem().renderItemIntoGUI(stack, this.x + 3, this.y + 2);
				}
			}
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

	public void setItemStackNBT(NBTTagCompound compound) {
		stack.setTagCompound(compound);
	}

	/**
	 * @param trainID the trainID to set
	 */
	public void setTrainID(int trainID) {
		this.trainID = trainID;
		trainName = new ScrollingName(I18n.format(TrainRegistry.getTrain(trainID).asItem().getUnlocalizedName() + ".name"), 12);
		stack = new ItemStack(TrainRegistry.getTrain(trainID).asItem());
	}

}
