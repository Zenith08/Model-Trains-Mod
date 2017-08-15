package me.kk47.modeltrains.math;

import net.minecraft.util.math.BlockPos;

public class Position3F {

	private float x;
	private float y;
	private float yaw;
	
	public Position3F() {
		this(0.0F, 0.0F, 0.0F);
	}

	public Position3F(float x, float y) {
		this(x, y, 0.0F);
	}

	public Position3F(float x, float y, float yaw) {
		this.x = x;
		this.y = y;
		this.yaw = yaw;
	}
	
	public Position3F clone(){
		return new Position3F(x, y, yaw);
	}
	
	@Override
	public String toString(){
		return "<Position3F {x = " + x + " y = " + y + " yaw = " + yaw + "}>";
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

}
