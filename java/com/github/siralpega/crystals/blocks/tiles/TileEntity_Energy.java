package com.github.siralpega.firstmod.blocks.tiles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.siralpega.firstmod.FirstModMain;
import com.github.siralpega.firstmod.api.inventory.IDracoContainer;
import com.github.siralpega.firstmod.api.inventory.ValidItemStackHandler;
import com.github.siralpega.firstmod.lists.ItemList;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class TileEntity_Energy extends TileEntity implements ITickableTileEntity, IDracoContainer
{
//  protected ItemStackHandler inputSlot; Use this if no whitelist is needed for input slot
	protected ValidItemStackHandler inputSlot;
	protected ItemStackHandler outputSlot;
	private final LazyOptional<ItemStackHandler> inputSlotHandler = LazyOptional.of(() -> inputSlot);
	private final LazyOptional<ItemStackHandler> outputSlotHandler = LazyOptional.of(() -> outputSlot);
	private final String name = "tile.infuser";
	

	public TileEntity_Energy(TileEntityType<?> tileEntityTypeIn)
	{
		super(tileEntityTypeIn);
		//inputSlot = new ItemStackHandler(2);
		inputSlot = new ValidItemStackHandler(2);
		inputSlot.addValidItem(ItemList.copper_axe, 0);
		inputSlot.addValidItem(ItemList.copper_ingot, 1);
		outputSlot = new ItemStackHandler(1);
	}
	
	@Override
	public void tick()
	{
		
	}

	@Override
	public void read(CompoundNBT tag)
	{
		super.read(tag);
		CompoundNBT invTag = tag.getCompound("inv");
		inputSlot.deserializeNBT(invTag);
		outputSlot.deserializeNBT(invTag);
		inputSlot.deserializeNBT(tag.getCompound(FirstModMain.MODID + ":inputSlot"));
		outputSlot.deserializeNBT(tag.getCompound(FirstModMain.MODID + ":outputSlot"));
		//craftTime = tag.getFloat(FirstModMain.MODID + ":craftTime");
	}

	@Override
	public CompoundNBT write(CompoundNBT tag)
	{
		tag = super.write(tag);
		tag.put("inv", inputSlot.serializeNBT());
		tag.put("inv", outputSlot.serializeNBT());
		tag.put(FirstModMain.MODID + ":inputSlot", inputSlot.serializeNBT());
		tag.put(FirstModMain.MODID + ":outputSlot", outputSlot.serializeNBT());
		//tag.putFloat(FirstModMain.MODID + ":craftTime", craftTime);
		return tag;
	}

/*	private IItemHandler createHandler()
	{
		return new ItemStackHandler(3) //CHANGE VALUE TO 2 OR 3. The (1) is the SIZE!!!!!
		{
			public boolean isItemValid(int slot, @Nonnull ItemStack stack)
			{
				return (slot == 0 && stack.getItem() == ItemList.copper_axe) || (stack.getItem() == ItemList.copper_ingot && slot == 1);
			}
			@Nonnull
			@Override
			public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
			{
				if((slot == 0 && stack.getItem() != ItemList.copper_axe)  || (slot == 1 && stack.getItem() != ItemList.copper_ingot))
					return stack;
				return super.insertItem(slot, stack, simulate);
			}
		};
	} */

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) 
	{
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) 
		{ //YOU CAN GET RID OF THE SIDES AND JUST USE if(side == null) return (or the one above aka the CombinedInvWrapper)
			this.markDirty();
			if(world != null && world.getBlockState(pos).getBlock() != this.getBlockState().getBlock()) {//if the block at myself isn't myself, allow full access (Block Broken)
				return LazyOptional.of(() -> new CombinedInvWrapper(inputSlot, outputSlot)).cast();
			}
			if(side == null) {
				return LazyOptional.of(() -> new CombinedInvWrapper(inputSlot, outputSlot)).cast();
			}
			if(world == null) {
				if(side == Direction.UP) {
					return inputSlotHandler.cast();
				}
				if(side == Direction.DOWN) {
					return outputSlotHandler.cast();
				}
				return super.getCapability(cap, side);
			}
			if(side == Direction.UP) {
				return inputSlotHandler.cast();
			}
			if(side == Direction.DOWN) {
				return outputSlotHandler.cast();
			}
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void openGUI(ServerPlayerEntity player)
	{
		if (!world.isRemote) {
			NetworkHooks.openGui(player, this, getPos());
		}
	} 

	@Override
	public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity player) 
	{
		return null;
		//Container accepts InfuserTileEntity, since this is a separate class, it throws an error. A soultion would to be have an interface and have this class implements, but since there is only one TileEntity per container that is overkill.
		//return new BloodInfuserContainer(windowID, playerInventory, new CombinedInvWrapper(inputSlot, outputSlot), this);
	}

	@Override
	public ITextComponent getDisplayName() 
	{
		return new TranslationTextComponent(FirstModMain.MODID + name);
	}

}
