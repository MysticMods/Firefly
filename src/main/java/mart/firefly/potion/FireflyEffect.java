package mart.firefly.potion;

import epicsquid.mysticallib.util.Util;
import mart.firefly.registry.ModEffects;
import mart.firefly.util.FireflyUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

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
            System.out.println("it runs");
            if(Util.rand.nextInt(100) == 0){
                System.out.println("Spawn entity");
                Entity entity = FireflyUtil.getRandomPassiveMob(entityLivingBaseIn.world);
                entity.posX = entityLivingBaseIn.posX + (Util.rand.nextInt(10) - 5);
                entity.posZ = entityLivingBaseIn.posZ + (Util.rand.nextInt(10) - 5);
                entity.posY = entityLivingBaseIn.posY;
                entityLivingBaseIn.world.addEntity(entity);
            }
        }
    }
}
