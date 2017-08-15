package me.kk47.modeltrains.blocks;

import me.kk47.modeltrains.ModelTrains;
import me.kk47.modeltrains.tileentity.TileEntity3DPrinter;
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
			//Open GUI
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

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

}
