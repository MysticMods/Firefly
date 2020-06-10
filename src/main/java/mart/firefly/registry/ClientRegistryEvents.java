package mart.firefly.registry;

import mart.firefly.Firefly;
import mart.firefly.entity.FireflyEntity;
import mart.firefly.entity.render.RenderFirefly;
import mart.firefly.gui.FireflyPressScreen;
import mart.firefly.gui.ScrollTableScreen;
import mart.firefly.render.FireflyJarTileRenderer;
import mart.firefly.setup.ModEntities;
import mart.firefly.setup.ModTileEntities;
import mart.firefly.tile.FireflyJarTile;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value= {Dist.CLIENT}, modid= Firefly.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistryEvents
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.FIREFLY, RenderFirefly::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.FIREFLY_JAR_BLOCK_TILE, FireflyJarTileRenderer::new);


        ScreenManager.registerFactory(ModTileEntities.FIREFLY_PRESS_CONTAINER, FireflyPressScreen::new);
        ScreenManager.registerFactory(ModTileEntities.SCROLL_TABLE_CONTAINER, ScrollTableScreen::new);
    }

    @SubscribeEvent
    public static void onParticleTextureStitch(TextureStitchEvent.Pre event){
        if(!event.getMap().getTextureLocation().equals(AtlasTexture.LOCATION_PARTICLES_TEXTURE)){
            return;
        }

        event.addSprite(new ResourceLocation(Firefly.MODID, "particle/particle_glow.png"));
    }



}