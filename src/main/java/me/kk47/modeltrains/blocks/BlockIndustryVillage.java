package me.kk47.modeltrains.blocks;

import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.tileentity.TileEntityIndustry;
import me.kk47.modeltrains.tileentity.TileEntityIndustryVillage;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockIndustryVillage extends BlockIndustry {

	public BlockIndustryVillage() {
		super(Material.WOOD);
		this.setUnlocalizedName("village");
		this.setRegistryName("village");
		this.translucent=true;
		this.fullBlock=false;
		this.setCreativeTab(ModelTrains.creativeTab); //Will be re-enabled along with industry implementation
	}

	@Override
	public TileEntityIndustry createNewTileEntity(World in, int meta) {
		return new TileEntityIndustryVillage();
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
