package com.github.siralpega.firstmod.lists;

import com.github.siralpega.firstmod.FirstModMain;
import com.github.siralpega.firstmod.FirstModMain.RegistryEvents;
import com.github.siralpega.firstmod.entities.FirstEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.event.RegistryEvent;

public class Entities //change class name to be more descriptive
{
	public static EntityType<?> FIRST_ENTITY = EntityType.Builder.create(FirstEntity::new, EntityClassification.CREATURE).build(FirstModMain.MODID + ":first_entity").setRegistryName(RegistryEvents.location("first_entity"));
	
	public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll
		(
				ItemList.first_entity_egg = registerEntitySpawnEgg(FIRST_ENTITY, 0x2f5882, 0x6f1499, "first_entity_egg")
				
		);
	}
	
	public static void registerEntityWorldSpawns()
	{
		registerEntityWorldSpawn(FIRST_ENTITY, Biomes.PLAINS, Biomes.BEACH, Biomes.DARK_FOREST, Biomes.MOUNTAINS);
	}
	
	public static Item registerEntitySpawnEgg(EntityType<?> type, int primaryColor, int secondaryColor, String name)
	{
		SpawnEggItem item = new SpawnEggItem(type, primaryColor, secondaryColor, new Item.Properties().group(FirstModMain.COPPERGROUP)); //switch group later to its own or whatever
		item.setRegistryName(RegistryEvents.location(name));
		return item;
	}
	
	public static void registerEntityWorldSpawn(EntityType<?> entity, Biome...biomes)
	{
		for(Biome biome : biomes)
		{
			if(biome != null)
			{
				biome.getSpawns(entity.getClassification()).add(new SpawnListEntry(entity, 10, 1, 10 )); //SpawnListEntry(entity, weight, min group spawn, max group spawn)
			}
		}
	}
}
