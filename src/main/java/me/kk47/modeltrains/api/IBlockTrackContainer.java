package me.kk47.modeltrains.api;

import net.minecraft.world.World;

public interface IBlockTrackContainer {

	public abstract ITileEntityTrackContainer createNewTileEntity(World worldIn, int meta);

}
