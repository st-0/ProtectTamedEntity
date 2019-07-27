package com.stmod.protmdent;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(
	modid = ProtectTamedEntity.MODID,
	name = ProtectTamedEntity.NAME,
	version = ProtectTamedEntity.VERSION,
	acceptedMinecraftVersions = ProtectTamedEntity.ACCEPTED_MCVERSIONS)
public class ProtectTamedEntity
{
    public static final String MODID = "protmdent";
    public static final String NAME = "ProtectTamedEntity";
    public static final String VERSION = "1.8.9-1.0";
	public static final String ACCEPTED_MCVERSIONS = "[1.8,1.8.9]";

    @EventHandler
    public void onInit(FMLInitializationEvent event)
    {
		MinecraftForge.EVENT_BUS.register(this);
    }

	@SubscribeEvent
	public void onAttackEntity(LivingAttackEvent event) {
		// 通常
		if (event.entity instanceof EntityTameable) {
			if (((EntityTameable)event.entity).isTamed()) {
				if (event.source.getEntity() instanceof EntityPlayer) {
					if (event.source.getSourceOfDamage() instanceof EntityArrow) {
						event.source.getSourceOfDamage().setDead();
					}
					event.setCanceled(true);
				}
			}
		}
		// ウマ
		if (event.entity instanceof EntityHorse) {
			if (((EntityHorse)event.entity).isTame()) {
				if (event.source.getEntity() instanceof EntityPlayer) {
					if (event.source.getSourceOfDamage() instanceof EntityArrow) {
						event.source.getSourceOfDamage().setDead();
					}
					event.setCanceled(true);
				}
			}
		}
	}
}
