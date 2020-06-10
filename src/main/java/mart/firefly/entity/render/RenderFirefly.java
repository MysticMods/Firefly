package mart.firefly.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mart.firefly.Firefly;
import mart.firefly.entity.FireflyEntity;
import mart.firefly.util.ColorUtil;
import mart.firefly.util.RgbColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderFirefly extends EntityRenderer<FireflyEntity> {



    public RenderFirefly(EntityRendererManager rendererManager) {
        super(rendererManager);
    }

    @Override
    public ResourceLocation getEntityTexture(FireflyEntity entity) {
        return new ResourceLocation(Firefly.MODID, "textures/particles/particle_glow.png");
    }

    @Override
    public void render(FireflyEntity entity, float entityYaw, float partialTicks, MatrixStack ms, IRenderTypeBuffer bufferIn, int packedLightIn) {
        TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_PARTICLES_TEXTURE).apply(new ResourceLocation(Firefly.MODID, "particle/particle_glow.png"));
        IVertexBuilder builder = bufferIn.getBuffer(RenderType.getTranslucent());
        ms.push();

        RgbColor color = ColorUtil.fireflyColors.get(entity.getFireflyType());
        GlStateManager.color4f(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, 1f);

        ms.rotate(Vector3f.YP.rotation(180 - this.renderManager.info.getYaw()));
        ms.rotate(Vector3f.XN.rotation(-this.renderManager.info.getPitch()));

        builder.pos(ms.getLast().getMatrix(), -0.5f / 4, 0.0f, 0.0f)
                .color(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, 1f)
                .tex(sprite.getMinU(), sprite.getMinV()).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(ms.getLast().getMatrix(), 0.5f/ 4, 0.0f, 0.0f)
                .color(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, 1f)
                .tex(sprite.getMaxU(), sprite.getMinV()).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(ms.getLast().getMatrix(), 0.5f / 4, 0.0f, 0.0f)
                .color(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, 1f)
                .tex(sprite.getMaxU(), sprite.getMaxV()).lightmap(240, 240).normal(1, 0, 0).endVertex();

        builder.pos(ms.getLast().getMatrix(), -0.5f / 4, 1.0f / 4, 0.0f)
                .color(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, 1f)
                .tex(sprite.getMinU(), sprite.getMaxV()).lightmap(240, 240).normal(1, 0, 0).endVertex();

        ms.pop();
        super.render(entity, entityYaw, partialTicks, ms, bufferIn, packedLightIn);
        //ms.translate((float) entity.getPosX(), (float) entity.getPosY() + 0.4f, (float) entity.getPosZ());

//
//        GlStateManager.enableAlphaTest();
//        GlStateManager.enableBlend();
//        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.param, GlStateManager.DestFactor.ONE.param );
//        GlStateManager.disableLighting();
//        GlStateManager.rotatef(180 - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
//        GlStateManager.rotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
//        bindTexture(new ResourceLocation(Firefly.MODID, "textures/particles/particle_glow.png"));
//        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, 240.0F, 240.0F);
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder buffer = tessellator.getBuffer();
//        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
//        buffer.pos(-0.5D / 4, 0.0D, 0.0D).tex(0D, 0.0D).endVertex();
//        buffer.pos(0.5D / 4, 0.0D, 0.0D).tex(1D, 0.0D).endVertex();
//        buffer.pos(0.5D / 4, 0.0D, 0.0D).tex(1D, 1D).endVertex();
//        buffer.pos(-0.5D / 4, 1.0D / 4, 0.0D).tex(0.0D, 1D).endVertex();
//        GLX.lastBrightnessX = 240;
//        GLX.lastBrightnessY = 240;
//        tessellator.draw();
//        GlStateManager.enableLighting();
//        GlStateManager.disableBlend();
//        GlStateManager.enableAlphaTest();

    }
}
