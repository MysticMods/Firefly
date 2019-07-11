package mart.firefly.util;

import mart.firefly.entity.FireflyEntity;

import java.util.HashMap;
import java.util.Map;

public class ColorUtil {
    public static Map<FireflyEntity.FireflyType, RgbColor> fireflyColors = new HashMap<>();

    public static void init(){
        fireflyColors.put(FireflyEntity.FireflyType.FAIRY, new RgbColor(231, 255, 79));
        fireflyColors.put(FireflyEntity.FireflyType.FOREST, new RgbColor(55, 201, 58));
        fireflyColors.put(FireflyEntity.FireflyType.MOUNTAIN, new RgbColor(193, 155, 82));
        fireflyColors.put(FireflyEntity.FireflyType.DEMON, new RgbColor(201, 60, 55));
        fireflyColors.put(FireflyEntity.FireflyType.ICE, new RgbColor(79, 255, 255));
        fireflyColors.put(FireflyEntity.FireflyType.VOID, new RgbColor(15, 10, 2));
        fireflyColors.put(FireflyEntity.FireflyType.EARTH, new RgbColor(88, 83, 75));
    }
}
