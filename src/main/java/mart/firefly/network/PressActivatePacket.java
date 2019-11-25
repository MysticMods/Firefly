package mart.firefly.network;

import mart.firefly.tile.FireflyPressTile;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PressActivatePacket {

    private BlockPos tileEntityPos;

    public PressActivatePacket(PacketBuffer buf)
    {
        tileEntityPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    public PressActivatePacket(BlockPos pos){
        this.tileEntityPos = pos;
    }

    public void encode(PacketBuffer buf)
    {
        buf.writeInt(tileEntityPos.getX());
        buf.writeInt(tileEntityPos.getY());
        buf.writeInt(tileEntityPos.getZ());
    }

    public void handle(Supplier<NetworkEvent.Context> context)
    {
        context.get().enqueueWork(() -> activatePress(tileEntityPos, context.get().getSender().world));
    }

    public static void activatePress(BlockPos tileEntityPos, World world){
        TileEntity press = world.getTileEntity(tileEntityPos);
        if(press instanceof FireflyPressTile){
            ((FireflyPressTile)press).activate();
        }
    }

}
