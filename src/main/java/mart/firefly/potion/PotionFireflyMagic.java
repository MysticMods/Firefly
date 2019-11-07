package mart.firefly.potion;

import mart.firefly.Firefly;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class PotionFireflyMagic extends Potion {


    public static ResourceLocation texture = new ResourceLocation(Firefly.MODID, "textures/effect/effect_bloodfury.png");


    public PotionFireflyMagic(@Nullable String name, EffectInstance... effectInstances) {
        super(name, effectInstances);
        setRegistryName(name);
    }
}
