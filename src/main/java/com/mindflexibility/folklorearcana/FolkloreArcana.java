package com.mindflexibility.folklorearcana;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mindflexibility.folklorearcana.init.ItemInit;
import com.mindflexibility.folklorearcana.world.gen.FolkloreArcanaOreGen;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("folklorearcana")
@Mod.EventBusSubscriber(modid = FolkloreArcana.MOD_ID, bus = Bus.MOD)
public class FolkloreArcana
{

    public static final Logger LOGGER = LogManager.getLogger();

    
    public static final String MOD_ID = "folklorearcana";
    
    public static FolkloreArcana instance;
    
    
    
    public FolkloreArcana() {
    	
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	
    	modEventBus.addListener(this::setup);
    	modEventBus.addListener(this::doClientStuff);
        
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }



    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }
    
    
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
    	FolkloreArcanaOreGen.generateOre();
    }
    
    
    
    
    
    public static class FolkloreArcanaItemGroup extends ItemGroup{
    	
    	public static final FolkloreArcanaItemGroup instance = new FolkloreArcanaItemGroup(ItemGroup.GROUPS.length, "folklorearcana");
    	
    	
    	private FolkloreArcanaItemGroup(int index, String label) {
    		super(index, label);
    	}
    	
    	
    	@Override
    	public ItemStack createIcon() {
    		 return new ItemStack(ItemInit.example_item);
    	}
    	
    	
    }
    
    

}
