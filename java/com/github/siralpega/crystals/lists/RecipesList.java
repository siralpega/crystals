package com.github.siralpega.firstmod.lists;

import com.github.siralpega.firstmod.api.Recipes;

import net.minecraft.item.ItemStack;

public class RecipesList extends Recipes
{
	
	public static void setup()
	{
		addInfuserRecipe(new ItemStack(ItemList.copper_axe, 1), new ItemStack(ItemList.copper_ingot, 5), new ItemStack(ItemList.blood_axe, 1));
	}
}
