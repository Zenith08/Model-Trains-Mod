package me.kk47.modeltrains.gui.client;

import java.io.IOException;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.gui.ContainerPrinter3D;
import me.kk47.modeltrains.gui.client.button.PageChangeButton;
import me.kk47.modeltrains.gui.client.button.PrintButton;
import me.kk47.modeltrains.gui.client.button.TrainSelectButton;
import me.kk47.modeltrains.items.trains.TrainRegistry;
import me.kk47.modeltrains.items.trains.TrainRegistryEntry;
import me.kk47.modeltrains.network.PacketPrintTrain;
import me.kk47.modeltrains.tileentity.TileEntity3DPrinter;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

//TODO Localize using I18n
public class GuiPrinter3D extends GuiContainer{

	private TileEntity3DPrinter te;

	private int page = 0;
	private int maxPages;
	private TrainRegistryEntry[] trains;

	private TrainSelectButton[] trainSelectors;
	private byte selectedTrainButton = 0;

	private PageChangeButton upPage;
	private PageChangeButton downPage;

	private PrintButton print;

	public GuiPrinter3D(IInventory playerInv, TileEntity3DPrinter te) {
		super(new ContainerPrinter3D(playerInv, te));
		this.te = te;
		this.xSize = 175;
		this.ySize = 215;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		//Draws basic stuff
		//Update buttons pressed
		for(byte i = 0; i < trainSelectors.length; i++) {
			if(i == selectedTrainButton) {
				trainSelectors[i].setSelected(true);
			} else {
				trainSelectors[i].setSelected(false);
			}
		}
		
		print.setPrinting(te.isPrinting());

		this.mc.getTextureManager().bindTexture(new ResourceLocation(Data.MODID + ":textures/gui/printergui.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id < 5) {
			selectedTrainButton = (byte) button.id;
		}else if(button.id == 6) {
			if(page > 0) {
				page-=1;
				updatePage();
				upPage.buttonPressed();
			}
		}else if(button.id == 7) {
			if(page < maxPages) {
				page+=1;
				updatePage();
				downPage.buttonPressed();
			}
		}else if(button.id == 8) {
//			System.out.println("Button Pressed");
			if(!te.isPrinting() && te.canPrint()) {
				te.setField(1, 0);
				te.setField(2, trainSelectors[selectedTrainButton].getTrainID());
				te.tryStartPrint();
				ModelTrains.packetHandler.sendToServer(new PacketPrintTrain(trainSelectors[selectedTrainButton].getTrainID(), te.getPos()));
			}
		}
	}

	private void updatePage() {
		selectedTrainButton = 0;
		for(int i = 0; i < trainSelectors.length; i++) {
			int nextArray = (page*5) + i;
			if(nextArray < trains.length) {
				trainSelectors[i].setTrainID(trains[nextArray].getRegisteredID());
			}
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}

	@Override
	public void initGui() {
		super.initGui();
		//  gui button             id, x cord, y cord, caption
		trains = TrainRegistry.getAllTrains();
		trainSelectors = new TrainSelectButton[5];
		for(int i = 0; i < trainSelectors.length; i++) {
			trainSelectors[i] = new TrainSelectButton(i, this.guiLeft+76, this.guiTop+26+20*i, trains[i].getTrain().getTrainType().toString(), trains[i].getRegisteredID(), this);
			this.addButton(trainSelectors[i]);
		}

		this.addButton(upPage = new PageChangeButton(6, this.guiLeft+76, this.guiTop+6, "", true));
		this.addButton(downPage = new PageChangeButton(7, this.guiLeft+122, this.guiTop+6, "", false));
		maxPages = (trains.length/5) + 1;

		this.addButton(print = new PrintButton(8, this.guiLeft+29, this.guiTop+40, 37, 78, ""));
	}

	/*	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

	}*/

	public FontRenderer getFontRenderer() {
		return this.fontRenderer;
	}

}
