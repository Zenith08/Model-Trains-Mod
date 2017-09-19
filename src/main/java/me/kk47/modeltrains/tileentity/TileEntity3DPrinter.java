package me.kk47.modeltrains.tileentity;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.model.Model3DPrinter;
import me.kk47.modeltrains.crafting.Printer3DRecipe;
import me.kk47.modeltrains.items.trains.TrainRegistry;
import me.kk47.modeltrains.network.PacketPrintTrain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;

//TODO Sync Client and Server more
//TODO Mark Dirty when printing Starts
public class TileEntity3DPrinter extends TileEntity implements ITickable, IInventory{

	private Model3DPrinter model = new Model3DPrinter();
	private ResourceLocation texture = new ResourceLocation(Data.MODID, "textures/blocks/3d-printer.png");

	private NonNullList<ItemStack> inventory; //0 = Clay, 1-3 = RGB, 4 = Output

	private boolean isPrinting;
	private int printingTime; //In ticks
	private int printingTrainId;
	private boolean canPrint = true;

	public TileEntity3DPrinter() { //NBT?
		inventory = NonNullList.withSize(5, ItemStack.EMPTY);

		isPrinting = false;
	}

	@Override
	public void update() {
		//TODO Use this to sync tick and frames together!
		//TODO Use this to check client server stuff!
		if(isPrinting) {
			printingTime+=1;
//			System.out.println("Update with isPrinting = true and print time = " + printingTime);
			if(printingTime >= 2886) {
				setInventorySlotContents(4, new ItemStack(TrainRegistry.getTrain(printingTrainId).asItem(), 1));
				isPrinting = false;
//				System.out.println("Finished Print and set item");
			}
//			System.out.println("Checked Print time");
			while(model.getFrame() < printingTime*3) {
				model.updateFrame();
			}
//			System.out.println("Synchronized model with tick");
			this.markDirty();
		}else {
			
		}
		
		if(getStackInSlot(4).getCount() == 0 && model.getFrame() >= 8658) {
			canPrint = true;
			model = new Model3DPrinter();
		}
	}

	//The Renderer can call this per frame rither than per tick
	public void updateModelAnimation() {
		if(isPrinting) {
			model.updateFrame(); //TODO reset this each time we finish a print
		}
	}

	public Model3DPrinter getModel() {
		return model;
	}

	public ResourceLocation getTexture() {
		return texture;
	}

	//Packet Handling ----------------------------------------------------
	PacketPrintTrain lastPacket = new PacketPrintTrain();
	public synchronized void handlePrintPacket(PacketPrintTrain packet){
		lastPacket = packet;
		((WorldServer) world).addScheduledTask(new Runnable() {
			@Override
			public void run() {
				tryStartPrint();
			}
		});
	}
	
	public void tryStartPrint() {
		Printer3DRecipe recipe = TrainRegistry.getTrain(lastPacket.trainRegistryID).getPrintingRecipe(lastPacket.trainRegistryID);
		
		if(getStackInSlot(4) == ItemStack.EMPTY && 
				getStackInSlot(0).getCount() >= recipe.getClay() &&
				getStackInSlot(1).getCount() >= recipe.getRed() &&
				getStackInSlot(2).getCount() >= recipe.getGreen() &&
				getStackInSlot(3).getCount() >= recipe.getBlue()) {

			isPrinting = true;
			canPrint = false;
			printingTrainId = lastPacket.trainRegistryID;

			decrStackSize(0, recipe.getClay());
			decrStackSize(1, recipe.getRed());
			decrStackSize(2, recipe.getGreen());
			decrStackSize(3, recipe.getBlue());

			printingTime = 0;
//			System.out.println("Print started");
		}
	}

	public boolean canPrint() {
		return canPrint;
	}
	
	public boolean isPrinting() {
		return isPrinting;
	}

	//Lets us save read and write time.
	//TODO Implement
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.printingTime = compound.getInteger("PrintingTime");
		this.isPrinting = compound.getBoolean("PrintingTime");
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.printingTrainId = compound.getInteger("PrintingTrainId");
		this.canPrint = compound.getBoolean("CanPrint");
		
		/*        this.furnaceItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.furnaceItemStacks);
        this.furnaceBurnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks.get(1));

        if (compound.hasKey("CustomName", 8))
        {
            this.furnaceCustomName = compound.getString("CustomName");
        }*/
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setInteger("PrintingTime", this.printingTime);
		compound.setBoolean("IsPrinting", isPrinting);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		compound.setInteger("PrintingTrainId", printingTrainId);
		compound.setBoolean("CanPrint", canPrint);
		
		/*       compound.setInteger("BurnTime", (short)this.furnaceBurnTime);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.furnaceItemStacks);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.furnaceCustomName);
        }*/
		return compound;
	}
	
	//Code added by IInventory

	@Override
	public int getField(int id) {
		if(id == 0) {
			if(isPrinting) {
				return 1;
			}else {
				return 0;
			}
		}else if(id == 1) {
			return printingTime;
		}else if(id == 2) {
			return printingTrainId;
		}
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		if(id == 0) {
			if(value == 1) {
				isPrinting = true;
			}else {
				isPrinting = false;
			}
		}else if(id == 1) {
			printingTime = value;
		}else if(id == 2) {
			printingTrainId = value;
		}

	}

	@Override
	public int getFieldCount() {
		return 3; //3 fields are in use. Field 0, Field 1, and Field 2.
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
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i) != ItemStack.EMPTY)
				return false;
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
			return ItemStack.EMPTY;
		return this.inventory.get(index);
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

		this.inventory.set(index, stack);
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
