package com.matthewperiut.birdnest.neoforge;

import com.matthewperiut.birdnest.BirdNest;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(BirdNest.MOD_ID)
public class BirdNestNeoForge
{
    public BirdNestNeoForge(IEventBus modEventBus)
    {
        BirdNest.init();
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
}