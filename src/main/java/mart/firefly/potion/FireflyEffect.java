package mart.firefly.potion;

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

}
