package com.ferret.bottledmilk.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Ferret on 6/3/2017.
 */
public class ModConfiguration
{
    Configuration configuration;

    public static boolean canCraftDrinkable = true;
    public static boolean canCraftSplash = true;
    public static boolean canCraftLingering = true;

    public static int drinkableStackSize = 16;
    public static int splashStackSize = 16;
    public static int lingeringStackSize = 16;

    public static boolean bottleMilking = true;

    public static int bottlesFromBucket = 2;

    public static int[] throwableEffectBlacklist = new int[0];

    public void preInit(FMLPreInitializationEvent event)
    {
        configuration = new Configuration(event.getSuggestedConfigurationFile());
        configuration.load();

        configuration.getCategory("1_recipes").setComment("Enable/disable the recipes for certain items.");

        canCraftDrinkable = configuration.get("1_recipes", "Milk Bottle", true).getBoolean();
        canCraftSplash = configuration.get("1_recipes", "Splash Milk Bottle", true).getBoolean();
        canCraftLingering = configuration.get("1_recipes", "Lingering Milk Bottle", true).getBoolean();

        configuration.getCategory("2_stacksizes").setComment("Change the max stack sizes of items.");

        drinkableStackSize = configuration.get("2_stacksizes", "Milk Bottle", 16).getInt();
        splashStackSize = configuration.get("2_stacksizes", "Splash Milk Bottle", 16).getInt();
        lingeringStackSize = configuration.get("2_stacksizes", "Lingering Milk Bottle", 16).getInt();

        configuration.getCategory("3_bottlemilking").setComment("Enable/disable milking cows directly with bottles. Enabled by default.");

        bottleMilking = configuration.get("3_bottlemilking", "Can milk cows with bottles",true).getBoolean();

        configuration.getCategory("4_bottlesperbucket").setComment("Change the maximum milk bottles you can get from a bucket. (min 1, max 8)");

        bottlesFromBucket = configuration.get("4_bottlesperbucket", "Max Bottles", 2, "", 1, 8).getInt();
        bottlesFromBucket = Math.max(Math.min(bottlesFromBucket, 8), 1);

        configuration.getCategory("5_effectblacklist").setComment("Prevent splash and lingering milk bottles (NOT normal milk bottles) from removing certain effects. (mainly for PvP balancing)" +
                "\n1: Speed" +
                "\n2: Slowness" +
                "\n3: Haste" +
                "\n4: Mining Fatigue" +
                "\n5: Strength" +
                "\n6: Instant Health" +
                "\n7: Instant Damage" +
                "\n8: Jump Boost" +
                "\n9: Nausea" +
                "\n10: Regeneration" +
                "\n11: Resistance" +
                "\n12: Fire Resistance" +
                "\n13: Water Breathing" +
                "\n14: Invisibility" +
                "\n15: Blindness" +
                "\n16: Night Vision" +
                "\n17: Hunger" +
                "\n18: Weakness" +
                "\n19: Poison" +
                "\n20: Wither" +
                "\n21: Health Boost" +
                "\n22: Absorption" +
                "\n23: Saturation" +
                "\n24: Glowing" +
                "\n25: Levitation" +
                "\n26: Luck" +
                "\n27: Bad Luck");

        throwableEffectBlacklist = configuration.get("6_effectblacklist", "Potion ID Blacklist", new int[0]).getIntList();

        if(configuration.hasChanged())
        {
            configuration.save();
        }
    }
}