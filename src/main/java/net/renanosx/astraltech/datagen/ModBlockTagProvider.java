package net.renanosx.astraltech.datagen;

import net.minecraft.world.level.block.Block;
import net.renanosx.astraltech.AstralTechMod;
import net.renanosx.astraltech.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.renanosx.astraltech.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AstralTechMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BISMUTH_BLOCK.get())
                .add(ModBlocks.BISMUTH_ORE.get())
                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.DUPE_TREE.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.DUPE_LEAVES.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BISMUTH_DEEPSLATE_ORE.get());

        tag(ModTags.Blocks.NEEDS_ULTIMATE_TOOL)
                .add(ModBlocks.DUPE_TREE.get())
                .add(ModBlocks.DUPE_LEAVES.get())
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);


        tag(ModTags.Blocks.INCORRECT_FOR_ULTIMATE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .remove(ModTags.Blocks.NEEDS_ULTIMATE_TOOL);
    }
}
