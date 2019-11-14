package me.kk47.modeltrains.blocks;

import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.gui.MTGuiHandler;
import me.kk47.modeltrains.tileentity.TileEntity3DPrinter;
import me.kk47.modeltrains.tileentity.TileEntityTrackBed;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Block3DPrinter extends BlockContainer {

	private static final String name = "printer-3d";
	
	public Block3DPrinter() {
		super(Material.CIRCUITS);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setHardness(1.0f);
		this.setResistance(3.0f);
		this.setCreativeTab(ModelTrains.creativeTab);
		this.fullBlock = false;
		this.translucent = true;
		this.setLightOpacity(0);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
		if (!worldIn.isRemote) {
			playerIn.openGui(ModelTrains.instance, MTGuiHandler.GUI_PRINTER_3D, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}	
		return true;
	}

	@Override
	public TileEntity3DPrinter createNewTileEntity(World worldIn, int meta) {
		return new TileEntity3DPrinter();
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

	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    @Override
	public void breakBlock(World world, BlockPos pos, IBlockState blockstate) {
		TileEntity3DPrinter te = (TileEntity3DPrinter) world.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(world, pos, te);
		super.breakBlock(world, pos, blockstate);
	}

}
