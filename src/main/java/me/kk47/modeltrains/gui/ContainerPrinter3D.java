package me.kk47.modeltrains.gui;

import me.kk47.modeltrains.gui.slot.SlotClay;
import me.kk47.modeltrains.gui.slot.SlotDye;
import me.kk47.modeltrains.gui.slot.SlotOutput;
import me.kk47.modeltrains.tileentity.TileEntity3DPrinter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPrinter3D extends Container{

	private TileEntity3DPrinter te;

	public ContainerPrinter3D(IInventory playerInv, TileEntity3DPrinter te) {
		this.te = te;

		this.addSlotToContainer(new SlotClay(te, 0, 8, 7));

		this.addSlotToContainer(new SlotDye(te, 1, 8, 41, (byte)0));
		this.addSlotToContainer(new SlotDye(te, 2, 8, 71, (byte)2));
		this.addSlotToContainer(new SlotDye(te, 3, 8, 101, (byte)1));

		this.addSlotToContainer(new SlotOutput(te, 4, 51, 7));

		//This should be fine
		// Player Inventory, Slot 9-35, Slot IDs 4-30
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, y*9+x+9, 8+x*18, 134+y*18));
			}
		}

		// Player Inventory, Slot 0-8, Slot IDs 31-39
		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, 8+x*18, 192));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.isUsableByPlayer(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();
			if(current != ItemStack.EMPTY) {
				//TODO Optimize for ItemTrain (When added)
				// [...] Custom behaviour
				if(fromSlot <= 3) {
					//Transfers the item from the TE to the player's inventory.
					for(int i = 4; i < this.inventorySlots.size(); i++) {
						if(inventorySlots.get(i).getHasStack()) {
							if(inventorySlots.get(i).getStack().getItem() == current.getItem()) {
								if(inventorySlots.get(i).getStack().getCount() + current.getCount() > inventorySlots.get(i).getSlotStackLimit()) {
									//Then don't do anything at all and keep going.
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
				}else {
					if(current.getItem() == Items.DYE) {
						//Filter based on dye
					}else if(current.getItem() == Items.CLAY_BALL) {

					}
				}
				//End Custom Behaviour
			}
			if (current.getCount() == 0)
				slot.putStack(ItemStack.EMPTY);
			else
				slot.onSlotChanged();

			if (current.getCount() == previous.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, current);

		}
		return previous;
	}
}