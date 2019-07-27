package com.stmod.protmdent;

import net.minecraft.entity.passive.AbstractHorse;
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
    public static final String VERSION = "1.12.2-1.0";
	public static final String ACCEPTED_MCVERSIONS = "[1.9,1.12.2]";

    @EventHandler
    public void onInit(FMLInitializationEvent event)
    {
		MinecraftForge.EVENT_BUS.register(this);
    }

	@SubscribeEvent
	public void onAttackEntity(LivingAttackEvent event) {
		// 通常
		if (event.getEntity() instanceof EntityTameable) {
			if (((EntityTameable)event.getEntity()).isTamed()) {
				if (event.getSource().getTrueSource() instanceof EntityPlayer) {
					if (event.getSource().getImmediateSource() instanceof EntityArrow) {
						event.getSource().getImmediateSource().setDead();
					}
					event.setCanceled(true);
				}
			}
		}
		// ウマ
		if (event.getEntity() instanceof AbstractHorse) {
			if (((AbstractHorse)event.getEntity()).isTame()) {
				if (event.getSource().getTrueSource() instanceof EntityPlayer) {
					if (event.getSource().getImmediateSource() instanceof EntityArrow) {
						event.getSource().getImmediateSource().setDead();
					}
					event.setCanceled(true);
				}
			}
		}
	}
}
