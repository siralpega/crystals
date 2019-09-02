package com.github.siralpega.firstmod.client.renders;

import com.github.siralpega.firstmod.FirstModMain.RegistryEvents;
import com.github.siralpega.firstmod.client.models.FirstEntityModel;
import com.github.siralpega.firstmod.entities.FirstEntity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT) //only runs in clients
public class FirstEntityRender extends LivingRenderer<FirstEntity, FirstEntityModel> 
{

	public FirstEntityRender(EntityRendererManager manager) 
	{
		super(manager, new FirstEntityModel(), 0f);
	}

	@Override
	protected ResourceLocation getEntityTexture(FirstEntity entity)
	{
		return RegistryEvents.location("textures/entity/first_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<FirstEntity>
	{

		@Override
		public EntityRenderer<? super FirstEntity> createRenderFor(EntityRendererManager manager)
		{
			return new FirstEntityRender(manager);
		}
		
	}
	
}
