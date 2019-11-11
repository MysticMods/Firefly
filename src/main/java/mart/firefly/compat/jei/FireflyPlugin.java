package mart.firefly.compat.jei;

import mart.firefly.Firefly;
import mart.firefly.compat.jei.potion.PotionCraftingCategory;
import mart.firefly.compat.jei.scroll.ScrollCraftingCategory;
import mart.firefly.setup.ModRecipes;
import mart.firefly.util.Constants;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class FireflyPlugin implements IModPlugin {
    public static IJeiHelpers helpers;

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        helpers = registration.getJeiHelpers();

        registration.addRecipes(ModRecipes.getPotionRecipes(), Constants.Compat.PotionCategory);
        registration.addRecipes(ModRecipes.getScrollRecipes(), Constants.Compat.ScrollCategory);

    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        if (helpers == null)
            helpers = registration.getJeiHelpers();

        registration.addRecipeCategories(new PotionCraftingCategory(helpers.getGuiHelper()));
        registration.addRecipeCategories(new ScrollCraftingCategory(helpers.getGuiHelper()));
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Firefly.MODID, "fireflyjeiplugin");
    }
}
