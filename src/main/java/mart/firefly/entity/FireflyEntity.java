package mart.firefly.entity;

import mart.firefly.entity.goals.RandomFlyingGoal;
import mart.firefly.registry.ModItems;
import mart.firefly.util.ColorUtil;
import mart.firefly.util.RgbColor;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FireflyEntity extends CreatureEntity{

    public static final DataParameter<String> TYPE = EntityDataManager.createKey(FireflyEntity.class, DataSerializers.STRING);

    private FlyingPathNavigator navigator;

    public FireflyEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.navigator = (FlyingPathNavigator) this.createNavigator(worldIn);
        this.moveController = new FireflyEntity.MoveHelperController(this);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(1, new FireflyEntity.FlyGoal(this));
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
    public boolean hasNoGravity() {
        return true;
    }

    ///Movement

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        return new FlyingPathNavigator(this, worldIn);
    }

    @Override
    public void travel(Vec3d relative) {
        if (this.isServerWorld()) {
            this.moveRelative(0.01F, relative);
            this.move(MoverType.SELF, this.getMotion());
            this.setMotion(this.getMotion().scale(0.9D));
        } else {
            super.travel(relative);
        }
    }

    static class FlyGoal extends RandomFlyingGoal {

        public FlyGoal(FireflyEntity fireflyEntity) {
            super(fireflyEntity, 0.8D, 40);
        }

        @Override
        public boolean shouldExecute() {
            return super.shouldExecute();
        }
    }

    static class MoveHelperController extends MovementController {
        private final FireflyEntity fireflyEntity;

        MoveHelperController(FireflyEntity fireflyEntity) {
            super(fireflyEntity);
            this.fireflyEntity = fireflyEntity;
        }

        @Override
        public void tick() {
            this.fireflyEntity.setMotion(this.fireflyEntity.getMotion().add(0.0D, 0.0D, 0.0D));

            //System.out.println(this.action);
            //System.out.println(!this.fireflyEntity.getNavigator().noPath());

            if (!this.fireflyEntity.getNavigator().noPath()) {
                double d0 = this.posX - this.fireflyEntity.posX;
                double d1 = this.posY - this.fireflyEntity.posY;
                double d2 = this.posZ - this.fireflyEntity.posZ;
                double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                d1 /= d3;
                float f = (float)(MathHelper.atan2(d2, d0) * 57.2957763671875D) - 90.0F;
                this.fireflyEntity.rotationYaw = this.limitAngle(this.fireflyEntity.rotationYaw, f, 90.0F);
                this.fireflyEntity.renderYawOffset = this.fireflyEntity.rotationYaw;
                float f1 = (float)(this.speed * this.fireflyEntity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue());
                this.fireflyEntity.setAIMoveSpeed(MathHelper.lerp(1.5F, this.fireflyEntity.getAIMoveSpeed(), f1));
                this.fireflyEntity.setMotion(this.fireflyEntity.getMotion().add(0.0D, (double)this.fireflyEntity.getAIMoveSpeed() * d1 * 0.01D, 0.0D));
            } else {
                this.fireflyEntity.setAIMoveSpeed(0.0F);
            }
        }
    }

    @Override
    public FlyingPathNavigator getNavigator() {
        return this.navigator;
    }

    //End movement

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


