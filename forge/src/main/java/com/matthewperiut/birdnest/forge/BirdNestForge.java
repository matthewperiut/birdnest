package com.matthewperiut.birdnest.forge;

import dev.architectury.platform.forge.EventBuses;
import com.matthewperiut.birdnest.BirdNest;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BirdNest.MOD_ID)
public class BirdNestForge {
    public BirdNestForge() {
        EventBuses.registerModEventBus(BirdNest.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        BirdNest.init();
    }
}
