package me.kk47.modeltrains.items.trains;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.ModelToyTrain;
import me.kk47.modeltrains.crafting.Printer3DRecipe;
import net.minecraft.util.ResourceLocation;

public class ItemToyTrain extends ItemTrain {

	public ItemToyTrain() {
		super(EnumTrainType.LOCOMOTIVE_STEAM, "toyTrain", 1);
		this.setModel(new ModelToyTrain());
		this.setTexture(new ResourceLocation(Data.MODID + ":textures/trains/toy-steam-engine.png"));
	}

	@Override
	public boolean isUsing3DPrinter() {
		return true;
	}

	@Override
	public Printer3DRecipe getPrintingRecipe(int trainRegistryID) {
		return new Printer3DRecipe(2, 0, 0, 0);
	}

}
