package io.github.techtastic.oc2rcreate.manual;

import io.github.techtastic.oc2rcreate.OC2RCreate;
import li.cil.manual.api.ManualModel;
import li.cil.manual.api.prefab.provider.NamespaceDocumentProvider;
import li.cil.manual.api.util.MatchResult;
import li.cil.oc2.client.manual.Manuals;
import org.jetbrains.annotations.NotNull;

public class OC2RCreateDocumentProvider extends NamespaceDocumentProvider {
    public OC2RCreateDocumentProvider() {
            super(OC2RCreate.MODID, "doc");
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
