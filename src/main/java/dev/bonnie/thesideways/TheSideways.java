package dev.bonnie.thesideways;

import com.mojang.logging.LogUtils;
import dev.bonnie.thesideways.block.ModBlocks;
import dev.bonnie.thesideways.item.ModCreativeModeTabs;
import dev.bonnie.thesideways.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheSideways.MOD_ID)
public class TheSideways {
    public static final String MOD_ID = "thesideways";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TheSideways() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == ModCreativeModeTabs.SIDEWAYS_TAB) {
            //  ITEMS
            event.accept(ModItems.PEANUT_ESSENCE);
            event.accept(ModItems.PEANUT_JUICE);

            //  BLOCKS
            event.accept(ModBlocks.SIDEWAYS_DIRT);
            event.accept(ModBlocks.SIDEWAYS_GRASS_BLOCK);
            event.accept(ModBlocks.NUTROOT_LOG);
            event.accept(ModBlocks.STRIPPED_NUTROOT_LOG);
            event.accept(ModBlocks.NUTROOT_WOOD);
            event.accept(ModBlocks.STRIPPED_NUTROOT_WOOD);
            event.accept(ModBlocks.NUTROOT_PLANKS);
            event.accept(ModBlocks.NUTROOT_LEAVES);
            event.accept(ModBlocks.NUTROOT_SAPLING);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
        }
    }
}
