package me.kk47.modeltrains.items.trains;

import java.util.ArrayList;
import java.util.List;

import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.api.IItemTrain;
import me.kk47.ueri.UERIMod;
import me.kk47.ueri.UERIRenderable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemTrain extends Item implements IItemTrain {

	//	protected ModelBase model;
	//	protected ResourceLocation texture;
	@SideOnly(Side.CLIENT)
	protected List<UERIRenderable> renderables;
	protected EnumTrainType type;
	protected final int trainRegistryID;

	/**This is just a default, you can change it.*/
	protected float size = 0.5F;

	public ItemTrain(EnumTrainType trainType, String registryName, int trainRegistryID){
		this.type = trainType;
		this.setRegistryName(registryName);
		this.setUnlocalizedName(registryName);
		this.setCreativeTab(ModelTrains.creativeTab);
		this.trainRegistryID = trainRegistryID;
		TrainRegistry.registerTrain(this);
		if(UERIMod.isClientSided) {
			renderables = new ArrayList<UERIRenderable>();
		}
	}

	@Override
	public EnumTrainType getTrainType(){
		return type;
	}

	@Override
	public int getTrainRegistryID(){
		return trainRegistryID;
	}

	public void addUERI(UERIRenderable renderable) {
		renderables.add(renderable);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public List<UERIRenderable> getRenderables(ItemStack stack) {
		return renderables;
	}

	/*	@Override
	public ModelBase getModel(){
		return model;
	}

	protected void setModel(ModelBase model){
		this.model = model;
	}

	@Override
	public ResourceLocation getTexture(){
		return texture;
	}*/

	@Override
	public float getSize(){
		return size+0.1F;
	}

	protected void setSize(float size){
		this.size = size;
	}

	/*	protected void setTexture(ResourceLocation texture){
		this.texture = texture;
	}*/

	public Item asItem() {
		return this;
	}

}
