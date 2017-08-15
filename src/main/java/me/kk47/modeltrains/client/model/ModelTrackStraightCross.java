package me.kk47.modeltrains.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTrackStraightCross extends ModelBase
{
  //fields
    ModelRenderer Rail1R1;
    ModelRenderer Rail1L1;
    ModelRenderer Rail2R1;
    ModelRenderer Rail2R2;
    ModelRenderer Rail2R3;
    ModelRenderer Rail2L1;
    ModelRenderer Rail2L3;
    ModelRenderer Rail2L2;
    ModelRenderer Rail1R2;
    ModelRenderer Rail1R3;
    ModelRenderer Rail1L2;
    ModelRenderer Rail1L3;
    ModelRenderer Sleeper1;
    ModelRenderer Sleeper2;
    ModelRenderer Sleeper3;
    ModelRenderer Sleeper4;
    ModelRenderer Sleeper11;
    ModelRenderer Sleeper12;
    ModelRenderer Sleeper13;
    ModelRenderer Sleeper14;
  
  public ModelTrackStraightCross()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Rail1R1 = new ModelRenderer(this, 19, 4);
      Rail1R1.addBox(0F, 0F, 0F, 2, 1, 4);
      Rail1R1.setRotationPoint(4F, 22F, -8F);
      Rail1R1.setTextureSize(64, 32);
      Rail1R1.mirror = true;
      setRotation(Rail1R1, 0F, 0F, 0F);
      Rail1L1 = new ModelRenderer(this, 19, 4);
      Rail1L1.addBox(0F, 0F, 0F, 2, 1, 4);
      Rail1L1.setRotationPoint(-6F, 22F, -8F);
      Rail1L1.setTextureSize(64, 32);
      Rail1L1.mirror = true;
      setRotation(Rail1L1, 0F, 0F, 0F);
      Rail2R1 = new ModelRenderer(this, 19, 0);
      Rail2R1.addBox(0F, 0F, 0F, 2, 1, 2);
      Rail2R1.setRotationPoint(-8F, 22F, -6F);
      Rail2R1.setTextureSize(64, 32);
      Rail2R1.mirror = true;
      setRotation(Rail2R1, 0F, 0F, 0F);
      Rail2R2 = new ModelRenderer(this, 0, 0);
      Rail2R2.addBox(0F, 0F, 0F, 6, 1, 2);
      Rail2R2.setRotationPoint(-3F, 22F, -5.973333F);
      Rail2R2.setTextureSize(64, 32);
      Rail2R2.mirror = true;
      setRotation(Rail2R2, 0F, 0F, 0F);
      Rail2R3 = new ModelRenderer(this, 19, 0);
      Rail2R3.addBox(0F, 0F, 0F, 2, 1, 2);
      Rail2R3.setRotationPoint(6F, 22F, -6F);
      Rail2R3.setTextureSize(64, 32);
      Rail2R3.mirror = true;
      setRotation(Rail2R3, 0F, 0F, 0F);
      Rail2L1 = new ModelRenderer(this, 19, 0);
      Rail2L1.addBox(0F, 0F, 0F, 2, 1, 2);
      Rail2L1.setRotationPoint(-8F, 22F, 4F);
      Rail2L1.setTextureSize(64, 32);
      Rail2L1.mirror = true;
      setRotation(Rail2L1, 0F, 0F, 0F);
      Rail2L3 = new ModelRenderer(this, 19, 0);
      Rail2L3.addBox(0F, 0F, 0F, 2, 1, 2);
      Rail2L3.setRotationPoint(6F, 22F, 4F);
      Rail2L3.setTextureSize(64, 32);
      Rail2L3.mirror = true;
      setRotation(Rail2L3, 0F, 0F, 0F);
      Rail2L2 = new ModelRenderer(this, 0, 0);
      Rail2L2.addBox(0F, 0F, 0F, 6, 1, 2);
      Rail2L2.setRotationPoint(-3F, 22F, 4F);
      Rail2L2.setTextureSize(64, 32);
      Rail2L2.mirror = true;
      setRotation(Rail2L2, 0F, 0F, 0F);
      Rail1R2 = new ModelRenderer(this, 0, 10);
      Rail1R2.addBox(0F, 0F, 0F, 2, 1, 6);
      Rail1R2.setRotationPoint(4F, 22F, -3F);
      Rail1R2.setTextureSize(64, 32);
      Rail1R2.mirror = true;
      setRotation(Rail1R2, 0F, 0F, 0F);
      Rail1R3 = new ModelRenderer(this, 19, 4);
      Rail1R3.addBox(0F, 0F, 0F, 2, 1, 4);
      Rail1R3.setRotationPoint(4F, 22F, 4F);
      Rail1R3.setTextureSize(64, 32);
      Rail1R3.mirror = true;
      setRotation(Rail1R3, 0F, 0F, 0F);
      Rail1L2 = new ModelRenderer(this, 0, 10);
      Rail1L2.addBox(0F, 0F, 0F, 2, 1, 6);
      Rail1L2.setRotationPoint(-6F, 22F, -3F);
      Rail1L2.setTextureSize(64, 32);
      Rail1L2.mirror = true;
      setRotation(Rail1L2, 0F, 0F, 0F);
      Rail1L3 = new ModelRenderer(this, 19, 4);
      Rail1L3.addBox(0F, 0F, 0F, 2, 1, 4);
      Rail1L3.setRotationPoint(-6F, 22F, 4F);
      Rail1L3.setTextureSize(64, 32);
      Rail1L3.mirror = true;
      setRotation(Rail1L3, 0F, 0F, 0F);
      Sleeper1 = new ModelRenderer(this, 30, 10);
      Sleeper1.addBox(0F, 0F, 0F, 1, 1, 16);
      Sleeper1.setRotationPoint(5F, 22.999F, -8F);
      Sleeper1.setTextureSize(64, 32);
      Sleeper1.mirror = true;
      setRotation(Sleeper1, 0F, 0F, 0F);
      Sleeper2 = new ModelRenderer(this, 30, 10);
      Sleeper2.addBox(0F, 0F, 0F, 1, 1, 16);
      Sleeper2.setRotationPoint(1F, 22.999F, -8F);
      Sleeper2.setTextureSize(64, 32);
      Sleeper2.mirror = true;
      setRotation(Sleeper2, 0F, 0F, 0F);
      Sleeper3 = new ModelRenderer(this, 30, 10);
      Sleeper3.addBox(0F, 0F, 0F, 1, 1, 16);
      Sleeper3.setRotationPoint(-3F, 22.999F, -8F);
      Sleeper3.setTextureSize(64, 32);
      Sleeper3.mirror = true;
      setRotation(Sleeper3, 0F, 0F, 0F);
      Sleeper4 = new ModelRenderer(this, 30, 10);
      Sleeper4.addBox(0F, 0F, 0F, 1, 1, 16);
      Sleeper4.setRotationPoint(-7F, 22.999F, -8F);
      Sleeper4.setTextureSize(64, 32);
      Sleeper4.mirror = true;
      setRotation(Sleeper4, 0F, 0F, 0F);
      Sleeper11 = new ModelRenderer(this, 0, 30);
      Sleeper11.addBox(0F, 0F, 0F, 16, 1, 1);
      Sleeper11.setRotationPoint(-8F, 23F, 6F);
      Sleeper11.setTextureSize(64, 32);
      Sleeper11.mirror = true;
      setRotation(Sleeper11, 0F, 0F, 0F);
      Sleeper12 = new ModelRenderer(this, 0, 30);
      Sleeper12.addBox(0F, 0F, 0F, 16, 1, 1);
      Sleeper12.setRotationPoint(-8F, 23F, 2F);
      Sleeper12.setTextureSize(64, 32);
      Sleeper12.mirror = true;
      setRotation(Sleeper12, 0F, 0F, 0F);
      Sleeper13 = new ModelRenderer(this, 0, 30);
      Sleeper13.addBox(0F, 0F, 0F, 16, 1, 1);
      Sleeper13.setRotationPoint(-8F, 23F, -2F);
      Sleeper13.setTextureSize(64, 32);
      Sleeper13.mirror = true;
      setRotation(Sleeper13, 0F, 0F, 0F);
      Sleeper14 = new ModelRenderer(this, 0, 30);
      Sleeper14.addBox(0F, 0F, 0F, 16, 1, 1);
      Sleeper14.setRotationPoint(-8F, 23F, -6F);
      Sleeper14.setTextureSize(64, 32);
      Sleeper14.mirror = true;
      setRotation(Sleeper14, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    Rail1R1.render(f5);
    Rail1L1.render(f5);
    Rail2R1.render(f5);
    Rail2R2.render(f5);
    Rail2R3.render(f5);
    Rail2L1.render(f5);
    Rail2L3.render(f5);
    Rail2L2.render(f5);
    Rail1R2.render(f5);
    Rail1R3.render(f5);
    Rail1L2.render(f5);
    Rail1L3.render(f5);
    Sleeper1.render(f5);
    Sleeper2.render(f5);
    Sleeper3.render(f5);
    Sleeper4.render(f5);
    Sleeper11.render(f5);
    Sleeper12.render(f5);
    Sleeper13.render(f5);
    Sleeper14.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
}
