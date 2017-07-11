package me.kk47.modeltrains.blocks;

import me.kk47.modeltrains.api.IBlockIndustry;
import me.kk47.modeltrains.tileentity.TileEntityIndustry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BlockIndustry extends BlockContainer implements IBlockIndustry {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockIndustry(Material materialIn) {
		super(materialIn);
	}

	public BlockIndustry(Material materialIn, MapColor color) {
		super(materialIn, color);
	
	}
	
	@Override
	protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
	    return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex();
	}
	
	@Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand){
    	IBlockState state = this.getDefaultState();
//    	System.out.println("onBlockPlaced");
    	EnumFacing direction = facing;
    	if(placer != null){
//			System.out.println("placer not null");
			if(placer.getHorizontalFacing() == EnumFacing.NORTH){
				direction = EnumFacing.NORTH;
			}else if(placer.getHorizontalFacing() == EnumFacing.SOUTH){
				direction = EnumFacing.SOUTH;
			}if(placer.getHorizontalFacing() == EnumFacing.EAST){
				direction = EnumFacing.EAST;
			}if(placer.getHorizontalFacing() == EnumFacing.WEST){
				direction = EnumFacing.WEST;
			}
		}
    	return state.withProperty(BlockTrainController.FACING, direction);
    }
	
	public abstract TileEntityIndustry createNewTileEntity(World in, int meta);
	
}
