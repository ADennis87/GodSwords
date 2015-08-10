package com.airborne.civilwar.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.airborne.civilwar.CivilWar;

public class ItemHaversack extends ItemDefault{
	
	public ItemHaversack() {
		super();
	}
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		par3EntityPlayer.openGui(CivilWar.instance, 9, par2World, par3EntityPlayer.serverPosX, par3EntityPlayer.serverPosY, par3EntityPlayer.serverPosZ);
        return par1ItemStack;
    }
}
