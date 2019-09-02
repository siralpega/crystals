package com.github.siralpega.firstmod.items;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;

public class CustomAxeItem extends AxeItem
{

	public CustomAxeItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Properties builder)
	{
		super(tier, attackDamageIn, attackSpeedIn, builder);	
	}
	
	@Override
	public int getBurnTime(ItemStack itemstack)
	{
		return 400;
	}
	
}
