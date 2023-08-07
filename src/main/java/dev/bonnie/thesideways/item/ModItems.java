package dev.bonnie.thesideways.item;

import dev.bonnie.thesideways.TheSideways;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    //  ITEM REGISTER
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheSideways.MOD_ID);

    //  ACTUAL ITEMS
    public static final RegistryObject<Item> PEANUT_ESSENCE = ITEMS.register("peanut_essence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PEANUT_JUICE = ITEMS.register("peanut_juice",
            () -> new Item(new Item.Properties()));

    //  SOMETHING
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
