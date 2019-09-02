package com.github.siralpega.firstmod;

import com.github.siralpega.firstmod.lists.ItemList;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CopperItemGroup extends ItemGroup
{
	public CopperItemGroup()
	{
		super("copper");
	}

	@Override
	public ItemStack createIcon() 
	{
		return new ItemStack(ItemList.copper_ingot);
		//	return new ItemStack(Item.BLOCK_TO_ITEM.get(BlockList.copper_block));
	}
}
