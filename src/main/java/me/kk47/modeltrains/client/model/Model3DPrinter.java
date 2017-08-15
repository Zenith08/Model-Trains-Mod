package me.kk47.modeltrains.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Model3DPrinter extends ModelBase{

	//Fields
	//Static Models
	ModelRenderer Base;
	ModelRenderer Hold1;
	ModelRenderer Hold2;
	ModelRenderer Hold3;
	ModelRenderer Hold4;
	ModelRenderer Top;

	//Dynamic Models
	ModelRenderer PrintingHead1;
	ModelRenderer PrintingHead2;
	ModelRenderer PrintingHead3;
	ModelRenderer PrintingHead4;
	ModelRenderer PrintingHead5;
	ModelRenderer PrintingHead6;
	ModelRenderer TrainWheel1;
	ModelRenderer TrainWheel2;
	ModelRenderer TrainWheel3;
	ModelRenderer TrainWheel4;
	ModelRenderer TrainBoiler1;
	ModelRenderer TrainBoiler2;
	ModelRenderer TrainBoiler3;
	ModelRenderer TrainCab1;
	ModelRenderer TrainCab2;
	ModelRenderer TrainLight;

	private int animationState = 0;

	private float printingHeadX = -6; //It goes -6 to +5
	private float printingHeadZ = -6;
	private int printingHeadZTarget = -6;

	private float printingHeadY = 0;
	private int printingHeadVertTarget = 0;

	private boolean movingUpwards = false;

	private boolean nextForward = false;
	
	private int frame;

	public Model3DPrinter()
	{
		textureWidth = 64;
		textureHeight = 64;

		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(0F, 0F, 0F, 14, 1, 14);
		Base.setRotationPoint(-7F, 23F, -7F);
		Base.setTextureSize(64, 64);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		Hold1 = new ModelRenderer(this, 0, 0);
		Hold1.addBox(0F, 0F, 0F, 1, 13, 1);
		Hold1.setRotationPoint(-7F, 10F, 6F);
		Hold1.setTextureSize(64, 64);
		Hold1.mirror = true;
		setRotation(Hold1, 0F, 0F, 0F);
		Hold2 = new ModelRenderer(this, 0, 0);
		Hold2.addBox(0F, 0F, 0F, 1, 13, 1);
		Hold2.setRotationPoint(6F, 10F, 6F);
		Hold2.setTextureSize(64, 64);
		Hold2.mirror = true;
		setRotation(Hold2, 0F, 0F, 0F);
		Hold3 = new ModelRenderer(this, 0, 0);
		Hold3.addBox(0F, 0F, 0F, 1, 13, 1);
		Hold3.setRotationPoint(6F, 10F, -7F);
		Hold3.setTextureSize(64, 64);
		Hold3.mirror = true;
		setRotation(Hold3, 0F, 0F, 0F);
		Hold4 = new ModelRenderer(this, 0, 0);
		Hold4.addBox(0F, 0F, 0F, 1, 13, 1);
		Hold4.setRotationPoint(-7F, 10F, -7F);
		Hold4.setTextureSize(64, 64);
		Hold4.mirror = true;
		setRotation(Hold4, 0F, 0F, 0F);
		Top = new ModelRenderer(this, 0, 0);
		Top.addBox(0F, 0F, 0F, 14, 2, 14);
		Top.setRotationPoint(-7F, 8F, -7F);
		Top.setTextureSize(64, 64);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		PrintingHead1 = new ModelRenderer(this, 0, 20);
		PrintingHead1.addBox(0F, 0F, 0F, 1, 2, 1);
		PrintingHead1.setRotationPoint(-6F, 10F, -6F);
		PrintingHead1.setTextureSize(64, 64);
		PrintingHead1.mirror = true;
		setRotation(PrintingHead1, 0F, 0F, 0F);
		PrintingHead2 = new ModelRenderer(this, 0, 20);
		PrintingHead2.addBox(0F, 0F, 0F, 1, 2, 1);
		PrintingHead2.setRotationPoint(-6F, 12F, -6F);
		PrintingHead2.setTextureSize(64, 64);
		PrintingHead2.mirror = true;
		setRotation(PrintingHead2, 0F, 0F, 0F);
		PrintingHead3 = new ModelRenderer(this, 0, 20);
		PrintingHead3.addBox(0F, 0F, 0F, 1, 2, 1);
		PrintingHead3.setRotationPoint(-6F, 14F, -6F);
		PrintingHead3.setTextureSize(64, 64);
		PrintingHead3.mirror = true;
		setRotation(PrintingHead3, 0F, 0F, 0F);
		PrintingHead4 = new ModelRenderer(this, 0, 20);
		PrintingHead4.addBox(0F, 0F, 0F, 1, 2, 1);
		PrintingHead4.setRotationPoint(-6F, 16F, -6F);
		PrintingHead4.setTextureSize(64, 64);
		PrintingHead4.mirror = true;
		setRotation(PrintingHead4, 0F, 0F, 0F);
		PrintingHead5 = new ModelRenderer(this, 0, 20);
		PrintingHead5.addBox(0F, 0F, 0F, 1, 2, 1);
		PrintingHead5.setRotationPoint(-6F, 18F, -6F);
		PrintingHead5.setTextureSize(64, 64);
		PrintingHead5.mirror = true;
		setRotation(PrintingHead5, 0F, 0F, 0F);
		PrintingHead6 = new ModelRenderer(this, 0, 20);
		PrintingHead6.addBox(0F, 0F, 0F, 1, 2, 1);
		PrintingHead6.setRotationPoint(-6F, 20F, -6F);
		PrintingHead6.setTextureSize(64, 64);
		PrintingHead6.mirror = true;
		setRotation(PrintingHead6, 0F, 0F, 0F);
		TrainWheel1 = new ModelRenderer(this, 20, 30);
		TrainWheel1.addBox(0F, 0F, 0F, 1, 1, 1);
		TrainWheel1.setRotationPoint(3F, 22F, -2F);
		TrainWheel1.setTextureSize(64, 64);
		TrainWheel1.mirror = true;
		setRotation(TrainWheel1, 0F, 0F, 0F);
		TrainWheel2 = new ModelRenderer(this, 20, 30);
		TrainWheel2.addBox(0F, 0F, 0F, 1, 1, 1);
		TrainWheel2.setRotationPoint(-3F, 22F, -2F);
		TrainWheel2.setTextureSize(64, 64);
		TrainWheel2.mirror = true;
		setRotation(TrainWheel2, 0F, 0F, 0F);
		TrainWheel3 = new ModelRenderer(this, 20, 30);
		TrainWheel3.addBox(0F, 0F, 0F, 1, 1, 1);
		TrainWheel3.setRotationPoint(-3F, 22F, 1F);
		TrainWheel3.setTextureSize(64, 64);
		TrainWheel3.mirror = true;
		setRotation(TrainWheel3, 0F, 0F, 0F);
		TrainWheel4 = new ModelRenderer(this, 20, 30);
		TrainWheel4.addBox(0F, 0F, 0F, 1, 1, 1);
		TrainWheel4.setRotationPoint(3F, 22F, 1F);
		TrainWheel4.setTextureSize(64, 64);
		TrainWheel4.mirror = true;
		setRotation(TrainWheel4, 0F, 0F, 0F);
		TrainBoiler1 = new ModelRenderer(this, 20, 30);
		TrainBoiler1.addBox(0F, 0F, 0F, 7, 1, 4);
		TrainBoiler1.setRotationPoint(-3F, 21F, -2F);
		TrainBoiler1.setTextureSize(64, 64);
		TrainBoiler1.mirror = true;
		setRotation(TrainBoiler1, 0F, 0F, 0F);
		TrainBoiler2 = new ModelRenderer(this, 20, 30);
		TrainBoiler2.addBox(0F, 0F, 0F, 7, 1, 4);
		TrainBoiler2.setRotationPoint(-3F, 20F, -2F);
		TrainBoiler2.setTextureSize(64, 64);
		TrainBoiler2.mirror = true;
		setRotation(TrainBoiler2, 0F, 0F, 0F);
		TrainBoiler3 = new ModelRenderer(this, 20, 30);
		TrainBoiler3.addBox(0F, 0F, 0F, 7, 1, 4);
		TrainBoiler3.setRotationPoint(-3F, 19F, -2F);
		TrainBoiler3.setTextureSize(64, 64);
		TrainBoiler3.mirror = true;
		setRotation(TrainBoiler3, 0F, 0F, 0F);
		TrainCab1 = new ModelRenderer(this, 20, 30);
		TrainCab1.addBox(0F, 0F, 0F, 2, 1, 4);
		TrainCab1.setRotationPoint(-3F, 18F, -2F);
		TrainCab1.setTextureSize(64, 64);
		TrainCab1.mirror = true;
		setRotation(TrainCab1, 0F, 0F, 0F);
		TrainCab2 = new ModelRenderer(this, 20, 30);
		TrainCab2.addBox(0F, 0F, 0F, 2, 1, 4);
		TrainCab2.setRotationPoint(-3F, 17F, -2F);
		TrainCab2.setTextureSize(64, 64);
		TrainCab2.mirror = true;
		setRotation(TrainCab2, 0F, 0F, 0F);
		TrainLight = new ModelRenderer(this, 20, 38);
		TrainLight.addBox(0F, 0F, 0F, 1, 1, 1);
		TrainLight.setRotationPoint(3.5F, 18.5F, -0.5F);
		TrainLight.setTextureSize(64, 64);
		TrainLight.mirror = true;
		setRotation(TrainLight, 0F, 0F, 0F);
	}

	public void updateFrame() {
		//This will be used to sync frame and tick rates
		if(animationState < 4) {
			frame+=1;
		}
		
		if(animationState == 0) {

			printingHeadX+=0.1;

			updatePrintingHead();

			if(printingHeadX >= 5) {
				printingHeadZTarget += 1;
				animationState = 2;
				nextForward = false;
			}
		}else if(animationState == 1) {

			printingHeadX-=0.1;

			updatePrintingHead();

			if(printingHeadX <= -6) {
				printingHeadZTarget += 1;
				animationState = 2;
				nextForward = true;
			}
		}else if(animationState == 2) {

			printingHeadZ+=0.1;
			updatePrintingHead();
			if(printingHeadZ >= printingHeadZTarget) {
				if(nextForward) {
					animationState = 0;
				}else {
					animationState = 1;
				}
			}

			if(printingHeadX >= 5 && printingHeadZ >= 5) {
				animationState = 3;
				movingUpwards = true;
				printingHeadVertTarget-=1;
			}

		}else if(animationState == 3){

			if(printingHeadX > -6) {
				printingHeadX-=0.1;
			}
			if(printingHeadZ > -6) {
				printingHeadZ-=0.1;
			}

			if(movingUpwards) {
				//				System.out.println("PHY " + printingHeadY + " PHT " + printingHeadVertTarget);
				printingHeadY-=0.1;
				if(printingHeadY <= printingHeadVertTarget) {
					movingUpwards = false;
				}
			}

			updatePrintingHead();

			if(printingHeadX <= -6 && printingHeadZ <= -6) {
				printingHeadZTarget = -6;
				animationState = 0;
				if(printingHeadY <= -6) {
					animationState = 4;
				}
			}
		} //else if (animationState == 4){Do Nothing}
	}

	public boolean isFloatInt(float d)
	{
		//select a "tolerance range" for being an integer
		double TOLERANCE = 1E-5;
		//do not use (int)d, due to weird floating point conversions!
		return Math.abs(Math.floor(d) - d) < TOLERANCE;
	}

	private void updatePrintingHead() {
		PrintingHead1.setRotationPoint(printingHeadX, 10+printingHeadY, printingHeadZ);
		PrintingHead2.setRotationPoint(printingHeadX, 12+printingHeadY, printingHeadZ);
		PrintingHead3.setRotationPoint(printingHeadX, 14+printingHeadY, printingHeadZ);
		PrintingHead4.setRotationPoint(printingHeadX, 16+printingHeadY, printingHeadZ);
		PrintingHead5.setRotationPoint(printingHeadX, 18+printingHeadY, printingHeadZ);
		PrintingHead6.setRotationPoint(printingHeadX, 20+printingHeadY, printingHeadZ);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		//Static Models
		Base.render(f5);
		Hold1.render(f5);
		Hold2.render(f5);
		Hold3.render(f5);
		Hold4.render(f5);
		Top.render(f5);

//		System.out.println("PHY " + printingHeadY + " PHYT " + printingHeadVertTarget);
		//Printing Head
		//Dynamic Printing Head
		if(printingHeadY >= -2) {
			PrintingHead1.render(f5); //First 2 Layers
		}
		if(printingHeadY >= -4) {
			PrintingHead2.render(f5); //4 layers
		}
		if(printingHeadY >= -6) {
			PrintingHead3.render(f5); //6 layers
		}

		//Static Printing Head
		PrintingHead4.render(f5); //Static
		PrintingHead5.render(f5); //Static
		PrintingHead6.render(f5); //Static

		//Train
		if(printingHeadVertTarget <= -1) {
			TrainWheel1.render(f5); //Layer 0
			TrainWheel2.render(f5); //Layer 0
			TrainWheel3.render(f5); //Layer 0
			TrainWheel4.render(f5); //Layer 0
		}
		if(printingHeadVertTarget <= -2) {
			TrainBoiler1.render(f5); //Layer 1
		}
		if(printingHeadVertTarget <= -3) {
			TrainBoiler2.render(f5); //Layer 2
		}
		if(printingHeadVertTarget <= -4) {
			TrainBoiler3.render(f5); //Layer 3
		}
		if(printingHeadVertTarget <= -5) {
			TrainCab1.render(f5); //Layer 4
		}
		if(printingHeadVertTarget <= -6) {
			TrainCab2.render(f5); //Layer 5
			TrainLight.render(f5); //Layer 5
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}
}



