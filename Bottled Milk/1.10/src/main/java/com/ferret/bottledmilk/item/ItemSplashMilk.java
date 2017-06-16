package com.ferret.bottledmilk.item;

import com.ferret.bottledmilk.BottledMilk;
import com.ferret.bottledmilk.config.ModConfiguration;
import com.ferret.bottledmilk.entity.projectile.EntitySplashMilk;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Ferret on 6/3/2017.
 */
public class ItemSplashMilk extends Item
{
    public ItemSplashMilk(String name)
    {
        super();

        this.setMaxStackSize(ModConfiguration.splashStackSize);
        this.setCreativeTab(CreativeTabs.MISC);

        setRegistryName(BottledMilk.MODID, name);
        GameRegistry.register(this);
        setUnlocalizedName(BottledMilk.MODID + "_" + name);
    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        ItemStack itemstack1 = playerIn.capabilities.isCreativeMode ? itemstack.copy() : itemstack.splitStack(1);
        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {
            EntitySplashMilk entitypotion = new EntitySplashMilk(worldIn, playerIn);
            entitypotion.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 0.5F, 1.0F);
            worldIn.spawnEntityInWorld(entitypotion);
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult(EnumActionResult.SUCCESS, itemstack);
    }
}