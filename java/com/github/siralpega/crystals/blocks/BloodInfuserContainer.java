package com.github.siralpega.firstmod.blocks;

import com.github.siralpega.firstmod.api.inventory.DracoContainer;
import com.github.siralpega.firstmod.api.inventory.SlotInput;
import com.github.siralpega.firstmod.api.inventory.SlotOutput;
import com.github.siralpega.firstmod.blocks.tiles.InfuserTileEntity;
import com.github.siralpega.firstmod.lists.BlockList;
import com.github.siralpega.firstmod.lists.ItemList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @original_author Draco18s
 * modifications siralpega
 */
public class BloodInfuserContainer extends DracoContainer
{
	public InfuserTileEntity tileEntity;
	
	public final static int NUM_OF_SLOTS = 3;
	
//	public BloodInfuserContainer(int windowID, PlayerInventory playerInventory, BlockPos pos)
//	{
//		this(windowID, playerInventory, new ItemStackHandler(3), (InfuserTileEntity) Minecraft.getInstance().world.getTileEntity(pos));
//	}
	
	public BloodInfuserContainer(int windowID, PlayerInventory playerInventory, PacketBuffer extraData)
	{
		this(windowID, playerInventory, new ItemStackHandler(3), (InfuserTileEntity) Minecraft.getInstance().world.getTileEntity(extraData.readBlockPos()));
	}

	public BloodInfuserContainer(int windowID, PlayerInventory playerInventory, IItemHandler inven, InfuserTileEntity te)
	{
		super(BlockList.blood_infuser_container, windowID, NUM_OF_SLOTS);

	//	addSlot(new SlotItemHandler(inven, 0, 64, 24));
	//	addSlot(new SlotItemHandler(inven, 1, 100, 24));
		addSlot(new SlotInput(inven, 0, 64, 24, ItemList.copper_axe));
		addSlot(new SlotInput(inven, 1, 100, 24, ItemList.copper_ingot));
		addSlot(new SlotOutput(inven, 2, 82, 46));
		bindPlayerInventory(playerInventory, 10, 70);
		tileEntity = te;
	}
}
