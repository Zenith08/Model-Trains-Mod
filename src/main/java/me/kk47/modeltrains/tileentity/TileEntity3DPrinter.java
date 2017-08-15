package me.kk47.modeltrains.tileentity;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.Model3DPrinter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;

public class TileEntity3DPrinter extends TileEntity implements ITickable, IInventory{

	private Model3DPrinter model = new Model3DPrinter();
	private ResourceLocation texture = new ResourceLocation(Data.MODID, "textures/blocks/3d-printer.png");
	
	private ItemStack[] inventory; //0 = Clay, 1-3 = RGB, 4 = Output
	
	boolean isPrinting;
	
	public TileEntity3DPrinter() { //NBT?
		inventory = new ItemStack[5];
		for(int i = 0; i < inventory.length; i++) {
			inventory[i] = ItemStack.EMPTY;
		}
		isPrinting = false;
	}

	@Override
	public void update() {
		//TODO Use this to sync tick and frames together!
		//TODO Use this to check client server stuff!
	}
	
	//The Renderer can call this per frame rither than per tick
	public void updateModelAnimation() {
		model.updateFrame(); //TODO reset this each frame
	}

	public Model3DPrinter getModel() {
		return model;
	}

	public ResourceLocation getTexture() {
		return texture;
	}

	//Code added by IInventory
	
	public static final int PRINTING_KEY = 0; //To be used with get and setField().
	public static final int TIME_KEY = 1;
	
	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		return 2; //2 fields are in use. Field 0 and Field 1.
	}
	
	@Override
	public String getName() {
		return "3D Printer";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public boolean isEmpty() {
		for(int i = 0; i < inventory.length; i++) {
			if(inventory[i] != ItemStack.EMPTY)
				return false;
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
			return ItemStack.EMPTY;
		return this.inventory[index];
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
		ItemStack stack = this.getStackInSlot(index);
		this.setInventorySlotContents(index, ItemStack.EMPTY);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory())
			return;

		if (stack != ItemStack.EMPTY && stack.getCount() > this.getInventoryStackLimit())
			stack.setCount(this.getInventoryStackLimit());

		if (stack != ItemStack.EMPTY && stack.getCount() == 0)
			stack = ItemStack.EMPTY;

		this.inventory[index] = stack;
		this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64; //Actually Per Slot
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {} //Not important

	@Override
	public void closeInventory(EntityPlayer player) {} //Not important

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(index == 0 && stack.getUnlocalizedName().equalsIgnoreCase(Items.CLAY_BALL.getUnlocalizedName())) {
			return true;
		}else if(index == 1 && stack.getItem() == Items.DYE && stack.getItemDamage() == 1) { //Rose Red
			return true;
		}else if(index == 2 && stack.getItem() == Items.DYE && stack.getItemDamage() == 2) { //Cactus Green
			return true;
		}else if(index == 3 && stack.getItem() == Items.DYE && stack.getItemDamage() == 4) { //Lapis Lazuli
			return true;
		}else if(index == 4) {
			return false;
		}
		return false;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.getSizeInventory(); i++)
			this.setInventorySlotContents(i, ItemStack.EMPTY);
	}

}
