package mart.firefly.potion;

import mart.firefly.registry.ModEffects;
import net.minecraft.potion.EffectInstance;

public class PotionWisdomDraught extends PotionFireflyMagic {

    public PotionWisdomDraught() {
        super("potion_wisdom_draught", new EffectInstance(ModEffects.EFFECT_WISDOM_DRAUGHT, 2400));
    }
}
