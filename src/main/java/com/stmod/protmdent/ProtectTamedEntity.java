package com.stmod.protmdent;

import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("protmdent")
public class ProtectTamedEntity
{
    public ProtectTamedEntity() {
        MinecraftForge.EVENT_BUS.register(this);
    }

	@SubscribeEvent
	public void onAttackEntity(LivingAttackEvent event) {
		// 通常
		if (event.getEntity() instanceof EntityTameable) {
			if (((EntityTameable)event.getEntity()).isTamed()) {
				if (event.getSource().getTrueSource() instanceof EntityPlayer) {
					if (event.getSource().getImmediateSource() instanceof EntityArrow) {
						event.getSource().getImmediateSource().remove();
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
						event.getSource().getImmediateSource().remove();
					}
					event.setCanceled(true);
				}
			}
		}
	}
}
