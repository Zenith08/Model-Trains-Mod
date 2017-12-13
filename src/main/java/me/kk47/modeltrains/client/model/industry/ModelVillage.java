package me.kk47.modeltrains.client.model.industry;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVillage extends ModelBase
{
  //fields
    ModelRenderer House1Base;
    ModelRenderer House1Roof;
    ModelRenderer House2Base;
    ModelRenderer House2Roof;
    ModelRenderer House3Base;
    ModelRenderer House3Roof;
    ModelRenderer StationBase;
    ModelRenderer StationRoof;
    ModelRenderer StationPlatform;
  
  public ModelVillage()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      House1Base = new ModelRenderer(this, 0, 0);
      House1Base.addBox(0F, 0F, 0F, 4, 4, 4);
      House1Base.setRotationPoint(4F, 20F, 4F);
      House1Base.setTextureSize(64, 32);
      House1Base.mirror = true;
      setRotation(House1Base, 0F, 0F, 0F);
      House1Roof = new ModelRenderer(this, 0, 12);
      House1Roof.addBox(0F, 0F, 0F, 3, 3, 4);
      House1Roof.setRotationPoint(6F, 17.9F, 4F);
      House1Roof.setTextureSize(64, 32);
      House1Roof.mirror = true;
      setRotation(House1Roof, 0F, 0F, 0.7853982F);
      House2Base = new ModelRenderer(this, 0, 0);
      House2Base.addBox(0F, 0F, 0F, 4, 4, 4);
      House2Base.setRotationPoint(-2F, 20F, 4F);
      House2Base.setTextureSize(64, 32);
      House2Base.mirror = true;
      setRotation(House2Base, 0F, 0F, 0F);
      House2Roof = new ModelRenderer(this, 0, 12);
      House2Roof.addBox(0F, 0F, 0F, 3, 3, 4);
      House2Roof.setRotationPoint(0F, 17.9F, 4F);
      House2Roof.setTextureSize(64, 32);
      House2Roof.mirror = true;
      setRotation(House2Roof, 0F, 0F, 0.7853982F);
      House3Base = new ModelRenderer(this, 18, 11);
      House3Base.addBox(0F, 0F, 0F, 4, 4, 4);
      House3Base.setRotationPoint(-8F, 20F, -1F);
      House3Base.setTextureSize(64, 32);
      House3Base.mirror = true;
      setRotation(House3Base, 0F, 0F, 0F);
      House3Roof = new ModelRenderer(this, 26, 0);
      House3Roof.addBox(0F, 0F, 0F, 4, 3, 3);
      House3Roof.setRotationPoint(-8F, 17.9F, 1F);
      House3Roof.setTextureSize(64, 32);
      House3Roof.mirror = true;
      setRotation(House3Roof, -0.7853982F, 0F, 0F);
      StationBase = new ModelRenderer(this, 0, 21);
      StationBase.addBox(0F, 0F, 0F, 7, 4, 4);
      StationBase.setRotationPoint(1F, 20F, -2F);
      StationBase.setTextureSize(64, 32);
      StationBase.mirror = true;
      setRotation(StationBase, 0F, 0F, 0F);
      StationRoof = new ModelRenderer(this, 24, 21);
      StationRoof.addBox(0F, 0F, 0F, 7, 3, 3);
      StationRoof.setRotationPoint(1F, 17.9F, 0F);
      StationRoof.setTextureSize(64, 32);
      StationRoof.mirror = true;
      setRotation(StationRoof, -0.7853982F, 0F, 0F);
      StationPlatform = new ModelRenderer(this, 38, 28);
      StationPlatform.addBox(0F, 0F, 0F, 7, 1, 2);
      StationPlatform.setRotationPoint(1F, 23F, -4F);
      StationPlatform.setTextureSize(64, 32);
      StationPlatform.mirror = true;
      setRotation(StationPlatform, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    House1Base.render(f5);
    House1Roof.render(f5);
    House2Base.render(f5);
    House2Roof.render(f5);
    House3Base.render(f5);
    House3Roof.render(f5);
    StationBase.render(f5);
    StationRoof.render(f5);
    StationPlatform.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

}
