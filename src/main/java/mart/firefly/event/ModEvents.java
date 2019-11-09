package mart.firefly.event;

import epicsquid.mysticallib.util.Util;
import mart.firefly.registry.ModEffects;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber
public class ModEvents {

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

    @SubscribeEvent
    public static void onPlayerOreBreak(BlockEvent.HarvestDropsEvent event){
        PlayerEntity player = event.getHarvester();
        Block block = event.getWorld().getBlockState(event.getPos()).getBlock();

        if(player == null || !(block instanceof OreBlock)){
            return;
        }

        for(EffectInstance effect : player.getActivePotionEffects()){
            if(effect.getPotion() == ModEffects.EFFECT_LIQUID_LUCK){
                List<ItemStack> drops = event.getDrops();
                if (Util.rand.nextInt(3) == 0) {
                    drops.addAll(new ArrayList<>(drops));
                }
            }
        }

    }
}