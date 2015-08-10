package com.airborne.civilwar.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import com.airborne.civilwar.CivilWarCommonProxy;
import com.airborne.civilwar.entity.EntityMusketBall;
import com.airborne.civilwar.entity.EntitySoldier_Rebel;
import com.airborne.civilwar.entity.EntitySoldier_Union;
import com.airborne.civilwar.render.RenderAmmo;
import com.airborne.civilwar.render.RenderRebelSoldier;
import com.airborne.civilwar.render.RenderUnionSoldier;
import com.airborne.civilwar.render.TileEntityCannonRenderer;
import com.airborne.civilwar.render.TileEntityMucketRenderer;
import com.airborne.civilwar.tileentity.TileEntityCannon;
import com.airborne.civilwar.tileentity.TileEntityMucket;


public class CivilWarClientProxy extends CivilWarCommonProxy{
	  @Override
	  public void registerRenderThings()
	  {
		    RenderingRegistry.registerEntityRenderingHandler(EntityMusketBall.class, new RenderAmmo());
			RenderingRegistry.registerEntityRenderingHandler(EntitySoldier_Union.class, new RenderUnionSoldier(new ModelBiped(), 0.5F));
			RenderingRegistry.registerEntityRenderingHandler(EntitySoldier_Rebel.class, new RenderRebelSoldier(new ModelBiped(), 0.5F));
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCannon.class, new TileEntityCannonRenderer());
			ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMucket.class, new TileEntityMucketRenderer());
			//MinecraftForgeClient.registerItemRenderer(Registry.cannon, new TileEntityCannonRenderer());
			//MinecraftForgeClient.registerItemRenderer(Registry.mucket, new TileEntityMucketRenderer());
	  }
		public int addArmor(String s) {
		    return RenderingRegistry.addNewArmourRendererPrefix(s);
		}
}
