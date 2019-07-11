package mart.firefly.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import mart.firefly.Firefly;
import mart.firefly.gui.button.ContainerButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class FireflyPressScreen extends ContainerScreen<FireflyPressContainer> {

    private ResourceLocation GUI = new ResourceLocation(Firefly.MODID, "textures/container/firefly_press.png");
    public int x;
    public int y;

    public FireflyPressScreen(FireflyPressContainer containerIn, PlayerInventory inventoryIn, ITextComponent titleIn) {
        super(containerIn, inventoryIn, titleIn);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double x, double y, int p_mouseClicked_5_) {
        for(ContainerButton button : container.buttonList){
            if(isPointInRegion(button.x, button.y, 18, 18, x, y)){
                button.activate(container);
            }
        }
        return super.mouseClicked(x, y, p_mouseClicked_5_);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
    }
}
