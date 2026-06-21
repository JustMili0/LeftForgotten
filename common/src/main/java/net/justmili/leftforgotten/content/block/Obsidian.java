
package net.justmili.leftforgotten.content.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class Obsidian extends Block {
	public Obsidian() {
		super(Properties.of().mapColor(MapColor.COLOR_BLACK).sound(SoundType.STONE).strength(50f, 1200f).requiresCorrectToolForDrops().pushReaction(PushReaction.IGNORE));
	}
}
