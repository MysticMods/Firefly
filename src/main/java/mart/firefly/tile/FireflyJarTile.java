package mart.firefly.tile;

import mart.firefly.entity.FireflyEntity;
import mart.firefly.setup.ModTileEntities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public class FireflyJarTile extends TileEntity{

    private FireflyEntity.FireflyType type;
    private int coordTex = 240;
    private boolean up = false;

    public FireflyJarTile() {
        super(ModTileEntities.FIREFLY_JAR_BLOCK_TILE);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        if(type !=  null){
            compound.putString("fireflyType", type.toString());
        }
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        System.out.println(compound.getString("fireflyType"));
        if(type == null){
            this.type = FireflyEntity.FireflyType.valueOf(compound.getString("fireflyType"));
        }
        super.read(compound);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 9, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        read(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return write(super.getUpdateTag());
    }

    public FireflyEntity.FireflyType getFireflyType() {
        return type;
    }

    public void setFireflyType(FireflyEntity.FireflyType type) {
        this.type = type;
    }

    public int getCoordTex() {
        return coordTex;
    }

    public void setCoordTex(int coordTex) {
        this.coordTex = coordTex;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
}
