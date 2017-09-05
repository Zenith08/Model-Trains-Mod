package me.kk47.ueri.util;

import org.lwjgl.util.vector.Vector3f;

import net.minecraft.client.renderer.GlStateManager;

public class RenderTransform {

	public static final RenderTransform DEFAULT = new RenderTransform();

	private boolean hasTransform;
	private Vector3f transformComponent;
	
	private boolean hasRotation;
	private Vector3f rotationComponent;
	
	private boolean hasScaling;
	private Vector3f scalingComponent;

	public RenderTransform() {
		this(new Vector3f(), new Vector3f(), new Vector3f(1, 1, 1));
		hasTransform = false;
		hasRotation = false;
		hasScaling = false;
	}

	public RenderTransform(Vector3f transformComponent, Vector3f rotationComponent, Vector3f scalingComponent) {
		this.transformComponent = transformComponent;
		if(transformComponent.x != 0 || transformComponent.y != 0 || transformComponent.z != 0) {
			hasTransform = true;
		} else {
			hasTransform = false;
		}
		this.rotationComponent = rotationComponent;
		if(rotationComponent.x != 0 || rotationComponent.y != 0 || rotationComponent.z != 0) {
			hasRotation = true;
		} else {
			hasRotation = false;
		}
		this.scalingComponent = scalingComponent;
		if(scalingComponent.x != 0 || scalingComponent.y != 0 || scalingComponent.z != 0) {
			hasScaling = true;
		} else {
			hasScaling = false;
		}
	}

	/**
	 * @return the transformComponent
	 */
	public Vector3f getTransformComponent() {
		return transformComponent;
	}

	/**
	 * @param transformComponent the transformComponent to set
	 */
	public void setTransformComponent(Vector3f transformComponent) {
		this.transformComponent = transformComponent;
	}

	/**
	 * @return the rotationComponent
	 */
	public Vector3f getRotationComponent() {
		return rotationComponent;
	}

	/**
	 * @param rotationComponent the rotationComponent to set
	 */
	public void setRotationComponent(Vector3f rotationComponent) {
		this.rotationComponent = rotationComponent;
	}

	/**
	 * @return the scalingComponent
	 */
	public Vector3f getScalingComponent() {
		return scalingComponent;
	}

	/**
	 * @param scalingComponent the scalingComponent to set
	 */
	public void setScalingComponent(Vector3f scalingComponent) {
		this.scalingComponent = scalingComponent;
	}

	/**
	 * @return the hasTransform
	 */
	public boolean isHasTransform() {
		return hasTransform;
	}

	/**
	 * @param hasTransform the hasTransform to set
	 */
	public void setHasTransform(boolean hasTransform) {
		this.hasTransform = hasTransform;
	}

	/**
	 * @return the hasRotation
	 */
	public boolean isHasRotation() {
		return hasRotation;
	}

	/**
	 * @param hasRotation the hasRotation to set
	 */
	public void setHasRotation(boolean hasRotation) {
		this.hasRotation = hasRotation;
	}

	/**
	 * @return the hasScaling
	 */
	public boolean isHasScaling() {
		return hasScaling;
	}

	/**
	 * @param hasScaling the hasScaling to set
	 */
	public void setHasScaling(boolean hasScaling) {
		this.hasScaling = hasScaling;
	}

	public void apply() {
		if(hasTransform) {
//			System.out.println("Applying transform");
			GlStateManager.translate(transformComponent.x, transformComponent.y, transformComponent.z);
		}
		
		if(hasRotation) {
//			System.out.println("Applying rotation");
			GlStateManager.rotate(rotationComponent.x, 1.0F, 0, 0);
			GlStateManager.rotate(rotationComponent.y, 0, 1.0F, 0);
			GlStateManager.rotate(rotationComponent.z, 0, 0, 1.0F);
		}
		
		if(hasScaling) {
//			System.out.println("Applying scaling");
			GlStateManager.scale(scalingComponent.x, scalingComponent.y, scalingComponent.z);
		}
	}
}
