package me.kk47.ueri.util;

import me.kk47.ueri.UERIObj;
import net.minecraft.util.ResourceLocation;

public class ObjModelLoadCallback {

	private ResourceLocation modelLocation;
	private UERIObj ueriContainer;
	
	public ObjModelLoadCallback(ResourceLocation modelLocation, UERIObj ueriContainer) {
		this.modelLocation = modelLocation;
		this.ueriContainer = ueriContainer;
	}

	/**
	 * @return the modelLocation
	 */
	public ResourceLocation getModelLocation() {
		return modelLocation;
	}

	/**
	 * @param modelLocation the modelLocation to set
	 */
	public void setModelLocation(ResourceLocation modelLocation) {
		this.modelLocation = modelLocation;
	}

	/**
	 * @return the ueriContainer
	 */
	public UERIObj getUeriContainer() {
		return ueriContainer;
	}

	/**
	 * @param ueriContainer the ueriContainer to set
	 */
	public void setUeriContainer(UERIObj ueriContainer) {
		this.ueriContainer = ueriContainer;
	}

}
