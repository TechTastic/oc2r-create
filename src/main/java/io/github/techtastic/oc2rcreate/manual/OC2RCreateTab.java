package io.github.techtastic.oc2rcreate.manual;

import com.simibubi.create.AllItems;
import li.cil.manual.api.ManualModel;
import li.cil.manual.api.prefab.tab.AbstractTab;
import li.cil.manual.api.util.MatchResult;
import li.cil.oc2.client.manual.Manuals;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class OC2RCreateTab extends AbstractTab {
    public OC2RCreateTab() {
        super(ManualModel.LANGUAGE_KEY + "/create.md", Component.translatable("oc2rcreate.manual.tab"));
    }

    @Override
    public void renderIcon(@NotNull GuiGraphics guiGraphics) {
        guiGraphics.renderItem(AllItems.WRENCH.asStack(), 0, 0);
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