package me.kk47.modeltrains.gui.industry;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.industry.MTResourceWood;
import me.kk47.modeltrains.tileentity.TileEntityIndustryVillage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiIndustryVillage extends GuiContainer {
	
	TileEntityIndustryVillage te;
	MTResourceWood woodref = new MTResourceWood();
	
	public GuiIndustryVillage(IInventory playerInv, TileEntityIndustryVillage te) {
		super(new ContainerIndustryVillage(playerInv, te));
		this.xSize = 176;
		this.ySize = 13+40+93;
		this.te = te;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Data.MODID + ":textures/gui/industry-gui-base.png"));
		
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, 13);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop+13, 0, 21, this.xSize, 40);
		//The Players Inventory
		this.drawTexturedModalRect(this.guiLeft, this.guiTop+13+40, 0, 113, this.xSize, 93);
		
		//Draw resource here
		this.drawTexturedModalRect(this.guiLeft+22, this.guiTop+13+4, 201, 42, 32, 32);
		//Draw item box as produced
		this.drawTexturedModalRect(this.guiLeft+122, this.guiTop+13+4, 201, 6, 32, 32);
		
		//Then draw the lumber image...
		this.mc.getTextureManager().bindTexture(woodref.getTexture());
		this.drawTexturedModalRect(this.guiLeft+22, this.guiTop+17, 0, 0, 32, 32);
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		//Draw how much wood we have. This is wrong btw
		this.drawString(this.fontRenderer, "Wood: " + te.getWoodResource() , this.guiLeft-212, this.guiTop-50, 0);
    }

}
