package mart.firefly.registry;

import mart.firefly.recipes.CauldronRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModRecipes {
    private static Map<Item, Item> pressItems = new HashMap<>();

    private static Map<Item, Item> scrollRecipes = new HashMap<>();

    private static List<CauldronRecipe> potionRecipes = new ArrayList<>();

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

        potionRecipes.add(new CauldronRecipe(new ItemStack(ModItems.POTION_DRUIDS_DELIGHT)).setName("recipe_druids_delight").addIngredients(
                new ItemStack(ModItems.FIREFLY_JUICE_FOREST),
                new ItemStack(Items.WHEAT),
                new ItemStack(Items.APPLE)
        ));
    }

    public static Item getPressOutput(Item input){
        return pressItems.getOrDefault(input, null);
    }

    public static Item getScrollRecipeOutput(Item input){
        return scrollRecipes.getOrDefault(input, null);
    }

    public static List<CauldronRecipe> getPotionRecipes() {
        return potionRecipes;
    }
}
