package me.kk47.ueri;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import me.kk47.ueri.util.RenderTransform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class UERITechne extends UERIRenderable {

	private ModelBase model;
	private ResourceLocation texture;
	
	public UERITechne(ModelBase model, ResourceLocation texture) {
		super();
		this.model = model;
		this.texture = texture;
	}

	public UERITechne(RenderTransform transform, boolean isUsingColour, Vector3f colour, ModelBase model,
			ResourceLocation texture) {
		super(transform, isUsingColour, colour);
		this.model = model;
		this.texture = texture;
	}

	@Override
	public void renderModel() {
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		//This is a second push matrix needed for techne for some reason.
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180F, 0.0F, 0.0F, 1.0F);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
}
