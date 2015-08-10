package com.airborne.civilwar.item;

import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.airborne.civilwar.CivilWar;
import com.airborne.civilwar.Strings;

public class ItemSaber extends ItemSword{

	public ItemSaber(ToolMaterial par3EnumToolMaterial) {
		super(par3EnumToolMaterial);
		
		this.setCreativeTab(CivilWar.tabCivilWar);
	}
}
