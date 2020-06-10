package mart.firefly.block;

import mart.firefly.tile.CauldronTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class CauldronBlock extends Block implements ITile<CauldronTile> {

    public static final BooleanProperty ON = BooleanProperty.create("on");
    public static final BooleanProperty WATER = BooleanProperty.create("water");

    private static final VoxelShape INSIDE = Block.makeCuboidShape(3.0D, 4.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    private static final VoxelShape WALLS = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), INSIDE, IBooleanFunction.ONLY_FIRST);

    public CauldronBlock(Properties properties) {
        super(properties);
        setDefaultState(this.getStateContainer().getBaseState().with(ON, Boolean.FALSE).with(WATER, Boolean.FALSE));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if(((CauldronTile)world.getTileEntity(pos)).onActivated(player, hand)){
            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.FAIL;
        }
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CauldronTile();
    }

    @Override
    public Supplier<CauldronTile> getTile() {
        return CauldronTile::new;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ON).add(WATER);
    }


    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return WALLS;
    }

}
