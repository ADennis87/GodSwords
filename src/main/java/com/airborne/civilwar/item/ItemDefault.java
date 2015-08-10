package com.airborne.civilwar.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.airborne.civilwar.CivilWar;
import com.airborne.civilwar.Extras;
import com.airborne.civilwar.Registry;
import com.airborne.civilwar.Strings;

public class ItemDefault extends Item{
	
	public ItemDefault(){
		super();
		this.setCreativeTab(CivilWar.tabCivilWar);
	}
}
