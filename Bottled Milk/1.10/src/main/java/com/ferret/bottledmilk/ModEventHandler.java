package com.ferret.bottledmilk;

import com.ferret.bottledmilk.config.ModConfiguration;
import com.ferret.bottledmilk.item.ModItems;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Ferret on 6/6/2017.
 */
public class ModEventHandler
{
    @SubscribeEvent
    public void bottleMilk(PlayerInteractEvent.EntityInteract event)
    {
        if(event.getTarget() instanceof EntityCow && event.getEntityPlayer().getHeldItem(event.getHand()) != null && event.getEntityPlayer().getHeldItem(event.getHand()).getItem().equals(Items.GLASS_BOTTLE) && ModConfiguration.bottleMilking)
        {
            event.getEntityPlayer().playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
            --event.getItemStack().stackSize;

            if (event.getItemStack().stackSize <= 0)
            {
                event.getEntityPlayer().setHeldItem(event.getHand(), new ItemStack(ModItems.bottledMilk));
            }
            else if (!event.getEntityPlayer().inventory.addItemStackToInventory(new ItemStack(ModItems.bottledMilk)))
            {
                event.getEntityPlayer().dropItem(new ItemStack(ModItems.bottledMilk), false);
            }
        }
    }
}