package me.kk47.modeltrains.blocks;

import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.gui.MTGuiHandler;
import me.kk47.modeltrains.tileentity.TileEntityIndustry;
import me.kk47.modeltrains.tileentity.TileEntityIndustryVillage;
import net.minecraft.block.material.MapColor;
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

public class BlockIndustryVillage extends BlockIndustry {

	public BlockIndustryVillage() {
		super(Material.WOOD);
		this.setUnlocalizedName("village");
		this.setRegistryName("village");
		this.translucent=true;
		this.fullBlock=false;
		this.setCreativeTab(ModelTrains.creativeTab);
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
    
    @Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
		if (!worldIn.isRemote) {
			playerIn.openGui(ModelTrains.instance, MTGuiHandler.GUI_INDUSTRY_VILLAGE, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
    
}
