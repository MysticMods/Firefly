package mart.firefly.item.scroll;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.IPlantable;

import java.util.Random;
import java.util.TreeMap;

public class DruidScrollItem extends ScrollItem {
    final TreeMap<Integer, Block> blockChances = new TreeMap<>();

    public DruidScrollItem(String name) {
        super(name);
        blockChances.put(95, Blocks.OAK_SAPLING);
        blockChances.put(70, Blocks.POPPY);
        blockChances.put(60, Blocks.DANDELION);
        blockChances.put(50, Blocks.BLUE_ORCHID);
        blockChances.put(40, Blocks.OXEYE_DAISY);
        blockChances.put(0, Blocks.TALL_GRASS);
    }

    @Override
    protected void action(World world, PlayerEntity player, Hand hand) {
        int radius = getScrollLevel(player.getHeldItem(hand)) * 2;
        Random rand = new Random();

        for(int x = -radius; x <= radius; x++){
            for(int z = -radius; z <= radius; z++){
                BlockPos pos = world.getHeight(Heightmap.Type.WORLD_SURFACE, new BlockPos(player.getPosition().getX() + x, 0, player.getPosition().getZ() + z));
                if(world.getBlockState(pos.down()).canSustainPlant(world, pos.down(), Direction.UP, (IPlantable) Blocks.ACACIA_SAPLING)){
                    world.setBlockState(pos, blockChances.floorEntry(rand.nextInt(100) + 1).getValue().getDefaultState());
                    if(world.getBlockState(pos).getBlock() == Blocks.OAK_SAPLING){
                        SaplingBlock sapling = (SaplingBlock) Blocks.OAK_SAPLING;
                        sapling.grow(world, pos, world.getBlockState(pos), rand);
                        sapling.grow(world, pos, world.getBlockState(pos), rand);
                    }
                }
            }
        }
        super.action(world, player, hand);
    }
}
