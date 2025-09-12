package io.github.techtastic.oc2rcreate.mixin;

import com.simibubi.create.content.redstone.displayLink.DisplayLinkBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import io.github.techtastic.oc2rcreate.OC2RProxy;
import io.github.techtastic.oc2rcreate.device.block.display_link.OC2RBehavior;
import io.github.techtastic.oc2rcreate.mixinducks.IDisplayLinkComputerBehaviour;
import li.cil.oc2.common.capabilities.Capabilities;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DisplayLinkBlockEntity.class)
public class MixinDisplayLinkBlockEntity {
    @Unique
    OC2RBehavior oc2rcreate$oc2RBehavior = null;

    @Inject(method = "addBehaviours", at = @At("HEAD"), remap = false)
    private void oc2rcreate$addCustomBehavior(List<BlockEntityBehaviour> behaviours, CallbackInfo ci) {
        behaviours.add(oc2rcreate$oc2RBehavior = OC2RProxy.behaviour((SmartBlockEntity) ((Object) this)));
    }

    @Inject(method = "getCapability", at = @At("RETURN"), cancellable = true, remap = false)
    private void oc2rcreate$getCapability(@NotNull Capability<?> cap, @Nullable Direction side, CallbackInfoReturnable<LazyOptional<?>> cir) {
        if (oc2rcreate$oc2RBehavior != null && cap == Capabilities.device())
            cir.setReturnValue(oc2rcreate$oc2RBehavior.getDeviceCapability());
    }

    @Inject(method = "invalidateCaps", at = @At("TAIL"), remap = false)
    private void oc2rcreate$invalidateCaps(CallbackInfo ci) {
        if (oc2rcreate$oc2RBehavior != null)
            oc2rcreate$oc2RBehavior.removeDevice();
    }
}
