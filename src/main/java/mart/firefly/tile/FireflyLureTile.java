package mart.firefly.tile;

import mart.firefly.block.FireflyLureBlock;
import mart.firefly.entity.FireflyEntity;
import mart.firefly.setup.ModBlocks;
import mart.firefly.setup.ModEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static mart.firefly.block.CauldronBlock.ON;

public class FireflyLureTile extends TileEntity implements ITickableTileEntity {

    private Map<Biome, FireflyEntity.FireflyType> map = new HashMap<>();

    private Random rand = new Random();

    public FireflyLureTile() {
        super(ModBlocks.FIREFLY_LURE_TILE);
        map.put(Biomes.FOREST, FireflyEntity.FireflyType.FOREST);
        map.put(Biomes.FLOWER_FOREST, FireflyEntity.FireflyType.FOREST);
        map.put(Biomes.BIRCH_FOREST, FireflyEntity.FireflyType.FOREST);
        map.put(Biomes.DARK_FOREST, FireflyEntity.FireflyType.FOREST);
        map.put(Biomes.TALL_BIRCH_FOREST, FireflyEntity.FireflyType.FOREST);
        map.put(Biomes.BIRCH_FOREST_HILLS, FireflyEntity.FireflyType.FOREST);
        map.put(Biomes.DARK_FOREST_HILLS, FireflyEntity.FireflyType.FOREST);

        map.put(Biomes.MOUNTAINS, FireflyEntity.FireflyType.MOUNTAIN);
        map.put(Biomes.MOUNTAIN_EDGE, FireflyEntity.FireflyType.MOUNTAIN);
        map.put(Biomes.SNOWY_TAIGA_MOUNTAINS, FireflyEntity.FireflyType.MOUNTAIN);

        map.put(Biomes.DESERT, FireflyEntity.FireflyType.EARTH);
        map.put(Biomes.DESERT_HILLS, FireflyEntity.FireflyType.EARTH);
        map.put(Biomes.DESERT_LAKES, FireflyEntity.FireflyType.EARTH);

        map.put(Biomes.SNOWY_MOUNTAINS, FireflyEntity.FireflyType.ICE);
        map.put(Biomes.SNOWY_TAIGA, FireflyEntity.FireflyType.ICE);
        map.put(Biomes.SNOWY_BEACH, FireflyEntity.FireflyType.ICE);
        map.put(Biomes.SNOWY_TAIGA_HILLS, FireflyEntity.FireflyType.ICE);
        map.put(Biomes.SNOWY_TUNDRA, FireflyEntity.FireflyType.ICE);

        map.put(Biomes.NETHER, FireflyEntity.FireflyType.DEMON);

        map.put(Biomes.THE_END, FireflyEntity.FireflyType.VOID);
        map.put(Biomes.END_BARRENS, FireflyEntity.FireflyType.VOID);
        map.put(Biomes.END_HIGHLANDS, FireflyEntity.FireflyType.VOID);
        map.put(Biomes.END_MIDLANDS, FireflyEntity.FireflyType.VOID);
        map.put(Biomes.SMALL_END_ISLANDS, FireflyEntity.FireflyType.VOID);

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

                FireflyEntity.FireflyType type = map.getOrDefault(world.getBiome(this.pos), FireflyEntity.FireflyType.FOREST);
                if(rand.nextInt(20) == 0){
                    type = FireflyEntity.FireflyType.FAIRY;
                }

                fly.getDataManager().set(FireflyEntity.TYPE, type.name());
                fly.setPosition(this.getPos().getX() + 0.5, this.getPos().getY() + 1, this.getPos().getZ() + 0.5);
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
