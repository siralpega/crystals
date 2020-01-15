package com.github.siralpega.firstmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.siralpega.firstmod.blocks.BloodInfuserBlock;
import com.github.siralpega.firstmod.blocks.BloodInfuserScreen;
import com.github.siralpega.firstmod.blocks.CrystalBlock;
import com.github.siralpega.firstmod.blocks.tiles.InfuserTileEntity;
import com.github.siralpega.firstmod.client.renders.RenderRegistry;
import com.github.siralpega.firstmod.config.ModConfig;
import com.github.siralpega.firstmod.items.CustomAxeItem;
import com.github.siralpega.firstmod.lists.ArmorTierList;
import com.github.siralpega.firstmod.lists.BlockList;
import com.github.siralpega.firstmod.lists.Entities;
import com.github.siralpega.firstmod.lists.ItemList;
import com.github.siralpega.firstmod.lists.ItemTierList;
import com.github.siralpega.firstmod.lists.RecipesList;
import com.github.siralpega.firstmod.world.CaveGeneration;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
/**
 * TODO:
 * put inventory and util (for Recipes class) as stand alone inside project. then just copy these for other projects
 */
/**
 * IDEA: CRYSTALS
 * Crystal shards / pieces are gathered from  a biome, either through mining (in caves?) or mob drops. 
 * Using an infuser, you can put these shards together to from a crystal.
 * Each crystal has their own color an special function. For example,  a yellow crystal acts as a battery.
 * When a crystal is active in its function, it will glow and give off a light level (use block states to accomplish).
 *
 */
@Mod("alpegafirstmod")
public class FirstModMain
{
	public static FirstModMain instance;
	public static final String MODID = "alpegafirstmod";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public static final ItemGroup COPPERGROUP = new CopperItemGroup();

	public FirstModMain()
	{
		instance = this;

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);

		ModConfig.loadConfig();
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		CaveGeneration.setupOreGeneration();  //remove
		CaveGeneration.setupCaveGeneration();
		//TileEntities's Recipes
		RecipesList.setup();
		LOGGER.info("Setup method registered!");
	}

	private void clientRegistries(final FMLClientSetupEvent event)
	{
		RenderRegistry.registryEntityRenders();
		ScreenManager.registerFactory(BlockList.blood_infuser_container, BloodInfuserScreen::new);

		LOGGER.info("Client method registered!");
	}

	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents //move this to its own separate .class file. i mean, it is already its own class but moving to its own file will be better organization
	{


		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			Item.Properties itemProperties = new Item.Properties().group(COPPERGROUP);

			event.getRegistry().registerAll
			(
					//Copper Items
					ItemList.copper_ingot = new Item(itemProperties).setRegistryName(location("copper_ingot")),
					ItemList.copper_axe = new AxeItem(ItemTierList.Copper, 3.0f, -3.1f, itemProperties).setRegistryName(location("copper_axe")),
					ItemList.copper_pickaxe = new PickaxeItem(ItemTierList.Copper, -2, -2.8f, itemProperties).setRegistryName(location("copper_pickaxe")),
					ItemList.copper_shovel = new ShovelItem(ItemTierList.Copper, -1.5f, -3.0f, itemProperties).setRegistryName(location("copper_shovel")),
					ItemList.copper_sword = new SwordItem(ItemTierList.Copper, 0, -2.5f, itemProperties).setRegistryName(location("copper_sword")),
					ItemList.copper_hoe = new HoeItem(ItemTierList.Copper, -1.5f, itemProperties).setRegistryName(location("copper_hoe")),
					ItemList.copper_helmet = new ArmorItem(ArmorTierList.Copper, EquipmentSlotType.HEAD, itemProperties).setRegistryName("copper_helmet"),
					ItemList.copper_chestplate = new ArmorItem(ArmorTierList.Copper, EquipmentSlotType.CHEST, itemProperties).setRegistryName("copper_chestplate"),
					ItemList.copper_leggings = new ArmorItem(ArmorTierList.Copper, EquipmentSlotType.LEGS, itemProperties).setRegistryName("copper_leggings"),
					ItemList.copper_boots = new ArmorItem(ArmorTierList.Copper, EquipmentSlotType.FEET, itemProperties).setRegistryName("copper_boots"),

					ItemList.copper_ore = new BlockItem(BlockList.copper_ore, new Item.Properties().group(COPPERGROUP)).setRegistryName(BlockList.copper_ore.getRegistryName()),	
					ItemList.copper_block = new BlockItem(BlockList.copper_block, new Item.Properties().group(COPPERGROUP)).setRegistryName(BlockList.copper_block.getRegistryName()),

					//Crystals
					ItemList.crystal_red = new BlockItem(BlockList.crystal_red, new Item.Properties().group(COPPERGROUP)).setRegistryName(BlockList.crystal_red.getRegistryName()),
					ItemList.crystal_orange = new BlockItem(BlockList.crystal_orange, new Item.Properties().group(COPPERGROUP)).setRegistryName(BlockList.crystal_orange.getRegistryName()),
					ItemList.crystal_yellow = new BlockItem(BlockList.crystal_yellow, new Item.Properties().group(COPPERGROUP)).setRegistryName(BlockList.crystal_yellow.getRegistryName()),
					ItemList.crystal_green = new BlockItem(BlockList.crystal_green, new Item.Properties().group(COPPERGROUP)).setRegistryName(BlockList.crystal_green.getRegistryName()),
					ItemList.crystal_blue = new BlockItem(BlockList.crystal_blue, new Item.Properties().group(COPPERGROUP)).setRegistryName(BlockList.crystal_blue.getRegistryName()),
					ItemList.crystal_white = new BlockItem(BlockList.crystal_white, new Item.Properties().group(COPPERGROUP)).setRegistryName(BlockList.crystal_white.getRegistryName()),
					ItemList.crystal_black = new BlockItem(BlockList.crystal_black, new Item.Properties().group(COPPERGROUP)).setRegistryName(BlockList.crystal_black.getRegistryName()),

					ItemList.blood_infuser = new BlockItem(BlockList.blood_infuser, new Item.Properties().group(COPPERGROUP)).setRegistryName(BlockList.blood_infuser.getRegistryName()),
					ItemList.blood_axe = new CustomAxeItem(ItemTierList.Copper, 3.5f, -3.1f, itemProperties).setRegistryName(location("blood_axe"))
					);

			Entities.registerEntitySpawnEggs(event);

			LOGGER.info("Items registered!");

		}

		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event)
		{
			//Block.Properties blockProperties = Block.Properties.create(Material.IRON);

			event.getRegistry().registerAll
			(
					BlockList.copper_ore = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2.5f, 3.0f).sound(SoundType.STONE).harvestLevel(2)).setRegistryName(location("copper_ore")),
					BlockList.copper_block = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(4.0f, 3.0f).sound(SoundType.METAL)).setRegistryName(location("copper_block")),

					//Crystals TODO: If you need to make the block have a gui / custom states, then it should be its own class. Otherwise, there is no need to make a custom class.
					BlockList.crystal_red = new CrystalBlock().setRegistryName(RegistryEvents.location("crystal_red")),
					BlockList.crystal_orange = new CrystalBlock().setRegistryName(RegistryEvents.location("crystal_orange")),
					BlockList.crystal_yellow = new CrystalBlock().setRegistryName(RegistryEvents.location("crystal_yellow")),
					BlockList.crystal_green = new CrystalBlock().setRegistryName(RegistryEvents.location("crystal_green")),
					BlockList.crystal_blue = new CrystalBlock().setRegistryName(RegistryEvents.location("crystal_blue")),
					BlockList.crystal_white = new CrystalBlock().setRegistryName(RegistryEvents.location("crystal_white")),
					BlockList.crystal_black = new CrystalBlock().setRegistryName(RegistryEvents.location("crystal_black")),

					BlockList.blood_infuser = new BloodInfuserBlock().setRegistryName(RegistryEvents.location("blood_infuser"))
					);

			LOGGER.info("Blocks registered!");

		}

		@SubscribeEvent
		public static void registerBlockTiles(final RegistryEvent.Register<TileEntityType<?>> event)
		{ //registering the block itself in BlockList directly
			event.getRegistry().register(TileEntityType.Builder.create(() -> new InfuserTileEntity(BlockList.blood_infuser_tile), BlockList.blood_infuser).build(null).setRegistryName(BlockList.blood_infuser.getRegistryName()));
		}

		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event)
		{
			event.getRegistry().registerAll
			(
					Entities.FIRST_ENTITY	
					);

			Entities.registerEntityWorldSpawns();
		}

		@SubscribeEvent
		public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event)
		{ //registering containers for the client. server side is registered  in createMenu method in TitleEntity class for the block

			event.getRegistry().registerAll
			(
					IForgeContainerType.create(com.github.siralpega.firstmod.blocks.BloodInfuserContainer::new).setRegistryName(BlockList.blood_infuser.getRegistryName())
					);


		}

		public static ResourceLocation location(String name)
		{ //returns the ResourceLocation object of the resource (item)
			return new ResourceLocation(MODID, name);
		}

	}

}
