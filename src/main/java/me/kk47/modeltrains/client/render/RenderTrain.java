package me.kk47.modeltrains.client.render;

import org.lwjgl.opengl.GL11;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.api.IItemTrain;
import me.kk47.modeltrains.api.IItemTrainLoadable;
import me.kk47.modeltrains.api.ITileEntityTrainContainer;
import me.kk47.modeltrains.client.model.MTmModelBase;
import me.kk47.modeltrains.client.model.ModelDummyTrain;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import me.kk47.modeltrains.train.RollingStock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderTrain extends TileEntitySpecialRenderer<TileEntityTrainController>{

//	private ModelDummyTrain dummytrain = new ModelDummyTrain();
//	private ResourceLocation dummytexture = new ResourceLocation(Data.MODID + ":textures/trains/DummyTrain.png");

	@Override
	public void render(TileEntityTrainController te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		//		System.out.println("render train");
			//te     x  y  z   model       texture         scale  xMod  yMod  zMod  rotation
			//te     x  y  z   te#getmodel te#gettexture    0.25   te#getposition
			//			System.out.println("render train");
			for(RollingStock r : te.getTrains()){
				if(r.getTrainItem() != null){
					IItemTrain it = r.getTrainItem();
					ModelBase model = null;
					
					if(it instanceof IItemTrainLoadable){
//						System.out.println("Rendering loadable train model");
						model = ((IItemTrainLoadable) it).getModel(r.getLoadedResource().getName());
					}else{
						model = it.getModel();
					}
					
/*					boolean b = model instanceof ModelLoggingCarLogs;
					System.out.println("Is actually right model " + b);
					
					boolean c = model instanceof ModelLoggingCarEmpty;
					if(c)
						System.out.println("Rendering empty logging car model");*/
					
					float rotation = 0.0F;
					if(model instanceof MTmModelBase){
						MTmModelBase mtmmodel = (MTmModelBase) model;
						rotation = mtmmodel.getRotation();
					}
					
					renderModel(te, x, y, z, model, it.getTexture(), 
							0.25F, r.getPos().getX(), 0.0F, r.getPos().getY(), r.getPos().getYaw() + rotation);
				}

			//Test render code
			/*			this.renderModel(traincontroller, x, y, z, dummytrain, dummytexture, 
					0.25F, traincontroller.getTrain().getPos().getX(), 0.0F, 
					traincontroller.getTrain().getPos().getY(), traincontroller.getTrain().getPos().getYaw());
			this.renderModel(traincontroller, x, y, z, dummytrain, dummytexture, 
					0.25F, traincontroller.getTrain2().getPos().getX(), 0.0F, 
					traincontroller.getTrain2().getPos().getY(), traincontroller.getTrain2().getPos().getYaw());*/
		}
	}

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
}
