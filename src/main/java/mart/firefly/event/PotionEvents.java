package mart.firefly.event;

import epicsquid.mysticallib.util.Util;
import mart.firefly.registry.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class PotionEvents {

    //Wisdom Draught Potion
    @SubscribeEvent
    public static void onXPPickup(PlayerXpEvent.PickupXp event)
    {
        PlayerEntity player = event.getPlayer();
        for(EffectInstance effect : player.getActivePotionEffects()){
            if(effect.getPotion() == ModEffects.EFFECT_WISDOM_DRAUGHT){
                event.getOrb().xpValue = event.getOrb().getXpValue() * 2;
            }
        }
    }

    //Mandragora Potion
    @SubscribeEvent
    public static void onPlayerAttack(LivingDamageEvent event){
        Random random = new Random();
        Entity source = event.getSource().getTrueSource();
        if(source instanceof PlayerEntity && event.getEntity() instanceof LivingEntity){
            PlayerEntity player = (PlayerEntity) source;
            player.getActivePotionEffects().forEach(potionEffect -> {
                if(potionEffect.getPotion() == ModEffects.EFFECT_MANDRAGORA){
                    if(random.nextInt(2) == 0){
                        event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.POISON, 100));
                    }
                }
            });
        }
    }

    //Bloodfury
    @SubscribeEvent
    public static void onPlayerDamageEntityBloodfury(LivingDamageEvent event){
        if(event.getSource().getTrueSource() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();

            for(EffectInstance effect : player.getActivePotionEffects()){
                if(effect.getPotion() == ModEffects.EFFECT_BLOODFURY){
                    if (Util.rand.nextInt(8) == 0) {
                        event.setAmount(event.getAmount() * 2);
                    }
                }
            }
        }
    }

    //Dragons Wrath
    @SubscribeEvent
    public static void onPlayerDamageEntityDW(LivingDamageEvent event){
        if(event.getEntity() instanceof PlayerEntity){
            PlayerEntity player = (PlayerEntity) event.getEntity();
            Entity damager = event.getSource().getTrueSource();
            if(damager == null){
                return;
            }
            for(EffectInstance effect : player.getActivePotionEffects()){
                if(effect.getPotion() == ModEffects.EFFECT_DRAGONS_WRATH){
                    if (Util.rand.nextInt(6) == 0) {
                        damager.setFire(5);
                        event.setAmount(0);
                    }
                }
            }
        }
    }
}