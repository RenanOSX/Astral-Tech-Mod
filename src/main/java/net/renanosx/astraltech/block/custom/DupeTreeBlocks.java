package net.renanosx.astraltech.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.renanosx.astraltech.Config;
import net.renanosx.astraltech.block.ModBlocks;

public class DupeTreeBlocks extends Block {
    public DupeTreeBlocks(Properties properties) {
        super(properties);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.randomTick(state, world, pos, random);
        DuplicatorTree(world, pos.getX(), pos.getY(), pos.getZ());
    }

    public void DuplicatorTree(Level world, int x, int y, int z) {

        // Find valid soil (3 blocks below base block)
        int realy = -1;
        for (int i = 1; i <= 3; i++) {
            BlockPos below = new BlockPos(x, y - i, z);
            Block b = world.getBlockState(below).getBlock();
            if (b == Blocks.GRASS_BLOCK || b == Blocks.DIRT || b == Blocks.FARMLAND) {
                realy = y - i;
                break;  // break loop if it finds a soil
            }
        }
        if (realy == -1) {
            // no valid soil on y-1, y-2, y-3
            return;
        }

        // If the base is air (Not connected to the earth)
        BlockPos base = new BlockPos(x, realy+1 , z);
        Block b = world.getBlockState(base).getBlock();
        if (b != Blocks.AIR) {

            // Grow trunk (3 times from the base)
            for (int h = 1; h <= 3; h++) {
                BlockPos trunkPos = new BlockPos(x, realy + h, z);
                BlockState stateHere = world.getBlockState(trunkPos);
                if (stateHere.getBlock() != ModBlocks.DUPE_TREE.get()) {
                    world.setBlockAndUpdate(trunkPos, ModBlocks.DUPE_TREE.get().defaultBlockState());
                    return; // One block per tick
                }
            }

            // Grow top leave
            BlockPos topCenter = new BlockPos(x, realy + 4, z);
            if (world.getBlockState(topCenter).getBlock() != ModBlocks.DUPE_LEAVES.get()) {
                world.setBlockAndUpdate(topCenter, ModBlocks.DUPE_LEAVES.get().defaultBlockState());
                return;
            }
            // Grow leaves around the trunk (hollow middle)
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if (dx == 0 && dz == 0) continue;
                    BlockPos leafPos = new BlockPos(x + dx, realy + 3, z + dz);
                    if (world.getBlockState(leafPos).getBlock() != ModBlocks.DUPE_LEAVES.get()) {
                        world.setBlockAndUpdate(leafPos, ModBlocks.DUPE_LEAVES.get().defaultBlockState());
                        return;
                    }
                }
            }

            // If the tree is allowed to dupe blocks
            if (!Config.dupeTreeAllowed) {
                return;
            }
            Block bidm = Blocks.AIR;
            // Search for block to dupe
            for (int tries = 0; tries < 20; tries++) {
                int k = world.random.nextInt(5) - 2; // -2 to +2
                int j = world.random.nextInt(5) - 2;
                bidm = world.getBlockState(new BlockPos(x + k, realy + 1, z + j)).getBlock();
                if (bidm != Blocks.AIR && bidm != ModBlocks.DUPE_TREE.get()) {
                    break;
                }
            }
            // If it found a valid block to dupe, find a spot to copy it there
            if (bidm != Blocks.AIR && bidm != ModBlocks.DUPE_TREE.get()) {
                for (int m = 0; m < 20; m++) {
                    int k = world.random.nextInt(5) - 2;
                    int j = world.random.nextInt(5) - 2;
                    BlockPos target = new BlockPos(x + k, realy + 1, z + j);
                    Block existing = world.getBlockState(target).getBlock();
                    if (existing == Blocks.AIR) {
                        world.setBlockAndUpdate(target, bidm.defaultBlockState());
                        return;
                    }
                }
            }
        }
    }
}