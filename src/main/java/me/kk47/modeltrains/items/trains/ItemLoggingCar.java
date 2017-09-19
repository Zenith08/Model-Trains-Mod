package me.kk47.modeltrains.items.trains;

import org.lwjgl.util.vector.Vector3f;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.ModelLoggingCarEmpty;
import me.kk47.modeltrains.client.model.ModelLoggingCarLogs;
import me.kk47.modeltrains.crafting.Printer3DRecipe;
import me.kk47.modeltrains.industry.MTResources;
import me.kk47.ueri.UERITechne;
import me.kk47.ueri.util.RenderTransform;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
//TODO Check if imports merged correctly
public class ItemLoggingCar extends ItemTrainLoadable {

	public ItemLoggingCar(int trainRegistryID) {
		super(EnumTrainType.CARRAGE_FREIGHT, "logging-car", trainRegistryID);
		this.addUERI(new UERITechne(new RenderTransform(new Vector3f(), new Vector3f(0, -90, 0), new Vector3f()), false, new Vector3f(), new ModelLoggingCarEmpty(), new ResourceLocation(Data.MODID + ":textures/trains/logging-car-all.png")));
		this.setSize(0.75F);
		this.loadableResources.put("wood", new ModelLoggingCarLogs());
	}

	@Override
	public boolean isUsing3DPrinter() {
		return true;
	}

	@Override
	public Printer3DRecipe getPrintingRecipe(int trainRegistryID) {
		return new Printer3DRecipe(1, 2, 2, 2);
	}

	//Disabled until industry rewrite
	@Override
	public ModelBase getModel(String loadedResource) {
		return null;
	}

}
