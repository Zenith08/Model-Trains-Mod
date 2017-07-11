package me.kk47.modeltrains.math;

import net.minecraft.util.EnumFacing;

public final class TurnHelper {

	private static Position3F[] TURN_ARRAY;
	private static final float RADIUS = 0.5F;
	
	public static void initTurnArray(){
		TURN_ARRAY = new Position3F[101];
		for(int i = 0; i < 101; i++){
			float degs = i*0.9F;
			float adegs = (100-i)*0.9F;
			float rads = (float) Math.toRadians(degs);
			float arads = (float) Math.toRadians(adegs);
			float x = (float)(Math.sin(rads) * RADIUS);
			float y = (float)(Math.cos(arads) * RADIUS);
			TURN_ARRAY[i] = new Position3F(x, y, (float) (i*0.9));
		}
		
/*		System.out.println("Printing turn array -------------------------------------------");
		for(int i = 0; i < TURN_ARRAY.length; i++){
			System.out.println(i + ":" + TURN_ARRAY[i].toString());
		}
		System.out.println("Done turn array -----------------------------------------------");*/
	}
	
	public static Position3F getTurnPosition(int turnProgress, EnumFacing direction){
		float x = TURN_ARRAY[turnProgress].getX();
		float y = TURN_ARRAY[turnProgress].getY();
		float yaw = TURN_ARRAY[turnProgress].getYaw();
		
		if(direction == EnumFacing.NORTH){
//			yaw+=270;
//			y=-y;
		}else if(direction == EnumFacing.SOUTH){
			//Do nothing
		}else if(direction == EnumFacing.EAST){
			yaw+=270;
//			x=-x;
//			y=-y;
		}else if(direction == EnumFacing.WEST){
//			x=-x;
//			yaw+=90;
		}
		
		return new Position3F(x, y, yaw);
	}

}
