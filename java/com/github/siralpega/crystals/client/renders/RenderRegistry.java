package com.github.siralpega.firstmod.client.renders;

import com.github.siralpega.firstmod.entities.FirstEntity;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)   //only runs in client side
public class RenderRegistry 
{
	public static void registryEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(FirstEntity.class, new FirstEntityRender.RenderFactory());
	}
}
