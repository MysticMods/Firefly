package mart.firefly.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import epicsquid.mysticallib.data.DeferredBlockLootTableProvider;
import mart.firefly.registry.ModBlocks;
import mart.firefly.setup.RegistryEvents;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.ValidationResults;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FLootTableProvider extends LootTableProvider {

    public FLootTableProvider(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    public String getName() {
        return "Firefly Loot Table Provider";
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(Pair.of(Blocks::new, LootParameterSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationResults validationresults) {
    }

    public static class Blocks extends DeferredBlockLootTableProvider {
        @Override
        protected void addTables() {
            self(() -> ModBlocks.FIREFLY_PRESS);
            self(() -> ModBlocks.SCROLL_TABLE);
            self(() -> ModBlocks.FIREFLY_LURE);
            self(() -> ModBlocks.CAULDRON);
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return RegistryEvents.getBlocks();
        }
    }
}
