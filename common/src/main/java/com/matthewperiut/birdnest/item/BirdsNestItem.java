package com.matthewperiut.birdnest.item;

import com.matthewperiut.birdnest.BirdNest;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.context.LootWorldContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class BirdsNestItem extends Item {

    private static final RegistryKey<LootTable> LOOT_TABLE = RegistryKey.of(
            RegistryKeys.LOOT_TABLE,
            Identifier.of(BirdNest.MOD_ID, "gameplay/birds_nest_loot")
    );

    public BirdsNestItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        var stack = player.getStackInHand(hand);
        if (world.isClient) return ActionResult.SUCCESS;

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.NEUTRAL, 0.5F, 0.4F / (player.getRandom().nextFloat() * 0.4F + 0.8F));

        stack.decrementUnlessCreative(1, player);
        world.playSound(player, player.getBlockPos(), SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        spawnLoot((ServerWorld)world, player);
        return ActionResult.SUCCESS;
    }

    private static void spawnLoot(ServerWorld world, PlayerEntity player) {
        LootTable lootTable = world.getServer().getReloadableRegistries().getLootTable(LOOT_TABLE);
        LootWorldContext parameters = new LootWorldContext.Builder(world)
                .build(LootContextTypes.EMPTY);
        List<ItemStack> loot = lootTable.generateLoot(parameters);
        if (loot.isEmpty()) return;

        loot.forEach(stack -> {
            ItemEntity entity = new ItemEntity(
                    world,
                    player.getX(), player.getY() + 0.5, player.getZ(),
                    stack
            );
            entity.setVelocity(
                    player.getRandom().nextGaussian() * 0.05F,
                    0.2D,
                    player.getRandom().nextGaussian() * 0.05F
            );
            world.spawnEntity(entity);
        });
    }

}
