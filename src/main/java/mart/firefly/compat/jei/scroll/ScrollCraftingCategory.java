package mart.firefly.compat.jei.scroll;

import mart.firefly.compat.jei.gui.BlockDrawable;
import mart.firefly.recipes.ScrollRecipe;
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
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class ScrollCraftingCategory implements IRecipeCategory {

    private IDrawable icon;
    private IDrawable back;

    public ScrollCraftingCategory(IGuiHelper helper){
        icon = new BlockDrawable(new ItemStack(Item.getItemFromBlock(ModBlocks.SCROLL_TABLE)));
        back = helper.createBlankDrawable(100, 100);
    }

    @Override
    public ResourceLocation getUid() {
        return Constants.Compat.ScrollCategory;
    }

    @Override
    public Class getRecipeClass() {
        return ScrollRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Scroll Crafting";
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
        if(o instanceof ScrollRecipe){
            List<Ingredient> input = new ArrayList<>();
            input.add(((ScrollRecipe) o).juice);

            iIngredients.setInputIngredients(input);
            iIngredients.setOutput(VanillaTypes.ITEM, ((ScrollRecipe) o).scroll.getMatchingStacks()[0]);
        }

    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, Object o, IIngredients iIngredients) {
        IGuiItemStackGroup itemStacks = iRecipeLayout.getItemStacks();
        itemStacks.init(0, false, 70, 41);
        itemStacks.init(1, true, 10, 31);
        itemStacks.init(2, true, 10, 51);


        if(o instanceof ScrollRecipe){
            itemStacks.set(0, ((ScrollRecipe) o).scroll.getMatchingStacks()[0]);
            itemStacks.set(1, new ItemStack(Items.PAPER));
            itemStacks.set(2, ((ScrollRecipe) o).juice.getMatchingStacks()[0]);
        }
    }
}
