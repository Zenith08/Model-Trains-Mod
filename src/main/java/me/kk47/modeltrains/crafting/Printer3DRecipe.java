package me.kk47.modeltrains.crafting;

/**Defines a Recipe for the 3D Printer to use with a clay requirement, and a red, green and blue dye requirement.
 * This can be the same for multiple trains with no risk of conflict*/
public class Printer3DRecipe {

	protected byte clay;
	protected byte red;
	protected byte green;
	protected byte blue;
	
	/**Creates a new recipe requiring 1 clay, and 1 red, green, and blue dye*/
	public Printer3DRecipe() {
		this((byte)1, (byte)1, (byte)1, (byte)1);
	}
	
	public Printer3DRecipe(int c, int r, int g, int b) {
		this((byte)c, (byte)r, (byte)g, (byte)b);
	}

	/**Creates a new recipe using a provided clay, red, green, and blue requirements.
	 * @param Clay - The amount of Clay needed.
	 * @param Red - The amount of Rose Red needed
	 * @param Green - The amount of Cactus Green needed
	 * @param Blue - the amount of Lapis Lazuli needed*/
	public Printer3DRecipe(byte clay, byte red, byte green, byte blue) {
		this.clay = clay;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**Creates a new recipe using a provided clay requirements and 1 red, green, and blue dye.
	 * @param Clay - The amount of Clay needed.*/
	public Printer3DRecipe(byte clay) {
		this(clay, (byte)1, (byte)1, (byte)1);
	}
	
	/**Creates a new recipe using a provided red, green, and blue requirements, and one Clay.
	 * @param Red - The amount of Rose Red needed
	 * @param Green - The amount of Cactus Green needed
	 * @param Blue - the amount of Lapis Lazuli needed*/
	public Printer3DRecipe(byte r, byte g, byte b) {
		this((byte)1, r, g, b);
	}

	/**
	 * @return The Clay requirement
	 */
	public byte getClay() {
		return clay;
	}

	/**
	 * @return The Rose Red requirement
	 */
	public byte getRed() {
		return red;
	}

	/**
	 * @return The Cactus Green Requirement
	 */
	public byte getGreen() {
		return green;
	}

	/**
	 * @return The Lapis Lazuli Requirement
	 */
	public byte getBlue() {
		return blue;
	}
	
}
