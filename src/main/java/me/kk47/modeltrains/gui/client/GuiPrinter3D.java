package me.kk47.modeltrains.gui.client;

import java.io.IOException;

import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.gui.ContainerTrainController;
import me.kk47.modeltrains.network.PacketChangeTrainDirection;
import me.kk47.modeltrains.network.PacketChangeTrainSpeed;
import me.kk47.modeltrains.tileentity.TileEntity3DPrinter;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;

public class GuiPrinter3D extends GuiContainer{
	
	private TileEntity3DPrinter te;
	
	public GuiPrinter3D(IInventory playerInv, TileEntity3DPrinter te) {
		super(new ContainerPrinter3D(playerInv, te));
		this.te = te;
		this.xSize = 0;
		this.ySize = 0;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		//Draws basic stuff
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		//Handles when a button is pressed
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void initGui() {
		super.initGui();
//		System.out.println("init gui");
		//  gui button             id, x cord, y cord, caption
		//Leftover from TrainControler Adds a button to the list so it detects ActionPerformed
//		this.buttonList.add(forwards = new ReverserButton(0, guiLeft + 7, guiTop + 7, 40, 13, "Forards"));
	
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		//Draws text and such
	}

}
