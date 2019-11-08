package mart.firefly.setup;

import mart.firefly.Firefly;
import mart.firefly.block.*;
import mart.firefly.entity.FireflyEntity;
import mart.firefly.entity.render.RenderFirefly;
import mart.firefly.gui.FireflyPressContainer;
import mart.firefly.gui.FireflyPressScreen;
import mart.firefly.gui.ScrollTableContainer;
import mart.firefly.gui.ScrollTableScreen;
import mart.firefly.item.FireflyJarItem;
import mart.firefly.item.FireflyJuiceItem;
import mart.firefly.item.FireflyPotion;
import mart.firefly.item.scroll.*;
import mart.firefly.network.PressActivatePacket;
import mart.firefly.network.ScrollTablePacket;
import mart.firefly.potion.*;
import mart.firefly.registry.ModBlocks;
import mart.firefly.registry.ModEffects;
import mart.firefly.util.ColorUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unchecked")
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

    private static List<Block> blocks = new ArrayList<>();

    @SubscribeEvent
    public static void setup(final FMLCommonSetupEvent event) {
        Firefly.setup.init();
        ColorUtil.init();

        int messageNumber = 0;
        Firefly.channel.registerMessage(messageNumber++, PressActivatePacket.class, PressActivatePacket::encode, PressActivatePacket::new, PressActivatePacket::handle);
        Firefly.channel.registerMessage(messageNumber++, ScrollTablePacket.class, ScrollTablePacket::encode, ScrollTablePacket::new, ScrollTablePacket::handle);

    }

    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event){
        ScreenManager.registerFactory(ModBlocks.FIREFLY_PRESS_CONTAINER, FireflyPressScreen::new);
        ScreenManager.registerFactory(ModBlocks.SCROLL_TABLE_CONTAINER, ScrollTableScreen::new);
    }

    @SubscribeEvent
    public static void onBlockRegistry(final RegistryEvent.Register<Block> event){
        blocks.add(new FireflyPressBlock());
        blocks.add(new ScrollTableBlock());
        blocks.add(new CauldronBlock());
        blocks.add(new FireflyLureBlock());

        for(Block b : blocks){
            event.getRegistry().register(b);
        }
    }

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> event){
        for(Block b : blocks){
            event.getRegistry().register(new BlockItem(b, new Item.Properties().group(Firefly.GROUP)).setRegistryName(b.getRegistryName()));
        }

        event.getRegistry().register(new Item(new Item.Properties().maxStackSize(1).group(Firefly.GROUP)).setRegistryName(new ResourceLocation(Firefly.MODID, "firefly_jar")));
        event.getRegistry().register(new FireflyJarItem("firefly_jar_fairy", FireflyEntity.FireflyType.FAIRY));
        event.getRegistry().register(new FireflyJarItem("firefly_jar_forest", FireflyEntity.FireflyType.FOREST));
        event.getRegistry().register(new FireflyJarItem("firefly_jar_mountain", FireflyEntity.FireflyType.MOUNTAIN));
        event.getRegistry().register(new FireflyJarItem("firefly_jar_demon", FireflyEntity.FireflyType.DEMON));
        event.getRegistry().register(new FireflyJarItem("firefly_jar_ice", FireflyEntity.FireflyType.ICE));
        event.getRegistry().register(new FireflyJarItem("firefly_jar_void", FireflyEntity.FireflyType.VOID));
        event.getRegistry().register(new FireflyJarItem("firefly_jar_earth", FireflyEntity.FireflyType.EARTH));
        event.getRegistry().register(new FireflyJuiceItem("firefly_juice_fairy"));
        event.getRegistry().register(new FireflyJuiceItem("firefly_juice_forest"));
        event.getRegistry().register(new FireflyJuiceItem("firefly_juice_mountain"));
        event.getRegistry().register(new FireflyJuiceItem("firefly_juice_demon"));
        event.getRegistry().register(new FireflyJuiceItem("firefly_juice_ice"));
        event.getRegistry().register(new FireflyJuiceItem("firefly_juice_void"));
        event.getRegistry().register(new FireflyJuiceItem("firefly_juice_earth"));
        event.getRegistry().register(new DruidScrollItem("scroll_druid"));
        event.getRegistry().register(new MinerScrollItem("scroll_miner"));
        event.getRegistry().register(new SageScrollItem("scroll_sage"));
        event.getRegistry().register(new ThunderScrollItem("scroll_thunder"));
        event.getRegistry().register(new DemonScrollItem("scroll_demon"));
        event.getRegistry().register(new FireflyPotion("potion_bloodfury", FireflyPotion.PotionType.BLOODFURY));
        event.getRegistry().register(new FireflyPotion("potion_dragons_wrath", FireflyPotion.PotionType.DRAGONS_WRATH));
        event.getRegistry().register(new FireflyPotion("potion_druids_delight", FireflyPotion.PotionType.DRUIDS_DELIGHT));
        event.getRegistry().register(new FireflyPotion("potion_liquid_luck", FireflyPotion.PotionType.LIQUID_LUCK));
        event.getRegistry().register(new FireflyPotion("potion_mandragora", FireflyPotion.PotionType.MANDRAGORA));
        event.getRegistry().register(new FireflyPotion("potion_wisdom_draught", FireflyPotion.PotionType.WISDOM_DRAUGHT));
    }

    @SubscribeEvent
    public static void onTileRegistry(final RegistryEvent.Register<TileEntityType<?>> event){
        for(Block block : blocks){
            if(block instanceof ITile){
                event.getRegistry().register((TileEntityType<?>) TileEntityType.Builder.create(((ITile) block).getTile(), block).build(null)
                        .setRegistryName(new ResourceLocation(Firefly.MODID, Objects.requireNonNull(block.getRegistryName()).getPath() + "_tile")));
            }
        }
    }

    @SubscribeEvent
    public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event){
        event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) ->{
            BlockPos pos = data.readBlockPos();
            return new FireflyPressContainer(windowId, Minecraft.getInstance().world, pos, inv);
        }).setRegistryName(new ResourceLocation(Firefly.MODID, "firefly_press_container")));

        event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) ->{
            BlockPos pos = data.readBlockPos();
            return new ScrollTableContainer(windowId, Minecraft.getInstance().world, pos, inv);
        }).setRegistryName(new ResourceLocation(Firefly.MODID, "scroll_table_container")));
    }

    @SubscribeEvent
    public static void registerEntityTypes(final RegistryEvent.Register<EntityType<?>> event)
    {
        event.getRegistry().register(EntityType.Builder.create(FireflyEntity::new, EntityClassification.CREATURE).size(0.5F, 0.5F).build("firefly")
                .setRegistryName(new ResourceLocation(Firefly.MODID, "firefly")));

        RenderingRegistry.registerEntityRenderingHandler(FireflyEntity.class, RenderFirefly::new);
    }

    @SubscribeEvent
    public static void registerPotion(final RegistryEvent.Register<Potion> event)
    {
        event.getRegistry().register(new PotionWisdomDraught());
        event.getRegistry().register(new PotionLiquidLuck());
        event.getRegistry().register(new PotionMandragora());
        event.getRegistry().register(new PotionDruidsDelight());
        event.getRegistry().register(new PotionDragonsWrath());
        event.getRegistry().register(new PotionBloodfury());
    }

    @SubscribeEvent
    public static void registerEffect(final RegistryEvent.Register<Effect> event)
    {
        event.getRegistry().register(ModEffects.EFFECT_BLOODFURY = new FireflyEffect(EffectType.BENEFICIAL, 8171462, "effect_bloodfury"));
        event.getRegistry().register(ModEffects.EFFECT_WISDOM_DRAUGHT = new FireflyEffect(EffectType.BENEFICIAL, 8171462, "effect_wisdom_draught"));
        event.getRegistry().register(ModEffects.EFFECT_LIQUID_LUCK = new FireflyEffect(EffectType.BENEFICIAL, 8171462, "effect_liquid_luck"));
        event.getRegistry().register(ModEffects.EFFECT_MANDRAGORA = new FireflyEffect(EffectType.BENEFICIAL, 8171462, "effect_mandragora"));
        event.getRegistry().register(ModEffects.EFFECT_DRUIDS_DELIGHT = new FireflyEffect(EffectType.BENEFICIAL, 8171462, "effect_druids_delight"));
        event.getRegistry().register(ModEffects.EFFECT_DRAGONS_WRATH = new FireflyEffect(EffectType.BENEFICIAL, 8171462, "effect_dragons_wrath"));
    }

}
