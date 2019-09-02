package com.github.siralpega.firstmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFaceBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class CrystalBlock extends HorizontalFaceBlock
{
	protected static final VoxelShape FLOOR_SHAPE = VoxelShapes.create(0.0D, 0.0D, 0.0D, 5.0D, 10.0D, 5.0D);;
	protected static final VoxelShape WALL_SHAPE = VoxelShapes.create(0.0D, 0.0D, 0.0D, 5.0D, 10.0D, 5.0D);;
	protected static final VoxelShape CEILING_SHAPE = VoxelShapes.create(0.0D, 0.0D, 0.0D, 5.0D, 10.0D, 5.0D);;
	
	 
	public CrystalBlock()
	{
		super(Properties.create(Material.GLASS).hardnessAndResistance(0.5f, 0.5f).sound(SoundType.GLASS).lightValue(5));
		this.setDefaultState(this.stateContainer.getBaseState().with(HORIZONTAL_FACING, Direction.NORTH).with(FACE, AttachFace.WALL));
	}
	
	@Override
	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos)
	{
		switch((AttachFace)state.get(FACE)) {
	      case FLOOR:
	         switch(state.get(HORIZONTAL_FACING).getAxis()) {
	         case X:
	            return FLOOR_SHAPE;
	         case Z:
	         default:
	            return FLOOR_SHAPE;
	         }
	      case WALL:
	         switch((Direction)state.get(HORIZONTAL_FACING)) {
	         case EAST:
	            return WALL_SHAPE;
	         case WEST:
	            return WALL_SHAPE;
	         case SOUTH:
	            return WALL_SHAPE;
	         case NORTH:
	         default:
	            return WALL_SHAPE;
	         }
	      case CEILING:
	      default:
	         switch(state.get(HORIZONTAL_FACING).getAxis()) {
	         case X:
	            return CEILING_SHAPE;
	         case Z:
	         default:
	            return CEILING_SHAPE;
	         } 
	      }
	}
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
	      builder.add(FACE, HORIZONTAL_FACING);
	}
	
	/**
	  * Gets the render layer this block will render on. SOLID for solid blocks, CUTOUT or CUTOUT_MIPPED for on-off
	  * transparency (glass, reeds), TRANSLUCENT for fully blended transparency (stained glass)
	  */
	@Override
	public BlockRenderLayer getRenderLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}
	
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) { return false; }
	
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) { return false; }
	   
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) { return false; }
	  
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)  { return true; }
}
