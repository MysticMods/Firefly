package mart.firefly.potion;

import mart.firefly.registry.ModEffects;
import net.minecraft.potion.EffectInstance;

public class PotionMandragora extends PotionFireflyMagic {

    public PotionMandragora() {
        super("potion_mandragora", new EffectInstance(ModEffects.EFFECT_MANDRAGORA, 2400));
    }
}
