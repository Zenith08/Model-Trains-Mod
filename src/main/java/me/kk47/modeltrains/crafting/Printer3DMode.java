package me.kk47.modeltrains.crafting;

public enum Printer3DMode {

	DISABLED(false), REQUIRED_RESOURCES(true), VARIABLE_COLOUR(true);

	private boolean printable;
	
	private Printer3DMode(boolean printable) {
		this.printable = printable;
	}
	
	public boolean isPrintable() {
		return printable;
	}
	
}
