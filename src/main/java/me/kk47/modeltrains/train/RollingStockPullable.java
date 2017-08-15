package me.kk47.modeltrains.train;

import me.kk47.modeltrains.api.ITileEntityTrainContainer;
import me.kk47.modeltrains.math.MathHelper;
import me.kk47.modeltrains.math.Position3F;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;

public class RollingStockPullable extends RollingStock {

	public static final double VARIANCE = 0.1;

	protected RollingStock cartAhead;

	public RollingStockPullable(Position3F position, ITileEntityTrainContainer tetc) {
		super(position, tetc);
	}

	@Override
	public void update() {
		//TODO this only works when we are going forwards!
		//Set the speed based on coupling status
		//The first thing we need to do is get how far apart from our forward cart we are
		double distance=0;
		//If either of these were true then both carriages are on a straight track
		/*		if(MathHelper.chopDecimals(this.pos.getX()) == MathHelper.chopDecimals(cartAhead.pos.getX())){
			if(this.pos.getY() > cartAhead.pos.getY()){
				distance = this.pos.getY()-cartAhead.pos.getY();
			}else{
				distance = cartAhead.pos.getY()-this.pos.getY();
			}
		}else if(MathHelper.chopDecimals(this.pos.getY()) == MathHelper.chopDecimals(cartAhead.pos.getY())){
			if(this.pos.getX() > cartAhead.pos.getX()){
				distance = this.pos.getX()-cartAhead.pos.getX();
			}else{
				distance = cartAhead.pos.getX()-this.pos.getX();
			}
		}*/

		double xDistance = this.pos.getX()-cartAhead.pos.getX();
		double yDistance = this.pos.getY()-cartAhead.pos.getY();

		distance = Math.sqrt(((xDistance*xDistance) + (yDistance*yDistance)));

		//If both of the above statements are false we are turning
		//TODO - We have a problem on turns

		//The actual logic
		//This will update our speed and then factor it in the next tick
		boolean frontTrainMoving=false;
		if(cartAhead.getSpeed()>0){
			frontTrainMoving = true;
		}
		
/*		if(this.isGoingForwards()){
			if(distance == getOptimalDistance()+VARIANCE){
				speed=cartAhead.speed;
			}else if(distance > getOptimalDistance()+VARIANCE){
				speed=cartAhead.speed+0.01F;
			}else if(distance < getOptimalDistance()-VARIANCE){
				speed=cartAhead.speed-0.01F;
			}
		}else{
			//Obviously we are going backwards!
		}*/

		//Now that we have distance and frontTrainMoving we can do our speed math
				if(frontTrainMoving && distance >= getOptimalDistance()+VARIANCE){
			speed = cartAhead.getSpeed();
		}else if(frontTrainMoving && distance <= getOptimalDistance()-VARIANCE && cartAhead.speed < this.speed){
			speed--;
		}else if(!frontTrainMoving && distance >= getOptimalDistance() + VARIANCE){
			//Do nothing
		}else if(!frontTrainMoving && distance <= getOptimalDistance() - VARIANCE){
			this.setSpeed(0);
		}

		//Then do normal logic
		super.update();
	}

	public double getOptimalDistance(){
		float thisSize = 0.6F;
		float aheadSize = 0.6F;
		if(this.item!=null){
			thisSize = item.getSize();
		}
		if(cartAhead.item!=null){
			aheadSize = cartAhead.item.getSize();
		}
		return thisSize+aheadSize;
	}

	public RollingStock getCartAhead() {
		return cartAhead;
	}

	public void setCartAhead(RollingStock cartAhead) {
		this.cartAhead = cartAhead;
	}
}
