package io.github.techtastic.oc2rcreate;

import io.github.techtastic.oc2rcreate.manual.OC2RCreateDocumentProvider;
import io.github.techtastic.oc2rcreate.manual.OC2RCreatePathProvider;
import io.github.techtastic.oc2rcreate.manual.OC2RCreateTab;
import li.cil.manual.api.Tab;
import li.cil.manual.api.provider.DocumentProvider;
import li.cil.manual.api.provider.PathProvider;
import li.cil.manual.api.util.Constants;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class Manuals {
    private static final DeferredRegister<Tab> TABS = DeferredRegister.create(Constants.TAB_REGISTRY, OC2RCreate.MODID);
    private static final DeferredRegister<PathProvider> PATHS = DeferredRegister.create(Constants.PATH_PROVIDER_REGISTRY, OC2RCreate.MODID);
    private static final DeferredRegister<DocumentProvider> DOCUMENTS = DeferredRegister.create(Constants.DOCUMENT_PROVIDER_REGISTRY, OC2RCreate.MODID);

    public static final RegistryObject<Tab> TAB = TABS.register("oc2rcreate", OC2RCreateTab::new);
    public static final RegistryObject<PathProvider> PATH_PROVIDER = PATHS.register("path_provider", OC2RCreatePathProvider::new);
    public static final RegistryObject<DocumentProvider> CONTENT_PROVIDER = DOCUMENTS.register("content_provider", OC2RCreateDocumentProvider::new);

    public static void register(IEventBus bus) {
        TABS.register(bus);
        PATHS.register(bus);
        DOCUMENTS.register(bus);
    }
}
