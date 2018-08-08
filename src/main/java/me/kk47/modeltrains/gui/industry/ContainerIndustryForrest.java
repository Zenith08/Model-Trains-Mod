package me.kk47.modeltrains.gui.industry;

import me.kk47.modeltrains.tileentity.TileEntityIndustryForrest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerIndustryForrest extends Container{

	private TileEntityIndustryForrest te;

	public ContainerIndustryForrest(IInventory playerInv, TileEntityIndustryForrest te) {
		this.te = te;
		//There are no slots.
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.isUsableByPlayer(playerIn);
	}

	@Override
	//There are no slots so do I still need this?
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
