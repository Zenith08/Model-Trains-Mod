package me.kk47.modeltrains.api;

import java.util.List;

import me.kk47.modeltrains.crafting.Printer3DMode;
import me.kk47.modeltrains.crafting.Printer3DRecipe;
import me.kk47.modeltrains.items.trains.EnumTrainType;
import me.kk47.ueri.UERIRenderable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IItemTrain {

	EnumTrainType getTrainType();

	int getTrainRegistryID();
	
	@SideOnly(Side.CLIENT)
	List<UERIRenderable> getRenderables(ItemStack stack);
	
	float getSize();
	
	Printer3DMode getPrintingMode();
	
	Printer3DRecipe getPrintingRecipe();
	
	Item asItem();
}