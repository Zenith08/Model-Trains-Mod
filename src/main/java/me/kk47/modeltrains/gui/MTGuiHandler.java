package me.kk47.modeltrains.gui;

import me.kk47.modeltrains.gui.client.GuiPrinter3D;
import me.kk47.modeltrains.gui.client.GuiTrackbed;
import me.kk47.modeltrains.gui.client.GuiTrainController;
import me.kk47.modeltrains.gui.industry.ContainerIndustryForrest;
import me.kk47.modeltrains.gui.industry.ContainerIndustryVillage;
import me.kk47.modeltrains.gui.industry.GuiIndustryForrest;
import me.kk47.modeltrains.gui.industry.GuiIndustryVillage;
import me.kk47.modeltrains.tileentity.TileEntity3DPrinter;
import me.kk47.modeltrains.tileentity.TileEntityIndustryForrest;
import me.kk47.modeltrains.tileentity.TileEntityIndustryVillage;
import me.kk47.modeltrains.tileentity.TileEntityTrackBed;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class MTGuiHandler implements IGuiHandler{

	public static final int GUI_TRACKBED_ID = 0;
	public static final int GUI_TRAINCONTROLLER_ID = 1;
	public static final int GUI_PRINTER_3D = 2;
	
	//For convenience, Industry IDs start at 10.
	public static final int GUI_INDUSTRY_FORREST = 10;
	public static final int GUI_INDUSTRY_VILLAGE = 11;

	public MTGuiHandler() {

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//		System.out.println("GuiHandler#getServerGuiElement at location " + x + ", " + y + ", " + z + " The block there is " + world.getBlockState(new BlockPos(x, y, z)) + " TE there is " + world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == GUI_TRACKBED_ID)
			return new ContainerTrackbed(player.inventory, (TileEntityTrackBed) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == GUI_TRAINCONTROLLER_ID)
			return new ContainerTrainController(player.inventory, (TileEntityTrainController) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == GUI_PRINTER_3D) {
			return new ContainerPrinter3D(player.inventory, (TileEntity3DPrinter) world.getTileEntity(new BlockPos(x, y, z)));
		}else if(ID == GUI_INDUSTRY_FORREST) {
			return new ContainerIndustryForrest(player.inventory, (TileEntityIndustryForrest) world.getTileEntity(new BlockPos(x, y, z)));
		}else if(ID == GUI_INDUSTRY_VILLAGE) {
			return new ContainerIndustryVillage(player.inventory, (TileEntityIndustryVillage) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//		System.out.println("GuiHandler#getClientGuiElement at location " + x + ", " + y + ", " + z + " The block there is " + world.getBlockState(new BlockPos(x, y, z)) + " TE there is " + world.getTileEntity(new BlockPos(x, y, z))); 
		if(ID == GUI_TRACKBED_ID)
			return new GuiTrackbed(player.inventory, (TileEntityTrackBed) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == GUI_TRAINCONTROLLER_ID)
			return new GuiTrainController(player.inventory, (TileEntityTrainController) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == GUI_PRINTER_3D)
			return new GuiPrinter3D(player.inventory, (TileEntity3DPrinter) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == GUI_INDUSTRY_FORREST)
			return new GuiIndustryForrest(player.inventory, (TileEntityIndustryForrest) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == GUI_INDUSTRY_VILLAGE)
			return new GuiIndustryVillage(player.inventory, (TileEntityIndustryVillage) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

}
