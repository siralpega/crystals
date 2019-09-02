package com.github.siralpega.firstmod.blocks;

import com.github.siralpega.firstmod.FirstModMain.RegistryEvents;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
/***
 * a screen exists only on the client side. (we register it in clientRegistires in main method). 
 * does all the mechanics for communicating between minecraft and the mod
 * 
 * @author siralpega
 *
 */
public class BloodInfuserScreen extends ContainerScreen<BloodInfuserContainer>
{

	
	public BloodInfuserScreen(BloodInfuserContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) 
	{ //registered from clientRegistries
		super(screenContainer, inv, titleIn);
	}

	@Override
    public void render(int mouseX, int mouseY, float partialTicks)
	{
        this.renderBackground(); //makes background out of focus effect
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY); //add custom tool tips here
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
    { //GUI text
       this.font.drawString("Blood Infuser",  9.5F,  6.0F,  4210752);
       this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 9.5F, (float)(this.ySize - 107), 4210752);
      // this.font.drawString("Blood: " + container.getBlood(), 10, 20, 0xffffff);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
    { //GUI image
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(RegistryEvents.location("textures/gui/blood_infuser_gui.png"));
        int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.blit(x, y, 0, 0, xSize, ySize);
        
    }

}
