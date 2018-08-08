package me.kk47.modeltrains.gui.industry;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.industry.MTResourceWood;
import me.kk47.modeltrains.tileentity.TileEntityIndustryForrest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiIndustryForrest extends GuiContainer{

	TileEntityIndustryForrest te;
	MTResourceWood woodref = new MTResourceWood();
	
	public GuiIndustryForrest(IInventory playerInv, TileEntityIndustryForrest te) {
		super(new ContainerIndustryForrest(playerInv, te));
		this.xSize = 176;
		this.ySize = 66;
		this.te = te;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Data.MODID + ":textures/gui/industry-gui-base.png"));
		
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, 13);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop+13, 0, 21, this.xSize, 40);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop+13+40, 0, 71, this.xSize, 13);
		
		//Block the other box
		this.drawTexturedModalRect(this.guiLeft+22, this.guiTop+13+4, 201, 42, 32, 32);
		this.drawTexturedModalRect(this.guiLeft+122, this.guiTop+13+4, 201, 42, 32, 32);
		
		//Then draw the lumber image...
		this.mc.getTextureManager().bindTexture(woodref.getTexture());
		this.drawTexturedModalRect(this.guiLeft+122, this.guiTop+17, 0, 0, 32, 32);
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		//Draw how much wood we have
		this.drawString(this.fontRenderer, "Wood: " + te.getWoodQty(), this.guiLeft-113, this.guiTop-90, 0);
    }
}
