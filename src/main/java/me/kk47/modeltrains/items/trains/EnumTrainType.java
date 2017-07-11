package me.kk47.modeltrains.items.trains;

public enum EnumTrainType {
	//Sounds will be added here later
	LOCOMOTIVE_STEAM(true),
	LOCOMOTIVE_ELECTRIC(true),
	LOCOMOTIVE_DIESEL(true),
	CARRAGE_FREIGHT(false),
	CARRIAGE_PASSENGER(false);
	
	private boolean isLocomotive;
	
	private EnumTrainType(boolean isLocomotive){
		this.isLocomotive = isLocomotive;
	}
	
	public boolean isLocomotive(){
		return isLocomotive;
	}
}
