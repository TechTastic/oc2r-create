package io.github.techtastic.oc2rcreate.manual;

import io.github.techtastic.oc2rcreate.OC2RCreate;
import li.cil.manual.api.ManualModel;
import li.cil.manual.api.prefab.provider.NamespacePathProvider;
import li.cil.manual.api.util.MatchResult;
import li.cil.oc2.client.manual.Manuals;
import org.jetbrains.annotations.NotNull;

public class OC2RCreatePathProvider extends NamespacePathProvider {
    public OC2RCreatePathProvider() {
        super(OC2RCreate.MODID, false);
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
