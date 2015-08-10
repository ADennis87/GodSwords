package com.airborne.godswords.sword;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

import com.airborne.godswords.GodSwords;
import com.airborne.godswords.Registry;

public class ItemGodSword extends ItemSword{
		
	public static int ef;
	
	public ItemGodSword(int par1) {
		super(Registry.materials[par1]);
		this.setCreativeTab(GodSwords.tabGodSwords);
		ef = par1;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3){
		if(!par2.isRemote && par3.getHeldItem().getItem() != null && par3.getHeldItem().getItem() instanceof ItemGodSword){
			String i = par3.getHeldItem().getUnlocalizedName();
			if(i.equals(Registry.swords[7].getUnlocalizedName())){
				par3.worldObj.spawnEntityInWorld(new EntityEnderPearl(par3.worldObj, par3));
			}
		}
		return par1;
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn){
		stack.addEnchantment(Enchantment.looting, 3);
		super.onCreated(stack, worldIn, playerIn);
	}
	
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems){
		if(itemIn.getUnlocalizedName().equals(Registry.swords[9].getUnlocalizedName())){
			ItemStack i = new ItemStack(Registry.swords[9]);
			i.addEnchantment(Enchantment.looting, 3);
			subItems.add(i);
		}
		super.getSubItems(itemIn, tab, subItems);
	}
}
