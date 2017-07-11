package me.kk47.modeltrains.api;

import net.minecraft.client.model.ModelBase;

public interface IItemTrainLoadable extends IItemTrain{

	boolean canLoadResource(String resource);

	int getMaxResourcesLoadable();

	ModelBase getModel(String loadedResource);

}