package me.kk47.modeltrains.gui.client;

import me.kk47.modeltrains.gui.SlotTrain;
import me.kk47.modeltrains.tileentity.TileEntity3DPrinter;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPrinter3D extends Container{

	private TileEntity3DPrinter te;
	
	public ContainerPrinter3D(IInventory playerInv, TileEntity3DPrinter te) {
		this.te = te; //TODO Re-write to work with new gui
//		System.out.println("ContainerTrainController(PlayerInv, te) Data: " + playerInv + ", " + te); 
		//TE Inventory, Slot 0-3
		for (int x = 0; x < 4; x++){
//			this.addSlotToContainer(new SlotTrain(te, 3-x, 98+18*x, 36));
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

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
		ItemStack previous = null;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();
			//TODO Optimize for ItemTrain (When added)
			// [...] Custom behaviour
			//End Custom Behaviour

			if (current.getCount() == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();

			if (current.getCount() == previous.getCount())
				return null;
			slot.onTake(playerIn, current);
			
		}
		return previous;
	}

}
