package com.airborne.civilwar;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.airborne.civilwar.entity.EntitySoldier;
import com.airborne.civilwar.item.ItemDefault;

public class Extras {
		
	public static EntitySoldier[] soldiers;

	public static void addBlock(Block block, String name, float hardness){
		GameRegistry.registerBlock(block, name);
		block.setHardness(hardness);
	}
	public static Item addDefaultItem(Item par1, String name){
		par1 = new ItemDefault();
		par1.setUnlocalizedName(name);
		GameRegistry.registerItem(par1, name);
		return par1;
	}
	/*public List getSoldiersAroundEntity(EntityLivingBase par1){
		par1.worldObj.getClosestPlayerToEntity(par1Entity, par2)
	}*/
}
