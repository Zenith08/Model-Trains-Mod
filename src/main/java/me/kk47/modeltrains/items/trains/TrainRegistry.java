package me.kk47.modeltrains.items.trains;

import java.util.HashMap;
import java.util.Map;

import me.kk47.modeltrains.api.IItemTrain;

//TODO Make a Minecraft Forge Registry System
/**Every ItemTrain must be registered here or it will cause issues*/
public final class TrainRegistry {

	//Internal array for train registry
	private static Map<Integer, TrainRegistryEntry> trainRegistry = new HashMap<Integer, TrainRegistryEntry>();

	/**Gets the ItemTrain registered to the specific index
	 * @param index - The index of the train to get*/
	public static final IItemTrain getTrain(int index){
//		System.out.println("Getting Train at Registry index " + index);
		if(trainRegistry.containsKey(index)) {
			return trainRegistry.get(index).getTrain();
		}else {
			return trainRegistry.get(1).getTrain();
		}
	}

	/**Registers a train to the mod. You must do this for each ItemTrain if you want it to work
	 * @param index - The index you want to register the train to
	 * @param train - The ItemTrain you want to register to the system
	 * @return True if the train was registered successfully but false if there is already a train with the same index*/
	public static final boolean registerTrain(IItemTrain train){
		if(!trainRegistry.containsKey(train.getTrainRegistryID())){
			trainRegistry.put(train.getTrainRegistryID(), new TrainRegistryEntry(train, train.getTrainRegistryID()));
			return true;
		}else{
			System.err.println("Error Registering train " + train + " the index " + train.getTrainRegistryID() + " was already used");
			int sub = 0;
			while(trainRegistry.containsKey(Integer.MAX_VALUE-sub)) {
				sub++;
			}
			trainRegistry.put(Integer.MAX_VALUE-sub, new TrainRegistryEntry(train, Integer.MAX_VALUE-sub));
			System.err.println("Used Error Handling to register train " + train + " to the registry at index " + (Integer.MAX_VALUE-sub) + ".");
			return false;
		}
	}

	public static final TrainRegistryEntry[] getAllTrains() {
		Object[] trains = trainRegistry.values().toArray();
		TrainRegistryEntry[] iitrains = new TrainRegistryEntry[trains.length];
		for(int i = 0; i < trains.length; i++) {
			iitrains[i] = (TrainRegistryEntry) trains[i];
		}
		return iitrains;
	}
}
