package me.kk47.modeltrains.industry;

import net.minecraft.util.ResourceLocation;

public class MTResource {

	protected String name;
	protected ResourceLocation texture;
	/**This is expected to be the weight based on 100 units of a resource*/
	protected double weight;
	
	public MTResource(String name, ResourceLocation texture, double weight){
		this.name = name;
		this.texture = texture;
		this.weight = weight;
	}
	
	public MTResource(String name, ResourceLocation texture){
		this(name, texture, 1.0D);
	}
	
	protected void setWeight(float weight){
		this.weight = weight;
	}
	
	protected void setName(String name) {
		this.name = name;
	}

	protected void setTexture(ResourceLocation texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public ResourceLocation getTexture() {
		return texture;
	}
	
	public double getWeight(){
		return weight;
	}

}
