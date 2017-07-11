package me.kk47.modeltrains.gui.client;

import java.io.IOException;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.gui.ContainerTrainController;
import me.kk47.modeltrains.network.PacketChangeTrainDirection;
import me.kk47.modeltrains.network.PacketChangeTrainSpeed;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiTrainController extends GuiContainer{

	private TileEntityTrainController te;
	
	private ReverserButton forwards;
	private ReverserButton backwards;
	
	private SpeedButton sb1;
	private SpeedButton sb2;
	private SpeedButton sb3;
	private SpeedButton sb4;
	private SpeedButton sb5;
	private SpeedButton sb6;
	
	public GuiTrainController(IInventory playerInv, TileEntityTrainController te) {
		super(new ContainerTrainController(playerInv, te));
		this.te = te;
		this.xSize = 176;
		this.ySize = 173;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
//		int offsetFromScreenLeft = (width - 256 ) / 2;
		
		//Updates speed buttons
		byte speed = te.getSpeedValue();
		sb1.isSelected = false;
		sb2.isSelected = false;
		sb3.isSelected = false;
		sb4.isSelected = false;
		sb5.isSelected = false;
		sb6.isSelected = false;
		if(speed == 0){
			sb1.isSelected = true;
		}else if(speed == 1){
			sb2.isSelected = true;
		}else if(speed == 2){
			sb3.isSelected = true;
		}else if(speed == 3){
			sb4.isSelected = true;
		}else if(speed == 4){
			sb5.isSelected = true;
		}else if(speed == 5){
			sb6.isSelected = true;
		}
		
		forwards.isSelected = false;
		backwards.isSelected = false;
		if(te.getDirectionValue()){
			forwards.isSelected = true;
		}else{
			backwards.isSelected = true;
		}
		
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Data.MODID + ":textures/gui/controllergui.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id > 1){
			ModelTrains.packetHandler.sendToServer(new PacketChangeTrainSpeed((byte) (button.id-2), te.getPos()));
		}else if(button.id == 0){
			ModelTrains.packetHandler.sendToServer(new PacketChangeTrainDirection(false, te.getPos()));
		}else if(button.id == 1){
			ModelTrains.packetHandler.sendToServer(new PacketChangeTrainDirection(true, te.getPos()));
		}
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
		this.buttonList.add(forwards = new ReverserButton(0, guiLeft + 7, guiTop + 7, 40, 13, "Forards"));
		this.buttonList.add(backwards = new ReverserButton(1, guiLeft + 52, guiTop + 7, 40, 13, "Backwards"));
		
		this.buttonList.add(sb1 = new SpeedButton(2, guiLeft + 10, guiTop + 23, 9, 9, ""));
		this.buttonList.add(sb2 = new SpeedButton(3, guiLeft + 10, guiTop + 34, 9, 9, ""));
		this.buttonList.add(sb3 = new SpeedButton(4, guiLeft + 10, guiTop + 45, 9, 9, ""));
		this.buttonList.add(sb4 = new SpeedButton(5, guiLeft + 10, guiTop + 56, 9, 9, ""));
		this.buttonList.add(sb5 = new SpeedButton(6, guiLeft + 10, guiTop + 67, 9, 9, ""));
		this.buttonList.add(sb6 = new SpeedButton(7, guiLeft + 10, guiTop + 78, 9, 9, ""));
	}
	
/*	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		
	}*/

}
