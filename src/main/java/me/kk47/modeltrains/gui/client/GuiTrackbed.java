package me.kk47.modeltrains.gui.client;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.gui.ContainerTrackbed;
import me.kk47.modeltrains.tileentity.TileEntityTrackBed;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiTrackbed extends GuiContainer{

	public GuiTrackbed(IInventory playerInv, TileEntityTrackBed te) {
	        super(new ContainerTrackbed(playerInv, te));

	        this.xSize = 176;
	        this.ySize = 170;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Data.MODID + ":textures/gui/trackbedgui.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		//draw text and stuff here
        //the parameters for drawString are: string, x, y, color
        fontRenderer.drawString("Trackbed", 47, 8, 4210752);
        //draws "Inventory" or your regional equivalent
        fontRenderer.drawString("Inventory", 9, 75, 4210752);
    }

}
