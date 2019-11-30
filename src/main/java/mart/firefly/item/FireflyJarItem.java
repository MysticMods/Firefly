package mart.firefly.item;

import mart.firefly.Firefly;
import mart.firefly.entity.FireflyEntity;
import mart.firefly.setup.ModBlocks;
import mart.firefly.setup.ModEntities;
import mart.firefly.setup.ModItems;
import mart.firefly.tile.FireflyJarTile;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class FireflyJarItem extends Item {

    private final FireflyEntity.FireflyType type;

    public FireflyJarItem(String name, FireflyEntity.FireflyType type) {
        super(new Item.Properties().maxStackSize(16).group(Firefly.GROUP));
        setRegistryName(name);
        this.type = type;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if(context.getPlayer().isSneaking()){
            return ActionResultType.FAIL;
        }
        ActionResultType actionresulttype = this.tryPlace(new BlockItemUseContext(context));
        return actionresulttype != ActionResultType.SUCCESS && this.isFood() ? this.onItemRightClick(context.getWorld(), context.getPlayer(), context.getHand()).getType() : actionresulttype;
    }

    public ActionResultType tryPlace(BlockItemUseContext context) {
        if (!context.canPlace()) {
            return ActionResultType.FAIL;
        } else {
            BlockState blockstate = this.getStateForPlacement(context);
            if (blockstate == null) {
                return ActionResultType.FAIL;
            } else if (!this.placeBlock(context, blockstate)) {
                return ActionResultType.FAIL;
            } else {
                BlockPos blockpos = context.getPos();
                World world = context.getWorld();
                PlayerEntity playerentity = context.getPlayer();
                ItemStack itemstack = context.getItem();
                BlockState blockstate1 = world.getBlockState(blockpos);
                Block block = blockstate1.getBlock();
                if (block == blockstate.getBlock()) {
                    blockstate1 = this.func_219985_a(blockpos, world, itemstack, blockstate1);
                    //this.onBlockPlaced(blockpos, world, playerentity, itemstack, blockstate1);
                    block.onBlockPlacedBy(world, blockpos, blockstate1, playerentity, itemstack);
                    if (playerentity instanceof ServerPlayerEntity) {
                        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerentity, blockpos, itemstack);
                    }
                }

                SoundType soundtype = blockstate1.getSoundType(world, blockpos, context.getPlayer());
                world.playSound(playerentity, blockpos, this.getPlaceSound(blockstate1, world, blockpos, context.getPlayer()), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                itemstack.shrink(1);

                FireflyJarTile  tile = (FireflyJarTile) world.getTileEntity(blockpos);
                if (tile != null) {
                    tile.setFireflyType(type);
                }

                return ActionResultType.SUCCESS;
            }
        }
    }

    @Nullable
    protected BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState blockstate = ModBlocks.FIREFLY_JAR.get().getStateForPlacement(context);
        return blockstate != null && this.canPlace(context, blockstate) ? blockstate : null;
    }


    protected boolean canPlace(BlockItemUseContext p_195944_1_, BlockState p_195944_2_) {
        PlayerEntity playerentity = p_195944_1_.getPlayer();
        ISelectionContext iselectioncontext = playerentity == null ? ISelectionContext.dummy() : ISelectionContext.forEntity(playerentity);
        return (p_195944_2_.isValidPosition(p_195944_1_.getWorld(), p_195944_1_.getPos())) && p_195944_1_.getWorld().func_217350_a(p_195944_2_, p_195944_1_.getPos(), iselectioncontext);
    }

    protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
        return context.getWorld().setBlockState(context.getPos(), state, 11);
    }

    protected SoundEvent getPlaceSound(BlockState state, World world, BlockPos pos, PlayerEntity entity) {
        return state.getSoundType(world, pos, entity).getPlaceSound();
    }

    private BlockState func_219985_a(BlockPos p_219985_1_, World p_219985_2_, ItemStack p_219985_3_, BlockState p_219985_4_) {
        BlockState blockstate = p_219985_4_;
        CompoundNBT compoundnbt = p_219985_3_.getTag();
        if (compoundnbt != null) {
            CompoundNBT compoundnbt1 = compoundnbt.getCompound("BlockStateTag");
            StateContainer<Block, BlockState> statecontainer = p_219985_4_.getBlock().getStateContainer();

            for(String s : compoundnbt1.keySet()) {
                IProperty<?> iproperty = statecontainer.getProperty(s);
                if (iproperty != null) {
                    String s1 = compoundnbt1.get(s).getString();
                    blockstate = func_219988_a(blockstate, iproperty, s1);
                }
            }
        }

        if (blockstate != p_219985_4_) {
            p_219985_2_.setBlockState(p_219985_1_, blockstate, 2);
        }

        return blockstate;
    }

    private static <T extends Comparable<T>> BlockState func_219988_a(BlockState p_219988_0_, IProperty<T> p_219988_1_, String p_219988_2_) {
        return p_219988_1_.parseValue(p_219988_2_).map((p_219986_2_) -> {
            return p_219988_0_.with(p_219988_1_, p_219986_2_);
        }).orElse(p_219988_0_);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(!worldIn.isRemote){
            if(playerIn.isSneaking()){
                Entity firefly = ModEntities.FIREFLY.spawn(worldIn, ItemStack.EMPTY, playerIn, playerIn.getPosition(), SpawnReason.TRIGGERED, false, false);
                firefly.getDataManager().set(FireflyEntity.TYPE, type.name());
                if(!playerIn.abilities.isCreativeMode){
                    playerIn.getHeldItem(handIn).shrink(1);
                    playerIn.addItemStackToInventory(new ItemStack(ModItems.FIREFLY_JAR));
                }
            }

        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
