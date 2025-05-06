package net.renanosx.astraltech.item;

import net.minecraft.world.item.*;
import net.renanosx.astraltech.AstralTechMod;
import net.renanosx.astraltech.item.custom.ChiselItem;
import net.renanosx.astraltech.item.custom.FuelItem;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.renanosx.astraltech.item.custom.MinersDreamItem;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AstralTechMod.MOD_ID);

    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));

    public static final DeferredItem<Item> RADISH = ITEMS.register("radish",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RADISH)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.astraltech.radish.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });

    public static final DeferredItem<Item> FROSTFIRE_ICE = ITEMS.register("frostfire_ice",
            () -> new FuelItem(new Item.Properties(), 800));

    public static final DeferredItem<Item> STARLIGHT_ASHES = ITEMS.register("starlight_ashes",
            () -> new Item(new Item.Properties()));

    // Atk Dmg , Atk Speed
    public static final DeferredItem<SwordItem> ULTIMATE_SWORD = ITEMS.register("ultimate_sword", () -> new SwordItem(ModToolTiers.ULTIMATE, new Item.Properties()
            .attributes(SwordItem.createAttributes(ModToolTiers.ULTIMATE,40.0F, 1f))));

    public static final DeferredItem<AxeItem> ULTIMATE_AXE = ITEMS.register("ultimate_axe", () -> new AxeItem(ModToolTiers.ULTIMATE, new Item.Properties()
            .attributes(AxeItem.createAttributes(ModToolTiers.ULTIMATE,50.0F, 1f))));

    public static final DeferredItem<ShovelItem> ULTIMATE_SHOVEL = ITEMS.register("ultimate_shovel", () -> new ShovelItem(ModToolTiers.ULTIMATE, new Item.Properties()
            .attributes(ShovelItem.createAttributes(ModToolTiers.ULTIMATE,10.0F, 1f))));

    public static final DeferredItem<HoeItem> ULTIMATE_HOE = ITEMS.register("ultimate_hoe", () -> new HoeItem(ModToolTiers.ULTIMATE, new Item.Properties()
            .attributes(HoeItem.createAttributes(ModToolTiers.ULTIMATE,20.0F, 2f))));

    public static final DeferredItem<PickaxeItem> ULTIMATE_PICKAXE = ITEMS.register("ultimate_pickaxe", () -> new PickaxeItem(ModToolTiers.ULTIMATE, new Item.Properties()
            .attributes(HoeItem.createAttributes(ModToolTiers.ULTIMATE,25.0F, 2f))));

    public static final DeferredItem<MinersDreamItem> MINERS_DREAM = ITEMS.register("miners_dream",
            () -> new MinersDreamItem(new MinersDreamItem.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
