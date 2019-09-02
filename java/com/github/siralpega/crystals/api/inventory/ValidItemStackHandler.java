package com.github.siralpega.firstmod.api.inventory;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
/**
 * A custom ItemStackHandler which allows for whitelisting items for certain slots. <br>
 * Purpose: To disallow external objects (hoppers, pipes, etc.) from inserting into whitelisted slots. <br>
 * For player, whitelist happens in SlotInput class, which extends SlotItemHandler.
 * TODO: make ValidItem accept multiple slots
 * 
 * @author siralpega
 *
 */
public class ValidItemStackHandler extends ItemStackHandler
{

	private static List<ValidItem> validItems = new ArrayList<ValidItem>();

	public ValidItemStackHandler(int size)
	{
		super(size);
	}
	
	public void addValidItem(Item item, int slot)
	{
		validItems.add(new ValidItem(item, slot));
	}
	
	/**
	 * Tests to see if an Item is allowed to be inserted
	 * @param the slot you are inserting into
	 * @param the ItemStack you want to insert into the slot
	 * @return if the item can be inserted into that slot or not
	 */
	@Override
	public boolean isItemValid(int slot, ItemStack stack) 
	{
		if(!super.isItemValid(slot, stack))
			return false;
		Item itemIn = stack.getItem();
		for(int i = 0; i < validItems.size(); i++)
		{
			if((validItems.get(i).getSlot() == slot || validItems.get(i).getSlot() == -1) && validItems.get(i).getItem() == itemIn) //-1 means accepted in all slots
				return true;
		}
		return false;
	}

	@Nonnull
	@Override
	public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
	{
		if((!isItemValid(slot, stack)))
			return stack;
		return super.insertItem(slot, stack, simulate);
	}
	
	public class ValidItem
	{
		private int slot;
		private Item item;
		public ValidItem(Item i, int s)
		{
			item = i;
			slot = s;
		}

		public void setSlot(int x)
		{
			slot = x;
		}

		public int getSlot()
		{
			return slot;
		}

		public void setItem(Item x)
		{
			item = x;
		}

		public Item getItem()
		{
			return item;
		}
	}
}
