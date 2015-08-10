package com.airborne.godswords;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabGodSwords extends CreativeTabs{

	public CreativeTabGodSwords(int position, String name) {
		super(position,name);
	}

	@Override
	public Item getTabIconItem() {
		return Registry.swords[9];
	}

}
