package mart.firefly.setup;

import mart.firefly.Firefly;
import mart.firefly.entity.FireflyEntity;
import mart.firefly.entity.render.RenderFirefly;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value= {Dist.CLIENT}, modid= Firefly.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegistryEvents
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(FireflyEntity.class, RenderFirefly::new);
    }
}