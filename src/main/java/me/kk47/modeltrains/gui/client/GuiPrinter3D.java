package me.kk47.modeltrains.gui.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.crafting.Printer3DMode;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class GuiPrinter3D extends GuiContainer{

	private TileEntity3DPrinter te;

	private int page = 0;
	private int maxPages;
	private List<TrainRegistryEntry> trains;

	private TrainSelectButton[] trainSelectors;
	private byte selectedTrainButton = 0;

	private PageChangeButton upPage;
	private PageChangeButton downPage;

	private PrintButton print;

	private boolean initalized;

	public GuiPrinter3D(IInventory playerInv, TileEntity3DPrinter te) {
		super(new ContainerPrinter3D(playerInv, te));
		this.te = te;
		this.xSize = 175;
		this.ySize = 215;
		initalized = false;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	public void updateScreen() {
		if(initalized) {
			for(TrainSelectButton tsb : trainSelectors) {
				if(TrainRegistry.getTrain(tsb.getTrainID()).getPrintingMode() == Printer3DMode.VARIABLE_COLOUR) {
					tsb.setItemStackNBT(buildNBTTag(te.getStackInSlot(1).getCount(), te.getStackInSlot(3).getCount(), te.getStackInSlot(2).getCount()));
				}
			}
		}
	}

	private NBTTagCompound buildNBTTag(int r, int g, int b) {
		if(r < 1) {
			r = 1;
		}else if(r > 5) {
			r = 5;
		}
		if(g < 1){
			g = 1;
		}else if(g > 5) {
			g = 5;
		}
		if(b < 1) {
			b = 1;
		}else if(b > 5) {
			b = 5;
		}
		NBTTagCompound out = new NBTTagCompound();
		out.setFloat("red", (r-1)*0.25F);
		out.setFloat("green", (g-1)*0.25F);
		out.setFloat("blue", (b-1)*0.25F);
		return out;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		if(initalized) {
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
		}

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
			if(nextArray < trains.size()) {
				trainSelectors[i].setTrainID(trains.get(nextArray).getRegisteredID());
				trainSelectors[i].enabled = true;
			}else {
				trainSelectors[i].enabled = false;
				trainSelectors[i].setTrainID(-1); //-1 indicates disabled
			}
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void initGui() {
		super.initGui();
		//  gui button             id, x cord, y cord, caption
		//Initializes the array of printable trains honouring the various modes
		trains = new ArrayList<TrainRegistryEntry>();
		TrainRegistryEntry[] allTrains = TrainRegistry.getAllTrains();

		for(TrainRegistryEntry tre : allTrains) {
			if(tre.getTrain().getPrintingMode().isPrintable()) {
				trains.add(tre);
			}
		}

		trainSelectors = new TrainSelectButton[5];
		for(int i = 0; i < trainSelectors.length; i++) {
			if(i < trains.size()) {
				trainSelectors[i] = new TrainSelectButton(i, this.guiLeft+76, this.guiTop+26+20*i, trains.get(i).getTrain().getTrainType().toString(), trains.get(i).getRegisteredID(), this);
			}else {
				trainSelectors[i] = new TrainSelectButton(i, this.guiLeft+76, this.guiTop+26+20*i, "", -1, this); //Train id -1 indicates disabled
				trainSelectors[i].enabled = false;
			}
			this.addButton(trainSelectors[i]);
		}

		this.addButton(upPage = new PageChangeButton(6, this.guiLeft+76, this.guiTop+6, "", true));
		this.addButton(downPage = new PageChangeButton(7, this.guiLeft+122, this.guiTop+6, "", false));
		maxPages = (trains.size()/5);

		this.addButton(print = new PrintButton(8, this.guiLeft+29, this.guiTop+40, 37, 78, ""));
		
		initalized = true;
	}

	/*	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

	}*/

	public FontRenderer getFontRenderer() {
		return this.fontRenderer;
	}

}
