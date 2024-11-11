package com.matthewperiut.birdnest;

import com.matthewperiut.birdnest.config.BirdNestConfig;
import com.matthewperiut.birdnest.event.BirdNestEvents;
import com.matthewperiut.birdnest.item.BirdNestItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BirdNest
{
	public static final String MOD_ID = "birdnest";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static void init()
	{
		BirdNestConfig.init();
		BirdNestItems.initialize();
		BirdNestEvents.initialize();
	}
}
