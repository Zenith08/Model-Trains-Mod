package me.kk47.modeltrains.tileentity;

import javax.annotation.Nullable;

import me.kk47.modeltrains.api.IItemModelTrack;
import me.kk47.modeltrains.api.IItemTrain;
import me.kk47.modeltrains.api.ITileEntityTrackContainer;
import me.kk47.modeltrains.api.ITileEntityTrainContainer;
import me.kk47.modeltrains.blocks.BlockTrainController;
import me.kk47.modeltrains.blocks.ModBlocks;
import me.kk47.modeltrains.items.ModItems;
import me.kk47.modeltrains.math.MathHelper;
import me.kk47.modeltrains.math.Position3F;
import me.kk47.modeltrains.network.PacketChangeTrainDirection;
import me.kk47.modeltrains.network.PacketChangeTrainSpeed;
import me.kk47.modeltrains.train.RollingStock;
import me.kk47.modeltrains.train.RollingStockPullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class TileEntityTrainController extends TileEntity implements ITileEntityTrackContainer, IInventory, ITickable, ITileEntityTrainContainer{

	public static final int BLOCK_OFFSET = 32;

	//The static stuff controlls the basic inventory of tracks and will always be the same.
	public static final ItemStack TRACK_NORTH = new ItemStack(ModItems.trackStraight, 1, 0);
	public static final ItemStack TRACK_EAST = new ItemStack(ModItems.trackStraight, 1, 1);

	public static final ItemStack[][] WEST_TRACKS = new ItemStack[][]{{TRACK_NORTH, TRACK_NORTH, TRACK_NORTH, TRACK_NORTH},
		{null, null, null, null},
		{null, null, null, null},
		{null, null, null, null}};

	public static final ItemStack[][] EAST_TRACKS = new ItemStack[][]{{null, null, null, null},
		{null, null, null, null},
		{null, null, null, null},
		{TRACK_NORTH, TRACK_NORTH, TRACK_NORTH, TRACK_NORTH}};

	public static final ItemStack[][] SOUTH_TRACKS = new ItemStack[][]{{null, null, null, TRACK_EAST},
		{null, null, null, TRACK_EAST},
		{null, null, null, TRACK_EAST},
		{null, null, null, TRACK_EAST}};

	public static final ItemStack[][] NORTH_TRACKS = new ItemStack[][]{{TRACK_EAST, null, null, null},
		{TRACK_EAST, null, null, null},
		{TRACK_EAST, null, null, null},
		{TRACK_EAST, null, null, null}};
	//x-1 for each position
	public static final Position3F[] NORTH_START_POS = new Position3F[]{new Position3F(BLOCK_OFFSET*4 + 0.5F-1, BLOCK_OFFSET*4 + 0.5F, 270.0F),
		new Position3F(BLOCK_OFFSET*4 + 0.5F-2, BLOCK_OFFSET*4 + 0.5F, 270.0F), new Position3F(BLOCK_OFFSET*4 + 0.5F-3, BLOCK_OFFSET*4 + 0.5F, 270.0F),
		new Position3F(BLOCK_OFFSET*4 + 0.5F-4, BLOCK_OFFSET*4 + 0.5F, 270.0F)};

	public static final Position3F[] SOUTH_START_POS = new Position3F[]{new Position3F(BLOCK_OFFSET*4 + 0.5F-4, BLOCK_OFFSET*4 + 3.5F, 90.0F), 
		new Position3F(BLOCK_OFFSET*4 + 0.5F-3, BLOCK_OFFSET*4 + 3.5F, 90.0F), new Position3F(BLOCK_OFFSET*4 + 0.5F-2, BLOCK_OFFSET*4 + 3.5F, 90.0F),
		new Position3F(BLOCK_OFFSET*4 + 0.5F-1, BLOCK_OFFSET*4 + 3.5F, 90.0F)};

	public static final Position3F[] EAST_START_POS = new Position3F[]{new Position3F(BLOCK_OFFSET*4 + 0.5F-1, BLOCK_OFFSET*4 + 3.5F, 180.0F),
		new Position3F(BLOCK_OFFSET*4 + 0.5F-1, BLOCK_OFFSET*4 + 2.5F, 180.0F), new Position3F(BLOCK_OFFSET*4 + 0.5F-1, BLOCK_OFFSET*4 + 1.5F, 180.0F),
		new Position3F(BLOCK_OFFSET*4 + 0.5F-1, BLOCK_OFFSET*4 + 0.5F, 180.0F)};

	public static final Position3F[] WEST_START_POS = new Position3F[]{new Position3F(BLOCK_OFFSET*4 + 0.5F-4, BLOCK_OFFSET*4 + 0.5F, 0.0F),
		new Position3F(BLOCK_OFFSET*4 + 0.5F-4, BLOCK_OFFSET*4 + 1.5F, 0.0F), new Position3F(BLOCK_OFFSET*4 + 0.5F-4, BLOCK_OFFSET*4 + 2.5F, 0.0F),
		new Position3F(BLOCK_OFFSET*4 + 0.5F-4, BLOCK_OFFSET*4 + 3.5F, 0.0F)};

	public static final float[] speeds = new float[]{0.00F, 0.01F, 0.03F, 0.05F, 0.07F, 0.09F, 0.10F};
	//The static stuff ends here -------------------------------------------------------------

	//The direction the TE's block is facing - North is a default but it is set in the first tick
	private EnumFacing direction = EnumFacing.NORTH;

	private RollingStock[] trains = new RollingStock[4]; //Each train is initalized in initTrain() called by firstTick()

	private ItemStack[] inventory = new ItemStack[this.getSizeInventory()];

	/**Is the train going backwards? Yes? then this is false*/
	private boolean forwards = true;
	/**Train's Speed - get it with speeds[this value]*/
	private byte speed = 0;

	/**The position is logical 0, 0*/
	private BlockPos startPos;

	/**It's in the name*/
	private boolean firstTick;

	public TileEntityTrainController(){
		firstTick = true; 
		for(int i = 0; i < trains.length; i++){
			if(i==0){
				trains[i] = new RollingStock(new Position3F(), this);
			}else{
				RollingStockPullable next = new RollingStockPullable(new Position3F(), this);
				next.setCartAhead(trains[i-1]);
				trains[i] = next;
			}
		}
		for(int i = 0; i < inventory.length; i++) {
			inventory[i] = ItemStack.EMPTY;
		}
	}

	@Override
	public int getDirection(){
		if(forwards)
			return 1;
		return -1;
	}

	@Override
	public boolean getDirectionValue(){
		return forwards;
	}

	@Override
	public float getSpeed(){
		return speeds[speed]*getDirection();
	}

	@Override
	public byte getSpeedValue(){
		return speed;
	}

	@Override
	public void setSpeed(byte speed){
		this.speed = speed;
	}

	@Override
	public RollingStock[] getTrains(){
		return trains;
	}

	@Override
	public boolean isTrack(Position3F pos){
		try{
			int x = 0;
			float spareX = pos.getX();
			while(spareX>=4){
				spareX-=4;
				x++;
			}
			int y = 0;
			float spareY = pos.getY();
			while(spareY>=4){
				spareY-=4;
				y++;
			}
			//			System.out.println("Checking location in MCWorld " + startPos.east(x+1).south(y));
			//			System.out.println("Checking blocks with the following values x = " + x + ", y = " + y + " spareX = " + spareX + " spareY = " + spareY);
			BlockPos check = startPos;
			//			System.out.println("Before Translation " + check);
			check = check.east((x+1));
			//			System.out.println("After EAST translation " + check);
			check = check.south(y);
			//			System.out.println("After SOUTH translation " + check);
			//			System.out.println("TE at check = " + world.getTileEntity(check));
			return ((ITileEntityTrackContainer)world.getTileEntity(check)).getInventory()[MathHelper.floorFloat(spareX)][MathHelper.floorFloat(spareY)] != null 
					&& ((ITileEntityTrackContainer)world.getTileEntity(check)).getInventory()[MathHelper.floorFloat(spareX)][MathHelper.floorFloat(spareY)].getItem() instanceof IItemModelTrack;
		}catch(Exception e){
			//			System.out.println("isTrack(Position3F) threw an exception, error handling");
			//			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isTrackBlock(Position3F pos){
		try{
			int x = 0;
			float rand = pos.getX();
			while(rand>=4){
				rand-=4;
				x++;
			}
			int y = 0;
			rand = pos.getY();
			while(rand>=4){
				rand-=4;
				y++;
			}
			//			System.out.println("Checking block " + startPos.east(x+1).south(y) + " ");
			if(world.getTileEntity(startPos.east(x+1).south(y)) != null && world.getTileEntity(startPos.east(x+1).south(y)).getBlockType().getUnlocalizedName().equalsIgnoreCase(ModBlocks.trackBed.getUnlocalizedName())){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			//			System.out.println("isTrackBlock(Position3F) threw an exception, error handling");
			return false;
		}
	}

	@Override
	public ITileEntityTrackContainer getTrackBedAt(Position3F pos){
		if(!isTrack(pos)) return null;

		int x = 0;
		float rand = pos.getX();
		while(rand>=4){
			rand-=4;
			x++;
		}
		int y = 0;
		rand = pos.getY();
		while(rand>=4){
			rand-=4;
			y++;
		}

		return (ITileEntityTrackContainer) world.getTileEntity(startPos.east(x+1).south(y));
	}

	@Override
	public ItemStack getTrackAt(Position3F pos){
		if(!isTrack(pos)) return null;

		int x = 0;
		float spareX = pos.getX();
		while(spareX>=4){
			spareX-=4;
			x++;
		}
		int y = 0;
		float spareY = pos.getY();
		while(spareY>=4){
			spareY-=4;
			y++;
		}
		return ((ITileEntityTrackContainer)world.getTileEntity(startPos.east(x+1).south(y))).getInventory()[MathHelper.floorFloat(spareX)][MathHelper.floorFloat(spareY)];
	}

	private Position3F getStartDirections(int index){
		switch(direction){
		case NORTH: return NORTH_START_POS[index].clone();
		case SOUTH: return SOUTH_START_POS[index].clone();
		case EAST: return EAST_START_POS[index].clone();
		case WEST: return WEST_START_POS[index].clone();
		default: return NORTH_START_POS[index].clone();
		}
	}

	private void initTrains(){
		for(int i = 0; i < trains.length; i++){
			if(i==0){
				trains[i] = new RollingStock(getStartDirections(i), this);
			}else{
				RollingStockPullable next = new RollingStockPullable(getStartDirections(i), this);
				next.setCartAhead(trains[i-1]);
				trains[i] = next;
			}
		}
		
	}

	private void firstTick(){
		//These fields MUST BE CALCULATED before anything else happens
		startPos = pos.west(BLOCK_OFFSET).north(BLOCK_OFFSET);
		direction = world.getBlockState(pos).getValue(BlockTrainController.FACING);
		//Now everything else can happen
		initTrains();
		firstTick = false;
	}

	@Override
	public void update() {
		//This should always happen first in update() to allow basic data setting
		if(firstTick){
			firstTick();
		}

		//Give each train an update tick and set inventory item
		if(!hasValidLoco())
			speed = 0;
		boolean hasTrainWithItem = false;
		for(int i = 0; i < trains.length; i++){
			RollingStock r = trains[i];
			//Updates the train item
			ItemStack ist = this.getStackInSlot(i);
			if(ist != null){
				if(ist.getCount() > 0 && ist.getItem() instanceof IItemTrain){
					IItemTrain it = (IItemTrain) ist.getItem();
					r.setTrainItem(it);
					hasTrainWithItem = true;
				}else{
					r.setTrainItem(null);
				}
			}else{
				r.setTrainItem(null);
			}

			if(r instanceof RollingStockPullable){
				//It will update speed on it's own
			}else{
				r.setSpeed(getSpeed());
			}
			r.update();
		}

		if(!hasTrainWithItem)
			initTrains();

		world.notifyBlockUpdate(pos, ModBlocks.trackBed.getDefaultState(), ModBlocks.trackBed.getDefaultState(), 1);
		this.markDirty();
	}

	//Packet Handling ----------------------------------------------------
	PacketChangeTrainSpeed lastPacket;
	public synchronized void handleTrainSpeedPacket(PacketChangeTrainSpeed packet){
		lastPacket = packet;
		((WorldServer) world).addScheduledTask(new Runnable() {
			@Override
			public void run() {
				if(hasValidLoco()){
					speed = lastPacket.getNewSpeed();
				}
			}
		});
	}

	PacketChangeTrainDirection lastDirPacket;
	public synchronized void handleTrainDirectionPacket(PacketChangeTrainDirection message) {
		lastDirPacket = message;
		((WorldServer) world).addScheduledTask(new Runnable() {
			@Override
			public void run() {
				if(speed == 0){
					forwards = lastDirPacket.getNewDirection();
				}
			}
		});
	}

	@Override
	public boolean hasValidLoco(){
		boolean out = false;
		for(RollingStock r : trains){
			if(r.getTrainItem() != null){
				if(r.getTrainItem().getTrainType().isLocomotive()){
					out = true;
				}
			}
		}
		return out;
	}

	//Default syncing ----------------------------------------------------
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
		//		NBTTagCompound syncData = new NBTTagCompound();
		NBTTagCompound syncData = super.getUpdateTag();
		this.writeSyncableDataToNBT(syncData);
		return syncData;
	}

	//Client
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
		//		System.out.println("<Client> recieved packet");
		super.onDataPacket(net, pkt);
		readSyncableDataFromNBT(pkt.getNbtCompound());
	}

	public NBTTagCompound writeSyncableDataToNBT(NBTTagCompound nbt){
		if(trains[0] != null){
			for(int i = 0; i < trains.length; i++){
				nbt.setFloat("train" + i + "x", trains[i].getPos().getX());
				nbt.setFloat("train" + i + "y", trains[i].getPos().getY());
				nbt.setFloat("train" + i + "yaw", trains[i].getPos().getYaw());
			}
		}
		nbt.setByte("speed", speed);
		nbt.setBoolean("forward", forwards);

		//Writes the inventory
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
		if(firstTick)
			this.writeSyncableDataToNBT(nbt);

		return nbt;
	}

	public void readSyncableDataFromNBT(NBTTagCompound nbt){
		forwards = nbt.getBoolean("forward");
		speed = nbt.getByte("speed");

		for(int i = 0; i < trains.length; i++){
			trains[i].setPos(new Position3F(nbt.getFloat("train" + i + "x"), nbt.getFloat("train" + i + "y"), nbt.getFloat("train" + i + "yaw")));
		}

		//Reads the inventory
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
		if(!firstTick)
			this.readSyncableDataFromNBT(nbt);
	}

	/**Gets the TRACK inventory
	 * For the train inventory use the getTrains() method*/
	@Override
	public ItemStack[][] getInventory() {
		switch(direction){
		case NORTH: return NORTH_TRACKS;
		case SOUTH: return SOUTH_TRACKS;
		case EAST: return EAST_TRACKS;
		case WEST: return WEST_TRACKS;
		default: return NORTH_TRACKS;
		}
	}

	//The inventory code --------------------------

	@Override
	public String getName() {
		return "Train Controller";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 4;
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
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = this.getStackInSlot(index);
		this.setInventorySlotContents(index, ItemStack.EMPTY);
		return stack;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void openInventory(EntityPlayer player){}

	@Override
	public void closeInventory(EntityPlayer player){}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.getSizeInventory(); i++)
			this.setInventorySlotContents(i, ItemStack.EMPTY);
	}

	@Override
	public int getField(int id){return 0;}

	@Override
	public void setField(int id, int value){}

	@Override
	public int getFieldCount(){return 0;}

	@Override
	public boolean isEmpty() {
		for(int i = 0; i < inventory.length; i++) {
			if(inventory[i] != ItemStack.EMPTY)
				return false;
		}
		return true;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

}


