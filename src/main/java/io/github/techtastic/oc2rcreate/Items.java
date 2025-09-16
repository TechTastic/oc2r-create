package io.github.techtastic.oc2rcreate;

import com.simibubi.create.content.redstone.link.RedstoneLinkNetworkHandler;
import com.simibubi.create.foundation.utility.Couple;
import li.cil.oc2.common.item.ModItem;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Items {
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OC2RCreate.MODID);

    public static final RegistryObject<Item> REDSTONE_LINK_CARD =
            ITEMS.register("redstone_link_card", () -> new ModItem(new Item.Properties().stacksTo(1)) {
                @Override
                public void m_7373_(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                    Couple<RedstoneLinkNetworkHandler.Frequency> frequency =
                            Couple.create(RedstoneLinkNetworkHandler.Frequency.EMPTY, RedstoneLinkNetworkHandler.Frequency.EMPTY);
                    int strength = 0;
                    if (stack.hasTag()) {
                        CompoundTag nbt = stack.getOrCreateTag();
                        if (nbt.contains("oc2rcreate")) {
                            CompoundTag tag = nbt.getCompound("oc2rcreate");
                            frequency = Couple.deserializeEach(tag.getList("frequency", ListTag.TAG_COMPOUND), comp ->
                                    RedstoneLinkNetworkHandler.Frequency.of(ItemStack.of(comp)));
                            strength = tag.getInt("strength");
                        }
                    }

                    tooltip.add(Component.translatable("create.logistics.firstFrequency")
                            .append(": ").withStyle(ChatFormatting.GOLD));
                    tooltip.add(Component.translatable(frequency.get(true).getStack().getDescriptionId())
                            .withStyle(ChatFormatting.RED));
                    tooltip.add(Component.translatable("create.logistics.secondFrequency")
                            .append(":").withStyle(ChatFormatting.GOLD));
                    tooltip.add(Component.translatable(frequency.get(false).getStack().getDescriptionId())
                            .withStyle(ChatFormatting.BLUE));
                    tooltip.add(Component.translatable("oc2rcreate.redstone_link.strength", strength)
                            .withStyle(ChatFormatting.DARK_RED));
                    tooltip.add(Component.empty());

                    super.m_7373_(stack, level, tooltip, flag);
                }
            });

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
