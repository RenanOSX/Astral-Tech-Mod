package net.renanosx.astraltech.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.renanosx.astraltech.block.ModBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MinersDreamItem extends Item {
    private static final int BATCH_SIZE = 500;

    public MinersDreamItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level lvl = context.getLevel();
        if (lvl.isClientSide()) return InteractionResult.SUCCESS;

        ServerLevel world = (ServerLevel) lvl;
        MinecraftServer server = world.getServer();
        Player player = context.getPlayer();
        BlockPos origin = context.getClickedPos();
        Direction dir = player.getDirection();
        short depth = 60; // How deep should the tunnel go
        short side_depth = 4; // How deep each side should be
        short height_depth = 3; // You know the drill lol
        final Set<Block> BLOCK_BLACKLIST = Set.of(
                Blocks.CHEST,
                Blocks.TRAPPED_CHEST,
                Blocks.SPAWNER,
                Blocks.TRIAL_SPAWNER,
                Blocks.BEDROCK,
                Blocks.END_PORTAL,
                Blocks.END_PORTAL_FRAME
        ); // The name already explains it lol

        // Consume item and play sound effect
        context.getItemInHand().shrink(1);
        world.playSound(null, origin,
                SoundEvents.DRAGON_FIREBALL_EXPLODE,
                SoundSource.BLOCKS, 1f, 1f);

        // Preload of blockstates and flags
        BlockState air   = Blocks.AIR.defaultBlockState();
        BlockState glass = Blocks.GLASS.defaultBlockState();
        BlockState torch = Blocks.TORCH.defaultBlockState();
        int invFlag    = Block.UPDATE_INVISIBLE;
        int notifyFlag = Block.UPDATE_CLIENTS | Block.UPDATE_NEIGHBORS;

        // 3. Coleta posições em um único laço
        List<BlockPos> tunnel    = new ArrayList<>();
        List<BlockPos> glassList = new ArrayList<>();
        List<BlockPos> torchList = new ArrayList<>();

        for (int dx = -side_depth; dx <= side_depth; dx++) {
            for (int dy = -1; dy <= height_depth; dy++) {
                for (int dz = -1; dz <= depth; dz++) {

                    // Calculate tunnel based on player facing direction
                    BlockPos target = switch (dir) {
                        case NORTH -> origin.offset(dx, dy, -dz);
                        case SOUTH -> origin.offset(dx, dy,  dz);
                        case EAST  -> origin.offset( dz, dy,  dx);
                        case WEST  -> origin.offset(-dz, dy,  dx);
                        default    -> origin;
                    };
                    Block targetBlock = world.getBlockState(target).getBlock();
                    Block belowTorchBlock = world.getBlockState(new BlockPos(dx,dy-1,dz)).getBlock();
                    String name = targetBlock.getDescriptionId().toLowerCase();
                    if (!name.contains("ore") && !BLOCK_BLACKLIST.contains(targetBlock)) {
                        tunnel.add(target.immutable());
                    }

                    if (dy == -1 && dx == 0 && (dz % 2) > 0 && belowTorchBlock != Blocks.AIR && belowTorchBlock != Blocks.COBWEB) {
                        torchList.add(target.immutable());
                    }

                    // Calculate outside tunnel to replace blocks with glass (if necessary)
                    for (int ex = dx - 1; ex <= dx + 1; ex++) {
                        for (int ey = dy - 1; ey <= dy + 1; ey++) {
                            for (int ez = dz - 1; ez <= dz + 1; ez++) {

                                boolean inside = ex >= -side_depth && ex <= side_depth && ey >= -1  && ey <= height_depth && ez >= -1 && ez <= depth;

                                if (inside) continue;

                                BlockPos out = switch (dir) {
                                    case NORTH -> origin.offset(ex, ey, -ez);
                                    case SOUTH -> origin.offset(ex, ey,  ez);
                                    case EAST  -> origin.offset( ez, ey,  ex);
                                    case WEST  -> origin.offset(-ez, ey,  ex);
                                    default    -> origin;
                                };
                                BlockState st = world.getBlockState(out);
                                if (st.getBlock() == Blocks.WATER || st.getBlock() == Blocks.LAVA) {
                                    glassList.add(out.immutable());
                                }
                            }
                        }
                    }
                }
            }
        }

        // Remove torch positions from tunnnel list
        tunnel.removeAll(torchList);

        // Schedule batches to be processed
        processBatch(world, server, tunnel,    0, air,   invFlag, notifyFlag);
        processBatch(world, server, glassList, 0, glass, invFlag, notifyFlag);
        processBatch(world, server, torchList, 0, torch, invFlag, notifyFlag);

        return InteractionResult.SUCCESS;
    }

    private void processBatch(ServerLevel world, MinecraftServer server, List<BlockPos> positions, int index, BlockState newState, int invFlag, int notifyFlag) {
        int end = Math.min(index + BATCH_SIZE, positions.size());
        for (int i = index; i < end; i++) {
            BlockPos pos = positions.get(i);
            BlockState old = world.getBlockState(pos);
            world.setBlock(pos, newState, invFlag);
            world.sendBlockUpdated(pos, old, newState, notifyFlag);
        }
        if (end < positions.size()) {
            server.execute(() ->
                    processBatch(world, server, positions, end, newState, invFlag, notifyFlag)
            );
        }
    }
}
