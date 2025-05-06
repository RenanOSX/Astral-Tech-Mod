package net.renanosx.astraltech.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AxeItem;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.renanosx.astraltech.AstralTechMod;
import net.renanosx.astraltech.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AstralTechMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BISMUTH.get());
        basicItem(ModItems.RAW_BISMUTH.get());
        basicItem(ModItems.RADISH.get());
        basicItem(ModItems.STARLIGHT_ASHES.get());
        basicItem(ModItems.FROSTFIRE_ICE.get());
        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.MINERS_DREAM.get());

        handheldItem(ModItems.ULTIMATE_AXE);
        handheldItem(ModItems.ULTIMATE_SHOVEL);
        handheldItem(ModItems.ULTIMATE_HOE);
        handheldItem(ModItems.ULTIMATE_SWORD);
        handheldItem(ModItems.ULTIMATE_PICKAXE);

    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AstralTechMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}
