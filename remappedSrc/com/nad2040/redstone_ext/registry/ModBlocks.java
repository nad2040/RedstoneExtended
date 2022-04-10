package com.nad2040.redstone_ext.registry;

import com.nad2040.redstone_ext.RedstoneExtended;
import com.nad2040.redstone_ext.block.HexadecimalBlock;
import com.nad2040.redstone_ext.block.MemoryIOBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final HexadecimalBlock HEX_BLOCK;
    public static final MemoryIOBlock MEM_IO_BLOCK;

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(RedstoneExtended.MOD_ID, "hex_block"), HEX_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(RedstoneExtended.MOD_ID, "mem_io_block"), MEM_IO_BLOCK);
    }

    static {
        HEX_BLOCK = new HexadecimalBlock(FabricBlockSettings.of(Material.STONE).requiresTool().sounds(BlockSoundGroup.STONE).strength(2f, 30f).luminance(15));
        MEM_IO_BLOCK = new MemoryIOBlock(FabricBlockSettings.of(Material.STONE).requiresTool().sounds(BlockSoundGroup.STONE).strength(2f, 30f));
    }
}
