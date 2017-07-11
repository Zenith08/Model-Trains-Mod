package me.kk47.modeltrains.items.trains;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.ModelCaboose;
import net.minecraft.util.ResourceLocation;

public class ItemCaboose extends ItemTrain {

	public ItemCaboose(int id) {
		super(EnumTrainType.CARRIAGE_PASSENGER, "caboose" + id, id + 25);
		this.setModel(new ModelCaboose());
		this.setTexture(new ResourceLocation(Data.MODID + ":textures/trains/trainColoured" + id + ".png"));
	}

}
