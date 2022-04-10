package com.nad2040.redstone_ext.block;

import com.nad2040.redstone_ext.RedstoneExtended;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block HEX_BLOCK = registerBlock("hex_block",
        new HexadecimalBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool()
            .sounds(BlockSoundGroup.STONE)
            .strength(2f, 30f)
            .luminance(15)));

    public static final Block MEMORY_IO_BLOCK = registerBlock("memory_io_block",
        new MemoryIOBlock(FabricBlockSettings.of(Material.STONE)
            .requiresTool()
            .sounds(BlockSoundGroup.STONE)
            .strength(2f, 30f)));

    private static Block registerBlockWithoutBlockItem(String name, Block block){
        return Registry.register(Registry.BLOCK, new Identifier(RedstoneExtended.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(RedstoneExtended.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registry.ITEM, new Identifier(RedstoneExtended.MOD_ID, name),
            new BlockItem(block, new FabricItemSettings().group(ItemGroup.REDSTONE)));
    }

    public static void registerModBlocks() {
        System.out.println("Registering ModBlocks for " + RedstoneExtended.MOD_ID);
    }
}
