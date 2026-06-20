package net.justmili.leftforgotten.content.block.dev;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.ToIntFunction;

public class CommonBlock {
    public static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (blockState) -> (Boolean)blockState.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }

    public static Boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entity) {
        return false;
    }

    public static Boolean always(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entity) {
        return true;
    }

    public static boolean always(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return true;
    }

    public static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return false;
    }

    public static BlockBehaviour.Properties log(MapColor topMapColor, MapColor sideMapColor) {
        /** axis Y = topMapColor, other = sideMapColor
         *  sound: SoundType.WOOD (default)
         */
        return BlockBehaviour.Properties.of()
            .mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sound(SoundType.WOOD)
            .ignitedByLava();
    }

    public static BlockBehaviour.Properties log(MapColor topMapColor, MapColor sideMapColor, SoundType soundType) {
        return BlockBehaviour.Properties.of()
            .mapColor((blockState) -> blockState.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F)
            .sound(soundType)
            .ignitedByLava();
    }

    public static BlockBehaviour.Properties leaves() {
        return BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT)
            .strength(0.2F)
            .randomTicks()
            .sound(SoundType.GRASS)
            .noOcclusion()
            .isValidSpawn(CommonBlock::ocelotOrParrot)
            .isSuffocating(CommonBlock::never)
            .isViewBlocking(CommonBlock::never)
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(CommonBlock::never);
    }

    public static BlockBehaviour.Properties button() {
        /** wooden - BlockSetType.OAK, 30, true
         *  stone - BlockSetType.STONE, 20, false
         */
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);

        return properties;
    }

    private static Boolean ocelotOrParrot(BlockState state, BlockGetter blockGetter, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }
}
