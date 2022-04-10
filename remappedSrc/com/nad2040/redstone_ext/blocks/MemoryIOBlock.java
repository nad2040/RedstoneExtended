package com.nad2040.redstone_ext.block;

import com.nad2040.redstone_ext.block.enums.Endianness;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;

public class MemoryIOBlock extends Block {
    private Direction dir;
    private int x,z;

    public static final DirectionProperty FACING;
    public static final EnumProperty<Endianness> ENDIANNESS;

    public MemoryIOBlock(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(ENDIANNESS, Endianness.BIG));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) { builder.add(FACING, ENDIANNESS); }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        dir = ctx.getPlayerFacing().rotateYClockwise();
        x = switch (dir) {
            case EAST -> 1;
            case WEST -> -1;
            default -> 0;
        };
        z = switch (dir) {
            case SOUTH -> 1;
            case NORTH -> -1;
            default -> 0;
        };
        return this.getDefaultState().with(FACING, dir);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            if (player.isSneaking()) {
                state = state.cycle(ENDIANNESS);
                world.setBlockState(pos, state);
                player.sendMessage(new LiteralText("Endianness set to " + state.get(ENDIANNESS)), false);
            } else {
                getMemoryValue(state, world, pos, player);
            }
            return ActionResult.CONSUME;
        }
    }

    private void getMemoryValue(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        BlockPos next = pos.add(x, 0, z);
        BlockState s = world.getBlockState(next);

        //read hex_block digits
        ArrayList<Byte> nibbles = new ArrayList<>();
        while (s.getBlock() instanceof HexadecimalBlock) {
            nibbles.add(s.get(HexadecimalBlock.POWER).byteValue());
            next = next.add(x, 0, z);
            s = world.getBlockState(next);
        }
        //player.sendMessage(new LiteralText(nibbles.toString()), false); // debug

        //condense to bytes for easy endian handling. Cut off last nibble if odd count
        ArrayList<Short> bytes = new ArrayList<>();
        for (int i = 0; i < nibbles.size() / 2; ++i) {
            bytes.add((short) ((nibbles.get(2 * i) << 4) | nibbles.get(2 * i + 1)));
        }
        //player.sendMessage(new LiteralText(bytes.toString()), false); // debug

        long value = 0L;
        switch (state.get(ENDIANNESS)) {
            case BIG -> {
                for (int i=0; i<bytes.size() && i<8; i++) {
                    value <<= 8;
                    value |= bytes.get(i);
                }
            }
            case LITTLE -> {
                for (int i=0; i<bytes.size() && i<8; i++) {
                    value |= (((long) bytes.get(i)) << 8 * i);
                }
            }
        }

        if (nibbles.size() > 16) player.sendMessage(new TranslatableText("block.redstone-ext.mem_io_block.too_much_data_error"), false);
        if (nibbles.size() % 2 == 1) player.sendMessage(new TranslatableText("block.redstone-ext.mem_io_block.odd_count_error"), false);

        player.sendMessage(new LiteralText("Endianness: " + state.get(ENDIANNESS) + "\nValue: " + value), false);
    }

    private void setMemoryValue(long value, BlockState state, World world, BlockPos pos) {
        BlockPos next = pos.add(x, 0, z);
        BlockState s = world.getBlockState(next);

        ArrayList<BlockPos> blockPositions = new ArrayList<>();
        while (s.getBlock() instanceof HexadecimalBlock && blockPositions.size() <= 16) {
            blockPositions.add(next);
            next = next.add(x, 0, z);
            s = world.getBlockState(next);
        }
        if (blockPositions.size() % 2 == 1) blockPositions.remove(blockPositions.size()-1); // remove the last one if odd.

        switch (state.get(ENDIANNESS)) {
            case BIG -> {

            }
            case LITTLE -> {

            }
        }
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        ENDIANNESS = EnumProperty.of("endian", Endianness.class);
    }
}
