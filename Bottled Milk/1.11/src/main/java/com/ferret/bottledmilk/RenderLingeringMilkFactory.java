package com.ferret.bottledmilk;

import com.ferret.bottledmilk.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 * Created by Ferret on 6/3/2017.
 */
public class RenderLingeringMilkFactory implements IRenderFactory<EntityThrowable>
{
    public static final RenderLingeringMilkFactory INSTANCE = new RenderLingeringMilkFactory();

    @Override
    public Render<? super EntityThrowable> createRenderFor(RenderManager manager)
    {

        return new RenderSnowball<EntityThrowable>(manager, ModItems.lingeringMilk, Minecraft.getMinecraft().getRenderItem());
    }
}