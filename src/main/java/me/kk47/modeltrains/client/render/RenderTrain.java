package me.kk47.modeltrains.client.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import me.kk47.modeltrains.api.IItemTrain;
import me.kk47.modeltrains.api.IItemTrainLoadable;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import me.kk47.modeltrains.train.RollingStock;
import me.kk47.ueri.UERIRenderable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderTrain extends TileEntitySpecialRenderer<TileEntityTrainController>{

	@Override
	public void render(TileEntityTrainController te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		//te     x  y  z   model       texture         scale  xMod  yMod  zMod  rotation
		//te     x  y  z   te#getmodel te#gettexture    0.25   te#getposition
		RollingStock[] trains = te.getTrains();
		for(int i = 0; i < trains.length; i++){
			if(trains[i].getTrainItem() != null){
				IItemTrain it = trains[i].getTrainItem();

				List<UERIRenderable> rendering = new ArrayList<UERIRenderable>();
				
				//List<UERIRenderable> trainModels = it.getRenderables(te.getStackInSlot(i));
				rendering.addAll(it.getRenderables(te.getStackInSlot(i)));
				
				if(it instanceof IItemTrainLoadable) {
					IItemTrainLoadable itl = (IItemTrainLoadable) it;
					List<UERIRenderable> loadedOverrides = itl.getModelOverrides(trains[i].getLoadedResource().getName());
					if(loadedOverrides != null) {
						rendering.addAll(loadedOverrides);
					}
				}
				
				for(UERIRenderable renderable : rendering) {
					renderUERI(te, x, y, z, renderable, 0.25F, trains[i].getPos().getX(), 0.0F, trains[i].getPos().getY(), trains[i].getPos().getYaw());
				}
			}
		}
	}

	@Deprecated
	public void renderModel(TileEntity te, double x, double y, double z, ModelBase model, ResourceLocation texture, float scale, float modifierX, float modifierY, float modifierZ, float rotation){
		//The PushMatrix tells the renderer to "start" doing something.
		GL11.glPushMatrix();
		//This is setting the initial location.
		//		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glTranslatef((float) x + 0.1F - TileEntityTrainController.BLOCK_OFFSET, (float) y+0.4F, (float) z + 0.125F - TileEntityTrainController.BLOCK_OFFSET);

		//Applies Scaling
		GL11.glScalef(scale, scale, scale);
		//Applies new translation
		GL11.glTranslatef(modifierX+3.6F, modifierY, modifierZ-0.5F);
		//		GL11.glTranslatef(modifierX+4F, modifierY, modifierZ-0.5F);

		GL11.glRotated(rotation, 0.0, 1.0, 0.0);
		//This disables lighting on the model so that it doesn't factor being in a block when rendered
		//		GL11.glDisable(GL11.GL_LIGHTING);
		GlStateManager.disableLighting();

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

	public void renderUERI(TileEntity te, double x, double y, double z, UERIRenderable renderable, float scale, float modifierX, float modifierY, float modifierZ, float rotation) {
		//		System.out.println("RenderUERI method called");
		//The PushMatrix tells the renderer to "start" doing something.
		GlStateManager.pushMatrix();
		//This is setting the initial location.
		GlStateManager.translate((float) x + 0.1F - TileEntityTrainController.BLOCK_OFFSET, (float) y+0.4F, (float) z + 0.125F - TileEntityTrainController.BLOCK_OFFSET);
		//Applies Scaling
		GlStateManager.scale(scale, scale, scale);
		//Applies new translation
		GlStateManager.translate(modifierX+3.6F, modifierY, modifierZ-0.5F);

		GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
		//This disables lighting on the model so that it doesn't factor being in a block when rendered
		GlStateManager.disableLighting();

		renderable.render();

		GlStateManager.popMatrix();
	}
}
