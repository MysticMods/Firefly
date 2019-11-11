package mart.firefly.registry;

import mart.firefly.recipes.ScrollRecipe;
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

    private static List<ScrollRecipe> scrollRecipes = new ArrayList<>();

    private static List<CauldronRecipe> potionRecipes = new ArrayList<>();

    public static void addRecipes(){
        pressItems.put(ModItems.FIREFLY_JAR_FOREST, ModItems.FIREFLY_JUICE_FOREST);
        pressItems.put(ModItems.FIREFLY_JAR_DEMON, ModItems.FIREFLY_JUICE_DEMON);
        pressItems.put(ModItems.FIREFLY_JAR_EARTH, ModItems.FIREFLY_JUICE_EARTH);
        pressItems.put(ModItems.FIREFLY_JAR_FAIRY, ModItems.FIREFLY_JUICE_FAIRY);
        pressItems.put(ModItems.FIREFLY_JAR_ICE, ModItems.FIREFLY_JUICE_ICE);
        pressItems.put(ModItems.FIREFLY_JAR_MOUNTAIN, ModItems.FIREFLY_JUICE_MOUNTAIN);
        pressItems.put(ModItems.FIREFLY_JAR_VOID, ModItems.FIREFLY_JUICE_VOID);

        scrollRecipes.add(new ScrollRecipe(ModItems.FIREFLY_JUICE_FAIRY, ModItems.SCROLL_SAGE));
        scrollRecipes.add(new ScrollRecipe(ModItems.FIREFLY_JUICE_FOREST, ModItems.SCROLL_DRUID));
        scrollRecipes.add(new ScrollRecipe(ModItems.FIREFLY_JUICE_EARTH, ModItems.SCROLL_MINER));
        scrollRecipes.add(new ScrollRecipe(ModItems.FIREFLY_JUICE_MOUNTAIN, ModItems.SCROLL_THUNDER));
        scrollRecipes.add(new ScrollRecipe(ModItems.FIREFLY_JUICE_DEMON, ModItems.SCROLL_DEMON));

        potionRecipes.add(new CauldronRecipe(new ItemStack(ModItems.POTION_DRUIDS_DELIGHT)).setName("recipe_druids_delight").addIngredients(
                new ItemStack(ModItems.FIREFLY_JUICE_FOREST),
                new ItemStack(Items.WHEAT),
                new ItemStack(Items.APPLE)
        ));
        potionRecipes.add(new CauldronRecipe(new ItemStack(ModItems.POTION_DRAGONS_WRATH)).setName("recipe_dragons_wrath").addIngredients(
                new ItemStack(ModItems.FIREFLY_JUICE_VOID),
                new ItemStack(Items.BLAZE_POWDER),
                new ItemStack(Items.NETHER_WART)
        ));
        potionRecipes.add(new CauldronRecipe(new ItemStack(ModItems.POTION_WISDOM_DRAUGHT)).setName("recipe_wisdom_draught").addIngredients(
                new ItemStack(ModItems.FIREFLY_JUICE_FAIRY),
                new ItemStack(Items.BOOK),
                new ItemStack(Items.GLOWSTONE_DUST)
        ));
        potionRecipes.add(new CauldronRecipe(new ItemStack(ModItems.POTION_MANDRAGORA)).setName("recipe_mandragora").addIngredients(
                new ItemStack(ModItems.FIREFLY_JUICE_DEMON),
                new ItemStack(Items.SPIDER_EYE),
                new ItemStack(Items.ROTTEN_FLESH)
        ));
        potionRecipes.add(new CauldronRecipe(new ItemStack(ModItems.POTION_LIQUID_LUCK)).setName("recipe_liquid_luck").addIngredients(
                new ItemStack(ModItems.FIREFLY_JUICE_MOUNTAIN),
                new ItemStack(Items.COAL),
                new ItemStack(Items.LAPIS_LAZULI)
        ));
        potionRecipes.add(new CauldronRecipe(new ItemStack(ModItems.POTION_BLOODFURY)).setName("recipe_bloodfury").addIngredients(
                new ItemStack(ModItems.FIREFLY_JUICE_DEMON),
                new ItemStack(Items.MAGMA_CREAM),
                new ItemStack(Items.GLISTERING_MELON_SLICE)
        ));
    }

    public static Item getPressOutput(Item input){
        return pressItems.getOrDefault(input, null);
    }

    public static Item getScrollRecipeOutput(Item input){
        for(ScrollRecipe recipe : scrollRecipes){
            for (ItemStack stack : recipe.juice.getMatchingStacks()){
                if(stack.getItem() == input){
                    return recipe.scroll.getMatchingStacks()[0].getItem();
                }
            }
        }
        return null;
    }

    public static List<CauldronRecipe> getPotionRecipes() {
        return potionRecipes;
    }

    public static List<ScrollRecipe> getScrollRecipes() {
        return scrollRecipes;
    }
}
