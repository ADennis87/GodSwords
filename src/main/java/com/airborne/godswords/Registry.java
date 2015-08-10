package com.airborne.godswords;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.airborne.godswords.sword.ItemGodSword;

public class Registry {
	
	/**DO NOT USE ANY OF THESE.  THEY ARE NOT INITIALIZED, AND WILL CRASH THE GAME*/
	private static Item Quartz, Obsidian, Coal, Redstone, Iron, Lapis, Gold, Endstone, Diamond, Emerald;
		
	public static Item[] swords = {Quartz, Obsidian, Coal, Redstone, Iron, Lapis, Gold, Endstone, Diamond, Emerald};
	
	public static int[] maxUses = {2000, 7500, 1500, 3000, 3500, 2000, 2500, 2000, 6000, 6500};
	
	public static float[] damage = {21, 31, 12, 19, 23, 14, 17, 21, 37, 42};
	
	public static Item.ToolMaterial[] materials = {
		EnumHelper.addToolMaterial("quartzgod", 0, Registry.maxUses[0], 12.0F, Registry.damage[0], 22),
		EnumHelper.addToolMaterial("obsidiangod", 0, Registry.maxUses[1], 12.0F, Registry.damage[1], 22),
		EnumHelper.addToolMaterial("coalgod", 0, Registry.maxUses[2], 12.0F, Registry.damage[2], 22),
		EnumHelper.addToolMaterial("redstonegod", 0, Registry.maxUses[3], 12.0F, Registry.damage[3], 22),
		EnumHelper.addToolMaterial("irongod", 0, Registry.maxUses[4], 12.0F, Registry.damage[4], 22),
		EnumHelper.addToolMaterial("lapisgod", 0, Registry.maxUses[5], 12.0F, Registry.damage[5], 22),
		EnumHelper.addToolMaterial("goldgod", 0, Registry.maxUses[6], 12.0F, Registry.damage[6], 44),
		EnumHelper.addToolMaterial("endstonegod", 0, Registry.maxUses[7], 12.0F, Registry.damage[7], 22),
		EnumHelper.addToolMaterial("diamondgod", 0, Registry.maxUses[8], 12.0F, Registry.damage[8], 22),
		EnumHelper.addToolMaterial("emeraldgod", 0, Registry.maxUses[9], 12.0F, Registry.damage[9], 22)
	};
			
	public static void preInit() {
		BlocksAndItems();
		GameRegistry();
	}	

	private static void GameRegistry() {	
		GameRegistry.addRecipe(new ItemStack(swords[0], 1), " xx", "yxx", "zy ", 'x', Blocks.quartz_block, 'y', Items.iron_ingot, 'z', Blocks.end_stone);
		GameRegistry.addRecipe(new ItemStack(swords[1], 1), " xx", "yxx", "zy ", 'x', Blocks.obsidian, 'y', Items.iron_ingot, 'z', Items.diamond);
		GameRegistry.addRecipe(new ItemStack(swords[2], 1), " xx", "yxx", "yy ", 'x', Blocks.coal_block, 'y', Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(swords[3], 1), " xx", "yxx", "zy ", 'x', Blocks.redstone_block, 'y', Items.iron_ingot, 'z', Blocks.obsidian);
		GameRegistry.addRecipe(new ItemStack(swords[4], 1), " xx", "yxx", "zy ", 'x', Blocks.iron_block, 'y', Items.iron_ingot, 'z', Items.redstone);
		GameRegistry.addRecipe(new ItemStack(swords[5], 1), " xx", "yxx", "zy ", 'x', Blocks.lapis_block, 'y', Items.iron_ingot, 'z', Items.coal);
		GameRegistry.addRecipe(new ItemStack(swords[6], 1), " xx", "yxx", "zy ", 'x', Blocks.gold_block, 'y', Items.iron_ingot, 'z', new ItemStack(Items.dye, 1, 4));
		GameRegistry.addRecipe(new ItemStack(swords[7], 1), " xx", "yxx", "zy ", 'x', Blocks.end_stone, 'y', Items.iron_ingot, 'z', Items.quartz);
		GameRegistry.addRecipe(new ItemStack(swords[8], 1), " xx", "yxx", "zy ", 'x', Blocks.diamond_block, 'y', Items.iron_ingot, 'z', Items.emerald);
		GameRegistry.addShapedRecipe(new ItemStack(swords[9], 1), " xx", "yxx", "zy ", 'x', Blocks.emerald_block, 'y', Items.iron_ingot, 'z', Items.gold_ingot);
	}

	private static void BlocksAndItems() {
		for(int i = 0; i < materials.length; i++){
			swords[i] = new ItemGodSword(i).setUnlocalizedName(materials[i].toString());
			GameRegistry.registerItem(swords[i], swords[i].getUnlocalizedName().substring(5), GodSwords.modid);
		}
	}

	public static void Init() {
	}
	
	public static void postInit(){
		FMLLog.info("God Sword v" + GodSwords.version + " initialized");
	}

}
