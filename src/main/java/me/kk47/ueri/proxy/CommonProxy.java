package me.kk47.ueri.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class CommonProxy {

	public abstract void preInit(FMLPreInitializationEvent e);
	public abstract void init(FMLInitializationEvent e);
	public abstract void postInit(FMLPostInitializationEvent e);
	
}
