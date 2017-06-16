package com.ferret.bottledmilk.entity.projectile;

import com.ferret.bottledmilk.BottledMilk;
import com.ferret.bottledmilk.config.ModConfiguration;
import com.ferret.bottledmilk.item.ModItems;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by Ferret on 6/3/2017.
 */
public class EntitySplashMilk extends EntityThrowable
{
    public EntitySplashMilk(World worldIn)
    {
        super(worldIn);
    }

    public EntitySplashMilk(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (!this.worldObj.isRemote)
        {
            this.applySplash(result);

            this.worldObj.playEvent(2002, new BlockPos(this), PotionType.getID(PotionTypes.SWIFTNESS));
            this.setDead();
        }
    }

    private void applySplash(RayTraceResult p_190543_1_)
    {
        AxisAlignedBB axisalignedbb = this.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D);
        List<EntityLivingBase> list = this.worldObj.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

        if (!list.isEmpty())
        {
            for (EntityLivingBase entitylivingbase : list)
            {
                if (entitylivingbase.canBeHitWithPotion())
                {
                    double d0 = this.getDistanceSqToEntity(entitylivingbase);

                    if (d0 < 16.0D)
                    {
                        double d1 = 1.0D - Math.sqrt(d0) / 4.0D;

                        if (entitylivingbase == p_190543_1_.entityHit)
                        {
                            d1 = 1.0D;
                        }

                        for(PotionEffect effect : entitylivingbase.getActivePotionEffects())
                        {
                            boolean isBlacklisted = false;

                            for(int id : ModConfiguration.throwableEffectBlacklist)
                            {
                                if(Potion.getIdFromPotion(effect.getPotion()) == id || effect.isCurativeItem(new ItemStack(Items.MILK_BUCKET)))
                                {
                                    isBlacklisted = true;
                                    break;
                                }
                            }

                            if(!isBlacklisted) effect.addCurativeItem(new ItemStack(ModItems.splashMilk));
                        }

                        entitylivingbase.curePotionEffects(new ItemStack(ModItems.splashMilk));
                    }
                }
            }
        }
    }

    protected float getGravityVelocity()
    {
        return 0.05F;
    }
}
