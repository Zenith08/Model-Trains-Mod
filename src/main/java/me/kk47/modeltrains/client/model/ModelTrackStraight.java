package me.kk47.modeltrains.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTrackStraight extends ModelBase
{
  //fields
    ModelRenderer Sleeper1;
    ModelRenderer Sleeper2;
    ModelRenderer Sleeper3;
    ModelRenderer Sleeper4;
    ModelRenderer Rail1;
    ModelRenderer Rail2;
  
  public ModelTrackStraight()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Sleeper1 = new ModelRenderer(this, 0, 0);
      Sleeper1.addBox(0F, 0F, 0F, 16, 1, 1);
      Sleeper1.setRotationPoint(-8F, 23F, -6F);
      Sleeper1.setTextureSize(64, 32);
      Sleeper1.mirror = true;
      setRotation(Sleeper1, 0F, 0F, 0F);
      Sleeper2 = new ModelRenderer(this, 0, 0);
      Sleeper2.addBox(0F, 0F, 0F, 16, 1, 1);
      Sleeper2.setRotationPoint(-8F, 23F, -2F);
      Sleeper2.setTextureSize(64, 32);
      Sleeper2.mirror = true;
      setRotation(Sleeper2, 0F, 0F, 0F);
      Sleeper3 = new ModelRenderer(this, 0, 0);
      Sleeper3.addBox(0F, 0F, 0F, 16, 1, 1);
      Sleeper3.setRotationPoint(-8F, 23F, 2F);
      Sleeper3.setTextureSize(64, 32);
      Sleeper3.mirror = true;
      setRotation(Sleeper3, 0F, 0F, 0F);
      Sleeper4 = new ModelRenderer(this, 0, 0);
      Sleeper4.addBox(0F, 0F, 0F, 16, 1, 1);
      Sleeper4.setRotationPoint(-8F, 23F, 6F);
      Sleeper4.setTextureSize(64, 32);
      Sleeper4.mirror = true;
      setRotation(Sleeper4, 0F, 0F, 0F);
      Rail1 = new ModelRenderer(this, 15, 5);
      Rail1.addBox(0F, 0F, 0F, 2, 1, 16);
      Rail1.setRotationPoint(-6F, 22F, -8F);
      Rail1.setTextureSize(64, 32);
      Rail1.mirror = true;
      setRotation(Rail1, 0F, 0F, 0F);
      Rail2 = new ModelRenderer(this, 15, 5);
      Rail2.addBox(0F, 0F, 0F, 2, 1, 16);
      Rail2.setRotationPoint(4F, 22F, -8F);
      Rail2.setTextureSize(64, 32);
      Rail2.mirror = true;
      setRotation(Rail2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    Sleeper1.render(f5);
    Sleeper2.render(f5);
    Sleeper3.render(f5);
    Sleeper4.render(f5);
    Rail1.render(f5);
    Rail2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
}
