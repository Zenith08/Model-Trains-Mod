package me.kk47.modeltrains.industry;

public class IndustryForrest extends Industry {

	public IndustryForrest() {
		super();
		this.resourceUses.add(new ResourceConsumptionMap(null, 0, MTResources.wood, 3));
	}

}
