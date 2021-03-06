package me.kk47.modeltrains.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPassengerCarage extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
  
  public ModelPassengerCarage()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 12, 6, 14);
      Shape1.setRotationPoint(-6F, 16F, -7F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 37);
      Shape2.addBox(0F, -10F, 0F, 1, 10, 14);
      Shape2.setRotationPoint(-6F, 22F, -7F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, -0.1396263F);
      Shape3 = new ModelRenderer(this, 0, 37);
      Shape3.addBox(-1F, -10F, 0F, 1, 10, 14);
      Shape3.setRotationPoint(6F, 22F, -7F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0.1396263F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(0F, 0F, 0F, 13, 4, 14);
      Shape4.setRotationPoint(-6.5F, 12F, -7F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(-5F, -1F, 0F, 5, 1, 14);
      Shape5.setRotationPoint(8F, 13F, -7F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0.3490659F);
      Shape6 = new ModelRenderer(this, 0, 0);
      Shape6.addBox(0F, -1F, 0F, 5, 1, 14);
      Shape6.setRotationPoint(-8F, 13F, -7F);
      Shape6.setTextureSize(64, 64);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, -0.3490659F);
      Shape7 = new ModelRenderer(this, 0, 0);
      Shape7.addBox(0F, 0F, 0F, 8, 2, 14);
      Shape7.setRotationPoint(-4F, 10.3F, -7F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 32, 38);
      Shape8.addBox(0F, 0F, 0F, 1, 3, 3);
      Shape8.setRotationPoint(-6.1F, 21F, -6F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 32, 38);
      Shape9.addBox(0F, 0F, 0F, 1, 3, 3);
      Shape9.setRotationPoint(-6.1F, 21F, 3F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 32, 38);
      Shape10.addBox(0F, 0F, 0F, 1, 3, 3);
      Shape10.setRotationPoint(5.1F, 21F, -6F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 32, 38);
      Shape11.addBox(0F, 0F, 0F, 1, 3, 3);
      Shape11.setRotationPoint(5.1F, 21F, 3F);
      Shape11.setTextureSize(64, 64);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
}
