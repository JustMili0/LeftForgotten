
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.content.block.dev.CommonBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;

public class WoodenFenceGate extends FenceGateBlock {
	public WoodenFenceGate() {
		super(Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(21f, 3f).noOcclusion().isRedstoneConductor(CommonBlock::never).forceSolidOn().instrument(NoteBlockInstrument.BASS).ignitedByLava(), WoodType.OAK);
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

}
