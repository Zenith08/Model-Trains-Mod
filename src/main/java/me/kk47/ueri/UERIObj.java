package me.kk47.ueri;

import org.lwjgl.util.vector.Vector3f;

import me.kk47.ueri.util.RenderTransform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.util.ResourceLocation;

public class UERIObj extends UERIRenderable{

	private ResourceLocation texture;
	private IBakedModel model;
	
	public UERIObj(IBakedModel model, ResourceLocation texture) {
		super();
		this.model = model;
		this.texture = texture;
	}
	
	public UERIObj(RenderTransform transform, boolean isUsingColour, Vector3f colourComponent, ResourceLocation texture, IBakedModel model) {
		super(transform, isUsingColour, colourComponent);
		this.model = model;
		this.texture = texture;
	}

	@Override
	protected void renderModel() {
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		blockrendererdispatcher.getBlockModelRenderer().renderModelBrightnessColor(model, 1.0F, 1.0F, 1.0F, 1.0F);
	}

	protected void addModelCallback(IBakedModel model) {
		this.model = model;
	}
}
