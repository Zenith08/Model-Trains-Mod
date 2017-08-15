package me.kk47.modeltrains.items;

import me.kk47.modeltrains.blocks.BlockTrainController;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockTrainController extends ItemBlock{

	public ItemBlockTrainController(Block block) {
		super(block);
		setRegistryName("traincontroller");
	}

	/**
	 * Called to actually place the block, after the location is determined
	 * and all permission checks have been made.
	 *
	 * @param stack The item stack that was used to place the block. This can be changed inside the method.
	 * @param player The player who is placing the block. Can be null if the block is not being placed by a player.
	 * @param side The side the player (or machine) right-clicked on.
	 */
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState){
//		System.out.println("Placeblockat called");
		EnumFacing direction = EnumFacing.NORTH;
		if(player != null){
//			System.out.println("Player not null");
			if(player.getHorizontalFacing() == EnumFacing.NORTH){
				direction = EnumFacing.NORTH;
			}else if(player.getHorizontalFacing() == EnumFacing.SOUTH){
				direction = EnumFacing.SOUTH;
			}if(player.getHorizontalFacing() == EnumFacing.EAST){
				direction = EnumFacing.EAST;
			}if(player.getHorizontalFacing() == EnumFacing.WEST){
				direction = EnumFacing.WEST;
			}
		}
		if (!world.setBlockState(pos, newState.withProperty(BlockTrainController.FACING, direction), 3)) return false;

		IBlockState state = world.getBlockState(pos);
		if (state.getBlock() == this.block)
		{
			setTileEntityNBT(world, player, pos, stack);
			this.block.onBlockPlacedBy(world, pos, state, player, stack);
		}

		return true;
	}

}
