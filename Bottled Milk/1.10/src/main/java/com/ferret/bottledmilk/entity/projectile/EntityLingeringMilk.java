package com.ferret.bottledmilk.entity.projectile;

import com.ferret.bottledmilk.BottledMilk;
import com.ferret.bottledmilk.potion.PotionMilk;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Ferret on 6/3/2017.
 */
public class EntityLingeringMilk extends EntityThrowable
{
    public EntityLingeringMilk(World worldIn)
    {
        super(worldIn);
    }

    public EntityLingeringMilk(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (!this.worldObj.isRemote)
        {
            this.makeAreaOfEffectCloud();

            this.worldObj.playEvent(2002, new BlockPos(this), PotionType.getID(PotionTypes.SWIFTNESS));
            this.setDead();
        }
    }

    private void makeAreaOfEffectCloud()
    {
        EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.worldObj, this.posX, this.posY, this.posZ);
        entityareaeffectcloud.setOwner(this.getThrower());
        entityareaeffectcloud.setRadius(3.0F);
        entityareaeffectcloud.setRadiusOnUse(-0.5F);
        entityareaeffectcloud.setWaitTime(10);
        entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
        entityareaeffectcloud.setPotion(BottledMilk.milkPotionType);

        for (PotionEffect potioneffect : BottledMilk.milkPotionType.getEffects())
        {
            entityareaeffectcloud.addEffect(new PotionEffect(potioneffect));
        }

        entityareaeffectcloud.setColor(BottledMilk.milkPotion.getLiquidColor());

        this.worldObj.spawnEntityInWorld(entityareaeffectcloud);
    }

    protected float getGravityVelocity()
    {
        return 0.05F;
    }
}
