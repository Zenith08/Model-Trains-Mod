package me.kk47.modeltrains.tileentity;

import me.kk47.modeltrains.api.IItemTrainLoadable;
import me.kk47.modeltrains.blocks.ModBlocks;
import me.kk47.modeltrains.industry.MTResources;
import me.kk47.modeltrains.train.RollingStock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public class TileEntityIndustryForrest extends TileEntityIndustry implements ITickable{

	int woodQty = 0;

	public TileEntityIndustryForrest() {
		super.setTimeUntilProduction(60);
		
		super.setTrackInventory(
				new ItemStack[][]{
		{null, TRACK_EAST, null, null},
		{null, TRACK_EAST, null, null},
		{TRACK_NORTH, TRACK_TURN_NORTH, null, null},
		{null, null, null, null}});
	}

	@Override
	public void process() {
		//System.out.println("Process called.");
		
		woodQty+=3;
		if(woodQty >= 10)
			woodQty=10;
		
		world.notifyBlockUpdate(pos, ModBlocks.forrest.getDefaultState(), ModBlocks.forrest.getDefaultState(), 1);
		this.markDirty();
		
		//System.out.println("Wood Qty " + woodQty);
	}

	@Override
	public void loadResources(RollingStock rs) {
		woodQty -= rs.load(MTResources.wood, woodQty);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		woodQty = compound.getInteger("wood");
		//System.out.println("Read wood and qty is now " + woodQty);
    }

	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("wood", woodQty);
        return compound;
    }
	
	@Override
	public void unloadResources(RollingStock rs) {
		//The forest doesn't unload anything.
	}

	public boolean isUsableByPlayer(EntityPlayer playerIn) {
		return this.world.getTileEntity(this.getPos()) == this && playerIn.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}
	
	public int getWoodQty() {
		//System.out.println("Wood Qty " + woodQty);
		return woodQty;
	}

}
