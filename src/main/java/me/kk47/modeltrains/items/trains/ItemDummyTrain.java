package me.kk47.modeltrains.items.trains;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.ModelDummyTrain;
import me.kk47.modeltrains.crafting.Printer3DMode;
import me.kk47.modeltrains.crafting.Printer3DRecipe;
import me.kk47.ueri.UERITechne;
import net.minecraft.util.ResourceLocation;

public class ItemDummyTrain extends ItemTrain {

	public ItemDummyTrain() {
		super(EnumTrainType.CARRAGE_FREIGHT, "dummyTrain", 0);
		this.addUERI(new UERITechne(new ModelDummyTrain(), new ResourceLocation(Data.MODID + ":/textures/trains/DummyTrain.png")));
	}

	@Override
	public Printer3DRecipe getPrintingRecipe(int trainRegistryID) {
		return null;
	}

	@Override
	public Printer3DMode getPrintingMode() {
		return Printer3DMode.DISABLED;
	}

}
