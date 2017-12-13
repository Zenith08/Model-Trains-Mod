package me.kk47.modeltrains.tileentity;

import me.kk47.modeltrains.api.IItemTrainLoadable;
import me.kk47.modeltrains.api.ITileEntityIndustry;
import me.kk47.modeltrains.api.ITileEntityTrackContainer;
import me.kk47.modeltrains.blocks.BlockTrainController;
import me.kk47.modeltrains.industry.MTResource;
import me.kk47.modeltrains.industry.MTResources;
import me.kk47.modeltrains.items.ModItems;
import me.kk47.modeltrains.math.MathHelper;
import me.kk47.modeltrains.train.RollingStock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public abstract class TileEntityIndustry extends TileEntity implements ITileEntityIndustry, ITileEntityTrackContainer, ITickable{

	public static ItemStack TRACK_NORTH = new ItemStack(ModItems.trackStraight, 1, 0);
	public static ItemStack TRACK_EAST = new ItemStack(ModItems.trackStraight, 1, 1);
	public static ItemStack TRACK_TURN_NORTH = new ItemStack(ModItems.trackCorner, 1, 0);
	public static ItemStack TRACK_TURN_SOUTH = new ItemStack(ModItems.trackCorner, 1, 1);
	public static ItemStack TRACK_TURN_EAST = new ItemStack(ModItems.trackCorner, 1, 2);
	public static ItemStack TRACK_TURN_WEST = new ItemStack(ModItems.trackCorner, 1, 3);

	protected int masterTimeUntilProduction;
	protected int timeUntilProduction;

	protected ItemStack[][] north_inventory;
	protected ItemStack[][] east_inventory;
	protected ItemStack[][] south_inventory;
	protected ItemStack[][] west_inventory;

	protected EnumFacing facing = EnumFacing.NORTH;

	protected boolean firstTick = true;

	protected void setTimeUntilProduction(int newTime){
		this.timeUntilProduction=newTime;
		this.masterTimeUntilProduction=newTime;
	}

	/**Sets the industries track layouts for all directions
	 * You have to provide the south facing direction*/
	protected void setTrackInventory(ItemStack[][] trackSouthIn){
		this.south_inventory = trackSouthIn;
		this.west_inventory = MathHelper.rotateTrackInv(duplicateArray(south_inventory));
		this.north_inventory = MathHelper.rotateTrackInv(duplicateArray(west_inventory));
		this.east_inventory = MathHelper.rotateTrackInv(duplicateArray(north_inventory));

		/*this.north_inventory = trackNorthInv;
		east_inventory = MathHelper.rotateTrackInv(north_inventory);
		south_inventory = (ItemStack[][]) MathHelper.rotateTrackInv(east_inventory);
		west_inventory = (ItemStack[][]) MathHelper.rotateTrackInv(south_inventory);*/
	}

	public EnumFacing facing(){
		return facing;
	}

	private ItemStack[][] duplicateArray(ItemStack[][] inArr) {
		ItemStack[][] output = new ItemStack[4][4];
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 4; y++) {
				if(inArr[x][y] != null && inArr[x][y] != ItemStack.EMPTY) {
					output[x][y] = new ItemStack(inArr[x][y].getItem(), inArr[x][y].getCount(), inArr[x][y].getItemDamage());
				}else {
					output[x][y] = ItemStack.EMPTY;
				}
			}
		}
		return output;
	}

	@Override
	public ItemStack[][] getInventory(){
		switch(facing){
		case NORTH: return north_inventory;
		case SOUTH: return south_inventory;
		case EAST: return east_inventory;
		case WEST: return west_inventory;
		default: return south_inventory;
		}
	}

	@Override
	public void update(){
		if(firstTick)
			firstTick();

		timeUntilProduction--;
		if(timeUntilProduction == 0){
			process(); //Produce stuff, convert stuff, etc.
			timeUntilProduction = masterTimeUntilProduction;
		}
	}
	
	@Override
	public void handleRollingStock (RollingStock rs) { //Load first so we can't unload a resource and then load it again.
		loadResources(rs);
		unloadResources(rs);
	}

	private void firstTick() {
		firstTick = false;
		facing = world.getBlockState(pos).getValue(BlockTrainController.FACING);
	}

}
