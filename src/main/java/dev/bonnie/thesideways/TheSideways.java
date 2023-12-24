package dev.bonnie.thesideways;

import com.google.common.reflect.Reflection;
import com.mojang.logging.LogUtils;
import dev.bonnie.thesideways.block.ModBlocks;
import dev.bonnie.thesideways.item.ModCreativeModeTabs;
import dev.bonnie.thesideways.item.ModItems;
import dev.bonnie.thesideways.network.SidewaysPacketHandler;
import dev.bonnie.thesideways.world.TSPoiTypes;
import dev.bonnie.thesideways.world.dimension.ModDimensions;
import dev.bonnie.thesideways.world.feature.SidewaysFeatures;
import dev.bonnie.thesideways.world.placementmodifier.TheSidewaysPlacementModifiers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.nio.file.Path;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheSideways.MOD_ID)
public class TheSideways {
    public static final String MOD_ID = "thesideways";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Path DIRECTORY = FMLPaths.CONFIGDIR.get().resolve(TheSideways.MOD_ID);

    public TheSideways() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        DeferredRegister<?>[] registers = {
                SidewaysFeatures.FEATURES,
        };

        for (DeferredRegister<?> register : registers) {
            register.register(modEventBus);
        }

        DIRECTORY.toFile().mkdirs(); // Ensures the mod config folder is generated.
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, TheSidewaysConfig.SERVER_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TheSidewaysConfig.COMMON_SPEC);

        //  ITEMS AND BLOCKS
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        //  DIMENSION
        ModDimensions.register();

        //  PORTAL POI
        TSPoiTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        Reflection.initialize(TheSidewaysPlacementModifiers.class);
        SidewaysPacketHandler.register();
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {

        //  ITEMS
        if (event.getTab() == ModCreativeModeTabs.SIDEWAYS_TAB) {
            event.accept(ModItems.PEANUT_ESSENCE);
            event.accept(ModItems.SIDEWAYS_CATALYST);
            event.accept(ModItems.RAW_LUMINITE);
            event.accept(ModItems.LUMINITE_SHARD);
            event.accept(ModItems.MALACHITE_CRYSTAL);
        }

        //  FOODSTUFFS
        if (event.getTab() == ModCreativeModeTabs.SIDEWAYS_FOODSTUFFS) {
            event.accept(ModItems.PEANUT);
            event.accept(ModItems.PEANUT_JUICE);
        }

        //  BLOCKS
        if(event.getTab() == ModCreativeModeTabs.SIDEWAYS_BLOCKS) {
            event.accept(ModBlocks.SIDEWAYS_GRASS_BLOCK);
            event.accept(ModBlocks.SIDEWAYS_DIRT);
            event.accept(ModBlocks.SIDEWAYS_STONE);
            event.accept(ModBlocks.SIDEWAYS_COBBLESTONE);
            event.accept(ModBlocks.NUTROOT_LOG);
            event.accept(ModBlocks.STRIPPED_NUTROOT_LOG);
            //event.accept(ModBlocks.NUTROOT_WOOD);             //  secret wood type
            //event.accept(ModBlocks.STRIPPED_NUTROOT_WOOD);    //  secret wood type
            event.accept(ModBlocks.NUTROOT_PLANKS);
            event.accept(ModBlocks.NUTROOT_LEAVES);
            event.accept(ModBlocks.NUTROOT_SAPLING);
            event.accept(ModBlocks.PEANUT_BLOCK);
            event.accept(ModBlocks.LUMINITE_ORE);
            event.accept(ModBlocks.MALACHITE_ORE);
            event.accept(ModBlocks.MALACHITE_BLOCK);
            //event.accept(ModBlocks.SIDEWAYS_PORTAL);  //  Crashes game lmao
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
