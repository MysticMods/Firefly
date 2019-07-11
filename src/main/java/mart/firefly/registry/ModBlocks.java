package mart.firefly.registry;


import mart.firefly.gui.FireflyPressContainer;
import mart.firefly.tile.FireflyPressTile;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;


@ObjectHolder("firefly")
public class ModBlocks {
    //Blocks
    public static final Block FIREFLY_PRESS = null;

    //Tiles
    public static final TileEntityType<FireflyPressTile> FIREFLY_PRESS_TILE = null;

    public static final ContainerType<FireflyPressContainer> FIREFLY_PRESS_CONTAINER = null;
}
