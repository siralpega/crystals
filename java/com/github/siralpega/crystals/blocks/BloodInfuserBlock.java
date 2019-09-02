package com.github.siralpega.firstmod.blocks;

import javax.annotation.Nullable;

import com.github.siralpega.firstmod.blocks.tiles.InfuserTileEntity;
import com.github.siralpega.firstmod.lists.BlockList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BloodInfuserBlock extends Block
{	
	
	//you can add powered too. just copy observer block, and it in textures / models
	// also you can fix it so that it can't face certain directions (up/down_. do so in blockstates json
	public BloodInfuserBlock()
	{
		super(Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 15.0f).sound(SoundType.STONE));
		this.setDefaultState(this.stateContainer.getBaseState().with(BlockStateProperties.FACING, Direction.NORTH));
	}
	
	public BlockState getStateForPlacement(BlockItemUseContext context)
	{
	      return this.getDefaultState().with(BlockStateProperties.FACING, context.getNearestLookingDirection().getOpposite());
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(BlockStateProperties.FACING);
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) 
	{
		if (!world.isRemote) 
		{
			final InfuserTileEntity tileEntity = (InfuserTileEntity) world.getTileEntity(pos);
			if (tileEntity != null)
				tileEntity.openGUI((ServerPlayerEntity) player);
		}
		return true;
	}

	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		return new InfuserTileEntity(BlockList.blood_infuser_tile);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) 
	{
		if (state.getBlock() != newState.getBlock()) 
		{
			final IItemHandler inventory = world.getTileEntity(pos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null);
			for(int i = 0; i < inventory.getSlots(); ++i)
				InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i));
			super.onReplaced(state, world, pos, newState, isMoving);
		}
	}
}
