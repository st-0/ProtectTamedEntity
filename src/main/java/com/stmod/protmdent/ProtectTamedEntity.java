package com.stmod.protmdent;

import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
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
		if (event.getEntity() instanceof TameableEntity) {
			if (((TameableEntity)event.getEntity()).isTamed()) {
				if (event.getSource().getTrueSource() instanceof PlayerEntity) {
					if (event.getSource().getImmediateSource() instanceof ArrowEntity) {
						event.getSource().getImmediateSource().remove();
					}
					event.setCanceled(true);
				}
			}
		}
		// ウマ
		if (event.getEntity() instanceof AbstractHorseEntity) {
			if (((AbstractHorseEntity)event.getEntity()).isTame()) {
				if (event.getSource().getTrueSource() instanceof PlayerEntity) {
					if (event.getSource().getImmediateSource() instanceof ArrowEntity) {
						event.getSource().getImmediateSource().remove();
					}
					event.setCanceled(true);
				}
			}
		}
	}
}
