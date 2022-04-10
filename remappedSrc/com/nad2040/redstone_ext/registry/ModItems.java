package com.nad2040.redstone_ext.registry;

import com.nad2040.redstone_ext.RedstoneExtended;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    //Items


    //Block Items
    public static final BlockItem HEX_BLOCK = new BlockItem(ModBlocks.HEX_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE));
    public static final BlockItem MEM_IO_BLOCK = new BlockItem(ModBlocks.MEM_IO_BLOCK, new Item.Settings().group(ItemGroup.REDSTONE));

    //Register
    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(RedstoneExtended.MOD_ID, "hex_block"), HEX_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(RedstoneExtended.MOD_ID, "mem_io_block"), MEM_IO_BLOCK);
    }
}