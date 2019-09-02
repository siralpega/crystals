package com.github.siralpega.firstmod.api.inventory;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
/**
 * Slot class for input slots to a TileEntity. <br>
 * Can set slots to have a whitelist. Declared from container class for TileEntity using addSlot(new SlotInput()). <br>
 * If any item can be accepted, call without Item parameter.
 * @author siralpega
 */
public class SlotInput extends SlotItemHandler
{

	private Item[] validItems;
	
	public SlotInput(IItemHandler itemHandler, int index, int xPosition, int yPosition)
	{
		this(itemHandler, index, xPosition, yPosition, (Item) null);
	}
	
	public SlotInput(IItemHandler itemHandler, int index, int xPosition, int yPosition, Item... itemsIn)
	{
		super(itemHandler, index, xPosition, yPosition);
		this.validItems = itemsIn;
	}

	@Override
	public boolean isItemValid(ItemStack stack) 
	{
		if(super.isItemValid(stack) && validItems != null && validItems.length > 0)
		{
			for(int i = 0; i < validItems.length; i++)
				if(validItems[i].equals(stack.getItem()))
					return true;
		}
		//If we want the Slot to accept anything, we leave Item... parameter null
		else if(super.isItemValid(stack) && validItems == null)
			return true;
		return false;
	}	
}