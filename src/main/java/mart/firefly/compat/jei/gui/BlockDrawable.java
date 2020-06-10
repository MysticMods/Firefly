package mart.firefly.compat.jei.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;

public class BlockDrawable implements IDrawable {

    private final ItemStack cauldron;

    public BlockDrawable(ItemStack stack){
        cauldron = stack;
    }

    @Override
    public int getWidth() {
        return 18;
    }

    @Override
    public int getHeight() {
        return 18;
    }

    @Override
    public void draw(int xOffset, int yOffset) {

        //RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.color4f(1, 1, 1, 1);
        GlStateManager.enableDepthTest();
        GlStateManager.pushMatrix();
        GlStateManager.translated(xOffset, yOffset, 0);

        GlStateManager.pushMatrix();
        GlStateManager.translated(1, 1, 0);
        Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(cauldron, 0, 0);
        GlStateManager.popMatrix();

        GlStateManager.popMatrix();
    }
}
