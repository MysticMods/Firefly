package mart.firefly.item;

import mart.firefly.Firefly;
import net.minecraft.item.Item;

public class FireflyPotion extends Item {

    public FireflyPotion(String name) {
        super(new Item.Properties().group(Firefly.GROUP));
        setRegistryName(name);
    }
}
