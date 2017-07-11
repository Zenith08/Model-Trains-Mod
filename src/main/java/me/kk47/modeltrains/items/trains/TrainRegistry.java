package me.kk47.modeltrains.items.trains;

import java.util.HashMap;
import java.util.Map;

import me.kk47.modeltrains.api.IItemTrain;

/**Every ItemTrain must be registered here or it will cause issues*/
public final class TrainRegistry {

	//Internal array for train registry
	private static Map<Integer, ItemTrain> trainRegistry = new HashMap<Integer, ItemTrain>();
	
	/**Gets the ItemTrain registered to the specific index
	 * @param index - The index of the train to get*/
	public static final IItemTrain getTrain(int index){
		return trainRegistry.get(index);
	}
	
	/**Registers a train to the mod. You must do this for each ItemTrain if you want it to work
	 * @param index - The index you want to register the train to
	 * @param train - The ItemTrain you want to register to the system
	 * @return True if the train was registered successfully but false if there is already a train with the same index*/
	public static final boolean registerTrain(ItemTrain train){
		if(!trainRegistry.containsKey(train.getTrainRegistryID())){
			trainRegistry.put(train.getTrainRegistryID(), train);
			return true;
		}else{
			System.err.println("Error Registering train " + train + " the index " + train.getTrainRegistryID() + " was already used");
			return false;
		}
	}
}
