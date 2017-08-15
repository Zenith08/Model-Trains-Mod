package me.kk47.modeltrains.industry;

import me.kk47.modeltrains.industry.MTResource;

public class ResourceConsumptionMap {

	protected MTResource in;
	protected int inCount;
	protected MTResource out;
	protected int outCount;
	
	public ResourceConsumptionMap(MTResource in, int inCount, MTResource out, int outCount) {
		this.in = in;
		this.inCount = inCount;
		this.out = out;
		this.outCount = outCount;
	}

	public MTResource getOut() {
		return out;
	}

	protected void setOut(MTResource out) {
		this.out = out;
	}

	public int getOutCount() {
		return outCount;
	}

	protected void setOutCount(int outCount) {
		this.outCount = outCount;
	}
	
	public MTResource getIn(){
		return in;
	}
	
	protected void setIn(MTResource in){
		this.in = in;
	}
	
	public int getInCount(){
		return inCount;
	}
	
	protected void setInCount(int inCount){
		this.inCount = inCount;
	}

}
