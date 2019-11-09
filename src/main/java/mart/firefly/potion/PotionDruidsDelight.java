package mart.firefly.potion;

import mart.firefly.registry.ModEffects;
import net.minecraft.potion.EffectInstance;

public class PotionDruidsDelight extends PotionFireflyMagic {

    public PotionDruidsDelight() {
        super("potion_druids_delight", new EffectInstance(ModEffects.EFFECT_DRUIDS_DELIGHT, 2400));
    }


}
