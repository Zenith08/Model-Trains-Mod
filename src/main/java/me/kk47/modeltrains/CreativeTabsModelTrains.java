package me.kk47.modeltrains;

import me.kk47.modeltrains.items.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsModelTrains extends CreativeTabs{

	public CreativeTabsModelTrains() {
		super("modeltrainsTab");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.trackStraight, 1, 1);
	}
	
}
