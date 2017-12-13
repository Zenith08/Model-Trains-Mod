package me.kk47.modeltrains.api;

import java.util.List;

import me.kk47.ueri.UERIRenderable;

public interface IItemTrainLoadable extends IItemTrain{

	boolean canLoadResource(String resource);

	int getMaxResourcesLoadable();

	List<UERIRenderable> getModelOverrides(String loadedResource);

}