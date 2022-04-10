package com.nad2040.redstone_ext.block.entity;

import com.nad2040.redstone_ext.RedstoneExtended;
import com.nad2040.redstone_ext.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<MemoryIOBlockEntity> MEMORY_IO_BLOCK_ENTITY =
        Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(RedstoneExtended.MOD_ID, "memory_io"),
            FabricBlockEntityTypeBuilder.create(MemoryIOBlockEntity::new,
                ModBlocks.MEMORY_IO_BLOCK).build(null));

    public static void registerModBlockEntities() {
        System.out.println("Registering ModBlockEntities for " + RedstoneExtended.MOD_ID);
    }
}
