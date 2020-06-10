package mart.firefly.block;

import mart.firefly.setup.ModItems;
import mart.firefly.tile.FireflyJarTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class FireflyJarBlock extends Block implements ITile<FireflyJarTile> {


    protected static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 12.0D, 12.0D);

    public FireflyJarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 10;
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FireflyJarTile();
    }

    @Override
    public Supplier<FireflyJarTile> getTile() {
        return FireflyJarTile::new;
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        FireflyJarTile tile = (FireflyJarTile)worldIn.getTileEntity(pos);
        ItemStack droppedItem = null;
        switch (tile.getFireflyType()){
            case ICE:
                droppedItem = new ItemStack(ModItems.FIREFLY_JAR_ICE);
                break;
            case FOREST:
                droppedItem = new ItemStack(ModItems.FIREFLY_JAR_FOREST);
                break;
            case EARTH:
                droppedItem = new ItemStack(ModItems.FIREFLY_JAR_EARTH);
                break;
            case FAIRY:
                droppedItem = new ItemStack(ModItems.FIREFLY_JAR_FAIRY);
                break;
            case MOUNTAIN:
                droppedItem = new ItemStack(ModItems.FIREFLY_JAR_MOUNTAIN);
                break;
            case DEMON:
                droppedItem = new ItemStack(ModItems.FIREFLY_JAR_DEMON);
                break;
            case VOID:
                droppedItem = new ItemStack(ModItems.FIREFLY_JAR_VOID);
                break;
        }

        worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), droppedItem));
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

}