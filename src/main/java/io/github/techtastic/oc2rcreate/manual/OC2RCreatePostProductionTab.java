package io.github.techtastic.oc2rcreate.manual;

import li.cil.manual.api.ManualModel;
import li.cil.manual.api.prefab.tab.AbstractTab;
import li.cil.manual.api.util.MatchResult;
import li.cil.oc2.client.manual.Manuals;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class OC2RCreatePostProductionTab extends AbstractTab {
    public OC2RCreatePostProductionTab() {
        super(ManualModel.LANGUAGE_KEY + "/post_production.md", Component.translatable("postproduction_backported.manual.tab"));
    }

    @Override
    public void renderIcon(@NotNull GuiGraphics guiGraphics) {
        guiGraphics.renderItem(new ItemStack(ForgeRegistries.ITEMS.getDelegateOrThrow(ResourceLocation.tryParse("create:cardboard_package_12x12")).get()), 0, 0);
    }

    @Override
    public @NotNull MatchResult matches(@NotNull ManualModel manual) {
        return manual == Manuals.MANUAL.get() ? MatchResult.MATCH : MatchResult.MISMATCH;
    }

    @Override
    public int sortOrder() {
        return Integer.MAX_VALUE;
    }
}