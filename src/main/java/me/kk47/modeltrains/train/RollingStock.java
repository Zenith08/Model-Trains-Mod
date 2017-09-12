package me.kk47.modeltrains.train;

import me.kk47.modeltrains.api.IItemTrain;
import me.kk47.modeltrains.api.IItemTrainLoadable;
import me.kk47.modeltrains.api.ITileEntityIndustry;
import me.kk47.modeltrains.api.ITileEntityTrainContainer;
import me.kk47.modeltrains.industry.MTResource;
import me.kk47.modeltrains.industry.MTResources;
import me.kk47.modeltrains.math.MathHelper;
import me.kk47.modeltrains.math.Position3F;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;

public class RollingStock implements ITickable {

	protected Position3F pos;
	protected float speed;
	protected ITileEntityTrainContainer te;
	protected int blockX = 0, blockY = 0;
	protected int lastBlockX = 0, lastBlockY = 0;
	
	protected IItemTrain item;

	protected boolean hasFactoredTurn = false;
	
	protected MTResource loadedResource;
	protected int loadedAmount;

	public RollingStock(Position3F position, ITileEntityTrainContainer tetc) {
		pos = position;
		speed = 0F;
		te = tetc;
		loadedResource = MTResources.air;
		loadedAmount =0;
	}
	
	public IItemTrain getTrainItem(){
		return item;
	}
	
	public void setTrainItem(IItemTrain it){
		this.item = it;
	}

	public boolean isGoingForwards(){
		if(speed >= 0)
			return true;
		return false;
	}

	@Override
	public void update() {
		//		System.out.println(pos);
		//		System.out.println(posOnTrack);

		//Start train position update
		//Make sure we are on a track
		if(te.isTrack(pos)){
			//			System.out.println("track");
			ItemStack is = te.getTrackAt(pos);
			//Straight track logic -------------------------------------------------------------------------
			if(is.getItem().getUnlocalizedName().contains("straight")){
				//Correct for facing the wrong direction ---------------------------------------------------
				if(is.getItem().getUnlocalizedName(is).contains("east")){
					//					System.out.println("on East Track and at pos " + pos.toString());
					if(pos.getYaw() != 90 && pos.getYaw() != 270){
						pos.setYaw(90);
						setPos(MathHelper.centerToTrack(pos));
					}
				}else if(is.getItem().getUnlocalizedName(is).contains("north")){
					//					System.out.println("on North Track and at pos " + pos.toString());
					if(pos.getYaw() != 0 && pos.getYaw() != 180){
						pos.setYaw(0);
						setPos(MathHelper.centerToTrack(pos));
					}
				}
				//End track correction logic ---------------------------------------------------------------
				//Start train moving logic -----------------------------------------------------------------
				if(pos.getYaw() == 0){
					pos.setY(pos.getY()-speed);
				}else if(pos.getYaw() == 90){
					pos.setX(pos.getX()-speed);
				}else if(pos.getYaw() == 180){
					pos.setY(pos.getY()+speed);
				}else if(pos.getYaw() == 270){
					pos.setX(pos.getX()+speed);
				}
			//End straight track logic ---------------------------------------------------------------------
			//Start turning logic --------------------------------------------------------------------------
			}else if(is.getItem().getUnlocalizedName().contains("corner")){
				if(pos.getYaw() == 0){
					pos.setY(pos.getY()-speed);
				}else if(pos.getYaw() == 90){
					pos.setX(pos.getX()-speed);
				}else if(pos.getYaw() == 180){
					pos.setY(pos.getY()+speed);
				}else if(pos.getYaw() == 270){
					pos.setX(pos.getX()+speed);
				}

				float testX = MathHelper.getDecimalsOnly(pos.getX());
				float testY = MathHelper.getDecimalsOnly(pos.getY());
				if(testX <= 0.55 && testX >= 0.45 && testY <=0.55 && testY >= 0.45){
					if(!hasFactoredTurn){
						String unLName = is.getItem().getUnlocalizedName(is);
						setPos(MathHelper.centerToTrack(pos));
						if(isGoingForwards()){
							if(unLName.contains("north")){
								if(pos.getYaw() == 270){
									pos.setYaw(180);
								}else if(pos.getYaw() == 0){
									pos.setYaw(90);
								}
							}else if(unLName.contains("south")){
								if(pos.getYaw() == 90){
									pos.setYaw(0);
								}else if(pos.getYaw() == 180){
									pos.setYaw(270);
								}
							}else if(unLName.contains("east")){
								if(pos.getYaw() == 270){
									pos.setYaw(0);
								}else if(pos.getYaw() == 180){
									pos.setYaw(90);
								}
							}else if(unLName.contains("west")){
								if(pos.getYaw() == 0){
									pos.setYaw(270);
								}else if(pos.getYaw() == 90){
									pos.setYaw(180);
								}
							}
						}else{ //is going backwards
							//Different instructions if we're going backwards
							if(unLName.contains("north")){
								if(pos.getYaw() == 180){
									pos.setYaw(270);
								}else if(pos.getYaw() == 90){
									pos.setYaw(0);
								}
							}else if(unLName.contains("south")){
								if(pos.getYaw() == 0){
									pos.setYaw(90);
								}else if(pos.getYaw() == 270){
									pos.setYaw(180);
								}
							}else if(unLName.contains("east")){
								if(pos.getYaw() == 0){
									pos.setYaw(270);
								}else if(pos.getYaw() == 90){
									pos.setYaw(180);
								}
							}else if(unLName.contains("west")){
								if(pos.getYaw() == 270){
									pos.setYaw(0);
								}else if(pos.getYaw() == 180){
									pos.setYaw(90);
								}
							}
						}
						hasFactoredTurn = true;
					}
				}else{
					hasFactoredTurn = false;
				}
			}
			
			//Start the industry logic ---------------------------------------------------------------------
//			System.out.println("Entering Industry logic");
			
			if(speed == 0 && this.item instanceof IItemTrainLoadable){
//				System.out.println("Loaded Resource is " + this.getLoadedResource().getName() + " amount is " + this.getLoadedAmount());
//				System.out.println("Speed is 0 and train is loadable");
				if(te.getTrackBedAt(pos) instanceof ITileEntityIndustry){
//					System.out.println("The block we are on is an industry");
					ITileEntityIndustry industry = (ITileEntityIndustry) te.getTrackBedAt(pos);
					industry.tryToLoad(this);
				}
			}
		}else{
			speed = 0;
		}
	}
	
	public MTResource getLoadedResource(){
		return loadedResource;
	}
	
	public int getLoadedAmount(){
		return loadedAmount;
	}
	
	public void load(MTResource resource, int amount){
		this.loadedResource = resource;
		this.loadedAmount += amount;
	}

	private void getBlockXY(){
		this.lastBlockX = blockX;
		this.lastBlockY = blockY;
		float bX = pos.getX();
		while(bX > 1){
			bX--;
			blockX++;
		}
		float bY = pos.getY();
		while(bY > 1){
			bY--;
			blockY++;
		}
	}

	public Position3F getPos() {
		return pos;
	}

	public void setPos(Position3F pos) {
		this.pos = pos;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
