package com.airborne.civilwar;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemSmeltedEvent;

import com.airborne.civilwar.entity.EntitySoldier;
import com.airborne.civilwar.world.WorldGenTent;

public class EventHandlerCW {
	
	@SubscribeEvent
	public void LivingDeathEvent(LivingDeathEvent event){
		EntityLivingBase victim = event.entityLiving;
		Entity attacker = event.source.getEntity();
		if(attacker instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)attacker;
			if(victim instanceof EntitySoldier){
				if(event.source.getDamageType() == "mb"){
					player.triggerAchievement(Achievements.killer);
				}
			}
			if(victim instanceof EntityDragon){
				if(event.source.getDamageType() == "mb"){
					player.triggerAchievement(Achievements.end);
				}
			}
			if(victim instanceof EntityWither){
				if(player.getHeldItem().getItem() == Registry.saber){
					player.triggerAchievement(Achievements.wither);
				}
			}
		}
		if(attacker instanceof EntitySoldier){
			EntitySoldier soldier = (EntitySoldier)attacker;
			if(soldier.isTamed()){
				EntityPlayer player = (EntityPlayer)soldier.getOwner();
				if(victim instanceof EntitySoldier){
					//player.triggerAchievement(Achievements.mongol);
				}
				soldier.levelUp(player);
			}
		}
	}
	@SubscribeEvent
	public void LivingHurtEvent(LivingHurtEvent event){
		EntityLivingBase victim = event.entityLiving;
		if(victim instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer)victim;
			if(event.source.getDamageType() == "mb"){
				player.triggerAchievement(Achievements.shot);
			}
		}
	}
	@SubscribeEvent
	public void onEntityConstuct(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer ep = (EntityPlayer) event.entity;
			ep.registerExtendedProperties(Strings.PROPERTIES, new ExtendedProperties(ep));
		}
	}
	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent event){
		if(event.crafting.getItem()== Registry.musketball || event.crafting.getItem() == Registry.cannonball){
    		event.player.addStat(Achievements.war, 1);
    	}
    	if(event.crafting.getItem() == Registry.coin || event.crafting.getItem() == Registry.coin2){
    		event.player.addStat(Achievements.coinage, 1);
    	}
	}
	@SubscribeEvent
	public void onItemSmelted(ItemSmeltedEvent event){
		if(event.smelting.getItem() == Registry.lead){
    		event.player.addStat(Achievements.lead, 1);
    	}
	}
	@SubscribeEvent
	public void LivingSpawnEvent(LivingSpawnEvent.CheckSpawn event){
		if(event.entityLiving instanceof EntitySoldier){
			if(event.entityLiving.worldObj.getTotalWorldTime() > 11000){
				event.setResult(Result.DENY);
			}
			else{
				event.setResult(Result.ALLOW);
			}
		}
	}
	
	@SubscribeEvent
	public void CommandEvent(CommandEvent event){
		event.setCanceled(true);
		event.sender.addChatMessage(new ChatComponentText(event.command.getName()));
		if(event.command.getName().equals("time")){
			WorldGenTent w = new WorldGenTent();
			w.generate(event.sender.getEntityWorld(), event.sender.getPosition().getX(), event.sender.getPosition().getY(), event.sender.getPosition().getZ());
		}
	}
}
