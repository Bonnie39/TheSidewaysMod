package dev.bonnie.thesideways.item;

import dev.bonnie.thesideways.TheSideways;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheSideways.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    public static CreativeModeTab SIDEWAYS_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        SIDEWAYS_TAB = event.registerCreativeModeTab(new ResourceLocation(TheSideways.MOD_ID, "sideways_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.PEANUT_ESSENCE.get()))
                        .title(Component.translatable("creativemodetab.sideways_tab")));
    }
}
