package me.kk47.modeltrains.gui.slot;

import javax.annotation.Nullable;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotClay extends Slot {

	public SlotClay(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack){
		if(stack.getItem().getUnlocalizedName().equalsIgnoreCase(Items.CLAY_BALL.getUnlocalizedName())) {
			return true;
		} else {
			return false;
		}
	}

}
