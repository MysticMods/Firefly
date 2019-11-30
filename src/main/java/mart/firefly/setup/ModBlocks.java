package mart.firefly.setup;


import mart.firefly.Firefly;
import mart.firefly.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;



public class ModBlocks {
    public static final Supplier<Item.Properties> SIG = () -> new Item.Properties().group(Firefly.GROUP);

    //Blocks
    public static RegistryObject<FireflyPressBlock> FIREFLY_PRESS = Firefly.REGISTRY.registerBlock("firefly_press", Firefly.REGISTRY.block(FireflyPressBlock::new, () -> Block.Properties.create(Material.ROCK).hardnessAndResistance(2.5F)), SIG);
    public static RegistryObject<ScrollTableBlock> SCROLL_TABLE = Firefly.REGISTRY.registerBlock("scroll_table", Firefly.REGISTRY.block(ScrollTableBlock::new, () -> Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F)), SIG);
    public static RegistryObject<CauldronBlock> CAULDRON = Firefly.REGISTRY.registerBlock("cauldron", Firefly.REGISTRY.block(CauldronBlock::new, () -> Block.Properties.create(Material.IRON).hardnessAndResistance(2.5F)), SIG);
    public static RegistryObject<FireflyLureBlock> FIREFLY_LURE = Firefly.REGISTRY.registerBlock("firefly_lure", Firefly.REGISTRY.block(FireflyLureBlock::new, () -> Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5F)), SIG);
    public static RegistryObject<FireflyJarBlock> FIREFLY_JAR= Firefly.REGISTRY.registerBlock("firefly_jar_block", Firefly.REGISTRY.block(FireflyJarBlock::new, () -> Block.Properties.create(Material.GLASS).hardnessAndResistance(1F).sound(SoundType.GLASS).lightValue(10)), SIG);

    public static void load() {
    }
}
