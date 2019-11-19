package mart.firefly.item;

import mart.firefly.Firefly;
import mart.firefly.entity.FireflyEntity;
import mart.firefly.setup.ModEntities;
import mart.firefly.setup.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class FireflyJarItem extends Item {

    private final FireflyEntity.FireflyType type;

    public FireflyJarItem(String name, FireflyEntity.FireflyType type) {
        super(new Item.Properties().maxStackSize(16).group(Firefly.GROUP));
        setRegistryName(name);
        this.type = type;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote && playerIn.isSneaking()){
            Entity firefly = ModEntities.FIREFLY.spawn(worldIn, ItemStack.EMPTY, playerIn, playerIn.getPosition(), SpawnReason.TRIGGERED, false, false);
            firefly.getDataManager().set(FireflyEntity.TYPE, type.name());
            if(!playerIn.abilities.isCreativeMode){
                playerIn.getHeldItem(handIn).shrink(1);
                playerIn.addItemStackToInventory(new ItemStack(ModItems.FIREFLY_JAR));
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
