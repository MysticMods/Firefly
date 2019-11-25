package mart.firefly.compat.jei.category;

import mart.firefly.Firefly;
import mart.firefly.compat.jei.gui.BlockDrawable;
import mart.firefly.recipes.JuiceRecipe;
import mart.firefly.setup.ModBlocks;
import mart.firefly.util.Constants;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class JuiceCraftingCategory implements IRecipeCategory {

    private IDrawable icon;
    private IDrawable back;

    public JuiceCraftingCategory(IGuiHelper helper){
        icon = new BlockDrawable(new ItemStack(Item.getItemFromBlock(ModBlocks.FIREFLY_PRESS.get())));
        back = helper.createDrawable(new ResourceLocation(Firefly.MODID, "textures/recipe/juice_recipe.png"), 0, 0, 100, 40);
    }

    @Override
    public ResourceLocation getUid() {
        return Constants.Compat.JuiceCategory;
    }

    @Override
    public Class getRecipeClass() {
        return JuiceRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Juice Crafting";
    }

    @Override
    public IDrawable getBackground() {
        return back;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(Object o, IIngredients iIngredients) {
        if(o instanceof JuiceRecipe){
            List<Ingredient> input = new ArrayList<>();
            input.add(((JuiceRecipe) o).input);

            iIngredients.setInputIngredients(input);
            iIngredients.setOutput(VanillaTypes.ITEM, ((JuiceRecipe) o).output.getMatchingStacks()[0]);
        }

    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, Object o, IIngredients iIngredients) {
        IGuiItemStackGroup itemStacks = iRecipeLayout.getItemStacks();
        itemStacks.init(0, false, 75, 11);
        itemStacks.init(1, true, 9, 11);


        if(o instanceof JuiceRecipe){
            itemStacks.set(0, ((JuiceRecipe) o).output.getMatchingStacks()[0]);
            itemStacks.set(1, ((JuiceRecipe) o).input.getMatchingStacks()[0]);
        }
    }
}
