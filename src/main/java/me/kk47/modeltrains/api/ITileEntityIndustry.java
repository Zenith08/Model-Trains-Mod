package me.kk47.modeltrains.api;

import me.kk47.modeltrains.train.RollingStock;

public interface ITileEntityIndustry {

	void handleRollingStock(RollingStock rs);
	
	void process();
	
	void loadResources(RollingStock rs);
	
	void unloadResources(RollingStock rs);

}