package mart.firefly.util;

import mart.firefly.Firefly;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class RenderHelper {
    public static final RenderType FIREFLY;

    static {
        RenderType.State glState = RenderType.State.getBuilder().build(false);

        FIREFLY = RenderType.makeType(Firefly.MODID + "firefly", DefaultVertexFormats.POSITION_COLOR, GL11.GL_TRIANGLES, 256, false, false, glState);
    }
}
