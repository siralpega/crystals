package com.github.siralpega.firstmod.api.inventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;

public interface IDracoContainer extends INamedContainerProvider 
{
	void openGUI(ServerPlayerEntity player);
}

