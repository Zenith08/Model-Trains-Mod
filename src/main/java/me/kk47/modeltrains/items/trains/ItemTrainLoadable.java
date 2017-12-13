package me.kk47.modeltrains.items.trains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.kk47.modeltrains.api.IItemTrainLoadable;
import me.kk47.modeltrains.client.model.ModelLoggingCarLogs;
import me.kk47.modeltrains.industry.MTResource;
import me.kk47.modeltrains.industry.MTResources;
import net.minecraft.client.model.ModelBase;

public abstract class ItemTrainLoadable extends ItemTrain implements IItemTrainLoadable {

	protected int maxResourcesLoadable = 5;
	protected List<String> loadableResources;

	public ItemTrainLoadable(EnumTrainType trainType, String registryName, int trainRegistryID) {
		super(trainType, registryName, trainRegistryID);
		loadableResources = new ArrayList<String>();
	}

	@Override
	public boolean canLoadResource(String resource){
		for(String s : loadableResources) {
			if(s.equalsIgnoreCase(resource)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getMaxResourcesLoadable(){
		return maxResourcesLoadable;
	}

}
