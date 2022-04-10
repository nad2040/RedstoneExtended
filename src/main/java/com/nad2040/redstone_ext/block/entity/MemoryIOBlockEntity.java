package com.nad2040.redstone_ext.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MemoryIOBlockEntity extends BlockEntity implements NamedScreenHandlerFactory {

    private int memory;

    public MemoryIOBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MEMORY_IO_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("memory", memory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        memory = nbt.getInt("memory");
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("Memory IO");
    }

    public static void tick(World world, BlockPos pos, BlockState state, MemoryIOBlockEntity entity) {

    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return null;
    }
}
