package com.github.siralpega.firstmod.api.inventory;
import javax.annotation.Nonnull;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

/**
 * No-nonsense container class that does all the things a container needs to do.<br>
 * Every "container" TE can create a subclass of this to use for a GUI.
 * @author Draco18s 
 * https://github.com/Draco18s/ReasonableRealism/blob/1.14.3/src/main/java/com/draco18s/hardlib/api/internal/CommonContainer.java
 * modifications siralpega
 */
public class DracoContainer extends Container
{
	protected final int invenSize;

	protected DracoContainer(ContainerType<?> type, int id, int size) 
	{
		super(type, id);
		invenSize = size;
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn)
	{
		return true;
	}

	protected void bindPlayerInventory(PlayerInventory playerInventory, int xPos, int yPos)
	{
		//Inventory (Slots 9-35)
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlot(new Slot(playerInventory, j + i * 9 + 9, xPos + j * 18, yPos + i * 18)); //10, 70    8, 84
			}
		}
		//Hotbar (Slots 0-8)
		for (int k = 0; k < 9; ++k) {
			this.addSlot(new Slot(playerInventory, k, xPos + k * 18, (yPos + 2 * 18) + 22));   //10, 70/128    8, 142
		}
	}

	@Override
	@Nonnull
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index <  invenSize) 
			{ 
				if (!this.mergeItemStack(itemstack1, invenSize, this.inventorySlots.size(), true)) 
				{ //from tile entity
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 0, invenSize, false))
			{//to tile entity
				return ItemStack.EMPTY;
			}

			if (itemstack1.getCount() == 0) 
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else 
			{
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}
}