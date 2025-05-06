package net.renanosx.astraltech.datagen;

import net.minecraft.tags.ItemTags;
import net.renanosx.astraltech.AstralTechMod;
import net.renanosx.astraltech.item.ModItems;
import net.renanosx.astraltech.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, AstralTechMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.BISMUTH.get())
                .add(ModItems.RAW_BISMUTH.get())
                .add(Items.COAL)
                .add(Items.STICK)
                .add(Items.COMPASS);

        tag(ItemTags.SWORDS)
                .add(ModItems.ULTIMATE_SWORD.get());

        tag(ItemTags.AXES)
                .add(ModItems.ULTIMATE_AXE.get());

        tag(ItemTags.HOES)
                .add(ModItems.ULTIMATE_HOE.get());

        tag(ItemTags.SHOVELS)
                .add(ModItems.ULTIMATE_SHOVEL.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.ULTIMATE_PICKAXE.get());

    }
}
