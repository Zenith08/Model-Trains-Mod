package me.kk47.modeltrains.client;

import java.awt.Color;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class ColourableTrains implements IItemColor{
	
	public ColourableTrains() {
		//Do nothing
	}
	
	@Override
	public int colorMultiplier(ItemStack stack, int tintIndex) {
		if(tintIndex == 0 && stack.getTagCompound() != null) {
			return new Color(stack.getTagCompound().getFloat("red"), stack.getTagCompound().getFloat("green"), stack.getTagCompound().getFloat("blue")).getRGB();
		}else {
			return 0;
		}
	}
}
