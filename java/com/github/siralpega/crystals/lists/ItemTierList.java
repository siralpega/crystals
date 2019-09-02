package com.github.siralpega.firstmod.lists;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public enum ItemTierList implements IItemTier
{
	Copper(4.5f, 5.0f, 300, 2, 10, ItemList.copper_ingot);
	//, copper2();
	
	private float attackDamage, efficiency;
	private int durability, harvestLevel, enchantability;
	private Item repairMat;
	
	private ItemTierList(float attackDamage, float efficiency, int durability, int harvestLevel, int enchantability, Item repairMat) 
	{
		this.attackDamage = attackDamage;
		this.efficiency = efficiency;
		this.durability = durability;
		this.harvestLevel = harvestLevel;
		this.enchantability = enchantability;
		this.repairMat = repairMat;
		
	}

	@Override
	public int getMaxUses() 
	{
		return this.durability;
	}

	@Override
	public float getEfficiency()
	{
		return this.efficiency;
	}

	@Override
	public float getAttackDamage()
	{
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() 
	{
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		
		return Ingredient.fromItems(this.repairMat);
	}
}
