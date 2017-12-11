package me.kk47.modeltrains.math;

import net.minecraft.item.ItemStack;

public class MathHelper {

	/**Uses 4 decimal places*/
	public static double chopDecimals(double roundable){
		double dec = getDecimalsOnly(roundable);
		double main = floorFloat(roundable);
		return dec+main;
	}

	public static int floorFloat(double f){
		int out = 0;
		double bf = f;
		while(bf >= 1){
			bf--;
			out++;
		}
		return out;
	}

	public static float getDecimalsOnly(double f){
		float ret = (float)f;
		while(ret>=1){
			ret-=1;
		}
		return ret;
	}

	/**Doesn't factor Yaw*/
	public static Position3F centerToTrack(Position3F p3f){
		double x1 = p3f.getX();
		int fx = 0;
		while(x1 >= 1){
			x1--;
			fx++;
		}
		double y1 = p3f.getY();
		int fy = 0;
		while(y1 >= 1){
			y1--;
			fy++;
		}
		return new Position3F(fx+0.5F, fy+0.5F, p3f.getYaw());
	}

	public static Object[][] rotateArray2d(Object[][] mat) {
		final int M = mat.length;
		final int N = mat[0].length;
		Object[][] ret = new Object[N][M];
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				ret[c][M-1-r] = mat[r][c];
			}
		}
		return ret;
	}

	public static ItemStack[][] rotateTrackInv(ItemStack[][] mat) {
		final int M = mat.length;
		final int N = mat[0].length;
		ItemStack[][] ret = new ItemStack[N][M];
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				ItemStack add = mat[r][c];
				ItemStack nextAdd = null;
				if(add != null){
//					System.out.println("Changing Item Meta from " + add.getItemDamage() + " to " + (add.getItemDamage()+1));
					add.setItemDamage(add.getItemDamage()+1);
					nextAdd = new ItemStack(add.getItem(), 1, add.getItemDamage());
					if(nextAdd.getItemDamage() > nextAdd.getMaxDamage()) {
						nextAdd = new ItemStack(add.getItem(), 1, 0);
					}
				}
				ret[c][M-1-r] = nextAdd;
			}
		}
		return ret;
	}
}
