package mart.firefly.tile;

import mart.firefly.registry.ModBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;

import static mart.firefly.block.CauldronBlock.ON;

public class FireflyLureTile extends TileEntity implements ITickableTileEntity {

    public FireflyLureTile() {
        super(ModBlocks.FIREFLY_LURE_TILE);
    }

    @Override
    public void tick() {

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
