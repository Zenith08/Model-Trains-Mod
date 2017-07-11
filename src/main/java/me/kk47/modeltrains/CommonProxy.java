package me.kk47.modeltrains;

import me.kk47.modeltrains.blocks.ModBlocks;
import me.kk47.modeltrains.gui.MTGuiHandler;
import me.kk47.modeltrains.items.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(ModBlocks.class);
		MinecraftForge.EVENT_BUS.register(ModItems.class);
    }

    public void init(FMLInitializationEvent e) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(ModelTrains.instance, new MTGuiHandler());
    	ModBlocks.registerTileEntities();
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
