package mart.firefly.item.scroll;

import mart.firefly.Firefly;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ScrollItem extends Item {

    public ScrollItem(String name) {
        super(new Item.Properties().group(Firefly.GROUP));
        setRegistryName(new ResourceLocation(Firefly.MODID, name));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(playerIn.isSneaking() && playerIn.abilities.isCreativeMode){
            setScrollLevel(playerIn.getHeldItem(handIn), getScrollLevel(playerIn.getHeldItem(handIn)) + 1);
            return super.onItemRightClick(worldIn, playerIn, handIn);
        }

        action(worldIn, playerIn, handIn);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }


    protected void removeScrol(PlayerEntity player, Hand hand){
        if(!player.abilities.isCreativeMode){
            player.getHeldItem(hand).shrink(1);
        }
    }

    protected void action(World world, PlayerEntity player, Hand hand){
        removeScrol(player, hand);
    }

    public static void setScrollLevel(ItemStack stack, int level){
        CompoundNBT compound = stack.getOrCreateChildTag("levelTag");
        compound.putInt("level", level);
    }

    public static int getScrollLevel(ItemStack stack){
        CompoundNBT compound = stack.getOrCreateChildTag("levelTag");
        return compound.getInt("level");
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("Level: " + getScrollLevel(stack)));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}