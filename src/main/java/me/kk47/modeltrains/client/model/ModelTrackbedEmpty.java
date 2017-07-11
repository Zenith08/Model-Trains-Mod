package me.kk47.modeltrains.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTrackbedEmpty extends ModelBase
{
	//fields
	ModelRenderer Shape1;

	public ModelTrackbedEmpty()
	{
		textureWidth = 32;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, -16, 0);
		Shape1.addBox(0F, 0F, 0F, 16, 0, 16);
		Shape1.setRotationPoint(-8F, 23.9F, -8F);
		Shape1.setTextureSize(32, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		Shape1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
