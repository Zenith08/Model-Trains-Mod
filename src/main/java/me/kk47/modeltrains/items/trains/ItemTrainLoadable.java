package me.kk47.modeltrains.items.trains;

import java.util.HashMap;
import java.util.Map;

import me.kk47.modeltrains.api.IItemTrainLoadable;
import me.kk47.modeltrains.client.model.ModelLoggingCarLogs;
import me.kk47.modeltrains.industry.MTResource;
import me.kk47.modeltrains.industry.MTResources;
import net.minecraft.client.model.ModelBase;

//Disabled until further notice
public abstract class ItemTrainLoadable extends ItemTrain implements IItemTrainLoadable {

	protected Map<String, ModelBase> loadableResources;
	protected int maxResourcesLoadable = 5;

	public ItemTrainLoadable(EnumTrainType trainType, String registryName, int trainRegistryID) {
		super(trainType, registryName, trainRegistryID);
		loadableResources = new HashMap<String, ModelBase>();
	}

	@Override
	public boolean canLoadResource(String resource){
		return loadableResources.containsKey(resource);
	}

	@Override
	public int getMaxResourcesLoadable(){
		return maxResourcesLoadable;
	}

/*	@Override
	public ModelBase getModel(String loadedResource){
//		System.out.println("getting loaded model");
		if(loadedResource != null){
//			System.out.println("requested resource isn't null");
			if(loadedResource.equalsIgnoreCase("air")){
//				System.out.println("requested resource is air");
				return getModel();
			}else{
//				System.out.println("Returning dynamic model");
				return loadableResources.get(loadedResource);
			}
		}else{
			return getModel();
		}
	}*/
}
