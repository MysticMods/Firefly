package mart.firefly.gui;

import mart.firefly.Firefly;
import mart.firefly.gui.button.ContainerButton;
import mart.firefly.network.PressActivatePacket;
import mart.firefly.setup.ModBlocks;
import mart.firefly.tile.FireflyPressTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class FireflyPressContainer extends BaseContainer {

    public FireflyPressContainer(int id, BlockPos pos, PlayerInventory inventory) {
        super(ModBlocks.FIREFLY_PRESS_CONTAINER, id, pos, inventory);

        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(new SlotItemHandler(h, 0, 80, 24));
        });

        addButton(new ContainerButton(79, 58){
            @Override
            public void activate(BaseContainer container) {
                if(tileEntity instanceof FireflyPressTile){
                    PressActivatePacket message = new PressActivatePacket(tileEntity.getPos());
                    Firefly.channel.sendToServer(message);
                }
            }
        });

        fillPlayerInv(inventory);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        System.out.println(index);
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();
            if (index == 0 || index == 1) {
                if (!this.mergeItemStack(stack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, itemstack);
            } else {
                if (!this.mergeItemStack(stack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerIn, ModBlocks.FIREFLY_PRESS);
    }
}
