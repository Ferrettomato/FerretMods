package com.ferret.bottledmilk.item;

import com.ferret.bottledmilk.BottledMilk;
import com.ferret.bottledmilk.config.ModConfiguration;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
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

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if(entityLiving instanceof EntityPlayer && !worldIn.isRemote)
        {
            if (!((EntityPlayer) entityLiving).capabilities.isCreativeMode)
            {
                --stack.stackSize;

                if (stack.stackSize > 0 && !((EntityPlayer)entityLiving).inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE)))
                {
                    ((EntityPlayer)entityLiving).dropItem(new ItemStack(Items.GLASS_BOTTLE), false);
                }
            }

            if (!worldIn.isRemote)
            {
                entityLiving.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
            }

            ((EntityPlayer) entityLiving).addStat(StatList.getObjectUseStats(this));

            return stack.stackSize <= 0 ? new ItemStack(Items.GLASS_BOTTLE) : stack;
        }

        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        playerIn.setActiveHand(hand);
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));
    }
}
