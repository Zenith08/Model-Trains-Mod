package me.kk47.modeltrains.client.render;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.api.IItemModelTrack;
import me.kk47.modeltrains.client.model.ModelTrackbedEmpty;
import me.kk47.modeltrains.tileentity.TileEntityTrackBed;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderTrackbed extends TileEntitySpecialRenderer<TileEntityTrackBed>{

	private ModelTrackbedEmpty mTrackbed = new ModelTrackbedEmpty();
	private ResourceLocation trackbedTexture = new ResourceLocation(Data.MODID + ":textures/blocks/trackbed.png");
	
	@Override
	public void render(TileEntityTrackBed te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
	//                  te     x  y  z   model       texture         scale  xMod  yMod  zMod  rotation
			boolean hasTrack = false;
			ItemStack[][] inv2d = te.getInventory();
			for(int lx = 0; lx < 4; lx++){
				for(int ly = 0; ly < 4; ly++){
					if(inv2d[lx][ly] != null){
						ItemStack i = inv2d[lx][ly];
//						System.out.println("Slot " + lx + ", " + ly + " is unlocalized name " + inv2d[lx][ly].getUnlocalizedName());
					
						if(i.getItem() instanceof IItemModelTrack){
							IItemModelTrack track = (IItemModelTrack) i.getItem();
							this.renderModel(te, x, y, z, track.getRenderModel(), track.getTexture(), 0.25F, 1.0F*lx, 0.0F, 1.0F*ly, (90.0F*i.getItemDamage()));
							hasTrack = true;
						}else{
							
						}
						
					}else{
//						System.out.println("Slot " + lx + ", " + ly + " is null");
					}
				}
			}
			
			if(!hasTrack){
				this.renderModel(te, x, y, z, mTrackbed, trackbedTexture);
			}
	}

	//TODO correct modifiers
	public void renderModel(TileEntity te, double x, double y, double z, ModelBase model, ResourceLocation texture, float scale, float modifierX, float modifierY, float modifierZ, float rotation){
		//The PushMatrix tells the renderer to "start" doing something.
		GL11.glPushMatrix();
		//This is setting the initial location.
		//		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glTranslatef((float) x + 0.125F, (float) y+0.4F, (float) z + 0.125F);
		//Applies Scaling
		GL11.glScalef(scale, scale, scale);
		//Applies new translation
		GL11.glTranslatef(modifierX, modifierY-0.1F, modifierZ);

		GL11.glRotated(rotation, 0.0, 1.0, 0.0);
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
