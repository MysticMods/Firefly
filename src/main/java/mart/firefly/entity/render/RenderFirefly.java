package mart.firefly.entity.render;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import mart.firefly.Firefly;
import mart.firefly.entity.FireflyEntity;
import mart.firefly.util.ColorUtil;
import mart.firefly.util.RgbColor;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class RenderFirefly extends EntityRenderer<FireflyEntity> {



    public RenderFirefly(EntityRendererManager rendererManager) {
        super(rendererManager);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(FireflyEntity fireflyEntity) {
        return new ResourceLocation(Firefly.MODID, "");
    }

    @Override
    public void doRender(FireflyEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();

        GlStateManager.translatef((float) x, (float) y + 0.4f, (float) z);

        RgbColor color = ColorUtil.fireflyColors.get(entity.getFireflyType());
        GlStateManager.color4f(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, 1f);


        GlStateManager.enableAlphaTest();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE );
        GlStateManager.disableLighting();

        GlStateManager.rotatef(180 - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

        bindTexture(new ResourceLocation(Firefly.MODID, "textures/particle/particle_glow.png"));

        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 240.0F, 240.0F);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(-0.5D / 4, 0.0D, 0.0D).tex(0D, 0.0D).endVertex();
        buffer.pos(0.5D / 4, 0.0D, 0.0D).tex(1D, 0.0D).endVertex();
        buffer.pos(0.5D / 4, 1.0D / 4, 0.0D).tex(1D, 1D).endVertex();
        buffer.pos(-0.5D / 4, 1.0D / 4, 0.0D).tex(0.0D, 1D).endVertex();
        GLX.lastBrightnessX = 240;
        GLX.lastBrightnessY = 240;
        tessellator.draw();


        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.enableAlphaTest();
        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
