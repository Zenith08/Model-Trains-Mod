package me.kk47.modeltrains.gui;

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

	@Override
	//FIXME This isn't working for anything at all
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();
			if (fromSlot < 16) {
				
				for(int i = 16; i < this.inventorySlots.size(); i++) {
					if(inventorySlots.get(i).getHasStack()) {
						if(inventorySlots.get(i).getStack().getItem() == current.getItem()) {
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
						
					}
				}
			} else {
				for(int i = 0; i < 15; i++) {
					if(inventorySlots.get(i).getHasStack()) {
						if(inventorySlots.get(i).getStack().getItem() == current.getItem()) {
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
					}
				}
			}
		}
			

			// [...] Custom behaviour
			/*if (fromSlot < 16) {
	            // From TE Inventory to Player Inventory
	            if (!this.mergeItemStack(current, 9, 45, true))
	                return ItemStack.EMPTY;
	        } else {
	            // From Player Inventory to TE Inventory
	            if (!this.mergeItemStack(current, 0, 16, false))
	                return ItemStack.EMPTY;
	        }
			if (current.getCount() == 0)
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();

			if (current.getCount() == previous.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, current);
			
		}*/
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

	            if (stackinslot != ItemStack.EMPTY && stackinslot.getItem() == stack.getItem() && (!stack.getHasSubtypes() || stack.getMetadata() == stackinslot.getMetadata()) && ItemStack.areItemStackTagsEqual(stack, stackinslot)) {
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
	            if (stackinslot == ItemStack.EMPTY && slot.isItemValid(stack)) {
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
