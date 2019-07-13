package mart.firefly.block;

import java.util.function.Supplier;

public interface ITile<T> {

    Supplier<T> getTile();

}
