package io.github.techtastic.oc2rcreate;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import io.github.techtastic.oc2rcreate.device.block.display_link.OC2RBehavior;

import java.util.function.Function;

public class OC2RProxy {
    private static Function<SmartBlockEntity, ? extends OC2RBehavior> computerFactory = OC2RBehavior::new;

    public static OC2RBehavior behaviour(SmartBlockEntity sbe) {
        return computerFactory.apply(sbe);
    }
}
