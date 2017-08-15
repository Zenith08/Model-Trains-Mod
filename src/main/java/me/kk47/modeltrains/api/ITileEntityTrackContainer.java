package me.kk47.modeltrains.api;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public interface ITileEntityTrackContainer{

	public abstract ItemStack[][] getInventory();

}
