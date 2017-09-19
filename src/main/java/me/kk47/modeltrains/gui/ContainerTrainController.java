package me.kk47.modeltrains.gui;

import me.kk47.modeltrains.api.IItemTrain;
import me.kk47.modeltrains.gui.slot.SlotTrain;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTrainController extends Container{

	private TileEntityTrainController te;

	public ContainerTrainController(IInventory playerInv, TileEntityTrainController te) {
		this.te = te;
		//TE Inventory, Slot 0-3
		for (int x = 0; x < 4; x++){
			this.addSlotToContainer(new SlotTrain(te, 3-x, 98+18*x, 36));
		}

		// Player Inventory, Slot 9-35, Slot IDs 4-30
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, y*9+x+9, 8+x*18, 91+y*18));
			}
		}

		// Player Inventory, Slot 0-8, Slot IDs 31-39
		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, 8+x*18, 149));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.isUsableByPlayer(playerIn);
	}

	private long lastCallTime = 0; //The first time this gets called has to work so this can start at 0

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(fromSlot);

		if(System.currentTimeMillis()-lastCallTime >= 2) {
			lastCallTime = System.currentTimeMillis();

			if (slot != null && slot.getHasStack()) {
				ItemStack current = slot.getStack();
				previous = current.copy();
				// [...] Custom behaviour
				if(fromSlot <= 3) {
					//Transfers the item from the TE to the player's inventory.
					for(int i = 4; i < this.inventorySlots.size(); i++) {
						if(inventorySlots.get(i).getHasStack()) {
							if(inventorySlots.get(i).getStack().getItem() == current.getItem()) {
								if(inventorySlots.get(i).getStack().getCount() + current.getCount() > inventorySlots.get(i).getSlotStackLimit()) {
									int c = inventorySlots.get(i).getStack().getCount() + current.getCount() - inventorySlots.get(i).getSlotStackLimit();
									inventorySlots.get(i).getStack().setCount(inventorySlots.get(i).getSlotStackLimit());
									inventorySlots.get(i).onSlotChanged();
									inventorySlots.get(fromSlot).getStack().setCount(c);
									inventorySlots.get(fromSlot).onSlotChanged(); //Shows that the change happened

								}else{
									inventorySlots.get(i).getStack().setCount(inventorySlots.get(i).getStack().getCount() + current.getCount());
									inventorySlots.get(i).onSlotChanged();
									inventorySlots.get(fromSlot).putStack(ItemStack.EMPTY);
									inventorySlots.get(fromSlot).onSlotChanged(); //Shows that the change happened
									return current;
								}
							}
						}else{
							inventorySlots.get(i).putStack(current.copy());
							inventorySlots.get(i).onSlotChanged(); //Shows that the change happened
							inventorySlots.get(fromSlot).putStack(ItemStack.EMPTY);
							inventorySlots.get(fromSlot).onSlotChanged(); //Shows that the change happened
							return current;
						}
					}
				}else{
					if(current.getItem() instanceof IItemTrain) {
						for(int i = 3; i >= 0; i--) { //Loop Backwards, that way the train is forwards
							if(!inventorySlots.get(i).getHasStack()) {
								ItemStack place = current.copy();
								place.setCount(1);
								inventorySlots.get(i).putStack(place);
								inventorySlots.get(i).onSlotChanged(); //Shows that the change happened
								inventorySlots.get(fromSlot).decrStackSize(1);
								inventorySlots.get(fromSlot).onSlotChanged(); //Shows that the change happened
								break; //We know the stack size is one so we don't need to do any more.
							}
						}
					}
				}

				//End Custom Behaviour
				if (current.getCount() == 0)
					slot.putStack(ItemStack.EMPTY);
				else
					slot.onSlotChanged();

				if (current.getCount() == previous.getCount())
					return ItemStack.EMPTY;
				slot.onTake(playerIn, current);

			}
		}
		return previous;
	}

	@Override
	protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean useEndIndex) {
		boolean success = false;
		int index = startIndex;

		if (useEndIndex)
			index = endIndex - 1;

		Slot slot;
		ItemStack stackinslot;

		if (stack.isStackable()) {
			while (stack.getCount() > 0 && (!useEndIndex && index < endIndex || useEndIndex && index >= startIndex)) {
				slot = (Slot) this.inventorySlots.get(index);
				stackinslot = slot.getStack();

				if (stackinslot != null && stackinslot.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == stackinslot.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, stackinslot)) {
					int l = stackinslot.getCount() + stack.getCount();
					int maxsize = Math.min(stack.getMaxStackSize(), slot.getItemStackLimit(stack));

					if (l <= maxsize) {
						stack.setCount(0);
						stackinslot.setCount(1);
						slot.onSlotChanged();
						success = true;
					} else if (stackinslot.getCount() < maxsize) {
						//	                    stack.stackSize -= stack.getMaxStackSize() - stackinslot.stackSize;
						stack.setCount(stack.getCount()-stack.getMaxStackSize() - stackinslot.getCount());
						//	                    stackinslot.stackSize = stack.getMaxStackSize();
						stackinslot.setCount(stack.getMaxStackSize());
						slot.onSlotChanged();
						success = true;
					}
				}

				if (useEndIndex) {
					--index;
				} else {
					++index;
				}
			}
		}

		if (stack.getCount() > 0) {
			if (useEndIndex) {
				index = endIndex - 1;
			} else {
				index = startIndex;
			}

			while (!useEndIndex && index < endIndex || useEndIndex && index >= startIndex && stack.getCount() > 0) {
				slot = (Slot) this.inventorySlots.get(index);
				stackinslot = slot.getStack();

				// Forge: Make sure to respect isItemValid in the slot.
				if (stackinslot == null && slot.isItemValid(stack)) {
					if (stack.getCount() < slot.getItemStackLimit(stack)) {
						slot.putStack(stack.copy());
						//	                    stack.stackSize = 0;
						stack.setCount(0);
						success = true;
						break;
					} else {
						ItemStack newstack = stack.copy();
						//	                    newstack.stackSize = slot.getItemStackLimit(stack);
						newstack.setCount(slot.getItemStackLimit(stack));
						slot.putStack(newstack);
						//	                    stack.stackSize -= slot.getItemStackLimit(stack);
						stack.setCount(stack.getCount()-slot.getItemStackLimit(stack));
						success = true;
					}
				}

				if (useEndIndex) {
					--index;
				} else {
					++index;
				}
			}
		}

		return success;
	}
}
