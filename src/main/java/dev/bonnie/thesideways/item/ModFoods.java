package dev.bonnie.thesideways.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties PEANUT_JUICE = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.1F).build();
    public static final FoodProperties PEANUT = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).build();
}
