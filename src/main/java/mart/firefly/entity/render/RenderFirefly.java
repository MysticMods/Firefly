package mart.firefly.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;
import epicsquid.mysticallib.particle.ParticleRenderer;
import epicsquid.mysticallib.setup.ClientProxy;
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

        GlStateManager.rotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(180, 1.0F, 0.0F, 0.0F);

        bindTexture(new ResourceLocation(Firefly.MODID, "textures/particle/particle_glow.png"));


        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(-0.5D / 4, 0.0D, 0.0D).tex(0D, 0.0D).endVertex();
        buffer.pos(0.5D / 4, 0.0D, 0.0D).tex(1D, 0.0D).endVertex();
        buffer.pos(0.5D / 4, 1.0D / 4, 0.0D).tex(1D, 1D).endVertex();
        buffer.pos(-0.5D / 4, 1.0D / 4, 0.0D).tex(0.0D, 1D).endVertex();
        tessellator.draw();


        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.enableAlphaTest();
        GlStateManager.popMatrix();

        ClientProxy.particleRenderer.spawnParticle(entity.world, "particle_flame", x, y, z, x + 1, y + 1, z + 1, 100d, 255, 255, 255, 1, 1);

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}
