package mart.firefly.block;

import mart.firefly.Firefly;
import mart.firefly.tile.FireflyLureTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class FireflyLureBlock extends Block implements ITile<FireflyLureTile> {

    public static final BooleanProperty ON = BooleanProperty.create("on");

    public FireflyLureBlock() {
        super(Block.Properties.create(Material.WOOD));
        setRegistryName(new ResourceLocation(Firefly.MODID, "firefly_lure"));
        setDefaultState(this.getStateContainer().getBaseState().with(ON, Boolean.FALSE));
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        return ((FireflyLureTile)world.getTileEntity(pos)).onActivated(player, hand);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FireflyLureTile();
    }

    @Override
    public Supplier<FireflyLureTile> getTile() {
        return FireflyLureTile::new;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ON);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
}
