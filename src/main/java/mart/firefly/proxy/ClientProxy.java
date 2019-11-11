package mart.firefly.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class ClientProxy implements IModProxy {
    @Override
    public World getWorld() {
        return Minecraft.getInstance().world;
    }
}
