package net.renanosx.astraltech.item;

import net.renanosx.astraltech.AstralTechMod;
import net.renanosx.astraltech.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AstralTechMod.MOD_ID);

    public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB = CREATIVE_MODE_TAB.register("bismuth_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BISMUTH.get()))
                    .title(Component.translatable("creativetab.astraltech.bismuth_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BISMUTH);
                        output.accept(ModItems.RAW_BISMUTH);

                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.RADISH);

                        output.accept(ModItems.FROSTFIRE_ICE);
                        output.accept(ModItems.STARLIGHT_ASHES);
                        output.accept(ModItems.MINERS_DREAM);

                        output.accept(ModItems.ULTIMATE_SWORD);
                        output.accept(ModItems.ULTIMATE_AXE);
                        output.accept(ModItems.ULTIMATE_HOE);
                        output.accept(ModItems.ULTIMATE_SHOVEL);
                        output.accept(ModItems.ULTIMATE_PICKAXE);
                        output.accept(ModBlocks.DUPE_TREE);
                        output.accept(ModBlocks.DUPE_LEAVES);
                        output.accept(ModBlocks.BISMUTH_BLOCK);
                        output.accept(ModBlocks.BISMUTH_ORE);
                        output.accept(ModBlocks.MAGIC_BLOCK);

                    }).build());

//    public static final Supplier<CreativeModeTab> BISMUTH_BLOCK_TAB = CREATIVE_MODE_TAB.register("bismuth_blocks_tab",
//            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BISMUTH_BLOCK))
//                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(AstralTechMod.MOD_ID, "bismuth_items_tab"))
//                    .title(Component.translatable("creativetab.astraltech.bismuth_blocks"))
//                    .displayItems((itemDisplayParameters, output) -> {
//                        output.accept(ModBlocks.BISMUTH_BLOCK);
//                        output.accept(ModBlocks.BISMUTH_ORE);
//                        output.accept(ModBlocks.BISMUTH_DEEPSLATE_ORE);
//
//                        output.accept(ModBlocks.MAGIC_BLOCK);
//
//                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
