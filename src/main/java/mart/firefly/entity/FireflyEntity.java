package mart.firefly.entity;

import mart.firefly.registry.ModItems;
import mart.firefly.util.ColorUtil;
import mart.firefly.util.RgbColor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FireflyEntity extends FlyingEntity {

    private BlockPos spawnPosition = null;

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

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
        if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.withinDistance(this.getPositionVec(), 2.0D)) {
            this.spawnPosition = new BlockPos(this.posX + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7), this.posY + (double)this.rand.nextInt(6) - 2.0D, this.posZ + (double)this.rand.nextInt(7) - (double)this.rand.nextInt(7));
        }
        double lvt_3_1_ = (double)this.spawnPosition.getX() + 0.5D - this.posX;
        double lvt_5_1_ = (double)this.spawnPosition.getY() + 0.1D - this.posY;
        double lvt_7_1_ = (double)this.spawnPosition.getZ() + 0.5D - this.posZ;
        Vec3d lvt_9_1_ = this.getMotion();
        Vec3d lvt_10_1_ = lvt_9_1_.add((Math.signum(lvt_3_1_) * 0.5D - lvt_9_1_.x) * 0.10000000149011612D, (Math.signum(lvt_5_1_) * 0.699999988079071D - lvt_9_1_.y) * 0.10000000149011612D, (Math.signum(lvt_7_1_) * 0.5D - lvt_9_1_.z) * 0.10000000149011612D);
        this.setMotion(lvt_10_1_);
        float lvt_11_1_ = (float)(MathHelper.atan2(lvt_10_1_.z, lvt_10_1_.x) * 57.2957763671875D) - 90.0F;
        float lvt_12_1_ = MathHelper.wrapDegrees(lvt_11_1_ - this.rotationYaw);
        this.moveForward = 0.1F;
        this.rotationYaw += lvt_12_1_;
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
