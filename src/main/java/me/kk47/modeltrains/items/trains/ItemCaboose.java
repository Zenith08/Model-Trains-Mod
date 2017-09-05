package me.kk47.modeltrains.items.trains;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.ModelCaboose;
import me.kk47.modeltrains.crafting.Printer3DRecipe;
import me.kk47.ueri.UERITechne;
import net.minecraft.util.ResourceLocation;

public class ItemCaboose extends ItemTrain {

	public ItemCaboose(int id) {
		super(EnumTrainType.CARRIAGE_PASSENGER, "caboose" + id, id + 25);
		this.addUERI(new UERITechne(new ModelCaboose(), new ResourceLocation(Data.MODID + ":textures/trains/train-coloured" + id + ".png")));
	}

	@Override
	public boolean isUsing3DPrinter() {
		return true;
	}

	@Override
	//TODO Implement Per Colour
	public Printer3DRecipe getPrintingRecipe(int trainRegistryID) {
		return new Printer3DRecipe();
	}

}
