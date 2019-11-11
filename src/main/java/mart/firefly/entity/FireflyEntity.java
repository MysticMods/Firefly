package mart.firefly.entity;

import mart.firefly.entity.goals.RandomFlyingGoal;
import mart.firefly.setup.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class FireflyEntity extends FlyingEntity {

    public static final DataParameter<String> TYPE = EntityDataManager.createKey(FireflyEntity.class, DataSerializers.STRING);

    private FlyingPathNavigator navigator;

    public FireflyEntity(EntityType<? extends FlyingEntity> type, World worldIn) {
        super(type, worldIn);
        this.navigator = (FlyingPathNavigator) this.createNavigator(worldIn);
        this.moveController = new MoveHelperController (this);
        this.setAIMoveSpeed(0.2F);
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RandomFlyingGoal(this));
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(TYPE, FireflyType.FOREST.name());
    }


    @Override
    public void tick() {
        FireflyType type = FireflyType.valueOf(getDataManager().get(TYPE));
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

    @Override
    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    protected void updateFallState(double p_184231_1_, boolean p_184231_3_, BlockState p_184231_4_, BlockPos p_184231_5_) {

    }

    ///Movement

    @Override
    protected PathNavigator createNavigator(World worldIn) {
        return new FlyingPathNavigator(this, worldIn);
    }

    @Override
    public FlyingPathNavigator getNavigator() {
        return this.navigator;
    }

    static class MoveHelperController extends MovementController {
        private final FireflyEntity parentEntity;
        private int courseChangeCooldown;

        public MoveHelperController(FireflyEntity ghast) {
            super(ghast);
            this.parentEntity = ghast;
        }

        @Override
        public void tick() {
            if (this.courseChangeCooldown-- <= 0) {
                this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 100;
                Random random = new Random();
                double d0 = this.parentEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16F);
                double d1 = this.parentEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16F);
                double d2 = this.parentEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16F);
                this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 0.01D);


            }

            Vec3d vec3d = new Vec3d(this.posX - this.parentEntity.posX, this.posY - this.parentEntity.posY, this.posZ - this.parentEntity.posZ);
            double d01 = vec3d.length();
            vec3d = vec3d.normalize();
            if (this.func_220673_a(vec3d, MathHelper.ceil(d01))) {
                this.parentEntity.setMotion(this.parentEntity.getMotion().add(vec3d.scale(0.005D)));
            } else {
                this.action = MovementController.Action.WAIT;
            }
        }


        private boolean func_220673_a(Vec3d p_220673_1_, int p_220673_2_) {
            AxisAlignedBB axisalignedbb = this.parentEntity.getBoundingBox();

            for(int i = 1; i < p_220673_2_; ++i) {
                axisalignedbb = axisalignedbb.offset(p_220673_1_);
                if (!this.parentEntity.world.isCollisionBoxesEmpty(this.parentEntity, axisalignedbb)) {
                    return false;
                }
            }

            return true;
        }
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


