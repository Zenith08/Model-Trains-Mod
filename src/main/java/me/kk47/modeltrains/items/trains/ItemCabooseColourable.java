package me.kk47.modeltrains.items.trains;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.api.IItemTrain;
import me.kk47.modeltrains.client.model.ModelCaboose;
import me.kk47.modeltrains.crafting.Printer3DMode;
import me.kk47.modeltrains.crafting.Printer3DRecipe;
import me.kk47.ueri.UERIRenderable;
import me.kk47.ueri.UERITechne;
import me.kk47.ueri.util.RenderTransform;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ItemCabooseColourable extends Item implements IItemColor, IItemTrain {

	public ItemCabooseColourable() {
		super();
		this.setRegistryName(Data.MODID, "coloured-caboose");
		this.setUnlocalizedName("coloured-caboose");
		this.setCreativeTab(ModelTrains.creativeTab);
		TrainRegistry.registerTrain(this);
		this.setMaxStackSize(1);
	}

	@Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
        	items.add(generateItemStack(1, 0, 0)); //Red
        	items.add(generateItemStack(0, 1, 0)); //Green
        	items.add(generateItemStack(0, 0, 1)); //Blue
        	items.add(generateItemStack(1, 1, 0)); //Yellow
        	
        	items.add(generateItemStack(0, 1, 1)); //Light Blue
        	items.add(generateItemStack(1, 0, 1)); //Magenta
        	items.add(generateItemStack(1, 1, 1)); //White
        }
    }
	
	private ItemStack generateItemStack(float r, float g, float b) {
    	ItemStack stack = new ItemStack(this);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setFloat("red", r);
		compound.setFloat("green", g);
		compound.setFloat("blue", b);
		stack.setTagCompound(compound);
		return stack;
	}
	
	@Override
	public EnumTrainType getTrainType() {
		return EnumTrainType.CARRIAGE_PASSENGER;
	}

	@Override
	public int getTrainRegistryID() {
		return 41;
	}

	List<UERIRenderable> out;
	@Override
	public List<UERIRenderable> getRenderables(ItemStack stack) {
		out = new ArrayList<UERIRenderable>();
		if(stack.getTagCompound() != null) {
			out.add(new UERITechne(new RenderTransform(), true, new Vector3f(stack.getTagCompound().getFloat("red"), stack.getTagCompound().getFloat("green"), stack.getTagCompound().getFloat("blue")), new ModelCaboose(), new ResourceLocation(Data.MODID + ":textures/trains/train-coloured-grey.png")));
			out.add(new UERITechne(new ModelCaboose(), new ResourceLocation(Data.MODID + ":textures/trains/train-coloured-static.png")));
		}else {
			out.add(new UERITechne(new ModelCaboose(), new ResourceLocation(Data.MODID + ":textures/trains/train-coloured-grey.png")));
		}
		return out;
	}

	@Override
	public float getSize() {
		return 0.5F;
	}

	@Override
	public Printer3DRecipe getPrintingRecipe(int trainRegistryID) {
		return new Printer3DRecipe((byte) 1);
	}

	@Override
	public Item asItem() {
		return this;
	}

	@Override
	public int getColorFromItemstack(ItemStack stack, int tintIndex) {
		if(tintIndex == 0 && stack.getTagCompound() != null) {
			return new Color(stack.getTagCompound().getFloat("red"), stack.getTagCompound().getFloat("green"), stack.getTagCompound().getFloat("blue")).getRGB();
		}else {
			return 0;
		}
	}

	@Override
	public Printer3DMode getPrintingMode() {
		return Printer3DMode.VARIABLE_COLOUR;
	}

}
