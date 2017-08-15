package me.kk47.modeltrains.gui;

import javax.annotation.Nullable;

import me.kk47.modeltrains.api.IItemModelTrack;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTrack extends Slot{

	public SlotTrack(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack){
        if(stack.getItem() instanceof IItemModelTrack){
        	return true;
        }else{
        	return false;
        }
        
    }

}
