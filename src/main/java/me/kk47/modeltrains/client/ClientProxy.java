package me.kk47.modeltrains.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import me.kk47.modeltrains.CommonProxy;
import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.blocks.ModBlocks;
import me.kk47.modeltrains.items.ModItems;

public class ClientProxy extends CommonProxy{

	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		ModBlocks.clientInit(e);
		
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(ModItems.colouredCar, ModItems.colouredCar);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(ModItems.colouredCaboose, ModItems.colouredCaboose);
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
		ModItems.clientPostInit(e);
	}

}
