package com.github.siralpega.firstmod.entities;

import com.github.siralpega.firstmod.lists.Entities;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.world.World;

public class FirstEntity extends CreatureEntity
{

	@SuppressWarnings("unchecked")
	public FirstEntity(EntityType<? extends CreatureEntity> type, World worldIn) 
	{
		super((EntityType<? extends CreatureEntity>) Entities.FIRST_ENTITY, worldIn);
		
	}
	
	@Override
	protected void registerGoals() 
	{
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(0, new RandomWalkingGoal(this, 1.2d));
		this.goalSelector.addGoal(0, new LookRandomlyGoal(this));
	//	this.goalSelector.addGoal(0,  );
	//	super.registerGoals();
	}

	@Override
	protected void registerAttributes() 
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0d);
		//this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2d);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1d);	
	}
//	@Override
//	protected boolean canBeRidden(Entity entityIn)
//	{
	
//		return true;
		//return super.canBeRidden(entityIn);
//	}
}
