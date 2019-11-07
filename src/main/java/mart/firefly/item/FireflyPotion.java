package mart.firefly.item;

import mart.firefly.Firefly;
import mart.firefly.registry.ModEffects;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class FireflyPotion extends Item {

    public enum PotionType {
        BLOODFURY, DRAGONS_WRATH, DRUIDS_DELIGHT, LIQUID_LUCK, MANDRAGORA, WISDOM_DRAUGHT
    }

    private PotionType type;

    public FireflyPotion(String name, PotionType type) {
        super(new Item.Properties().group(Firefly.GROUP).maxStackSize(1));
        setRegistryName(name);
        this.type = type;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
        PlayerEntity playerEntity = entity instanceof PlayerEntity ? (PlayerEntity)entity : null;
        if (playerEntity == null || !playerEntity.abilities.isCreativeMode) {
            stack.shrink(1);
        }

        if (playerEntity instanceof ServerPlayerEntity) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
        }

        if (!world.isRemote) {
            List<EffectInstance> effectList;

            switch (type){
                case BLOODFURY:
                    effectList = ModEffects.POTION_BLOODFURY.getEffects();
                    break;
                case MANDRAGORA:
                    effectList = ModEffects.POTION_MANDRAGORA.getEffects();
                    break;
                case LIQUID_LUCK:
                    effectList = ModEffects.POTION_LIQUID_LUCK.getEffects();
                    break;
                case DRAGONS_WRATH:
                    effectList = ModEffects.POTION_DRAGONS_WRATH.getEffects();
                    break;
                case DRUIDS_DELIGHT:
                    effectList = ModEffects.POTION_DRUIDS_DELIGHT.getEffects();
                    break;
                case WISDOM_DRAUGHT:
                    effectList = ModEffects.POTION_WISDOM_DRAUGHT.getEffects();
                    break;
                default:
                    effectList = ModEffects.POTION_DRUIDS_DELIGHT.getEffects();
            }

            Iterator iterator = effectList.iterator();

            while(iterator.hasNext()) {
                EffectInstance effectInstance = (EffectInstance)iterator.next();
                if (effectInstance.getPotion().isInstant()) {
                    effectInstance.getPotion().affectEntity(playerEntity, playerEntity, entity, effectInstance.getAmplifier(), 100.0D);
                } else {
                    entity.addPotionEffect(new EffectInstance(effectInstance));
                }
            }
        }

        if (playerEntity != null) {
            playerEntity.addStat(Stats.ITEM_USED.get(this));
        }

        if (playerEntity == null || !playerEntity.abilities.isCreativeMode) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            if (playerEntity != null) {
                playerEntity.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return stack;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        player.setActiveHand(hand);
        return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
}
