package com.ferret.bottledmilk.item;

import com.ferret.bottledmilk.BottledMilk;
import com.ferret.bottledmilk.config.ModConfiguration;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Ferret on 6/3/2017.
 */
public class ItemBottledMilk extends Item
{
    public ItemBottledMilk(String name)
    {
        super();

        this.setMaxStackSize(ModConfiguration.drinkableStackSize);
        this.setCreativeTab(CreativeTabs.MISC);

        setRegistryName(BottledMilk.MODID, name);
        GameRegistry.register(this);
        setUnlocalizedName(BottledMilk.MODID + "_" + name);
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if(entityLiving instanceof EntityPlayer && !worldIn.isRemote)
        {
            entityLiving.stopActiveHand();

            if (!((EntityPlayer) entityLiving).capabilities.isCreativeMode)
            {
                stack.shrink(1);

                if (!stack.isEmpty() && !((EntityPlayer)entityLiving).inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE)))
                {
                    ((EntityPlayer)entityLiving).dropItem(new ItemStack(Items.GLASS_BOTTLE), false);
                }
            }

            entityLiving.curePotionEffects(new ItemStack(Items.MILK_BUCKET));

            ((EntityPlayer) entityLiving).addStat(StatList.getObjectUseStats(this));

            return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
        }

        return stack;
    }

    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.setActiveHand(handIn);
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
