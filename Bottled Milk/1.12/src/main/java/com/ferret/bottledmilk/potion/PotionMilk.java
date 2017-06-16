package com.ferret.bottledmilk.potion;

import com.ferret.bottledmilk.config.ModConfiguration;
import com.ferret.bottledmilk.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import javax.annotation.Nullable;

/**
 * Created by Ferret on 6/3/2017.
 */
public class PotionMilk extends Potion
{
    public PotionMilk()
    {
        super(false, 16777215);
    }

    @Override
    public boolean isInstant()
    {
        return true;
    }

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entitylivingbase, int amplifier, double health)
    {
        for(PotionEffect effect : entitylivingbase.getActivePotionEffects())
        {
            boolean isBlacklisted = false;

            for(int id : ModConfiguration.throwableEffectBlacklist)
            {
                if(Potion.getIdFromPotion(effect.getPotion()) == id || !effect.isCurativeItem(new ItemStack(Items.MILK_BUCKET)))
                {
                    isBlacklisted = true;
                    break;
                }
            }

            if(!isBlacklisted) effect.addCurativeItem(new ItemStack(ModItems.lingeringMilk));
        }

        entitylivingbase.curePotionEffects(new ItemStack(ModItems.lingeringMilk));
    }

    @Override
    public Potion setIconIndex(int p_76399_1_, int p_76399_2_)
    {
        return super.setIconIndex(p_76399_1_, p_76399_2_);
    }
}
