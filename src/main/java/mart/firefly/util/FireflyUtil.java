package mart.firefly.util;

import epicsquid.mysticallib.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.*;
import net.minecraft.world.World;

public class FireflyUtil {

    public static Entity getRandomPassiveMob(World world){
        Entity entity = null;
        switch(Util.rand.nextInt(10)){
            case 1:
                entity = new SheepEntity(EntityType.SHEEP, world);
                break;
            case 2:
                entity = new ChickenEntity(EntityType.CHICKEN, world);
                break;
            case 3:
                entity = new CowEntity(EntityType.COW, world);
                break;
            case 4:
                entity = new PigEntity(EntityType.PIG, world);
                break;
            case 5:
                entity = new RabbitEntity(EntityType.RABBIT, world);
                break;
            default:
                entity = new SheepEntity(EntityType.SHEEP, world);
                break;
        }
        return entity;
    }

}
