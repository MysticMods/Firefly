package mart.firefly.potion;

import mart.firefly.setup.ModEffects;
import net.minecraft.potion.EffectInstance;

public class PotionWisdomDraught extends PotionFireflyMagic {

    public PotionWisdomDraught() {
        super("potion_wisdom_draught", new EffectInstance(ModEffects.EFFECT_WISDOM_DRAUGHT, 5 * 60 * 20));
    }
}
