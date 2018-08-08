package me.kk47.modeltrains.blocks;

import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.api.IBlockIndustry;
import me.kk47.modeltrains.gui.MTGuiHandler;
import me.kk47.modeltrains.tileentity.TileEntityIndustryForrest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
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
		this.setCreativeTab(ModelTrains.creativeTab); //Will be re-enabled along with industry implementation
	}

	@Override
	public TileEntityIndustryForrest createNewTileEntity(World worldIn, int meta) {
		return new TileEntityIndustryForrest();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
		if (!worldIn.isRemote) {
			playerIn.openGui(ModelTrains.instance, MTGuiHandler.GUI_INDUSTRY_FORREST, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}	
		return true;
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
