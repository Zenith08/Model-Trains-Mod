package me.kk47.modeltrains.math;

/**Represents an integer with "wrap around" ends.
 * For instance if the max value was 100 then -5 = 95*/
public class WraparoundInt{

	private int value;

	private final int minValue;
	private final int maxValue;
	
	/**Creates a wraparound int with a min and max value and sets it to a starting value
	 * @param minValue - The minimum value of the number
	 * @param maxValue - The maximum value of the number
	 * @param startingValue - The value the number will be equal to*/
	public WraparoundInt(int minValue, int maxValue, int startingValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
		set(value);
	}

	/**Creates a wraparound int with a min and max value and sets it to a value of 0
	 * @param minValue - The minimum value of the number
	 * @param maxValue - The maximum value of the number*/
	public WraparoundInt(int minValue, int maxValue) {
		this(minValue, maxValue, 0);
	}
	
	/**Gets the minimum value the number can be
	 * @return The minimum value the number can be*/
	public int minValue(){
		return minValue;
	}
	
	/**Gets the maximum value the number can be
	 * @return The maximum value the number can be*/
	public int maxValue(){
		return maxValue;
	}
	
	@Override
	public String toString(){
		return "<WraparoundInt {value = " + value + " Min Value = " + minValue + " Max Value = " + maxValue + "}>";
	}

	/**Increases the number by an amount and applies wraparound
	 * @param amount - The amount to increase the number by*/
	public void increaseBy(int amount){
		set(value+amount);
	}

	/**Decreases the number by an amount and applies wraparound
	 * @param amount - The amount to decrease the number by*/
	public void decreaseBy(int amount){
		set(value-amount);
	}

	/**Multiplies the number by an amount and applies wraparound
	 * @param amount - The amount to multiply the number by*/
	public void multiplyBy(int amount){
		set(value*amount);
	}

	/**Divides the number by an amount and applies wraparound
	 * @param amount - The amount to divide the number by*/
	public void divideBy(int amount){
		set(value/amount);
	}

	/**Sets the number by an amount and applies wraparound
	 * @param newValue - the new value the number will be set to*/
	public void set(int newValue){
		if(newValue > maxValue){
			newValue-=maxValue;
		}else if(newValue < minValue){
			newValue+=maxValue;
		}
		value = newValue;
	}

	/**Gets the value that the number is currently at
	 * @return The value the number is currently at*/
	public int value(){
		return value;
	}

}
