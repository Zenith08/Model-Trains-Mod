package me.kk47.modeltrains.items.trains;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.ModelPassengerCarage;
import me.kk47.modeltrains.crafting.Printer3DMode;
import me.kk47.modeltrains.crafting.Printer3DRecipe;
import me.kk47.ueri.UERITechne;
import net.minecraft.util.ResourceLocation;

public class ItemPasengerCarrage extends ItemTrain {

	public ItemPasengerCarrage(int id) {
		super(EnumTrainType.CARRIAGE_PASSENGER, "pasengercar" + id, 2+id);
		this.addUERI(new UERITechne(new ModelPassengerCarage(), new ResourceLocation(Data.MODID + ":textures/trains/train-coloured" + id + ".png")));
		this.setCreativeTab(null);
	}

	@Override
	public Printer3DRecipe getPrintingRecipe(int trainRegistryID) {
		return new Printer3DRecipe(1, 1, 1, 1);
	}

	@Override
	public Printer3DMode getPrintingMode() {
		return Printer3DMode.DISABLED;
	}

}
