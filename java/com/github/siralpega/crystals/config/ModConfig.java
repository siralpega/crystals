package com.github.siralpega.firstmod.config;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.github.siralpega.firstmod.FirstModMain;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod.EventBusSubscriber
public class ModConfig 
{
	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SERVER_CONFIG;
	
	//Configuration Values
	public static ForgeConfigSpec.IntValue copper_ore_chance;
	public static ForgeConfigSpec.BooleanValue generate_overworld, crystal_generate_overworld;
	
	
	static
	{
		//Initialize & Write Configuration
		writeDefault(SERVER_BUILDER);
		
		SERVER_CONFIG = SERVER_BUILDER.build();

	}
	
	public static void loadConfig()
	{
		String path = FMLPaths.CONFIGDIR.get().resolve(FirstModMain.MODID + "-server.toml").toString();
		FirstModMain.LOGGER.info("Loading config: " + path);
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
		file.load();
		FirstModMain.LOGGER.info("Loaded config: " + path);
		SERVER_CONFIG.setConfig(file);
		ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.SERVER, ModConfig.SERVER_CONFIG, FirstModMain.MODID + "-server.toml");
	}
	
	private static void writeDefault(ForgeConfigSpec.Builder server)
	{
		server.comment("Crystal Config (Server)");
		crystal_generate_overworld = server.comment("Enable crystal spawning in caves").define("crystalgen.crystal_generate_overworld", true);
		
		//Oregen
		server.comment("Oregen Config");
		copper_ore_chance = server.comment("max size of copper ore vein")
							.defineInRange("oregen.copper_ore_chance", 5, 1, 100);
		
		
		generate_overworld = server.comment("Enable " + FirstModMain.MODID + " ores generating in Overworld")
							 .define("oregen.generate_overworld", true);
		
		
	}
}
