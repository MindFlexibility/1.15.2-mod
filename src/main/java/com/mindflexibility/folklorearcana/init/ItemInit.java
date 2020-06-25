package com.mindflexibility.folklorearcana.init;

import java.util.function.Supplier;

import com.mindflexibility.folklorearcana.FolkloreArcana;
import com.mindflexibility.folklorearcana.FolkloreArcana.FolkloreArcanaItemGroup;
import com.mindflexibility.folklorearcana.objects.items.SpecialItem;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;


@Mod.EventBusSubscriber(modid = FolkloreArcana.MOD_ID, bus = Bus.MOD)
@ObjectHolder(FolkloreArcana.MOD_ID)


public class ItemInit {
	
	public static final Item example_item = null;
	public static final Item lightning_item = null;
	public static final Item vine_item = null;
	public static final Item special_item = null;
	
	
	
	
	
	//Tools
	public static final Item example_sword = null;
	public static final Item example_pickaxe = null;
	public static final Item example_axe = null;
	public static final Item example_shovel = null;
	public static final Item example_hoe = null;
	
	
	//Armor
	public static final Item example_helmet = null;
	public static final Item example_chestplate = null;
	public static final Item example_leggings = null;
	public static final Item example_boots = null;
	public static final Item test_helmet = null;
	
	
	
	
	
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName("example_item"));
		event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName("lightning_item"));
		event.getRegistry().register(new Item(new Item.Properties().group(FolkloreArcanaItemGroup.instance).food(new Food.Builder().hunger(3).saturation(1.2f).effect(new EffectInstance(Effects.STRENGTH, 6000, 0), 1.0f).build())).setRegistryName("vine_item"));
		event.getRegistry().register(new SpecialItem(new Item.Properties().group(FolkloreArcanaItemGroup.instance)).setRegistryName("special_item"));
		
		
		
		
		
		//Tools
		event.getRegistry().register(new SwordItem(ModItemTier.EXAMPLE, 8, -3.0f, new Item.Properties().group(FolkloreArcanaItemGroup.instance)).setRegistryName("example_sword"));
		event.getRegistry().register(new PickaxeItem(ModItemTier.EXAMPLE, 4, -2.7f, new Item.Properties().group(FolkloreArcanaItemGroup.instance)).setRegistryName("example_pickaxe"));
		event.getRegistry().register(new AxeItem(ModItemTier.EXAMPLE, 5, -2.8f, new Item.Properties().group(FolkloreArcanaItemGroup.instance)).setRegistryName("example_axe"));
		event.getRegistry().register(new ShovelItem(ModItemTier.EXAMPLE, 1, -2.0f, new Item.Properties().group(FolkloreArcanaItemGroup.instance)).setRegistryName("example_shovel"));
		event.getRegistry().register(new HoeItem(ModItemTier.EXAMPLE, 0.0f, new Item.Properties().group(FolkloreArcanaItemGroup.instance)).setRegistryName("example_hoe"));
	
	
		//Armor
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.HEAD, new Item.Properties().group(FolkloreArcanaItemGroup.instance)). setRegistryName("example_helmet"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.CHEST, new Item.Properties().group(FolkloreArcanaItemGroup.instance)). setRegistryName("example_chestplate"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.LEGS, new Item.Properties().group(FolkloreArcanaItemGroup.instance)). setRegistryName("example_leggings"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.FEET, new Item.Properties().group(FolkloreArcanaItemGroup.instance)). setRegistryName("example_boots"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.EXAMPLE, EquipmentSlotType.HEAD, new Item.Properties().group(FolkloreArcanaItemGroup.instance)).setRegistryName("test_helmet"));
	
	
	}
	
	public enum ModItemTier implements IItemTier{
		EXAMPLE(0, 2000, 2.0f, 0, 5, () -> {
			return Ingredient.fromItems(ItemInit.example_item);
		});
		
		private final int harvestLevel;
		private final int maxUses;
		private final float efficiency;
		private final int attackDamage;
		private final int enchantability;
		private final LazyValue<Ingredient> repairMaterial;
		
		
		private ModItemTier(int harvestLevel, int maxUses, float efficiency, int attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
			
			this.harvestLevel = harvestLevel;
			this.maxUses = maxUses;
			this.efficiency = efficiency;
			this.attackDamage = attackDamage;
			this.enchantability = enchantability;
			this.repairMaterial = new LazyValue<>(repairMaterial);
		}


		@Override
		public int getMaxUses() {
			
			return this.maxUses;
		}


		@Override
		public float getEfficiency() {
			return this.efficiency;
		}


		@Override
		public float getAttackDamage() {
			return this.attackDamage;
		}


		@Override
		public int getHarvestLevel() {
			return this.harvestLevel;
		}


		@Override
		public int getEnchantability() {
			return this.enchantability;
		}


		@Override
		public Ingredient getRepairMaterial() {
			return this.repairMaterial.getValue();
		}
	}
	
	
	public enum ModArmorMaterial implements IArmorMaterial{
		TEST(FolkloreArcana.MOD_ID + ":test", 5, new int[] {7, 9, 11, 7}, 420, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 6.9f, () -> {
			return Ingredient.fromItems(ItemInit.lightning_item);
		}),
		EXAMPLE(FolkloreArcana.MOD_ID + ":example", 5, new int[] {7, 9, 11, 7}, 420, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 6.9f, () -> {
			return Ingredient.fromItems(ItemInit.lightning_item);
		});
		
		
		private static final int[] MAX_DAMAGE_ARRAY = new int[] {16, 16, 16, 16};
		private final String name;
		private final int maxDamageFactor;
		private final int[] damageReductionAmountArray;
		private final int enchantability;
		private final SoundEvent soundEvent;
		private final float toughness;
		private final LazyValue<Ingredient> repairMaterial;
		
		
		
		private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn, Supplier<Ingredient> repairMaterialIn) {
			this.name = nameIn;
			this.maxDamageFactor = maxDamageFactorIn;
			this.damageReductionAmountArray = damageReductionAmountIn;
			this.enchantability = enchantabilityIn;
			this.soundEvent = soundEventIn;
			this.toughness = toughnessIn;
			this.repairMaterial = new LazyValue<>(repairMaterialIn);
		}



		@Override
		public int getDurability(EquipmentSlotType slotIn) {
			return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
		}



		@Override
		public int getDamageReductionAmount(EquipmentSlotType slotIn) {
			return this.damageReductionAmountArray[slotIn.getIndex()];
		}



		@Override
		public int getEnchantability() {
			return this.enchantability;
		}



		@Override
		public SoundEvent getSoundEvent() {
			return this.soundEvent;
		}



		@Override
		public Ingredient getRepairMaterial() {
			return this.repairMaterial.getValue();
		}


		@OnlyIn(Dist.CLIENT)

		@Override
		public String getName() {
			return this.name;
		}



		@Override
		public float getToughness() {
			return this.toughness;
		}
		
	}
	
	

}
