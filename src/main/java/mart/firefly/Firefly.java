package mart.firefly;

import epicsquid.mysticallib.setup.ClientProxy;
import epicsquid.mysticallib.setup.IProxy;
import epicsquid.mysticallib.setup.ServerProxy;
import mart.firefly.setup.ModSetup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("firefly")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Firefly {

    public static final ItemGroup GROUP = new ItemGroup(12, "firefly") {
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
            return new ItemStack(Items.GLASS_BOTTLE);
        }
    };

    public static final String MODID = "firefly";

    // Sided setup
    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    // Side agnostic setup
    public static ModSetup setup = new ModSetup();

    public Firefly() {
    }


}
