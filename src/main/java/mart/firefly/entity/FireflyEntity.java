package mart.firefly.entity;

import mart.firefly.registry.ModEntities;
import mart.firefly.registry.ModItems;
import mart.firefly.util.ColorUtil;
import mart.firefly.util.RgbColor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FireflyEntity extends FlyingEntity {

    public static final DataParameter<String> TYPE = EntityDataManager.createKey(FireflyEntity.class, DataSerializers.STRING);

    public FireflyEntity(EntityType<? extends FlyingEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(TYPE, FireflyType.FOREST.name());
    }


    @Override
    public void tick() {
        FireflyType type = FireflyType.valueOf(getDataManager().get(TYPE));
        RgbColor color = ColorUtil.fireflyColors.get(type);
        super.tick();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRender3d(double x, double y, double z) {
        return true;
    }

    @Override
    public boolean isInRangeToRenderDist(double distance) {
        return distance < 65;
    }

    public FireflyType getFireflyType(){
        return FireflyType.valueOf(getDataManager().get(TYPE));
    }

    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItem() == ModItems.FIREFLY_JAR) {
            player.playSound(SoundEvents.ENTITY_SPLASH_POTION_THROW, 1.0F, 1.0F);

            ItemStack resultStack;

            FireflyType type = FireflyType.valueOf(getDataManager().get(TYPE));
            switch(type){
                case FOREST:
                    resultStack = new ItemStack(ModItems.FIREFLY_JAR_FOREST);
                    break;
                case VOID:
                    resultStack = new ItemStack(ModItems.FIREFLY_JAR_VOID);
                    break;
                case FAIRY:
                    resultStack = new ItemStack(ModItems.FIREFLY_JAR_FAIRY);
                    break;
                case MOUNTAIN:
                    resultStack = new ItemStack(ModItems.FIREFLY_JAR_MOUNTAIN);
                    break;
                case EARTH:
                    resultStack = new ItemStack(ModItems.FIREFLY_JAR_EARTH);
                    break;
                case DEMON:
                    resultStack = new ItemStack(ModItems.FIREFLY_JAR_DEMON);
                    break;
                case ICE:
                    resultStack = new ItemStack(ModItems.FIREFLY_JAR_ICE);
                    break;
                default:
                    resultStack = new ItemStack(ModItems.FIREFLY_JAR_FOREST);
            }

            if(!player.abilities.isCreativeMode){
                itemstack.shrink(1);
            }
            if (itemstack.isEmpty()) {
                player.setHeldItem(hand, resultStack);
            } else if (!player.inventory.addItemStackToInventory(resultStack)) {
                player.dropItem(resultStack, false);
            }

            this.remove(false);

            return true;
        } else {
            return super.processInteract(player, hand);
        }
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean canBeLeashedTo(PlayerEntity player) {
        return false;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }


    public enum FireflyType {
        FAIRY,
        FOREST,
        MOUNTAIN,
        DEMON,
        ICE,
        VOID,
        EARTH
    }
}
