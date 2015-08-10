package com.airborne.godswords;

import java.util.Random;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

import com.airborne.godswords.sword.ItemGodSword;

public class EventHandlerGS {
	
	@SubscribeEvent
	public void LivingDeathEvent(LivingDeathEvent event){
		Entity attacker = event.source.getEntity();
		if(attacker instanceof EntityLivingBase){
			EntityLivingBase attack = (EntityLivingBase)attacker;
			if(attack.getHeldItem().getItem() != null && attack.getHeldItem().getItem() == Registry.swords[0]){
				attack.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, 1));
			}
		}
	}
	
	@SubscribeEvent
	public void livingAttackEvent(LivingAttackEvent event){
		if(event.source.getEntity() instanceof EntityPlayer){
			EntityPlayer attacker = (EntityPlayer) event.source.getEntity();
			if(attacker.getHeldItem().getItem() instanceof ItemGodSword){
				String i = attacker.getHeldItem().getUnlocalizedName();
				if(i.equals(Registry.swords[0].getUnlocalizedName())){
					System.out.println("QUARTZ SWORD");
					attacker.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 300));
				}else if(i.equals(Registry.swords[1].getUnlocalizedName())){
					attacker.addPotionEffect(new PotionEffect(Potion.absorption.id, 200));
				}else if(i.equals(Registry.swords[2].getUnlocalizedName())){
					int rand = new Random().nextInt(10) + 3;
					event.entityLiving.setFire(rand);
				}else if(i.equals(Registry.swords[3].getUnlocalizedName())){
					if(!attacker.worldObj.isRemote){
						attacker.worldObj.newExplosion(event.entityLiving, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, 1, true, true);
					}
				}else if(i.equals(Registry.swords[5].getUnlocalizedName())){
					event.entityLiving.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 300));
				}
			}
		}
	}
	
	/*@SubscribeEvent
	public void playerUseItemEvent(PlayerUseItemEvent event){
		if(event.entityPlayer.worldObj.isRemote && event.entityPlayer.getHeldItem().getItem() instanceof ItemGodSword){
			String i = event.entityPlayer.getHeldItem().getUnlocalizedName();
			if(i.equals(Registry.swords[0].getUnlocalizedName())){
				event.entityPlayer.worldObj.spawnEntityInWorld(new EntityEnderPearl(event.entityPlayer.worldObj, event.entityPlayer));
			}
		}
	}*/
		

}
