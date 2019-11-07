package mart.firefly.potion;


import mart.firefly.registry.ModEffects;
import net.minecraft.potion.EffectInstance;

public class PotionBloodfury extends PotionFireflyMagic {

    public PotionBloodfury() {
        super("potion_bloodfury", new EffectInstance(ModEffects.EFFECT_BLOODFURY));
    }

}
