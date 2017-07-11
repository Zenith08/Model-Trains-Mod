package me.kk47.modeltrains.tileentity;

import me.kk47.modeltrains.api.IItemTrainLoadable;
import me.kk47.modeltrains.industry.Industries;
import me.kk47.modeltrains.industry.MTResources;
import me.kk47.modeltrains.items.ModItems;
import me.kk47.modeltrains.train.RollingStock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;

public class TileEntityIndustryForrest extends TileEntityIndustry implements ITickable{

	int timeUntilProduction = 20;
	int woodQty = 0;

	public TileEntityIndustryForrest() {
		this.industry = Industries.forrest;
		super.setTimeUntilProduction(20);
		this.setTrackInventory(new ItemStack[][]{
		{null, TRACK_EAST, null, null},
		{null, TRACK_EAST, null, null},
		{TRACK_NORTH, TRACK_TURN_NORTH, null, null},
		{null, null, null, null}});
	}

	@Override
	protected void produce() {
		woodQty+=1;
		if(woodQty >= 10)
			woodQty=10;
	}

	@Override
	public void tryToLoad(RollingStock rs) {
		if(rs.getTrainItem() instanceof IItemTrainLoadable){
			IItemTrainLoadable loadable = (IItemTrainLoadable) rs.getTrainItem();
			if(loadable.canLoadResource(MTResources.wood.getName())){
				if(rs.getLoadedResource().getName().equalsIgnoreCase("air") || rs.getLoadedResource().getName().equalsIgnoreCase("wood")){
					int amountToLoad = loadable.getMaxResourcesLoadable()-rs.getLoadedAmount();
					rs.load(MTResources.wood, amountToLoad);
					this.woodQty-=amountToLoad;
				}
			}
		}
	}

}
