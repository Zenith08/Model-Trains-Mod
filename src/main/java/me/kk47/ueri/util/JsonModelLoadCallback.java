package me.kk47.ueri.util;

import me.kk47.ueri.UERIJson;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public class JsonModelLoadCallback {

	private ModelResourceLocation modelLocation;
	private UERIJson ueriContainer;
	
	public JsonModelLoadCallback(ModelResourceLocation modelLocation, UERIJson ueriContainer) {
		this.modelLocation = modelLocation;
		this.ueriContainer = ueriContainer;
	}

	/**
	 * @return the modelLocation
	 */
	public ModelResourceLocation getModelLocation() {
		return modelLocation;
	}

	/**
	 * @param modelLocation the modelLocation to set
	 */
	public void setModelLocation(ModelResourceLocation modelLocation) {
		this.modelLocation = modelLocation;
	}

	/**
	 * @return the ueriContainer
	 */
	public UERIJson getUeriContainer() {
		return ueriContainer;
	}

	/**
	 * @param ueriContainer the ueriContainer to set
	 */
	public void setUeriContainer(UERIJson ueriContainer) {
		this.ueriContainer = ueriContainer;
	}
}
