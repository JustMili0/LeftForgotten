
package net.justmili.leftforgotten.content.block;

import net.justmili.leftforgotten.core.registries.BlockRegistry;
import net.minecraft.world.level.block.WallBlock;

public class CobblestoneWall extends WallBlock {
	public CobblestoneWall() {
		super(Properties.copy(BlockRegistry.COBBLESTONE.get()).forceSolidOn());
	}
}