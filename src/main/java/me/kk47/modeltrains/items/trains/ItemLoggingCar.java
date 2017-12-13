package me.kk47.modeltrains.items.trains;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.ModelLoggingCarEmpty;
import me.kk47.modeltrains.client.model.ModelLoggingCarLogs;
import me.kk47.modeltrains.crafting.Printer3DMode;
import me.kk47.modeltrains.crafting.Printer3DRecipe;
import me.kk47.modeltrains.industry.MTResources;
import me.kk47.ueri.UERIMod;
import me.kk47.ueri.UERIRenderable;
import me.kk47.ueri.UERITechne;
import me.kk47.ueri.util.RenderTransform;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLoggingCar extends ItemTrainLoadable {

	@SideOnly(Side.CLIENT)
	private UERITechne loadedModel;
	
	public ItemLoggingCar(int trainRegistryID) {
		super(EnumTrainType.CARRAGE_FREIGHT, "logging-car", trainRegistryID);
		this.setSize(0.75F);
		this.loadableResources.add("wood");
		if(UERIMod.isClientSided) {
			this.addUERI(new UERITechne(new RenderTransform(new Vector3f(), new Vector3f(0, -90, 0), new Vector3f()), false, new Vector3f(), new ModelLoggingCarEmpty(), new ResourceLocation(Data.MODID + ":textures/trains/logging-car-all.png")));
			loadedModel = new UERITechne(new RenderTransform(new Vector3f(), new Vector3f(0, -90, 0), new Vector3f()), false, new Vector3f(), new ModelLoggingCarLogs(), new ResourceLocation(Data.MODID + ":textures/trains/logging-car-all.png"));
		}
	}

	@Override
	public Printer3DRecipe getPrintingRecipe() {
		return new Printer3DRecipe(1, 2, 2, 2);
	}

	@Override
	public Printer3DMode getPrintingMode() {
		return Printer3DMode.REQUIRED_RESOURCES;
	}

	@Override
	public List<UERIRenderable> getModelOverrides(String loadedResource) {
		if(loadedResource.equalsIgnoreCase(MTResources.wood.getName())) {
			ArrayList<UERIRenderable> overrides = new ArrayList<UERIRenderable>();
			overrides.add(loadedModel);
			return overrides;
		}
		return null;
	}

}
