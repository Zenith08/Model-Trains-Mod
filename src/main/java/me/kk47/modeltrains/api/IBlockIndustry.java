package me.kk47.modeltrains.api;

import net.minecraft.world.World;

public interface IBlockIndustry {

	public abstract ITileEntityIndustry createNewTileEntity(World worldIn, int meta);
	
}
