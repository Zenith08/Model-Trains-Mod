package me.kk47.modeltrains.industry;

import java.util.ArrayList;
import java.util.List;

public abstract class Industry {

	protected List<ResourceConsumptionMap> resourceUses;
	
	public Industry() {
		resourceUses = new ArrayList<ResourceConsumptionMap>();
	}
	
	public ResourceConsumptionMap getResourceConsumptionInfoIn(String InResourcename){
		for(ResourceConsumptionMap r : resourceUses){
			if(r.getIn().getName().equalsIgnoreCase(InResourcename)){
				return r;
			}
		}
		return null;
	}

	public ResourceConsumptionMap getResourceConsumptionInfoOut(String OutResourcename){
		for(ResourceConsumptionMap r : resourceUses){
			if(r.getOut().getName().equalsIgnoreCase(OutResourcename)){
				return r;
			}
		}
		return null;
	}
	
	public List<ResourceConsumptionMap> getResourceUses(){
		return resourceUses;
	}
	
}
