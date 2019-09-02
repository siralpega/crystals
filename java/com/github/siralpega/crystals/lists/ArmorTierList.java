package com.github.siralpega.firstmod.lists;

import com.github.siralpega.firstmod.FirstModMain;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public enum ArmorTierList implements IArmorMaterial
{
	Copper("copper", 300, new int[] {3,5,5,1}, 10, ItemList.copper_ingot, "item.armor.equip_iron", 0.0f);
	
	private static final int[] MAX_DAMAGE_ARRAY = new int[] {13,15,16,11};
	private String name, equipSound;
	private int durability, enchantability; 
	private int[] damageReductionAmounts;
	private Item repairItem;
	private float toughness;
	private ArmorTierList(String name, int durability, int[] damageReductionAmounts, int enchantability, Item repiarItem, String equipSound, float toughness)
	{
		this.name = name;
		this.durability = durability;   //if you want custom durability per item, then you need to create int[] to hold dur amount and change getGDurability to get the proper int (like getDamageReduction)
		this.damageReductionAmounts = damageReductionAmounts;
		this.enchantability = enchantability;
		this.repairItem = repiarItem;
		this.equipSound = equipSound;
		this.toughness = toughness;
	}
	@Override
	public int getDurability(EquipmentSlotType slotIn) 
	{
		return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.durability;
	}
	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) 
	{
		return this.damageReductionAmounts[slotIn.getIndex()];
	}
	@Override
	public int getEnchantability() 
	{
		return this.enchantability;
	}
	@Override
	public SoundEvent getSoundEvent()
	{
		return new SoundEvent(new ResourceLocation(equipSound));
	}
	@Override
	public Ingredient getRepairMaterial()
	{
		return Ingredient.fromItems(this.repairItem);
	}
	@Override
	public String getName() 
	{
		return FirstModMain.MODID + ":" + this.name;
	}
	@Override
	public float getToughness()
	{
		return this.toughness;
	}
}
