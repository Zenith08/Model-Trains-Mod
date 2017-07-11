package me.kk47.modeltrains.gui;

import javax.annotation.Nullable;

import me.kk47.modeltrains.api.IItemTrain;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTrain extends Slot {

	public SlotTrain(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(@Nullable ItemStack stack){
		if(stack != null){
			if(stack.getItem() instanceof IItemTrain){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
}
