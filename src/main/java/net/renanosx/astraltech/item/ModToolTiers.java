package net.renanosx.astraltech.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.renanosx.astraltech.util.ModTags;

public class ModToolTiers {
    public static final Tier ULTIMATE = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_ULTIMATE_TOOL,
            2500, 10.0F, 0.0F, 28, () -> Ingredient.of(ModItems.BISMUTH));

}
