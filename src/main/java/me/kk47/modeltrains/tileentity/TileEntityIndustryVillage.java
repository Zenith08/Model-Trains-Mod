package me.kk47.modeltrains.tileentity;

import me.kk47.modeltrains.train.RollingStock;
import net.minecraft.item.ItemStack;

public class TileEntityIndustryVillage extends TileEntityIndustry {

	public TileEntityIndustryVillage() {
		super.setTimeUntilProduction(20);
		super.setTrackInventory(new ItemStack[][]{
		{TRACK_EAST, null, null, null},
		{TRACK_EAST, null, null, null},
		{TRACK_EAST, null, null, null},
		{TRACK_EAST, null, null, null}});
	}

	@Override
	public void tryToLoad(RollingStock rs) {
		//TODO Implement with abstractions
	}

	@Override
	protected void produce() {
		// TODO abstract
	}

}
