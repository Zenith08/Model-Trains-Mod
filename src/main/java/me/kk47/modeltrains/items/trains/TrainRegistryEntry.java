package me.kk47.modeltrains.items.trains;

import me.kk47.modeltrains.api.IItemTrain;

/**Shows the entry of a train in the train registry allowing two way mapping between id and train if necessary.*/
public class TrainRegistryEntry {

	private IItemTrain train;
	private final int registeredID;
	
	/**Creates a train registry entry with the actual id of the train registered.
	 * @param itrain - The Train registered.
	 * @param trainID - The ID the train is actually registered to.*/
	public TrainRegistryEntry(IItemTrain itrain, int trainID) {
		this.train = itrain;
		this.registeredID = trainID;
	}

	/**
	 * @return The Train registered.
	 */
	public IItemTrain getTrain() {
		return train;
	}

	/**
	 * @return The Registry ID actually used.
	 */
	public int getRegisteredID() {
		return registeredID;
	}

}
