package mart.firefly.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class JuiceRecipe {
    public final Ingredient input;
    public final Ingredient output;

    public JuiceRecipe(ItemStack input, ItemStack output) {
        this.input = Ingredient.fromStacks(input);
        this.output = Ingredient.fromStacks(output);
    }

    public JuiceRecipe(Item input, Item output) {
        this.input = Ingredient.fromItems(input);
        this.output = Ingredient.fromItems(output);
    }
}
