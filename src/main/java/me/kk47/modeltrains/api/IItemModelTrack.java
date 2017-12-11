package me.kk47.modeltrains.api;

import java.util.List;

import me.kk47.ueri.UERIRenderable;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IItemModelTrack {

	@SideOnly(Side.CLIENT)
	List<UERIRenderable> getRenderables(ItemStack stack);

}