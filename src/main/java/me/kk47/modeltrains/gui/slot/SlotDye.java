package me.kk47.modeltrains.gui.slot;

import javax.annotation.Nullable;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotDye extends Slot {

	private final byte dyeColour;

	/**@param dyeColour - 0 is red, 1, is green, 2 is blue*/
	public SlotDye(IInventory inventoryIn, int index, int xPosition, int yPosition, byte dyeColour) {
		super(inventoryIn, index, xPosition, yPosition);
		this.dyeColour = dyeColour;
	}

	@Override
	public boolean isItemValid(@Nullable ItemStack stack){
		if(dyeColour == 0 && stack.getItem() == Items.DYE && stack.getItemDamage() == 1) { //Rose Red
			return true;
		}else if(dyeColour == 1 && stack.getItem() == Items.DYE && stack.getItemDamage() == 2) { //Cactus Green
			return true;
		}else if(dyeColour == 2 && stack.getItem() == Items.DYE && stack.getItemDamage() == 4) { //Lapis Lazuli
			return true;
		}else {
			return false;
		}
	}
}
