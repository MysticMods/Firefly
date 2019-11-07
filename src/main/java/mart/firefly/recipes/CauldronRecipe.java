package mart.firefly.recipes;

import epicsquid.mysticallib.util.ListUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class CauldronRecipe {
    private List<Ingredient> ingredients = new ArrayList<>();
    private ItemStack result;
    private String name;

    public CauldronRecipe(ItemStack result){
        this.result = result;
    }

    public CauldronRecipe setName(String name){
        this.name = name;
        return this;
    }

    public CauldronRecipe addIngredient(Ingredient stack) {
        this.ingredients.add(stack);
        return this;
    }

    public CauldronRecipe addIngredient(ItemStack stack) {
        this.ingredients.add(Ingredient.fromStacks(stack));
        return this;
    }

    public CauldronRecipe addIngredients(Object... stacks) {
        for (Object stack : stacks) {
            if (stack instanceof Ingredient) {
                ingredients.add((Ingredient) stack);
            } else if (stack instanceof ItemStack) {
                ingredients.add(Ingredient.fromStacks((ItemStack) stack));
            } else if(stack instanceof Item){
                ingredients.add(Ingredient.fromItems((Item) stack));
            }
        }
        return this;
    }

    public boolean matches(List<ItemStack> ingredients) {
        return ListUtil.matchesIngredients(ingredients, this.ingredients);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public ItemStack getResult() {
        return result;
    }

    public String getName() {
        return name;
    }
}
