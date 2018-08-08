package me.kk47.modeltrains.tileentity;

import me.kk47.modeltrains.industry.MTResources;
import me.kk47.modeltrains.train.RollingStock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityIndustryVillage extends TileEntityIndustry implements IInventory{

	private int woodQty = 0;
	private ItemStack[] woodInventory;
	
	public TileEntityIndustryVillage() {
		super.setTimeUntilProduction(20);
		super.setTrackInventory(new ItemStack[][]{
		{TRACK_EAST, null, null, null},
		{TRACK_EAST, null, null, null},
		{TRACK_EAST, null, null, null},
		{TRACK_EAST, null, null, null}});
		
		woodInventory = new ItemStack[] {ItemStack.EMPTY};
	}

	@Override
	public void process() {
		if(woodQty >= 4) {
			//Convert it into actual wood.
			if(woodInventory[0] == ItemStack.EMPTY) {
				woodInventory[0] = new ItemStack(Blocks.PLANKS);
			}else {
				woodInventory[0].setCount(woodInventory[0].getCount()+1);
			}
			woodQty-=4;
			System.out.println("Wood Produced.");
		}
	}

	public int getWoodResource() {
		return woodQty;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		woodQty = compound.getInteger("wood");
		int woodItemQty = compound.getInteger("itemWood");
		woodInventory[0] = new ItemStack(Blocks.PLANKS, woodItemQty);
    }

	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("wood", woodQty);
        compound.setInteger("itemWood", woodInventory[0].getCount());
        return compound;
    }
	
	@Override
	public void loadResources(RollingStock rs) {
		//The village doesn't load any resources right now.
	}

	@Override
	public void unloadResources(RollingStock rs) {
		woodQty += rs.unload(MTResources.wood, 10);
	}

	@Override
	public String getName() {
		return "Village";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return woodInventory[0] == ItemStack.EMPTY || woodInventory[0].getCount() == 0;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
			return ItemStack.EMPTY;
		
		return woodInventory[index];
		
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.getStackInSlot(index) != ItemStack.EMPTY) {
			ItemStack itemstack;

			if (this.getStackInSlot(index).getCount() <= count) {
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, ItemStack.EMPTY);
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).getCount() <= 0) {
					this.setInventorySlotContents(index, ItemStack.EMPTY);
				} else {
					//Just to show that changes happened
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
			return ItemStack.EMPTY;
		
		int ammount = getStackInSlot(index).getCount();
		decrStackSize(index, ammount);
		return new ItemStack(getStackInSlot(index).getItem(), ammount);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		
			if (index < 0 || index >= this.getSizeInventory())
				return;

			if (stack != ItemStack.EMPTY && stack.getCount() > this.getInventoryStackLimit())
				stack.setCount(this.getInventoryStackLimit());
			if (stack != ItemStack.EMPTY && stack.getCount() == 0)
				stack = ItemStack.EMPTY;

			woodInventory[index] = stack;
			this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return stack.getItem() == Item.getItemFromBlock(Blocks.PLANKS);
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for(int i = 0; i < woodInventory.length; i++) {
			woodInventory[i] = ItemStack.EMPTY;
		}
	}

}
