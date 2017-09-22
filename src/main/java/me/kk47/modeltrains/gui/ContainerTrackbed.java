package me.kk47.modeltrains.gui;

import me.kk47.modeltrains.api.IItemModelTrack;
import me.kk47.modeltrains.gui.slot.SlotTrack;
import me.kk47.modeltrains.tileentity.TileEntityTrackBed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTrackbed extends Container{

	private TileEntityTrackBed te;

	public ContainerTrackbed(IInventory playerInv, TileEntityTrackBed te) {
		this.te = te;
		/*
		 * SLOTS:
		 * 
		 * Tile Entity 0-16 ....... 0  - 15
		 * Player Inventory 9-35 .. 16  - 42
		 * Player Inventory 0-8 ... 43 - 51
		 */
		//TE Inventory
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 4; y++){
				this.addSlotToContainer(new SlotTrack(te, x*4+y, 98+18*y, 62-18*x));
			}
		}

		// Player Inventory, Slot 9-35, Slot IDs 9-35
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x+y*9+9, 8+x*18, 88+y*18));
			}
		}

		// Player Inventory, Slot 0-8, Slot IDs 36-44
		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, 8+x*18, 146));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.isUsableByPlayer(playerIn);
	}

	private long lastCallTime = 0; //The first time this gets called has to work so this can start at 0

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
//		System.out.println("Transfer Stack in Slot at time " + System.nanoTime());
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if(System.currentTimeMillis()-lastCallTime >= 2) {
			lastCallTime = System.currentTimeMillis(); //This is actually a work around to make the thing not call over and over again messing it up
			
			if (slot != null && slot.getHasStack()) {
				ItemStack current = slot.getStack();
				previous = current.copy();
				if(current != ItemStack.EMPTY) {
					if (fromSlot <= 15) {
						//Transfers the item from the TE to the player's inventory.
						for(int i = 16; i < this.inventorySlots.size(); i++) {
							if(inventorySlots.get(i).getHasStack()) {
								if(ItemStack.areItemStacksEqual(inventorySlots.get(i).getStack(), current)) {
									if(inventorySlots.get(i).getStack().getCount() + current.getCount() > inventorySlots.get(i).getSlotStackLimit()) {
										int c = inventorySlots.get(i).getStack().getCount() + current.getCount() - inventorySlots.get(i).getSlotStackLimit();
										inventorySlots.get(i).getStack().setCount(inventorySlots.get(i).getSlotStackLimit());
										inventorySlots.get(i).onSlotChanged();
										inventorySlots.get(fromSlot).getStack().setCount(c);
										inventorySlots.get(fromSlot).onSlotChanged(); //Shows that the change happened
									}else {
										inventorySlots.get(i).getStack().setCount(inventorySlots.get(i).getStack().getCount() + current.getCount());
										inventorySlots.get(i).onSlotChanged();
										inventorySlots.get(fromSlot).putStack(ItemStack.EMPTY);
										inventorySlots.get(fromSlot).onSlotChanged(); //Shows that the change happened
										return current;
									}
								}
							}else {
								inventorySlots.get(i).putStack(current.copy());
								inventorySlots.get(i).onSlotChanged(); //Shows that the change happened
								inventorySlots.get(fromSlot).putStack(ItemStack.EMPTY);
								inventorySlots.get(fromSlot).onSlotChanged(); //Shows that the change happened
								return current;
							}
						}
					} else {
						if(current.getItem() instanceof IItemModelTrack) {
							//System.out.println("Track, going into loop");
							for(int i = 0; i < 16; i++) {
								//System.out.println("Looping with i = " + i);
								if(!inventorySlots.get(i).getHasStack()) {
									//System.out.println("Copying ItemStack");
									ItemStack place = current.copy();
									place.setCount(1);
									inventorySlots.get(i).putStack(place);
									inventorySlots.get(i).onSlotChanged(); //Shows that the change happened
									inventorySlots.get(fromSlot).decrStackSize(1);
									inventorySlots.get(fromSlot).onSlotChanged(); //Shows that the change happened
									//System.out.println("Break should happen");
									break; //We know the stack size is one so we don't need to do any more.
								}
							}
						}
					}

					//End Custom Behavior
					if (current.getCount() == 0)
						slot.putStack(ItemStack.EMPTY);
					else
						slot.onSlotChanged();

					if (current.getCount() == previous.getCount())
						return ItemStack.EMPTY;
					slot.onTake(playerIn, current);
				}
			}
		}

		return previous;
	}
}
