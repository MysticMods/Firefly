package mart.firefly.tile;

import mart.firefly.entity.FireflyEntity;
import mart.firefly.setup.ModTileEntities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class FireflyJarTile extends TileEntity implements ITickableTileEntity {

    private FireflyEntity.FireflyType type = null;
    private int coordTex = 240;
    private boolean up = false;

    public FireflyJarTile() {
        super(ModTileEntities.FIREFLY_JAR_BLOCK_TILE);
    }

    @Override
    public void tick() {

    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putString("fireflyType", type.toString());
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        System.out.println(compound.getString("fireflyType"));
        this.type = FireflyEntity.FireflyType.valueOf(compound.getString("fireflyType"));
        super.read(compound);
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
