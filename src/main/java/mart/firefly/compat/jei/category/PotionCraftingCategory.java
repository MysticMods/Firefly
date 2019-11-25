package mart.firefly.compat.jei.category;

import mart.firefly.Firefly;
import mart.firefly.compat.jei.gui.BlockDrawable;
import mart.firefly.recipes.CauldronRecipe;
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
import net.minecraft.util.ResourceLocation;

public class PotionCraftingCategory implements IRecipeCategory {

    private IDrawable icon;
    private IDrawable back;

    public PotionCraftingCategory(IGuiHelper helper){
        icon = new BlockDrawable(new ItemStack(Item.getItemFromBlock(ModBlocks.CAULDRON.get())));
        back = helper.createDrawable(new ResourceLocation(Firefly.MODID, "textures/recipe/potion_recipe.png"), 0, 0, 78, 78);
    }

    @Override
    public ResourceLocation getUid() {
        return Constants.Compat.PotionCategory;
    }

    @Override
    public Class getRecipeClass() {
        return CauldronRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Cauldron Potion Crafting";
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
        if(o instanceof CauldronRecipe){
            iIngredients.setInputIngredients(((CauldronRecipe) o).getIngredients());
            iIngredients.setOutput(VanillaTypes.ITEM, ((CauldronRecipe) o).getResult());
        }

    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, Object o, IIngredients iIngredients) {
        IGuiItemStackGroup itemStacks = iRecipeLayout.getItemStacks();
        itemStacks.init(0, false, 29, 5);
        itemStacks.init(1, true, 10, 55);
        itemStacks.init(2, true, 30, 55);
        itemStacks.init(3, true, 50, 55);

        if(o instanceof CauldronRecipe){
            itemStacks.set(0, ((CauldronRecipe) o).getResult());
            itemStacks.set(1, ((CauldronRecipe) o).getIngredients().get(0).getMatchingStacks()[0]);
            itemStacks.set(2, ((CauldronRecipe) o).getIngredients().get(1).getMatchingStacks()[0]);
            itemStacks.set(3, ((CauldronRecipe) o).getIngredients().get(2).getMatchingStacks()[0]);
        }
    }
}
