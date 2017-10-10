package me.kk47.modeltrains.tileentity;

import javax.annotation.Nullable;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.MTConfig;
import me.kk47.modeltrains.api.IItemTrain;
import me.kk47.modeltrains.blocks.ModBlocks;
import me.kk47.modeltrains.client.model.Model3DPrinter;
import me.kk47.modeltrains.crafting.Printer3DMode;
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
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;

public class TileEntity3DPrinter extends TileEntity implements ITickable, IInventory{

	private Model3DPrinter model = new Model3DPrinter();
	private ResourceLocation texture = new ResourceLocation(Data.MODID, "textures/blocks/3d-printer.png");

	private NonNullList<ItemStack> inventory; //0 = Clay, 1-3 = RGB, 4 = Output

	private boolean isPrinting;
	private int printingTime; //In ticks
	private int printingTrainId;
	private NBTTagCompound printingTrainNBT;
	private boolean canPrint = true;

	public TileEntity3DPrinter() {
		inventory = NonNullList.withSize(5, ItemStack.EMPTY);

		isPrinting = false;
	}

	private byte lastUpdate = MTConfig.TRACKBED_UPDATE_RATE;

	@Override
	public void update() {
		if(isPrinting) {
			printingTime+=1;
			//			System.out.println("Update with isPrinting = true and print time = " + printingTime);
			if(printingTime >= 2886) {
				completePrint();
				//				System.out.println("Finished Print and set item");
			}
			//			System.out.println("Checked Print time");
			while(model.getFrame() < printingTime*3) {
				model.updateFrame();
			}
			//System.out.println("Synchronized model with tick");
			this.markDirty();
		}else {

		}

		if(getStackInSlot(4).getCount() == 0 && model.getFrame() >= 8658) {
			canPrint = true;
			model = new Model3DPrinter();
		}

		//Synchronization stuff
		lastUpdate--;
		if(lastUpdate == 0){
			lastUpdate = MTConfig.TRACKBED_UPDATE_RATE;
			world.notifyBlockUpdate(pos, ModBlocks.trackBed.getDefaultState(), ModBlocks.trackBed.getDefaultState(), 1);
			this.markDirty();
		}
	}

	private void completePrint() {
		ItemStack printedTrain = new ItemStack(TrainRegistry.getTrain(printingTrainId).asItem(), 1);
		if(printingTrainNBT != null) {
			printedTrain.setTagCompound(printingTrainNBT);
		}

		setInventorySlotContents(4, printedTrain);
		isPrinting = false;
	}

	//The Renderer can call this per frame rather than per tick
	public void updateModelAnimation() {
		if(isPrinting) {
			model.updateFrame();
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
		//		System.out.println("Handle Printing Packet");
		lastPacket = packet;
		((WorldServer) world).addScheduledTask(new Runnable() {
			@Override
			public void run() {
				//				System.out.println("Attempting to start print");
				tryStartPrint();
			}
		});
	}

	//Client Server Synchronization

	//Called on Server side
	@Nullable
	@Override
	public SPacketUpdateTileEntity getUpdatePacket(){
		return new SPacketUpdateTileEntity(this.pos, 0, getUpdateTag());
	}

	//Server
	@Override
	public NBTTagCompound getUpdateTag(){
		NBTTagCompound syncData = new NBTTagCompound();
		syncData.setInteger("PrintingTime", printingTime);
		syncData.setBoolean("IsPrinting", isPrinting);
		syncData.setInteger("PrintingTrainId", printingTrainId);
		syncData.setBoolean("CanPrint", canPrint);

		if(printingTrainNBT != null) {
			syncData.setTag("PrintingTrainNBT", printingTrainNBT);
		}

		return syncData;
	}

	//Client
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
		NBTTagCompound compound = pkt.getNbtCompound();

		printingTime = compound.getInteger("PrintingTime");
		isPrinting = compound.getBoolean("IsPrinting");
		printingTrainId = compound.getInteger("PrintingTrainId");
		canPrint = compound.getBoolean("CanPrint");
		if(compound.hasKey("PrintingTrainNBT")) {
			printingTrainNBT = compound.getCompoundTag("PrintingTrainNBT");
		}else {
			printingTrainNBT = null;
		}
	}

	public void tryStartPrint() {
		IItemTrain possiblePrint = TrainRegistry.getTrain(lastPacket.trainRegistryID);
		Printer3DRecipe recipe = possiblePrint.getPrintingRecipe();

		if(possiblePrint.getPrintingMode() == Printer3DMode.REQUIRED_RESOURCES) {
			startRequiredPrint(recipe);
		}else if(possiblePrint.getPrintingMode() == Printer3DMode.VARIABLE_COLOUR) {
			startColouredPrint(recipe);
		}
	}

	private void startColouredPrint(Printer3DRecipe recipe) {
		if(getStackInSlot(4) == ItemStack.EMPTY && 
				getStackInSlot(0).getCount() >= recipe.getClay() &&
				getStackInSlot(1).getCount() >= 1 && //Red
				getStackInSlot(2).getCount() >= 1 && //Blue
				getStackInSlot(3).getCount() >= 1 //Green
				) {

			//Determines colouring based on stack size
			int red, green, blue;

			//Determines Red Colouring
			if(getStackInSlot(1).getCount() > 5) {
			//	System.out.println("Red is > 5");
				red = 5;
			}else {
				red = getStackInSlot(1).getCount();
			}
			
			//Determines Blue Colouring
			if(getStackInSlot(2).getCount() > 5) {
			//	System.out.println("Blue is > 5");
				blue = 5;
			}else {
				blue = getStackInSlot(2).getCount();
			}
			
			//Determines Green Colouring
			if(getStackInSlot(3).getCount() > 5) {
			//	System.out.println("Green is > 5");
				green = 5;
			}else {
				green = getStackInSlot(3).getCount();
			}
			
			//System.out.println("Starting print with rgb: " + red + ", " + green + ", " + blue);

			isPrinting = true;
			canPrint = false;
			printingTrainId = lastPacket.trainRegistryID;
			printingTrainNBT = new NBTTagCompound();

			printingTrainNBT.setFloat("red", (red-1)*0.25F);
			printingTrainNBT.setFloat("green", (green-1)*0.25F);
			printingTrainNBT.setFloat("blue", (blue-1)*0.25F);

			decrStackSize(0, recipe.getClay());
			decrStackSize(1, red);
			decrStackSize(2, blue);
			decrStackSize(3, green);

			printingTime = 0;

			this.markDirty();
		}
	}

	private void startRequiredPrint(Printer3DRecipe recipe) {
		//This is the code for required resources
		if(getStackInSlot(4) == ItemStack.EMPTY && 
				getStackInSlot(0).getCount() >= recipe.getClay() &&
				getStackInSlot(1).getCount() >= recipe.getRed() &&
				getStackInSlot(2).getCount() >= recipe.getGreen() &&
				getStackInSlot(3).getCount() >= recipe.getBlue()
				) {

			isPrinting = true;
			canPrint = false;
			printingTrainId = lastPacket.trainRegistryID;
			printingTrainNBT = null;

			decrStackSize(0, recipe.getClay());
			decrStackSize(1, recipe.getRed());
			decrStackSize(2, recipe.getGreen());
			decrStackSize(3, recipe.getBlue());

			printingTime = 0;

			this.markDirty(); //The printing can start so save that it has started
		}
	}

	public boolean canPrint() {
		return canPrint;
	}

	public boolean isPrinting() {
		return isPrinting;
	}

	//Lets us save read and write printing time and other stuff
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.printingTime = compound.getInteger("PrintingTime");
		this.isPrinting = compound.getBoolean("IsPrinting");
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.printingTrainId = compound.getInteger("PrintingTrainId");
		this.canPrint = compound.getBoolean("CanPrint");

		if(compound.hasKey("PrintingTrainNBT")) {
			this.printingTrainNBT = compound.getCompoundTag("PrintingTrainNBT");
		}
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

		if(printingTrainNBT != null) {
			compound.setTag("PrintingTrainNBT", printingTrainNBT);
		}

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
