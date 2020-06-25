package com.mindflexibility.folklorearcana.init;

import com.mindflexibility.folklorearcana.FolkloreArcana;
import com.mindflexibility.folklorearcana.FolkloreArcana.FolkloreArcanaItemGroup;
import com.mindflexibility.folklorearcana.objects.blocks.SpecialBlock;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;



@ObjectHolder(FolkloreArcana.MOD_ID)
@Mod.EventBusSubscriber(modid = FolkloreArcana.MOD_ID, bus = Bus.MOD)

public class BlockInit {
	public static final Block example_block = null;
	public static final Block gilded_marble_block = null;
	public static final Block special_block = null;
	
	
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event) {
		event.getRegistry().register(new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(0.5f, 15.0f).sound(SoundType.SAND)).setRegistryName("example_block"));
		event.getRegistry().register(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5f, 6.0f)).setRegistryName("gilded_marble_block"));
		event.getRegistry().register(new SpecialBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0f, 10.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.GLASS).lightValue(4).slipperiness(1.2f).speedFactor(0.7f).noDrops()).setRegistryName("special_block"));
	}
	
	
	@SubscribeEvent
	public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new BlockItem(example_block, new Item.Properties().maxStackSize(16).group(ItemGroup.MISC)).setRegistryName("example_block"));
		event.getRegistry().register(new BlockItem(gilded_marble_block, new Item.Properties().maxStackSize(64).group(ItemGroup.MISC)).setRegistryName("gilded_marble_block"));
		event.getRegistry().register(new BlockItem(special_block, new Item.Properties().maxStackSize(64).group(FolkloreArcanaItemGroup.instance)).setRegistryName("special_block"));
	}

}
