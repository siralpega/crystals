package com.github.siralpega.firstmod.client.models;

import com.github.siralpega.firstmod.entities.FirstEntity;

import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT) //only runs in clients
public class FirstEntityModel extends CowModel<FirstEntity> //extends EntityModel<FirstEntity> look at 1.13 tutorial on entities to see how to model custom
{

}
