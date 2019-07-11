package mart.firefly.gui;

import mart.firefly.gui.button.ContainerButton;
import mart.firefly.registry.ModBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class BaseContainer extends Container {

    protected TileEntity tileEntity;
    protected List<ContainerButton> buttonList;

    public BaseContainer(int id, World world, BlockPos pos, PlayerInventory inventory) {
        super(ModBlocks.FIREFLY_PRESS_CONTAINER, id);
        this.tileEntity = world.getTileEntity(pos);
        buttonList = new ArrayList<>();
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return false;
    }

    public void addButton(ContainerButton button){
        this.buttonList.add(button);
    }
}
