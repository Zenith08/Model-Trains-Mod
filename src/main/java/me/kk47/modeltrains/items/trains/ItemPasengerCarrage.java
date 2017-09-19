package me.kk47.modeltrains.items.trains;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.ModelPassengerCarage;
import me.kk47.modeltrains.crafting.Printer3DRecipe;
import net.minecraft.util.ResourceLocation;

public class ItemPasengerCarrage extends ItemTrain {

	public ItemPasengerCarrage(int id) {
		super(EnumTrainType.CARRIAGE_PASSENGER, "pasengercar" + id, 2+id);
		this.setModel(new ModelPassengerCarage());
		this.setTexture(new ResourceLocation(Data.MODID + ":textures/trains/train-coloured" + id + ".png"));
	}

	@Override
	public boolean isUsing3DPrinter() {
		return true;
	}

	@Override
	//TODO Implement Per Colour
	public Printer3DRecipe getPrintingRecipe(int trainRegistryID) {
		return new Printer3DRecipe(1, 1, 1, 1);
	}

}
