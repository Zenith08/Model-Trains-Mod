package me.kk47.modeltrains.gui.industry;

import me.kk47.modeltrains.gui.slot.SlotOutput;
import me.kk47.modeltrains.gui.slot.SlotTrack;
import me.kk47.modeltrains.tileentity.TileEntityIndustryVillage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerIndustryVillage extends Container {

	private TileEntityIndustryVillage te;
	
	public ContainerIndustryVillage(IInventory playerInv, TileEntityIndustryVillage te) {
		this.te = te;
		/*
		 * SLOTS:
		 * 
		 * Tile Entity 0-0 ....... 0  - 0
		 * Player Inventory 9-35 .. 1  - 27
		 * Player Inventory 0-8 ... 28 - 36
		 */
		//TE Inventory
		this.addSlotToContainer(new SlotOutput(te, 0, 130, 24));

		// Player Inventory, Slot 9-35, Slot IDs 9-35
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x+y*9+9, 8+x*18, 64+y*18));
			}
		}

		// Player Inventory, Slot 0-8, Slot IDs 36-44
		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, 8+x*18, 122));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.isUsableByPlayer(playerIn);
	}

	@Override
	//Should probably implement this.
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();
			if(current != ItemStack.EMPTY) {
				// [...] Custom behaviour		
				//I'll get to this later.
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
