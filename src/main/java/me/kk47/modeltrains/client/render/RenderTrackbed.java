package me.kk47.modeltrains.client.render;

import java.util.List;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.api.IItemModelTrack;
import me.kk47.modeltrains.client.model.ModelTrackbedEmpty;
import me.kk47.modeltrains.tileentity.TileEntityTrackBed;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import me.kk47.ueri.UERIRenderable;
import me.kk47.ueri.UERITechne;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderTrackbed extends TileEntitySpecialRenderer<TileEntityTrackBed>{

	private ModelTrackbedEmpty mTrackbed = new ModelTrackbedEmpty();
	private ResourceLocation trackbedTexture = new ResourceLocation(Data.MODID + ":textures/blocks/trackbed.png");
	private UERIRenderable emptyModel;
	
	public RenderTrackbed() {
		emptyModel = new UERITechne(mTrackbed, trackbedTexture);
	}
	
	@Override
	public void render(TileEntityTrackBed te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		//                  te     x  y  z   model       texture         scale  xMod  yMod  zMod  rotation
		boolean hasTrack = false;
		ItemStack[][] inv2d = te.getInventory();
		for(int lx = 0; lx < 4; lx++){
			for(int ly = 0; ly < 4; ly++){
				if(inv2d[lx][ly] != null && inv2d[lx][ly] != ItemStack.EMPTY){
					ItemStack i = inv2d[lx][ly];
					//						System.out.println("Slot " + lx + ", " + ly + " is unlocalized name " + inv2d[lx][ly].getUnlocalizedName());

					if(i.getItem() instanceof IItemModelTrack){
						IItemModelTrack track = (IItemModelTrack) i.getItem();
						List<UERIRenderable> trainModels = track.getRenderables(i);
						for(UERIRenderable renderable : trainModels) {
							renderUERI(te, x, y, z, renderable, 0.25F, 1.0F*lx, 0.0F, 1.0F*ly, 90.0F*i.getItemDamage());
						}
						hasTrack = true;
					}else{

					}
				}else{
					//						System.out.println("Slot " + lx + ", " + ly + " is null");
				}
			}
		}

		if(!hasTrack){
			this.renderUERI(te, x, y, z, emptyModel, 1.0F, 0.375F, 1.2F, 0.375F, 0.0F);
		}
	}
	
	public void renderUERI(TileEntity te, double x, double y, double z, UERIRenderable renderable, float scale, float modifierX, float modifierY, float modifierZ, float rotation) {
		//		System.out.println("RenderUERI method called");
		//The PushMatrix tells the renderer to "start" doing something.
		GlStateManager.pushMatrix();
		//This is setting the initial location.
		GlStateManager.translate((float) x + 0.125F, (float) y+0.4F, (float) z + 0.125F);
		//Applies Scaling
		GlStateManager.scale(scale, scale, scale);
		//Applies new translation
		GlStateManager.translate(modifierX, modifierY-0.1F, modifierZ);
		GlStateManager.rotate(rotation, 0.0F, 1.0F, 0.0F);
	
		renderable.render();

		GlStateManager.popMatrix();
	}
}
