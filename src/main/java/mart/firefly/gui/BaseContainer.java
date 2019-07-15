package mart.firefly.gui;

import mart.firefly.gui.button.ContainerButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BaseContainer extends Container {

    protected TileEntity tileEntity;
    protected List<ContainerButton> buttonList;

    public BaseContainer(@Nullable ContainerType<?> type, int id, World world, BlockPos pos, PlayerInventory inventory) {
        super(type, id);
        this.tileEntity = world.getTileEntity(pos);
        buttonList = new ArrayList<>();
    }

    public void fillPlayerInv(PlayerInventory inventory){
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return false;
    }

    public void addButton(ContainerButton button){
        this.buttonList.add(button);
    }
}
