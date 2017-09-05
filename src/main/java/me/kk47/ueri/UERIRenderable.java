package me.kk47.ueri;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import me.kk47.ueri.util.RenderTransform;

public abstract class UERIRenderable {

	private RenderTransform transform;
	private boolean isUsingColour;
	private Vector3f colourComponent;
	
	public UERIRenderable() {
		transform = RenderTransform.DEFAULT;
		isUsingColour = false;
		colourComponent = new Vector3f();
	}
	
	public UERIRenderable(RenderTransform transform, boolean isUsingColour, Vector3f colourComponent) {
		super();
		this.transform = transform;
		this.isUsingColour = isUsingColour;
		this.colourComponent = colourComponent;
	}

	public RenderTransform getTransform() {
		return transform;
	}

	public boolean isUsingColour() {
		return this.isUsingColour;
	}
	
	public Vector4f getColour() {
		return this.getColour();
	}
	
	public void render() {
//		System.out.println("UERI Render Method Called");
		transform.apply();
		if(isUsingColour()) {
			GL11.glColor3f(colourComponent.x, colourComponent.y, colourComponent.z);
		}
//		System.out.println("UERI GL Stuff Done. Calling RenderModel");
		renderModel();
	}
	
	//TODO This is going to need several arguments.
	//Will it?
	protected abstract void renderModel();
	
}
