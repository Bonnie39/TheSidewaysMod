package dev.bonnie.thesideways;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = TheSideways.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TheSidewaysEventSubscriber {
    private static final int TRANSFORM_CHANCE = 20; // Adjust this probability (out of 100)

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            //  i cant figure it out
        }
    }
}



