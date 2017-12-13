package me.kk47.modeltrains.tileentity;

import me.kk47.modeltrains.api.IItemTrainLoadable;
import me.kk47.modeltrains.industry.MTResources;
import me.kk47.modeltrains.train.RollingStock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;

public class TileEntityIndustryForrest extends TileEntityIndustry implements ITickable{

	int timeUntilProduction = 20;
	int woodQty = 0;

	public TileEntityIndustryForrest() {
		super.setTimeUntilProduction(20);
		super.setTrackInventory(new ItemStack[][]{
		{null, TRACK_EAST, null, null},
		{null, TRACK_EAST, null, null},
		{TRACK_NORTH, TRACK_TURN_NORTH, null, null},
		{null, null, null, null}});
	}

	@Override
	public void process() {
		woodQty+=1;
		if(woodQty >= 10)
			woodQty=10;
	}

	@Override
	public void loadResources(RollingStock rs) {
		woodQty -= rs.load(MTResources.wood, woodQty);
		
	}

	@Override
	public void unloadResources(RollingStock rs) {
		//The forest doesn't unload anything.
	}

}
