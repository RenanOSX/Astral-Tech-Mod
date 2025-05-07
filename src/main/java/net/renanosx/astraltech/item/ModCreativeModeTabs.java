package net.renanosx.astraltech.item;

import net.minecraft.world.level.ItemLike;
import net.renanosx.astraltech.AstralTechMod;
import net.renanosx.astraltech.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;

public class ModCreativeModeTabs {

    public static Set<ItemLike> ModItemList = Set.of(

            // ModItems
            ModItems.BISMUTH,
            ModItems.RAW_BISMUTH,
            ModItems.CHISEL,
            ModItems.RADISH,
            ModItems.FROSTFIRE_ICE,
            ModItems.STARLIGHT_ASHES,
            ModItems.MINERS_DREAM,
            ModItems.ULTIMATE_SWORD,
            ModItems.ULTIMATE_AXE,
            ModItems.ULTIMATE_HOE,
            ModItems.ULTIMATE_SHOVEL,
            ModItems.ULTIMATE_PICKAXE,
            ModItems.ULTIMATE_HELMET,
            ModItems.ULTIMATE_CHESTPLATE,
            ModItems.ULTIMATE_LEGGINGS,
            ModItems.ULTIMATE_BOOTS,

            // ModBlocks
            ModBlocks.DUPE_TREE,
            ModBlocks.DUPE_LEAVES,
            ModBlocks.BISMUTH_BLOCK,
            ModBlocks.BISMUTH_ORE,
            ModBlocks.MAGIC_BLOCK

    );

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AstralTechMod.MOD_ID);

    public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB = CREATIVE_MODE_TAB.register("astral_tech_mod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MINERS_DREAM.get()))
                    .title(Component.translatable("creativetab.astraltech.astral_tech_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        for (ItemLike i: ModItemList) {
                            output.accept(i);
                        }
                    }).build());


    public static void register(IEventBus eventBus) {

        CREATIVE_MODE_TAB.register(eventBus);
    }
}
