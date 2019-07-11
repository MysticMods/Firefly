package mart.firefly.item;

import mart.firefly.Firefly;
import net.minecraft.item.Item;

public class FireflyJuiceItem extends Item {

    public FireflyJuiceItem(String name) {
        super(new Item.Properties().group(Firefly.GROUP));
        setRegistryName(name);
    }
}
