
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.libs.v1.utils.common.BlockBehaviorUtil;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class WoodenFence extends FenceBlock {
	public WoodenFence() {
		super(Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(2f, 3f).noOcclusion().isRedstoneConductor(BlockBehaviorUtil::no).forceSolidOn().instrument(NoteBlockInstrument.BASS).ignitedByLava());
	}
}