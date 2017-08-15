package me.kk47.modeltrains.items.trains;

import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.api.IItemTrain;
import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public abstract class ItemTrain extends Item implements IItemTrain {

	protected ModelBase model;
	protected ResourceLocation texture;
	protected EnumTrainType type;
	protected final int trainRegistryID;
	
	/**This is just a default, you can change it.*/
	protected float size = 0.5F;
	
	public ItemTrain(EnumTrainType trainType, String registryName, int trainRegistryID){
		this.type = trainType;
		this.setRegistryName(registryName);
		this.setUnlocalizedName(registryName);
		this.setCreativeTab(ModelTrains.creativeTab);
		this.trainRegistryID = trainRegistryID;
		TrainRegistry.registerTrain(this);
	}
	
	@Override
	public EnumTrainType getTrainType(){
		return type;
	}
	
	@Override
	public int getTrainRegistryID(){
		return trainRegistryID;
	}
	
	@Override
	public ModelBase getModel(){
		return model;
	}

	protected void setModel(ModelBase model){
		this.model = model;
	}

	@Override
	public ResourceLocation getTexture(){
		return texture;
	}
	
	@Override
	public float getSize(){
		return size+0.1F;
	}
	
	protected void setSize(float size){
		this.size = size;
	}

	protected void setTexture(ResourceLocation texture){
		this.texture = texture;
	}

}
