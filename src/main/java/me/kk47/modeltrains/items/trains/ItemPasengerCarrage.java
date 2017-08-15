package me.kk47.modeltrains.items.trains;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.ModelPassengerCarage;
import net.minecraft.util.ResourceLocation;

public class ItemPasengerCarrage extends ItemTrain {

	public ItemPasengerCarrage(int id) {
		super(EnumTrainType.CARRIAGE_PASSENGER, "pasengercar" + id, 2+id);
		this.setModel(new ModelPassengerCarage());
		this.setTexture(new ResourceLocation(Data.MODID + ":textures/trains/train-coloured" + id + ".png"));
	}

}
