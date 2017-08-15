package me.kk47.modeltrains.client.render;

import org.lwjgl.opengl.GL11;

import me.kk47.modeltrains.tileentity.TileEntity3DPrinter;
import me.kk47.modeltrains.tileentity.TileEntityIndustryForrest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class Render3DPrinter extends TileEntitySpecialRenderer<TileEntity3DPrinter> {

	public Render3DPrinter() {
		
	}

	@Override
	public void render(TileEntity3DPrinter te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		te.updateModelAnimation(); //Don't forget to call this!
		renderModel(te, x, y, z, te.getModel(), te.getTexture());
	}
	
	public void renderModel(TileEntity te, double x, double y, double z, ModelBase model, ResourceLocation texture){
		//The PushMatrix tells the renderer to "start" doing something.
		GL11.glPushMatrix();
		//This is setting the initial location.
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.51F, (float) z + 0.5F);

		//This disables lighting on the model so that it doesn't factor being in a block when rendered
		//			GL11.glDisable(GL11.GL_LIGHTING);

		//the ':' is very important
		//binding the textures
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		//This rotation part is very important! Without it, your model will render upside-down! And for some reason you DO need PushMatrix again!                       
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		//A reference to your Model file. Again, very important.
		model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		//Tell it to stop rendering for both the PushMatrix's
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
