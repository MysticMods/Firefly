package mart.firefly.potion;

import mart.firefly.setup.ModEffects;
import net.minecraft.potion.EffectInstance;

public class PotionDragonsWrath extends PotionFireflyMagic {

    public PotionDragonsWrath() {
        super("potion_dragons_wrath", new EffectInstance(ModEffects.EFFECT_DRAGONS_WRATH, 5 * 60 * 20));
    }


}
