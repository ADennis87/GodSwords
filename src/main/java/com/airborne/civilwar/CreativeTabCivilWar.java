package com.airborne.civilwar;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTabCivilWar extends CreativeTabs{

	public CreativeTabCivilWar(int position, String name) {
		super(position,name);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Item getTabIconItem() {
		return Registry.musket;
	}

}
