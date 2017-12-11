package me.kk47.modeltrains.items;

import java.util.ArrayList;
import java.util.List;

import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.api.IItemModelTrack;
import me.kk47.ueri.UERIRenderable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class ItemModelTrackBase extends Item implements IItemModelTrack{

	public List<UERIRenderable> renderables;
	
	public ItemModelTrackBase(String registryName){
		this.setRegistryName(registryName);
		this.setUnlocalizedName(registryName);
		this.setCreativeTab(ModelTrains.creativeTab);
		renderables = new ArrayList<UERIRenderable>();
	}
	
	protected void addRenderable(UERIRenderable renderable) {
		renderables.add(renderable);
	}
	
	@Override
	public List<UERIRenderable> getRenderables(ItemStack stack) {
		return renderables;
	}
}
