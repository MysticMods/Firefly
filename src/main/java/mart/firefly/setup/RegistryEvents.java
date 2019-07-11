package mart.firefly.setup;

import mart.firefly.Firefly;
import mart.firefly.block.FireflyPressBlock;
import mart.firefly.block.ITile;
import mart.firefly.gui.FireflyPressContainer;
import mart.firefly.gui.FireflyPressScreen;
import mart.firefly.registry.ModBlocks;
import mart.firefly.tile.FireflyPressTile;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    private static List<Block> blocks = new ArrayList<>();

    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event) {
        Firefly.setup.init();
        Firefly.proxy.init();
    }

    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event){
        ScreenManager.registerFactory(ModBlocks.FIREFLY_PRESS_CONTAINER, FireflyPressScreen::new);
    }

    @SubscribeEvent
    public static void onBlockRegistry(final RegistryEvent.Register<Block> event){
        blocks.add(new FireflyPressBlock());
        for(Block b : blocks){
            event.getRegistry().register(b);
        }
    }

    @SubscribeEvent
    public static void onItemBlockRegistry(final RegistryEvent.Register<Item> event){
        for(Block b : blocks){
            event.getRegistry().register(new BlockItem(b, new Item.Properties().group(Firefly.GROUP)).setRegistryName(b.getRegistryName()));
        }
    }

    @SubscribeEvent
    public static void onTileRegistry(final RegistryEvent.Register<TileEntityType<?>> event){
        for(Block block : blocks){
            if(block instanceof ITile){
                event.getRegistry().register(TileEntityType.Builder.create(FireflyPressTile::new, block).build(null)
                        .setRegistryName(new ResourceLocation(Firefly.MODID, block.getRegistryName().getPath() + "_tile")));
            }
        }
    }

    @SubscribeEvent
    public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event){
        event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) ->{
            BlockPos pos = data.readBlockPos();
            return new FireflyPressContainer(windowId, Firefly.proxy.getClientWorld(), pos, inv);
        }).setRegistryName(new ResourceLocation(Firefly.MODID, "firefly_press_container")));
    }

}
