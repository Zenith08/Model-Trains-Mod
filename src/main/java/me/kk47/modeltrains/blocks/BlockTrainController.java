package me.kk47.modeltrains.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.api.IBlockTrackContainer;
import me.kk47.modeltrains.api.ITileEntityTrackContainer;
import me.kk47.modeltrains.gui.MTGuiHandler;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTrainController extends Block implements IBlockTrackContainer, ITileEntityProvider{

	private static final String name = "traincontroller";
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BlockTrainController() {
		super(Material.CIRCUITS);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setHardness(1.0f);
		this.setResistance(3.0f);
		this.setCreativeTab(ModelTrains.creativeTab);
		this.fullBlock = false;
		this.isBlockContainer = true;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
		if (!worldIn.isRemote) {
//			System.out.println("Displaying all available data on player#opengui: Mod " + ModelTrains.instance + " ID " + MTGuiHandler.GUI_TRAINCONTROLLER_ID + " worldIn " + worldIn + " position " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
			playerIn.openGui(ModelTrains.instance, MTGuiHandler.GUI_TRAINCONTROLLER_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
	    TileEntityTrainController te = (TileEntityTrainController) world.getTileEntity(pos);
	    InventoryHelper.dropInventoryItems(world, pos, te);
	    super.breakBlock(world, pos, blockstate);
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
	public TileEntityTrainController createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTrainController();
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
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state){
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state){
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Nullable
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(ModBlocks.trainController);
    }
}
