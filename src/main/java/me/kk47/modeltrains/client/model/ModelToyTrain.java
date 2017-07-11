package me.kk47.modeltrains.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelToyTrain extends MTmModelBase
{
  //fields
    ModelRenderer BoilerAndBody;
    ModelRenderer Cab;
    ModelRenderer SmokeStack;
    ModelRenderer Light;
    ModelRenderer WheelR1;
    ModelRenderer WheelR2;
    ModelRenderer WheelL1;
    ModelRenderer WheelL2;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
  
  public ModelToyTrain()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      BoilerAndBody = new ModelRenderer(this, 0, 11);
      BoilerAndBody.addBox(0F, 0F, 0F, 18, 2, 12);
      BoilerAndBody.setRotationPoint(-7F, 20F, -6F);
      BoilerAndBody.setTextureSize(128, 128);
      BoilerAndBody.mirror = true;
      setRotation(BoilerAndBody, 0F, 0F, 0F);
      Cab = new ModelRenderer(this, 0, 71);
      Cab.addBox(0F, 0F, 0F, 6, 10, 12);
      Cab.setRotationPoint(-7F, 11F, -6F);
      Cab.setTextureSize(128, 128);
      Cab.mirror = true;
      setRotation(Cab, 0F, 0F, 0F);
      SmokeStack = new ModelRenderer(this, 8, 0);
      SmokeStack.addBox(0F, 0F, 0F, 1, 2, 1);
      SmokeStack.setRotationPoint(9F, 12F, -0.5F);
      SmokeStack.setTextureSize(128, 128);
      SmokeStack.mirror = true;
      setRotation(SmokeStack, 0F, 0F, 0F);
      Light = new ModelRenderer(this, 0, 0);
      Light.addBox(0F, 0F, 0F, 2, 1, 2);
      Light.setRotationPoint(8.5F, 11F, -1F);
      Light.setTextureSize(128, 128);
      Light.mirror = true;
      setRotation(Light, 0F, 0F, 0F);
      WheelR1 = new ModelRenderer(this, 0, 25);
      WheelR1.addBox(0F, 0F, 0F, 2, 2, 1);
      WheelR1.setRotationPoint(-6F, 22F, -6F);
      WheelR1.setTextureSize(128, 128);
      WheelR1.mirror = true;
      setRotation(WheelR1, 0F, 0F, 0F);
      WheelR2 = new ModelRenderer(this, 0, 25);
      WheelR2.addBox(0F, 0F, 0F, 2, 2, 1);
      WheelR2.setRotationPoint(8F, 22F, -6F);
      WheelR2.setTextureSize(128, 128);
      WheelR2.mirror = true;
      setRotation(WheelR2, 0F, 0F, 0F);
      WheelL1 = new ModelRenderer(this, 0, 25);
      WheelL1.addBox(0F, 0F, 0F, 2, 2, 1);
      WheelL1.setRotationPoint(-6F, 22F, 5F);
      WheelL1.setTextureSize(128, 128);
      WheelL1.mirror = true;
      setRotation(WheelL1, 0F, 0F, 0F);
      WheelL2 = new ModelRenderer(this, 0, 25);
      WheelL2.addBox(0F, 0F, 0F, 2, 2, 1);
      WheelL2.setRotationPoint(8F, 22F, 5F);
      WheelL2.setTextureSize(128, 128);
      WheelL2.mirror = true;
      setRotation(WheelL2, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 40, 0);
      Shape1.addBox(0F, 0F, 0F, 12, 6, 6);
      Shape1.setRotationPoint(-1F, 12.84F, 0F);
      Shape1.setTextureSize(128, 128);
      Shape1.mirror = true;
      setRotation(Shape1, -0.7853982F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(0F, 0F, 0F, 12, 1, 6);
      Shape2.setRotationPoint(-1F, 19F, -3F);
      Shape2.setTextureSize(128, 128);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(0F, 0F, 0F, 6, 1, 8);
      Shape3.setRotationPoint(-7F, 10F, -4F);
      Shape3.setTextureSize(128, 128);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(0F, 0F, -5F, 6, 1, 5);
      Shape4.setRotationPoint(-7F, 10F, -4F);
      Shape4.setTextureSize(128, 128);
      Shape4.mirror = true;
      setRotation(Shape4, 0.3490659F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(0F, 0F, 0F, 6, 1, 5);
      Shape5.setRotationPoint(-7F, 10F, 4F);
      Shape5.setTextureSize(128, 128);
      Shape5.mirror = true;
      setRotation(Shape5, -0.3490659F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    BoilerAndBody.render(f5);
    Cab.render(f5);
    SmokeStack.render(f5);
    Light.render(f5);
    WheelR1.render(f5);
    WheelR2.render(f5);
    WheelL1.render(f5);
    WheelL2.render(f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  @Override
  public float getRotation(){
		return 270.0F;
	}

}
