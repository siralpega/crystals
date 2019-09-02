package com.github.siralpega.firstmod.world;

import com.github.siralpega.firstmod.config.ModConfig;
import com.github.siralpega.firstmod.lists.BlockList;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.BlockBlobConfig;
import net.minecraft.world.gen.feature.BushConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GlowstoneBlobFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.ChanceRangeConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class CaveGeneration
{
	private static final CountRangeConfig COPPER_ORE_PLACEMENT = new CountRangeConfig(10, 30, 0, 75); //max num of veins in a chunk, lowest level can spawn (min), maxOffset, highest level can spawn (max)
	
	private static final BushConfig CRYSTAL_CONFIG = new BushConfig(BlockList.crystal_blue.getDefaultState());
	private static final ChanceRangeConfig CRYSTAL_CHANCE_RANGE = new ChanceRangeConfig(0.1F, 20, 20, 60);
	private static final CountRangeConfig CRYSTAL_COUNT_RANGE = new CountRangeConfig(2, 20, 20, 60);
	private static final FrequencyConfig CRYSTAL_FREQ = new FrequencyConfig(10);


	//THIS METHOD IS USED FOR COOPER ORE. IF YOU EVER MAKE THIS INTO A MOD, YOU'LL PROAB REMOVE THIS!!!!!!!!!!!!!!
	public static void setupOreGeneration()
	{
		if(ModConfig.generate_overworld.get())
		{
			for(Biome biome : ForgeRegistries.BIOMES)
			{
				// we have no End or Nether ores, so skip those.
				if (biome.getCategory() == Biome.Category.THEEND || biome.getCategory() == Biome.Category.NETHER)
					continue;

				//Crystals
			/*	biome.addFeature(Decoration.UNDERGROUND_DECORATION, 
						Biome.createDecoratedFeature(
								Feature.BUSH, 
								new BushConfig(BlockList.crystal_blue.getDefaultState()), 
								Placement.CHANCE_RANGE, 
								new ChanceRangeConfig(0.5F, 0, 0, 128)));
					biome.addFeature(Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(Feature.GLOWSTONE_BLOB, IFeatureConfig.NO_FEATURE_CONFIG, Placement.LIGHT_GEM_CHANCE, CRYSTAL_FREQ));
					biome.addFeature(Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(Feature.GLOWSTONE_BLOB, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_RANGE, CRYSTAL_PLACEMENT));
			*/

				//Copper Ore
				biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(FillerBlockType.NATURAL_STONE, BlockList.copper_ore.getDefaultState(), ModConfig.copper_ore_chance.get()), Placement.COUNT_RANGE, COPPER_ORE_PLACEMENT));
			}
		}
	}

	public static void setupCaveGeneration()
	{
		if(ModConfig.crystal_generate_overworld.get())
		{
			for(Biome biome : ForgeRegistries.BIOMES)
			{
				if (biome.getCategory() == Biome.Category.THEEND || biome.getCategory() == Biome.Category.NETHER)
					continue;
				biome.addFeature(Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(
																	Feature.BUSH, 
																	CRYSTAL_CONFIG, 
																	Placement.CHANCE_RANGE, 
																	CRYSTAL_CHANCE_RANGE));  
				biome.addFeature(Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(Feature.BUSH, CRYSTAL_CONFIG , Placement.LIGHT_GEM_CHANCE, CRYSTAL_FREQ));
				biome.addFeature(Decoration.UNDERGROUND_DECORATION, Biome.createDecoratedFeature(
																	Feature.BUSH, 
																	CRYSTAL_CONFIG , 
																	Placement.COUNT_RANGE, 
																	CRYSTAL_COUNT_RANGE));  
			}
		}
	}
}
