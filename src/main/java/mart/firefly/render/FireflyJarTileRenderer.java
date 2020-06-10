package mart.firefly.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;
import mart.firefly.Firefly;
import mart.firefly.tile.FireflyJarTile;
import mart.firefly.util.ColorUtil;
import mart.firefly.util.RgbColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class FireflyJarTileRenderer extends TileEntityRenderer<FireflyJarTile> {

    public FireflyJarTileRenderer(TileEntityRendererDispatcher p_i226006_1_) {
        super(p_i226006_1_);
    }

    @Override
    public void render(FireflyJarTile fireflyJarTile, float v, MatrixStack ms, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1) {

    }


//    @Override
//    public void render(FireflyJarTile entity, double x, double y, double z, float partialTicks, int destroyStage) {
//        super.render(entity, x, y, z, partialTicks, destroyStage);
//
//        if(entity.getFireflyType() == null){
//            entity.markDirty();
//            return;
//        }
//
//        if(entity.isUp()){
//            entity.setCoordTex(entity.getCoordTex() + 1);
//            if(entity.getCoordTex() >= 240){
//                entity.setUp(false);
//            }
//        }
//        else{
//            entity.setCoordTex(entity.getCoordTex() - 1);
//            if(entity.getCoordTex() <= 110){
//                entity.setUp(true);
//            }
//        }
//
//        GlStateManager.pushMatrix();
//        GlStateManager.translatef((float) x + 0.5f, (float) y + 0.2f, (float) z + 0.5f);
//
//        RgbColor color = ColorUtil.fireflyColors.get(entity.getFireflyType());
//        GlStateManager.color4f(color.getRed()/255f, color.getGreen()/255f, color.getBlue()/255f, 1f);
//
//        GlStateManager.enableAlphaTest();
//        GlStateManager.enableBlend();
//        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE );
//        GlStateManager.disableLighting();
//
//        GlStateManager.rotatef(180 - manager.playerViewY, 0.0F, 1.0F, 0.0F);
//        GlStateManager.rotatef(-manager.playerViewX, 1.0F, 0.0F, 0.0F);
//
//        bindTexture(new ResourceLocation(Firefly.MODID, "textures/particles/particle_glow.png"));
//
//        GLX.glMultiTexCoord2f(GLX.GL_TEXTURE1, entity.getCoordTex(), entity.getCoordTex());
//
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder buffer = tessellator.getBuffer();
//        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
//        buffer.pos(-0.5D / 4, 0.0D, 0.0D).tex(0D, 0.0D).endVertex();
//        buffer.pos(0.5D / 4, 0.0D, 0.0D).tex(1D, 0.0D).endVertex();
//        buffer.pos(0.5D / 4, 1.0D / 4, 0.0D).tex(1D, 1D).endVertex();
//        buffer.pos(-0.5D / 4, 1.0D / 4, 0.0D).tex(0.0D, 1D).endVertex();
//        GLX.lastBrightnessX = 240;
//        GLX.lastBrightnessY = 240;
//        tessellator.draw();
//
//        GlStateManager.enableLighting();
//        GlStateManager.disableBlend();
//        GlStateManager.enableAlphaTest();
//        GlStateManager.popMatrix();
//    }
}
