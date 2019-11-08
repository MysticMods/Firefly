package mart.firefly.tile;

import mart.firefly.item.FireflyPotion;
import mart.firefly.recipes.CauldronRecipe;
import mart.firefly.registry.ModBlocks;
import mart.firefly.registry.ModRecipes;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static mart.firefly.block.CauldronBlock.ON;
import static mart.firefly.block.CauldronBlock.WATER;

public class CauldronTile extends TileEntity implements ITickableTileEntity {

    private LazyOptional<ItemStackHandler> handler = LazyOptional.of(this::createHandler);

    public CauldronTile() {
        super(ModBlocks.CAULDRON_TILE);
    }

    public boolean onActivated(PlayerEntity playerIn, Hand hand) {
        if(playerIn.getHeldItem(hand).getItem() == Items.FLINT_AND_STEEL){
            world.setBlockState(getPos(), getBlockState().with(ON, true));
            return true;
        } else if(playerIn.getHeldItem(hand).getItem() == Items.WATER_BUCKET){
            world.setBlockState(getPos(), getBlockState().with(WATER, true));
            if(!playerIn.isCreative()){
                playerIn.setHeldItem(hand, new ItemStack(Items.BUCKET));
            }
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        if(getBlockState().get(WATER)){
            List<ItemEntity> items = world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(
                    getPos().getX(),getPos().getY(),getPos().getZ(),
                    getPos().getX() + 1,getPos().getY() + 0.5,getPos().getZ() + 1)
            );

            if(world.getBlockState(this.pos).get(ON)){
                if(items.size() > 0){
                    for(ItemEntity entity : items){
                        handler.ifPresent(handler ->{
                            for(int i = 0; i < handler.getSlots(); i++){
                                ItemStack returnStack = handler.insertItem(i, entity.getItem(), false);
                                if(returnStack == ItemStack.EMPTY){
                                    entity.remove();

                                    List<ItemStack> handlerItems = getItemListFromHandler();
                                    for(CauldronRecipe recipe : ModRecipes.getPotionRecipes()){
                                        if(recipe.matches(handlerItems)){
                                            world.addEntity(new ItemEntity(world, this.pos.getX() + 0.5, this.pos.getY() + 1, this.pos.getZ() + 0.5, recipe.getResult().copy()));
                                            clearStorage();
                                        }
                                    }

                                    break;
                                }
                            }
                        });
                    }
                }
            }
        }
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
                return !(stack.getItem() instanceof FireflyPotion);
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

            @Override
            protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
                return 1;
            }
        };
    }

    public List<ItemStack> getItemListFromHandler(){
        List<ItemStack> items = new ArrayList<>();
        this.handler.ifPresent(itemStackHandler -> {
            for(int i = 0; i < itemStackHandler.getSlots(); i++){
                items.add(itemStackHandler.getStackInSlot(i).copy());
            }
        });
        return items;
    }

    public void clearStorage() {
        this.handler.ifPresent(handler ->{
            for (int i = 0; i < handler.getSlots(); i++) {
                handler.setStackInSlot(i, ItemStack.EMPTY);
            }
        });

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
