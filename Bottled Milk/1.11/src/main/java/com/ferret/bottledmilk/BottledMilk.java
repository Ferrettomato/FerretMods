package com.ferret.bottledmilk;

import com.ferret.bottledmilk.config.ModConfiguration;
import com.ferret.bottledmilk.entity.projectile.EntityLingeringMilk;
import com.ferret.bottledmilk.entity.projectile.EntitySplashMilk;
import com.ferret.bottledmilk.item.ModItems;
import com.ferret.bottledmilk.potion.PotionMilk;
import com.ferret.bottledmilk.proxies.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = BottledMilk.MODID, version = BottledMilk.VERSION)
public class BottledMilk
{
    public static final String MODID = "bottledmilk";
    public static final String VERSION = "1.0";

    @Mod.Instance(MODID)
    public static BottledMilk instance;

    @SidedProxy(clientSide="com.ferret.bottledmilk.proxies.ClientProxy", serverSide="com.ferret.bottledmilk.proxies.ServerProxy")
    public static CommonProxy proxy;

    public ModConfiguration configuration;

    public ModEventHandler eventHandler = new ModEventHandler();
    public TANEventHandler tanHandler = new TANEventHandler();

    public static Potion milkPotion;
    public static PotionType milkPotionType;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);

        configuration = new ModConfiguration();
        configuration.preInit(event);

        MinecraftForge.EVENT_BUS.register(eventHandler);

        ModItems.init();
        EntityRegistry.registerModEntity(new ResourceLocation(this.MODID, "splashmilk"), EntitySplashMilk.class, "splashmilk", 1, this, 80, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(this.MODID, "lingeringmilk"), EntityLingeringMilk.class, "lingeringmilk", 2, this, 80, 3, true);
    }

    @Optional.Method(modid = "toughasnails")
    @EventHandler
    public void preInitTAN(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(tanHandler);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);

        milkPotion = new PotionMilk().setIconIndex(0, 0).setPotionName("milk");
        milkPotionType = new PotionType(new PotionEffect(milkPotion));
//        PotionType.REGISTRY.register(32, new ResourceLocation("milkpotion"), milkPotionType);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);

        if(ModConfiguration.canCraftDrinkable)
        {
            switch(ModConfiguration.bottlesFromBucket)
            {
                case 8 : GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bottledMilk, 8), Items.MILK_BUCKET, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE);
                case 7 : GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bottledMilk, 7), Items.MILK_BUCKET, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE);
                case 6 : GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bottledMilk, 6), Items.MILK_BUCKET, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE);
                case 5 : GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bottledMilk, 5), Items.MILK_BUCKET, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE);
                case 4 : GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bottledMilk, 4), Items.MILK_BUCKET, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE);
                case 3 : GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bottledMilk, 3), Items.MILK_BUCKET, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE);
                case 2 : GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bottledMilk, 2), Items.MILK_BUCKET, Items.GLASS_BOTTLE, Items.GLASS_BOTTLE);
                case 1 : GameRegistry.addShapelessRecipe(new ItemStack(ModItems.bottledMilk, 1), Items.MILK_BUCKET, Items.GLASS_BOTTLE);
            }
        }
        if(ModConfiguration.canCraftSplash) GameRegistry.addShapelessRecipe(new ItemStack(ModItems.splashMilk, 1), ModItems.bottledMilk, Items.GUNPOWDER);
        if(ModConfiguration.canCraftLingering) GameRegistry.addShapelessRecipe(new ItemStack(ModItems.lingeringMilk, 1), ModItems.bottledMilk, Items.DRAGON_BREATH);

        ItemStack m = new ItemStack(ModItems.bottledMilk);
        ItemStack s = new ItemStack(Items.SUGAR);
        ItemStack e = new ItemStack(Items.SUGAR);
        ItemStack w = new ItemStack(Items.SUGAR);

//        GameRegistry.addRecipe(new ItemStack(Items.CAKE), m, m, m, );
//        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(new ItemStack(ModItems.bottledMilk), new ItemStack(Items.GUNPOWDER), new ItemStack(ModItems.splashMilk)));
    }
}