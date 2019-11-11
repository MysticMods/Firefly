package mart.firefly.potion;


import mart.firefly.setup.ModEffects;
import net.minecraft.potion.EffectInstance;

public class PotionBloodfury extends PotionFireflyMagic {

    public PotionBloodfury() {
        super("potion_bloodfury", new EffectInstance(ModEffects.EFFECT_BLOODFURY, 2400));
    }

}
