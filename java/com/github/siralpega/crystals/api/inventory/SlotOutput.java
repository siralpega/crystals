package com.github.siralpega.firstmod.api.inventory;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotOutput extends SlotItemHandler
{
	
	public SlotOutput(IItemHandler itemHandler, int index, int xPosition, int yPosition)
	{
		super(itemHandler, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(@Nullable ItemStack stack)
	{
		return false;
	}
	
	@Override
	public boolean canTakeStack(@Nullable PlayerEntity playerIn)
	{
		return true;
	}
}