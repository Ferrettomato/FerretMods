package com.ferret.bottledmilk;

import com.ferret.bottledmilk.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import toughasnails.api.thirst.ThirstHelper;
import toughasnails.thirst.ThirstHandler;

/**
 * Created by Ferret on 6/4/2017.
 */
public class TANEventHandler
{
    @SubscribeEvent
    public void onItemUseFinish(LivingEntityUseItemEvent.Finish event)
    {
        if(event.getEntityLiving() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            ItemStack stack = player.getHeldItem(player.getActiveHand());
            ThirstHandler thirstHandler = (ThirstHandler) ThirstHelper.getThirstData(player);

            if (thirstHandler.isThirsty())
            {
                if (stack.getItem().equals(ModItems.bottledMilk))
                {
                    thirstHandler.addStats(6, 0.7F);
                }
            }
        }
    }
}