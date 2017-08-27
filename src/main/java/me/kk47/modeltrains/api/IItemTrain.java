package me.kk47.modeltrains.api;

import me.kk47.modeltrains.crafting.Printer3DRecipe;
import me.kk47.modeltrains.items.trains.EnumTrainType;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public interface IItemTrain {

	EnumTrainType getTrainType();

	int getTrainRegistryID();

	ModelBase getModel();

	ResourceLocation getTexture();

	float getSize();
	
	String getUnlocalizedName();
	
	boolean isUsing3DPrinter();
	
	Printer3DRecipe getPrintingRecipe(int trainRegistryID);
	
	Item asItem();
	
}