package me.kk47.modeltrains.api;

import java.util.List;

import me.kk47.modeltrains.crafting.Printer3DRecipe;
import me.kk47.modeltrains.items.trains.EnumTrainType;
import me.kk47.ueri.UERIRenderable;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public interface IItemTrain {

	EnumTrainType getTrainType();

	int getTrainRegistryID();

//	ModelBase getModel();

//	ResourceLocation getTexture();

	List<UERIRenderable> getRenderables(ItemStack stack);
	
	float getSize();
	
	String getUnlocalizedName();
	
	boolean isUsing3DPrinter();
	
	Printer3DRecipe getPrintingRecipe(int trainRegistryID);
	
	Item asItem();
	
}