package com.nad2040.redstone_ext;

import com.nad2040.redstone_ext.registry.ModBlocks;
import com.nad2040.redstone_ext.registry.ModItems;
import net.fabricmc.api.ModInitializer;

public class RedstoneExtended implements ModInitializer {
	public static final String MOD_ID = "redstone-ext";

	@Override
	public void onInitialize() {
		ModBlocks.registerBlocks();
		ModItems.registerItems();
	}
}
