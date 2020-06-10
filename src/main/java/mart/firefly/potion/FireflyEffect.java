package mart.firefly.potion;

import epicsquid.mysticallib.util.Util;
import mart.firefly.setup.ModEffects;
import mart.firefly.util.FireflyUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import javax.annotation.Nullable;

public class FireflyEffect extends Effect {

    private String effectName;

    public FireflyEffect(EffectType effectType, int color, String name) {
        super(effectType, color);
        setRegistryName(name);
        this.effectName = name;
    }

    @Override
    public String getName() {
        return effectName;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (this == ModEffects.EFFECT_DRUIDS_DELIGHT) {
            if(!entityLivingBaseIn.world.isRemote){

                Entity entity = FireflyUtil.getRandomPassiveMob(entityLivingBaseIn.world);
                entity.setPosition(
                        entityLivingBaseIn.getPosX() + (Util.rand.nextInt(10) - 5),
                        entityLivingBaseIn.getPosY(),
                        entityLivingBaseIn.getPosZ() + (Util.rand.nextInt(10) - 5)
                );
                entityLivingBaseIn.world.addEntity(entity);
            }
        }
    }

    @Override
    public void affectEntity(@Nullable Entity p_180793_1_, @Nullable Entity p_180793_2_, LivingEntity p_180793_3_, int p_180793_4_, double p_180793_5_) {
        super.affectEntity(p_180793_1_, p_180793_2_, p_180793_3_, p_180793_4_, p_180793_5_);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        if (this == ModEffects.EFFECT_DRUIDS_DELIGHT) {
            int k = 200 >> amplifier;
            if (k > 0) {
                return duration % k == 0;
            } else {
                return true;
            }
        }
        return super.isReady(duration, amplifier);
    }
}
