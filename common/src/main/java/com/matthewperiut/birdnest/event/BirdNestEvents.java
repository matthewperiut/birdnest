package com.matthewperiut.birdnest.event;

import dev.architectury.event.events.common.LootEvent;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

import static com.matthewperiut.birdnest.item.BirdNestItems.BIRD_NEST;


public class BirdNestEvents {
    public static void initialize() {
        LootEvent.MODIFY_LOOT_TABLE.register(BirdNestEvents::modifyLootTable);
    }

    private static void modifyLootTable(RegistryKey<LootTable> lootTableRegistryKey, LootEvent.LootTableModificationContext lootTableModificationContext, boolean builtin) {
        if (!builtin) return;
        if (!isLeavesBlock(lootTableRegistryKey)) return;

        LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.05f))
                .with(ItemEntry.builder(BIRD_NEST.get()))
                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)));
        lootTableModificationContext.addPool(poolBuilder);
    }

    public static boolean isLeavesBlock(RegistryKey<LootTable> key) {
        Identifier id = key.getValue();
        String[] split = id.getPath().split("/");
        String type = split[0];
        if (!type.equals("blocks")) return false;

        String blockPath = split[split.length-1];
        Identifier blockId = id.withPath(blockPath);
        Optional<Block> block = Registries.BLOCK.getOrEmpty(blockId);
        return block.filter(value -> value instanceof LeavesBlock).isPresent();
    }
}
