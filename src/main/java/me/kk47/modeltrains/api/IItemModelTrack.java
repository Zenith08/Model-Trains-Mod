package me.kk47.modeltrains.api;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

public interface IItemModelTrack {

	ModelBase getRenderModel();

	ResourceLocation getTexture();

}