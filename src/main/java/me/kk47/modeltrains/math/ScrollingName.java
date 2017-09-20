package me.kk47.modeltrains.math;

public class ScrollingName {

	private final String name;
	private final int maxChars;
	
	private int currentState;
	
	public ScrollingName(String name, int maxChars) {
		this.name = name;
		this.maxChars = maxChars;
		
		currentState = 0;
	}
	
	public String getCurrent() {
		int endIndex = currentState+maxChars-1;
		if(name.length() <= currentState+maxChars) {
			endIndex = name.length()-1;
		}
		
		if(currentState == name.length()) {
			currentState = 0;
		}
		
		return name.substring(currentState, endIndex);
	}
	
	public String getNext() {
		currentState++;
		return getCurrent();
	}
	
}
