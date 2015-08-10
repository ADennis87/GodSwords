package com.airborne.godswords.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.airborne.godswords.CommonProxy;
import com.airborne.godswords.GodSwords;
import com.airborne.godswords.Registry;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerRenderThings(){
		for(int i = 0; i < Registry.swords.length; i++){
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Registry.swords[i],  0, new ModelResourceLocation(GodSwords.modid + ":" + Registry.swords[i].getUnlocalizedName().substring(5), "inventory"));
		}
	}
}
