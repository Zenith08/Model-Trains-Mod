package me.kk47.modeltrains.items.trains;

import org.lwjgl.util.vector.Vector3f;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.ModelToyTrain;
import me.kk47.modeltrains.crafting.Printer3DRecipe;
import me.kk47.ueri.UERITechne;
import me.kk47.ueri.util.RenderTransform;
import net.minecraft.util.ResourceLocation;

public class ItemToyTrain extends ItemTrain {

	public ItemToyTrain() {
		super(EnumTrainType.LOCOMOTIVE_STEAM, "toyTrain", 1);
		this.addUERI(new UERITechne(new RenderTransform(new Vector3f(), new Vector3f(0, -90, 0), new Vector3f()), false, new Vector3f(), new ModelToyTrain(), new ResourceLocation(Data.MODID + ":textures/trains/toy-steam-engine.png")));
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
