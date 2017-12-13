package me.kk47.modeltrains.tileentity;

import me.kk47.modeltrains.industry.MTResources;
import me.kk47.modeltrains.train.RollingStock;
import net.minecraft.item.ItemStack;

public class TileEntityIndustryVillage extends TileEntityIndustry {

	int woodAmount = 0;
	
	public TileEntityIndustryVillage() {
		super.setTimeUntilProduction(20);
		super.setTrackInventory(new ItemStack[][]{
		{TRACK_EAST, null, null, null},
		{TRACK_EAST, null, null, null},
		{TRACK_EAST, null, null, null},
		{TRACK_EAST, null, null, null}});
	}

	@Override
	public void process() {
		if(woodAmount >= 4) {
			//Convert it into actual wood.
		}
	}

	@Override
	public void loadResources(RollingStock rs) {
		//The village doesn't load any resources right now.
	}

	@Override
	public void unloadResources(RollingStock rs) {
		woodAmount += rs.unload(MTResources.wood, 10);
	}

}
