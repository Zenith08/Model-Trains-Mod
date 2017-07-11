package me.kk47.modeltrains.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTrackTurn extends ModelBase
{
  //fields
    ModelRenderer Sleeper1;
    ModelRenderer Sleeper2;
    ModelRenderer Sleeper3;
    ModelRenderer Sleeper4;
    ModelRenderer Sleeper5;
    ModelRenderer RailR1;
    ModelRenderer RailR2;
    ModelRenderer RailR3;
    ModelRenderer RailL1;
    ModelRenderer RailL2;
    ModelRenderer RailL3;
    ModelRenderer RailL4;
    ModelRenderer RailL5;
    ModelRenderer RailL6;
  
  public ModelTrackTurn()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Sleeper1 = new ModelRenderer(this, 0, 0);
      Sleeper1.addBox(0F, 0F, 0F, 16, 1, 1);
      Sleeper1.setRotationPoint(8F, 23F, -6.5F);
      Sleeper1.setTextureSize(64, 32);
      Sleeper1.mirror = true;
      setRotation(Sleeper1, 0F, -3.054326F, 0F);
      Sleeper2 = new ModelRenderer(this, 0, 0);
      Sleeper2.addBox(0F, 0F, 0F, 16, 1, 1);
      Sleeper2.setRotationPoint(8F, 23F, -6.5F);
      Sleeper2.setTextureSize(64, 32);
      Sleeper2.mirror = true;
      setRotation(Sleeper2, 0F, -2.70526F, 0F);
      Sleeper3 = new ModelRenderer(this, 0, 0);
      Sleeper3.addBox(0F, 0F, 0F, 16, 1, 1);
      Sleeper3.setRotationPoint(8F, 23F, -6.5F);
      Sleeper3.setTextureSize(64, 32);
      Sleeper3.mirror = true;
      setRotation(Sleeper3, 0F, -2.356194F, 0F);
      Sleeper4 = new ModelRenderer(this, 0, 0);
      Sleeper4.addBox(0F, 0F, 0F, 16, 1, 1);
      Sleeper4.setRotationPoint(8F, 23F, -6.5F);
      Sleeper4.setTextureSize(64, 32);
      Sleeper4.mirror = true;
      setRotation(Sleeper4, 0F, -2.007129F, 0F);
      Sleeper5 = new ModelRenderer(this, 0, 0);
      Sleeper5.addBox(0F, 0F, 0F, 15, 1, 1);
      Sleeper5.setRotationPoint(8F, 23F, -7F);
      Sleeper5.setTextureSize(64, 32);
      Sleeper5.mirror = true;
      setRotation(Sleeper5, 0F, -1.658063F, 0F);
      RailR1 = new ModelRenderer(this, 0, 7);
      RailR1.addBox(0F, 0F, 0F, 2, 1, 3);
      RailR1.setRotationPoint(4F, 22F, -8F);
      RailR1.setTextureSize(64, 32);
      RailR1.mirror = true;
      setRotation(RailR1, 0F, 0.1784573F, 0F);
      RailR2 = new ModelRenderer(this, 0, 8);
      RailR2.addBox(0F, 0F, 0F, 2, 1, 2);
      RailR2.setRotationPoint(5.25F, 22F, -6.75F);
      RailR2.setTextureSize(64, 32);
      RailR2.mirror = true;
      setRotation(RailR2, 0F, -0.8448839F, 0F);
      RailR3 = new ModelRenderer(this, 0, 8);
      RailR3.addBox(0F, 0F, 0F, 3, 1, 2);
      RailR3.setRotationPoint(8F, 22F, -4F);
      RailR3.setTextureSize(64, 32);
      RailR3.mirror = true;
      setRotation(RailR3, 0F, 2.9557F, 0F);
      RailL1 = new ModelRenderer(this, 13, 8);
      RailL1.addBox(0F, 0F, 0F, 2, 1, 3);
      RailL1.setRotationPoint(-6F, 22F, -8F);
      RailL1.setTextureSize(64, 32);
      RailL1.mirror = true;
      setRotation(RailL1, 0F, 0F, 0F);
      RailL2 = new ModelRenderer(this, 11, 6);
      RailL2.addBox(0F, 0F, 0F, 2, 1, 5);
      RailL2.setRotationPoint(-6F, 22F, -5F);
      RailL2.setTextureSize(64, 32);
      RailL2.mirror = true;
      setRotation(RailL2, 0F, 0.2974289F, 0F);
      RailL3 = new ModelRenderer(this, 11, 6);
      RailL3.addBox(0F, 0F, 0F, 2, 1, 5);
      RailL3.setRotationPoint(-5F, 22F, -1F);
      RailL3.setTextureSize(64, 32);
      RailL3.mirror = true;
      setRotation(RailL3, 0F, 0.5948578F, 0F);
      RailL4 = new ModelRenderer(this, 18, 15);
      RailL4.addBox(0F, 0F, 0F, 5, 1, 2);
      RailL4.setRotationPoint(2F, 22F, 5.5F);
      RailL4.setTextureSize(64, 32);
      RailL4.mirror = true;
      setRotation(RailL4, 0F, 2.647117F, 0F);
      RailL5 = new ModelRenderer(this, 18, 20);
      RailL5.addBox(0F, 0F, 0F, 4, 1, 2);
      RailL5.setRotationPoint(6F, 22F, 6F);
      RailL5.setTextureSize(64, 32);
      RailL5.mirror = true;
      setRotation(RailL5, 0F, 3.052364F, 0F);
      RailL6 = new ModelRenderer(this, 0, 15);
      RailL6.addBox(0F, 0F, 0F, 2, 1, 2);
      RailL6.setRotationPoint(6F, 22F, 4F);
      RailL6.setTextureSize(64, 32);
      RailL6.mirror = true;
      setRotation(RailL6, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    Sleeper1.render(f5);
    Sleeper2.render(f5);
    Sleeper3.render(f5);
    Sleeper4.render(f5);
    Sleeper5.render(f5);
    RailR1.render(f5);
    RailR2.render(f5);
    RailR3.render(f5);
    RailL1.render(f5);
    RailL2.render(f5);
    RailL3.render(f5);
    RailL4.render(f5);
    RailL5.render(f5);
    RailL6.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
}
