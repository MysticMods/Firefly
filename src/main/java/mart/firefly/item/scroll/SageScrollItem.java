package mart.firefly.item.scroll;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SageScrollItem extends ScrollItem {

    public SageScrollItem(String name) {
        super(name);
    }

    @Override
    protected void action(World world, PlayerEntity player, Hand hand) {
        player.giveExperiencePoints(getScrollLevel(player.getHeldItem(hand)) * 30);
        super.action(world, player, hand);
    }
}
