package dev.bonnie.thesideways.item;

import dev.bonnie.thesideways.TheSideways;
import dev.bonnie.thesideways.item.custom.CatalystItem;
import dev.bonnie.thesideways.item.custom.DrinkableFoodItem;
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
            () -> new DrinkableFoodItem(new Item.Properties().food(ModFoods.PEANUT_JUICE).stacksTo(1)));
    public static final RegistryObject<Item> SIDEWAYS_CATALYST = ITEMS.register("sideways_catalyst", CatalystItem::new);
    public static final RegistryObject<Item> RAW_LUMINITE = ITEMS.register("raw_luminite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LUMINITE_SHARD = ITEMS.register("luminite_shard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PEANUT = ITEMS.register("peanut",
            () -> new Item(new Item.Properties().food(ModFoods.PEANUT)));

    //  SOMETHING
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
