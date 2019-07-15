package mart.firefly.registry;

import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ModRecipes {
    private static Map<Item, Item> pressItems = new HashMap<>();

    private static Map<Item, Item> scrollRecipes = new HashMap<>();

    public static void addRecipes(){
        pressItems.put(ModItems.FIREFLY_JAR_FOREST, ModItems.FIREFLY_JUICE_FOREST);
        pressItems.put(ModItems.FIREFLY_JAR_DEMON, ModItems.FIREFLY_JUICE_DEMON);
        pressItems.put(ModItems.FIREFLY_JAR_EARTH, ModItems.FIREFLY_JUICE_EARTH);
        pressItems.put(ModItems.FIREFLY_JAR_FAIRY, ModItems.FIREFLY_JUICE_FAIRY);
        pressItems.put(ModItems.FIREFLY_JAR_ICE, ModItems.FIREFLY_JUICE_ICE);
        pressItems.put(ModItems.FIREFLY_JAR_MOUNTAIN, ModItems.FIREFLY_JUICE_MOUNTAIN);
        pressItems.put(ModItems.FIREFLY_JAR_VOID, ModItems.FIREFLY_JUICE_VOID);

        scrollRecipes.put(ModItems.FIREFLY_JUICE_FAIRY, ModItems.SCROLL_SAGE);
        scrollRecipes.put(ModItems.FIREFLY_JUICE_FOREST, ModItems.SCROLL_DRUID);
        scrollRecipes.put(ModItems.FIREFLY_JUICE_EARTH, ModItems.SCROLL_MINER);
        scrollRecipes.put(ModItems.FIREFLY_JUICE_MOUNTAIN, ModItems.SCROLL_THUNDER);
        scrollRecipes.put(ModItems.FIREFLY_JUICE_DEMON, ModItems.SCROLL_DEMON);
    }

    public static Item getPressOutput(Item input){
        return pressItems.getOrDefault(input, null);
    }

    public static Item getScrollRecipeOutput(Item input){
        return scrollRecipes.getOrDefault(input, null);
    }
}
