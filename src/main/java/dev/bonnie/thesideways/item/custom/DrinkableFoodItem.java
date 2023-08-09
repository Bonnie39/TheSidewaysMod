package dev.bonnie.thesideways.item.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.HoneyBottleItem;

public class DrinkableFoodItem extends HoneyBottleItem {
    public DrinkableFoodItem(Properties properties) {
        super(properties);
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }
}
