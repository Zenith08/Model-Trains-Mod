package me.kk47.modeltrains.items.trains;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.ModelLoggingCarEmpty;
import me.kk47.modeltrains.client.model.ModelLoggingCarLogs;
import me.kk47.modeltrains.industry.MTResources;
import net.minecraft.util.ResourceLocation;

public class ItemLoggingCar extends ItemTrainLoadable {

	public ItemLoggingCar(int trainRegistryID) {
		super(EnumTrainType.CARRAGE_FREIGHT, "logging-car", trainRegistryID);
		this.setModel(new ModelLoggingCarEmpty());
		this.setTexture(new ResourceLocation(Data.MODID + ":textures/trains/logging-car-all.png"));
		this.setSize(0.75F);
		this.loadableResources.put("wood", new ModelLoggingCarLogs());
	}

}
