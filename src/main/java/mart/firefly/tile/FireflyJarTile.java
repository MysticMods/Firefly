package mart.firefly.tile;

import mart.firefly.entity.FireflyEntity;
import mart.firefly.setup.ModTileEntities;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;

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
