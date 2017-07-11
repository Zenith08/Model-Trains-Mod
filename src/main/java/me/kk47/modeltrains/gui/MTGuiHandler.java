package me.kk47.modeltrains.gui;

import me.kk47.modeltrains.gui.client.GuiTrackbed;
import me.kk47.modeltrains.gui.client.GuiTrainController;
import me.kk47.modeltrains.tileentity.TileEntityTrackBed;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class MTGuiHandler implements IGuiHandler{

	public static final int GUI_TRACKBED_ID = 0;
	public static final int GUI_TRAINCONTROLLER_ID = 1;

	public MTGuiHandler() {

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//		System.out.println("GuiHandler#getServerGuiElement at location " + x + ", " + y + ", " + z + " The block there is " + world.getBlockState(new BlockPos(x, y, z)) + " TE there is " + world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == GUI_TRACKBED_ID)
			return new ContainerTrackbed(player.inventory, (TileEntityTrackBed) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == GUI_TRAINCONTROLLER_ID)
			return new ContainerTrainController(player.inventory, (TileEntityTrainController) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
//		System.out.println("GuiHandler#getClientGuiElement at location " + x + ", " + y + ", " + z + " The block there is " + world.getBlockState(new BlockPos(x, y, z)) + " TE there is " + world.getTileEntity(new BlockPos(x, y, z))); 
		if(ID == GUI_TRACKBED_ID)
			return new GuiTrackbed(player.inventory, (TileEntityTrackBed) world.getTileEntity(new BlockPos(x, y, z)));
		if(ID == GUI_TRAINCONTROLLER_ID)
			return new GuiTrainController(player.inventory, (TileEntityTrainController) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

}
