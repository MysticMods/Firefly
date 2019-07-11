package mart.firefly.item.scroll;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;

import java.util.List;

public class ThunderScrollItem extends ScrollItem {

    public ThunderScrollItem(String name) {
        super(name);
    }

    @Override
    protected void action(World world, PlayerEntity player, Hand hand) {
        if(world.isRemote){
            return;
        }
        int maxEntities = getScrollLevel(player.getHeldItem(hand)) * 2;
        BlockPos pos = player.getPosition();
        List<LivingEntity> entityLivingList = world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(pos.getX() + -10, pos.getY() + -5, pos.getZ() + -10, pos.getX() + 10, pos.getY() + 10, pos.getZ() + 10));
        int entitiesHit = 0;
        for (LivingEntity living : entityLivingList){
            if(living instanceof PlayerEntity){
                continue;
            }
            if(entitiesHit >= maxEntities){
                break;
            }

            ((ServerWorld) world).addLightningBolt(new LightningBoltEntity(world, living.getPosition().getX(), living.getPosition().getY(), living.getPosition().getZ(), false));
            entitiesHit++;
        }
        super.action(world, player, hand);
    }

}
