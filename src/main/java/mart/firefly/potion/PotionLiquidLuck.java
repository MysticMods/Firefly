package mart.firefly.potion;

import mart.firefly.setup.ModEffects;
import net.minecraft.potion.EffectInstance;

public class PotionLiquidLuck extends PotionFireflyMagic {

    public PotionLiquidLuck() {
        super("potion_liquid_luck", new EffectInstance(ModEffects.EFFECT_LIQUID_LUCK, 10 * 60 * 20));
    }
}
