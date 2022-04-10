package com.nad2040.redstone_ext;

import com.nad2040.redstone_ext.block.ModBlocks;
import com.nad2040.redstone_ext.block.entity.ModBlockEntities;
import com.nad2040.redstone_ext.item.ModItems;
import net.fabricmc.api.ModInitializer;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RedstoneExtended implements ModInitializer {
	public static final String MOD_ID = "redstone-ext";
	public static final Logger LOGGER = LogManager.getLogManager().getLogger("redstone-ext");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric World!");
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();
	}
}
