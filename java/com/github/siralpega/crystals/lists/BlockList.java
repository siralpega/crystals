package com.github.siralpega.firstmod.lists;

import com.github.siralpega.firstmod.FirstModMain;
import com.github.siralpega.firstmod.blocks.BloodInfuserContainer;
import com.github.siralpega.firstmod.blocks.tiles.InfuserTileEntity;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class BlockList
{
	public static Block copper_block;
	public static Block copper_ore;
	public static Block blood_infuser;
	
	//Crystals
	public static Block crystal_red;
	public static Block crystal_orange;
	public static Block crystal_yellow;
	public static Block crystal_green;
	public static Block crystal_blue;
	public static Block crystal_white;
	public static Block crystal_black;
	
	
	
	@ObjectHolder(FirstModMain.MODID + ":blood_infuser")
	public static TileEntityType<InfuserTileEntity> blood_infuser_tile;
	
	@ObjectHolder(FirstModMain.MODID + ":blood_infuser")
	public static ContainerType<BloodInfuserContainer> blood_infuser_container;
}
