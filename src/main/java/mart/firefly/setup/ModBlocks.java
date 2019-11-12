package mart.firefly.setup;


import mart.firefly.Firefly;
import mart.firefly.block.CauldronBlock;
import mart.firefly.block.FireflyLureBlock;
import mart.firefly.block.FireflyPressBlock;
import mart.firefly.block.ScrollTableBlock;
import net.minecraft.block.Block;
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

    public static void load() {
    }
}
