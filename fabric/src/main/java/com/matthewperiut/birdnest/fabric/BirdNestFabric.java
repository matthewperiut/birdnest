package com.matthewperiut.birdnest.fabric;

import com.matthewperiut.birdnest.BirdNest;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class BirdNestFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BirdNest.init();
    }
}
