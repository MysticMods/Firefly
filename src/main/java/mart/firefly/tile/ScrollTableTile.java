package mart.firefly.tile;

import mart.firefly.gui.ScrollTableContainer;
import mart.firefly.item.FireflyJuiceItem;
import mart.firefly.item.scroll.ScrollItem;
import mart.firefly.setup.ModRecipes;
import mart.firefly.setup.ModTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ScrollTableTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private LazyOptional<ItemStackHandler> handler = LazyOptional.of(this::createHandler);

    public ScrollTableTile() {
        super(ModTileEntities.SCROLL_TABLE_TILE);
    }

    @Override
    public void tick() {

    }

    public void activate(){
        handler.ifPresent(h ->{
            if(h.getStackInSlot(2) != ItemStack.EMPTY){
                return;
            }
            ItemStack juice = h.getStackInSlot(0);
            ItemStack paper = h.getStackInSlot(1);
            if(juice == ItemStack.EMPTY || paper == ItemStack.EMPTY){
                return;
            }

            Item outputItem = ModRecipes.getScrollRecipeOutput(juice.getItem());
            if(outputItem == null){
                return;
            }

            int level = juice.getCount();
            if(level > 10){
                level = 10;
            }

            juice.shrink(level);
            paper.shrink(1);
            ItemStack outputItemStack = new ItemStack(outputItem);
            ScrollItem.setScrollLevel(outputItemStack, level);
            h.setStackInSlot(2, outputItemStack);
            world.playSound(null, getPos(), SoundEvents.ITEM_BOOK_PAGE_TURN, SoundCategory.BLOCKS, 1, 1);
        });

    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Scroll Table");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory inventory, PlayerEntity playerEntity) {
        return new ScrollTableContainer(id, getPos(), inventory);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(3) {

            @Override
            protected void onContentsChanged(int slot) {
                markDirty();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if(slot == 0){
                    return stack.getItem() instanceof FireflyJuiceItem;
                }
                else if(slot == 1){
                    return stack.getItem() == Items.PAPER;
                }
                return false;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }

            @Nonnull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                return super.extractItem(slot, amount, simulate);
            }
        };
    }

    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        handler.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            tag.put("inv", compound);
        });
        return super.write(tag);
    }
}
