package mart.firefly.entity.goals;

import mart.firefly.entity.FireflyEntity;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;
import java.util.Random;

public class RandomFlyingGoal extends Goal {
    private final FireflyEntity parentEntity;

    public RandomFlyingGoal(FireflyEntity ghast) {
        this.parentEntity = ghast;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute() {
        MovementController movementcontroller = this.parentEntity.getMoveHelper();
        if (!movementcontroller.isUpdating()) {
            return true;
        } else {
            double d0 = movementcontroller.getX() - this.parentEntity.posX;
            double d1 = movementcontroller.getY() - this.parentEntity.posY;
            double d2 = movementcontroller.getZ() - this.parentEntity.posZ;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            return d3 < 1.0D || d3 > 3600.0D;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    @Override
    public boolean shouldContinueExecuting() {
        return true;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        Random random = this.parentEntity.getRNG();
        double d0 = this.parentEntity.posX + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
        double d1 = this.parentEntity.posY + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
        double d2 = this.parentEntity.posZ + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
        this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1D);
    }
}