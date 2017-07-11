package me.kk47.modeltrains.blocks;

import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.api.IBlockIndustry;
import me.kk47.modeltrains.tileentity.TileEntityIndustryForrest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockIndustryForrest extends BlockIndustry implements IBlockIndustry {

	protected BlockIndustryForrest() {
		super(Material.WOOD);
		this.setUnlocalizedName("forrest");
		this.setRegistryName("forrest");
		this.translucent=true;
		this.fullBlock=false;
		this.setCreativeTab(ModelTrains.creativeTab);
	}

	@Override
	public TileEntityIndustryForrest createNewTileEntity(World worldIn, int meta) {
		return new TileEntityIndustryForrest();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer(){
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState ibs){
		return false;
	}

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

}
