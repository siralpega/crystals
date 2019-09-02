package com.github.siralpega.firstmod.blocks;

/*
import com.github.siralpega.firstmod.lists.BlockList;
import com.github.siralpega.firstmod.lists.ItemList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

/****
 * this is a container class. it exists for both the server and client. it contains slots linking the inventory to every slot location on the screen
 * there are two inventories in this class. one for the player, and one for the title entity (in this case, block: blood_infuser_tile class: BloodInfuserBlockTile)
 * @author Alek
 *
 */
/*
public class BloodInfuserContainer_OLD extends Container
{
	private TileEntity tileE;
	private IItemHandler pi;

	public BloodInfuserContainer_OLD(int id, World world, BlockPos pos, PlayerInventory pi)
	{
		super(BlockList.blood_infuser_container, id);
		tileE = world.getTileEntity(pos);
		this.pi = new InvWrapper(pi);
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn)
	{
		return isWithinUsableDistance(IWorldPosCallable.of(tileE.getWorld(), tileE.getPos()), playerIn, BlockList.blood_infuser);
	}
}

		
		//Adding slot for TileEntity GUI
		tileE.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> 
		{
		//	addSlot(new SlotItemHandler(h, 0, 64, 24)); //64, 24 is again, the pixels on my gui. addSlot makes a slot for the inventory
			addSlot(new SlotItemHandler(h, 0, 100, 24));
		//	addSlot(new SlotItemHandler(h, 2, 82, 46));
		});

		//Adding slots for PlayerInventory GUI
		layoutPlayerInventorySlots(10, 70);  //your gui left corner layout for player inv. pixels, i think

		trackInt(new IntReferenceHolder() 
		{
			@Override
			public int get() 
			{
				return getBlood();
			}

			@Override
			public void set(int value) 
			{ //sets blood
				tileE.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((CustomBloodStorage)h).setBlood(value));
			}
		});
	}

	public int getBlood() 
	{
		return tileE.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
	}

	
	@Override
	@Nonnull
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index <  invenSize) {
				if (!this.mergeItemStack(itemstack1, invenSize, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			}
			else if (!this.mergeItemStack(itemstack1, 0, invenSize, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			}
			else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}
	@Override
	public ItemStack transferStackInSlot(PlayerEntity pe, int index)
	{ //makes it so shift-click works and doesnt crash
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) 
		{
			ItemStack stack = slot.getStack();
			itemstack = stack.copy();
			if (index == 0) 
			{ //if the player is shift clicking on the title entity inventory, we put it in player inventory
				if(!this.mergeItemStack(stack, 1, 37, true)) 
				{
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(stack, itemstack);
			} 
			else 
			{
				if (stack.getItem() == ItemList.copper_ingot)
				{ //put into our tile entity's first slot
					if (!this.mergeItemStack(stack, 0, 1, false))
					{ 
						return ItemStack.EMPTY;
					}
				} 
				else if(index < 28) 
				{ //merge into hotbar if not item we want
					if (!this.mergeItemStack(stack, 28, 37, false))
					{
						return ItemStack.EMPTY;
					}
				} 
				else if(index < 37 && !this.mergeItemStack(stack, 1, 28, false)) 
				{//merge into player inventory excluding hotbar
					return ItemStack.EMPTY;
				}
			}

			if (stack.isEmpty()) 
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else 
			{
				slot.onSlotChanged();
			}

			if (stack.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(pe, stack);
		}

		return itemstack;
	}

	private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) 
	{
		for (int i = 0 ; i < amount ; i++) 
		{
			addSlot(new SlotItemHandler(handler, index, x, y));
			x += dx;
			index++;
		}
		return index;
	}

	private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy)
	{
		for (int j = 0 ; j < verAmount ; j++)
		{
			index = addSlotRange(handler, index, x, y, horAmount, dx);
			y += dy;
		}
		return index;
	}

	private void layoutPlayerInventorySlots(int leftCol, int topRow) 
	{
		// Player inventory
		addSlotBox(pi, 9, leftCol, topRow, 9, 18, 3, 18);

		// Hotbar
		topRow += 58;
		addSlotRange(pi, 0, leftCol, topRow, 9, 18);
	}
}
	*/
