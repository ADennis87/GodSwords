package com.airborne.civilwar;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

import com.airborne.civilwar.entity.EntityMusketBall;

public class DamageSources extends DamageSource{

	protected DamageSources(String par1Str) {
		super(par1Str);
	}
	public static DamageSource causeShotDamage(EntityMusketBall par0EntityMusketBall, Entity par1Entity) {
		return (new EntityDamageSourceIndirect("mb", par0EntityMusketBall, par1Entity)).setProjectile();
    }
}
