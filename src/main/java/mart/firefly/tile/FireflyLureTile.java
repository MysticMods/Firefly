package mart.firefly.tile;

import mart.firefly.block.FireflyLureBlock;
import mart.firefly.entity.FireflyEntity;
import mart.firefly.setup.ModEntities;
import mart.firefly.setup.ModTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static mart.firefly.block.CauldronBlock.ON;

public class FireflyLureTile extends TileEntity implements ITickableTileEntity {

    private Map<Biome.Category, FireflyEntity.FireflyType> map = new HashMap<>();

    private Random rand = new Random();

    public FireflyLureTile() {
        super(ModTileEntities.FIREFLY_LURE_TILE);
        map.put(Biome.Category.BEACH, FireflyEntity.FireflyType.EARTH);
        map.put(Biome.Category.DESERT, FireflyEntity.FireflyType.EARTH);
        map.put(Biome.Category.EXTREME_HILLS, FireflyEntity.FireflyType.MOUNTAIN);
        map.put(Biome.Category.FOREST, FireflyEntity.FireflyType.FOREST);
        map.put(Biome.Category.ICY, FireflyEntity.FireflyType.ICE);
        map.put(Biome.Category.JUNGLE, FireflyEntity.FireflyType.FOREST);
        map.put(Biome.Category.MESA, FireflyEntity.FireflyType.MOUNTAIN);
        map.put(Biome.Category.MUSHROOM, FireflyEntity.FireflyType.FAIRY);
        map.put(Biome.Category.NETHER, FireflyEntity.FireflyType.DEMON);
        map.put(Biome.Category.NONE, FireflyEntity.FireflyType.VOID);
        map.put(Biome.Category.OCEAN, FireflyEntity.FireflyType.FOREST);
        map.put(Biome.Category.PLAINS, FireflyEntity.FireflyType.FOREST);
        map.put(Biome.Category.RIVER, FireflyEntity.FireflyType.FOREST);
        map.put(Biome.Category.SAVANNA, FireflyEntity.FireflyType.EARTH);
        map.put(Biome.Category.SWAMP, FireflyEntity.FireflyType.FOREST);
        map.put(Biome.Category.TAIGA, FireflyEntity.FireflyType.ICE);
        map.put(Biome.Category.THEEND, FireflyEntity.FireflyType.VOID);
    }

    @Override
    public void tick() {
        if(world.getBlockState(this.pos).get(FireflyLureBlock.ON)){

            List<FireflyEntity> flies = world.getEntitiesWithinAABB(FireflyEntity.class, new AxisAlignedBB(
                    getPos().getX() - 10,getPos().getY(),getPos().getZ() - 10,
                    getPos().getX() + 11,getPos().getY() + 10,getPos().getZ() + 11)
            );

            if(flies.size() < 10 && rand.nextInt(200) == 0){
                FireflyEntity fly = new FireflyEntity(ModEntities.FIREFLY, world);

                FireflyEntity.FireflyType type = map.getOrDefault(world.getBiome(this.pos).getCategory(), FireflyEntity.FireflyType.FOREST);
                if(rand.nextInt(20) == 0){
                    type = FireflyEntity.FireflyType.FAIRY;
                }

                fly.getDataManager().set(FireflyEntity.TYPE, type.name());
                fly.setPosition(this.getPos().getX() + 0.5, this.getPos().getY() + 1, this.getPos().getZ() + 0.5);
                fly.getDataManager().set(FireflyEntity.ANCHOR, new BlockPos(this.getPos().getX() + 0.5, this.getPos().getY() + 1, this.getPos().getZ() + 0.5));
                world.addEntity(fly);
            }
        }
    }

    public boolean onActivated(PlayerEntity playerIn, Hand hand) {
        if(playerIn.getHeldItem(hand).getItem() == Items.WATER_BUCKET){
            world.setBlockState(getPos(), getBlockState().with(ON, true));
            if(!playerIn.isCreative()){
                playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
            }
            return true;
        }
        return false;
    }
}
