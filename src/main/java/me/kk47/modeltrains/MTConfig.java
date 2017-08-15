package me.kk47.modeltrains;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public final class MTConfig {

	public static final String TAG_TRACKBED_UPDATE_RATE = "trackbed-update-rate";
	public static final String CATAGORY_BLOCKS = "blocks";
	
	public static byte TRACKBED_UPDATE_RATE = 5;
	public static byte TRAIN_CONTROLLER_OFFSET = 32;
	
	public static final void loadConfig(FMLPreInitializationEvent e){
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		config.load();
		
		//Custom Stuff [...]
		TRACKBED_UPDATE_RATE = (byte)config.getInt(TAG_TRACKBED_UPDATE_RATE, CATAGORY_BLOCKS, 5, 1, 20, "How often the Trackbed Syncs client and server data **Does affect local worlds**");
		
		config.save();
	}
}
