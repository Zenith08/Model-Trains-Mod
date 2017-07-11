package me.kk47.modeltrains.tileentity;

import javax.annotation.Nullable;

import me.kk47.modeltrains.MTConfig;
import me.kk47.modeltrains.api.ITileEntityTrackContainer;
import me.kk47.modeltrains.blocks.ModBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileEntityTrackBed extends TileEntity implements ITileEntityTrackContainer, IInventory, ITickable{

	private ItemStack[][] inventory;

	public TileEntityTrackBed(){
		this.inventory = new ItemStack[4][4];
	}

	@Override
	public ItemStack[][] getInventory(){
		//		System.out.println("getting inventory 2d");
		return inventory;
	}

	//Called on Server side
	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket(){
		//		System.out.println("<Server> sending update packet");
		return new SPacketUpdateTileEntity(this.pos, 0, getUpdateTag());
	}

	//Server
	public NBTTagCompound getUpdateTag(){
		//		System.out.println("<Server> get update tag");
		NBTTagCompound syncData = new NBTTagCompound();
		this.writeSyncableDataToNBT(syncData);
		return syncData;
	}

	//Client
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
		//		System.out.println("<Client> recieved packet");
		readSyncableDataFromNBT(pkt.getNbtCompound());
	}

	@Override
	public String getName() {
		return "Track Bed";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 16;
	}
	//default implementation
	/*	@Override
	public ItemStack getStackInSlot(int index) {
	    if (index < 0 || index >= this.getSizeInventory())
	        return null;
	    return this.inventory[index];
	}*/
	//2d implementation
	@Override
	public ItemStack getStackInSlot(int index){
		if (index < 0 || index >= this.getSizeInventory())
			return null;
		int x = 0;
		int y = index;
		while(y >= 4){
			x++;
			y-=4;
		}
		return inventory[x][y];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		if (this.getStackInSlot(index) != null) {
			ItemStack itemstack;

			if (this.getStackInSlot(index).getCount() <= count) {
				itemstack = this.getStackInSlot(index);
				this.setInventorySlotContents(index, null);
				this.markDirty();
				return itemstack;
			} else {
				itemstack = this.getStackInSlot(index).splitStack(count);

				if (this.getStackInSlot(index).getCount() <= 0) {
					this.setInventorySlotContents(index, null);
				} else {
					//Just to show that changes happened
					this.setInventorySlotContents(index, this.getStackInSlot(index));
				}

				this.markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return decrStackSize(index, 1);
	}

	//default implementation
	/*	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
	    if (index < 0 || index >= this.getSizeInventory())
	        return;

	    if (stack != null && stack.stackSize > this.getInventoryStackLimit())
	        stack.stackSize = this.getInventoryStackLimit();

	    if (stack != null && stack.stackSize == 0)
	        stack = null;

	    this.inventory[index] = stack;
	    this.markDirty();
	}*/

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		if (index < 0 || index >= this.getSizeInventory())
			return;

		if (stack != null && stack.getCount() > this.getInventoryStackLimit())
			stack.setCount(this.getInventoryStackLimit());
		if (stack != null && stack.getCount() == 0)
			stack = null;

		int x = 0;
		int y = index;
		while(y >= 4){
			x++;
			y-=4;
		}
		inventory[x][y] = stack;

		this.markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	//Client / Server syncing part 2
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
	//End Client / Server syncing

	@Override
	public void clear() {
		for (int i = 0; i < this.getSizeInventory(); i++)
			this.setInventorySlotContents(i, null);
	}

	public NBTTagCompound writeSyncableDataToNBT(NBTTagCompound nbt){
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.getSizeInventory(); ++i) {
			if (this.getStackInSlot(i) != null) {
				NBTTagCompound stackTag = new NBTTagCompound();
				stackTag.setByte("Slot", (byte) i);
				this.getStackInSlot(i).writeToNBT(stackTag);
				list.appendTag(stackTag);
			}
		}
		nbt.setTag("Items", list);
		return nbt;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		this.writeSyncableDataToNBT(nbt);
		return nbt;
	}

	public void readSyncableDataFromNBT(NBTTagCompound nbt){
		NBTTagList list = nbt.getTagList("Items", 10);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound stackTag = list.getCompoundTagAt(i);
			int slot = stackTag.getByte("Slot") & 255;
			this.setInventorySlotContents(slot, new ItemStack(stackTag));
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.readSyncableDataFromNBT(nbt);
	}

	private byte lastUpdate = MTConfig.TRACKBED_UPDATE_RATE;

	@Override
	public void update() {
		lastUpdate--;
		if(lastUpdate == 0){
			lastUpdate = MTConfig.TRACKBED_UPDATE_RATE;
			world.notifyBlockUpdate(pos, ModBlocks.trackBed.getDefaultState(), ModBlocks.trackBed.getDefaultState(), 1);
			this.markDirty();
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

}
