package com.ferret.bottledmilk.item;

import net.minecraft.item.Item;

/**
 * Created by Ferret on 6/3/2017.
 */
public class ModItems
{
    public static Item bottledMilk;
    public static Item splashMilk;
    public static Item lingeringMilk;

    public static void init()
    {
        bottledMilk = new ItemBottledMilk("milk_bottle_drinkable");
        splashMilk = new ItemSplashMilk("milk_bottle_splash");
        lingeringMilk = new ItemLingeringMilk("milk_bottle_lingering");
    }
}
