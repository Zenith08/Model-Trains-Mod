package me.kk47.modeltrains.api;

import me.kk47.modeltrains.math.Position3F;
import me.kk47.modeltrains.train.RollingStock;
import net.minecraft.item.ItemStack;

public interface ITileEntityTrainContainer {

	int getDirection();

	boolean getDirectionValue();

	float getSpeed();

	byte getSpeedValue();

	void setSpeed(byte speed);

	RollingStock[] getTrains();

	boolean isTrack(Position3F pos);

	boolean isTrackBlock(Position3F pos);

	ITileEntityTrackContainer getTrackBedAt(Position3F pos);

	ItemStack getTrackAt(Position3F pos);

	boolean hasValidLoco();

}