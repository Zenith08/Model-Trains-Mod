package me.kk47.modeltrains.items;

import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.api.IItemModelTrack;
import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public abstract class ItemModelTrackBase extends Item implements IItemModelTrack{

	public ItemModelTrackBase(String registryName){
		this.setRegistryName(registryName);
		this.setUnlocalizedName(registryName);
		this.setCreativeTab(ModelTrains.creativeTab);
	}
	
	@Override
	public abstract ModelBase getRenderModel();
	@Override
	public abstract ResourceLocation getTexture();
}
