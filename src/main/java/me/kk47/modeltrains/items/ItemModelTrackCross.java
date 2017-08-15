package me.kk47.modeltrains.items;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.ModelTrackStraightCross;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

public class ItemModelTrackCross extends ItemModelTrackBase{

	private static ModelTrackStraightCross MODEL = new ModelTrackStraightCross();
	private static ResourceLocation TEXTURE = new ResourceLocation(Data.MODID + ":textures/blocks/track/track-straight-cross.png");
	
	public ItemModelTrackCross() {
		super("trackStraightCross");
	}

	@Override
	public ModelBase getRenderModel() {
		return MODEL;
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

}
