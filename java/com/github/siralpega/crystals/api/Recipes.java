package com.github.siralpega.firstmod.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
/**
 * Common recipe types for TileEntites. Put into api, then create sub-class (RecipesList) to declare recipes using add_____Recipe().
 * In Main Class in setup method, run RecipesList.setup().
 * @author siralpega
 *
 */
public class Recipes
{

	private static List<ItemStack> infuserRecipes = new ArrayList<ItemStack>();
	
	/**
	 * Adds a recipe for the Infuser
	 * @param input 1
	 * @param input 2
	 * @param output
	 */
	public static void addInfuserRecipe(ItemStack x, ItemStack y, ItemStack result)
	{
		infuserRecipes.add(infuserRecipes.size(), x);
		infuserRecipes.add(infuserRecipes.size(), y);
		infuserRecipes.add(infuserRecipes.size(), result);
	}
	/**
	 * If the recipe exists, returns the location in infuserRecipes
	 * @param input 1
	 * @param input 2
	 * @return location of recipe in List. returns -1 if not valid
	 */
	private static int isInfuserRecipe(ItemStack x, ItemStack y) 
	{
		int num = -1;
		int i = 0;
		int temp = y.getCount();
		while(num == -1 && i < infuserRecipes.size())
		{
			if(infuserRecipes.get(i).equals(x, true))
				num = i;
			i++;
		}
		if(y.getCount() > infuserRecipes.get(num + 1).getCount()) //if the itemstack y 's count is greater than recipe, set it to recipe count to check if equal
			y.setCount(infuserRecipes.get(num + 1).getCount());
		if(num > -1 && num != infuserRecipes.size() && infuserRecipes.get(num + 1).equals(y, false))
		{
			y.setCount(temp);
			return num;
		}
		return -1;
	}	
	
	/**
	 * 
	 * @param input 1
	 * @param input 2
	 * @return Output of the recipe. RETURNS EMPTY IF NOT A RECIPE. call isEmpty().
	 */
	public static ItemStack getInfuserResult(ItemStack x, ItemStack y) 
	{
		for(int i = 0; i < infuserRecipes.size() / 3; i = i + 3)
		{
			int num = isInfuserRecipe(x,y);
			if(num != -1)
				return infuserRecipes.get(num + 2);
		}
		return ItemStack.EMPTY;
	}
	
	public static int[] getInfuserAmount(ItemStack a, ItemStack b)
	{
		int num = isInfuserRecipe(a,b);
		if(num != -1)
		{
			return new int[] {infuserRecipes.get(num).getCount(), infuserRecipes.get(num + 1).getCount()};
		}
		return new int[] {-1, -1};
	}

}
