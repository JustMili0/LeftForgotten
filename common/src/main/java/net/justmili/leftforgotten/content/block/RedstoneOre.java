package net.justmili.leftforgotten.content.block;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class RedstoneOre extends RedStoneOreBlock {

    public RedstoneOre() {
        super(Properties.of()
            .mapColor(MapColor.STONE)
            .sound(SoundType.STONE)
            .strength(3f)
            .lightLevel(state -> state.getValue(LIT) ? 9 : 0)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .randomTicks()
        );
    }

    @Override // Purposefully added extra functionality
    public void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        var pos = hit.getBlockPos();
        if (!state.getValue(LIT)) {
            level.setBlock(pos, state.setValue(LIT, true), 3);
        }
    }
}