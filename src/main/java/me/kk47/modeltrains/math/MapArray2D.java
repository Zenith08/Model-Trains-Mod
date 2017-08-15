package me.kk47.modeltrains.math;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.math.BlockPos;

public class MapArray2D {

	private Map<String, BlockPos> data;
	
	public MapArray2D(){
		data = new HashMap<String, BlockPos>();
	}
	
	public void set(int x, int y, BlockPos o){
		data.put(convertToString(x, y), o);
	}
	
	public boolean contains(int x, int y){
		return data.containsKey(convertToString(x, y));
	}
	
	public BlockPos get(int x, int y){
		if(contains(x, y))
			return data.get(convertToString(x, y));
		return null;
	}
	
	public String convertToString(int x, int y){
		return x + "," + y;
	}
	
	protected Map<String, BlockPos> getData(){
		return data;
	}
	
}
