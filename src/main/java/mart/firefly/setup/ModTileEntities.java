package mart.firefly.setup;

import mart.firefly.gui.FireflyPressContainer;
import mart.firefly.gui.ScrollTableContainer;
import mart.firefly.tile.CauldronTile;
import mart.firefly.tile.FireflyLureTile;
import mart.firefly.tile.FireflyPressTile;
import mart.firefly.tile.ScrollTableTile;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("firefly")
public class ModTileEntities {

    public static final TileEntityType<FireflyPressTile> FIREFLY_PRESS_TILE = null;
    public static final TileEntityType<ScrollTableTile> SCROLL_TABLE_TILE = null;
    public static final TileEntityType<CauldronTile> CAULDRON_TILE = null;
    public static final TileEntityType<FireflyLureTile> FIREFLY_LURE_TILE = null;

    public static final ContainerType<FireflyPressContainer> FIREFLY_PRESS_CONTAINER = null;
    public static final ContainerType<ScrollTableContainer> SCROLL_TABLE_CONTAINER = null;

}
