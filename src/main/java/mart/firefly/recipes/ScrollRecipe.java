package mart.firefly.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class ScrollRecipe {

    public final Ingredient juice;
    public final Ingredient scroll;

    public ScrollRecipe(ItemStack juice, ItemStack scroll) {
        this.juice = Ingredient.fromStacks(juice);
        this.scroll = Ingredient.fromStacks(scroll);
    }

    public ScrollRecipe(Item juice, Item scroll) {
        this.juice = Ingredient.fromItems(juice);
        this.scroll = Ingredient.fromItems(scroll);
    }
}
