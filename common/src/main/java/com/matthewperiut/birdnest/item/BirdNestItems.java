package com.matthewperiut.birdnest.item;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static com.matthewperiut.birdnest.BirdNest.MOD_ID;

public class BirdNestItems {
    public static final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));
    public static final Registrar<Item> ITEMS = MANAGER.get().get(RegistryKeys.ITEM);
    public static final RegistrySupplier<Item> BIRD_NEST = ITEMS.register(Identifier.of(MOD_ID, "birds_nest"), () -> new BirdsNestItem(new Item.Settings()));

    public static void initialize() {
        CreativeTabRegistry.modifyBuiltin(Registries.ITEM_GROUP.get(ItemGroups.TOOLS.getValue()), (flags, output, canUseGameMasterBlocks) -> {
            output.acceptAfter(Items.WARPED_FUNGUS_ON_A_STICK, new ItemStack(BIRD_NEST));
        });
    }
}
