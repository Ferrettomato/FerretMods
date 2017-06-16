package com.ferret.bottledmilk.proxies;

import com.ferret.bottledmilk.RenderLingeringMilkFactory;
import com.ferret.bottledmilk.RenderSplashMilkFactory;
import com.ferret.bottledmilk.entity.projectile.EntityLingeringMilk;
import com.ferret.bottledmilk.entity.projectile.EntitySplashMilk;
import com.ferret.bottledmilk.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Ferret on 4/12/2017.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent e)
    {
        super.preInit(e);

        RenderingRegistry.registerEntityRenderingHandler(EntityLingeringMilk.class, new RenderLingeringMilkFactory());
        RenderingRegistry.registerEntityRenderingHandler(EntitySplashMilk.class, new RenderSplashMilkFactory());
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        super.init(e);

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ModItems.bottledMilk, 0, new ModelResourceLocation("bottledmilk:milk_bottle_drinkable", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ModItems.splashMilk, 0, new ModelResourceLocation("bottledmilk:milk_bottle_splash", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ModItems.lingeringMilk, 0, new ModelResourceLocation("bottledmilk:milk_bottle_lingering", "inventory"));
    }

    @Override
    public void postInit(FMLPostInitializationEvent e)
    {
        super.postInit(e);
    }
}