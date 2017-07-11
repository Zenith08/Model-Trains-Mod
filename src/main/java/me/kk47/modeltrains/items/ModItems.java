package me.kk47.modeltrains.items;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.items.trains.ItemCaboose;
import me.kk47.modeltrains.items.trains.ItemLoggingCar;
import me.kk47.modeltrains.items.trains.ItemPasengerCarrage;
import me.kk47.modeltrains.items.trains.ItemToyTrain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModItems {

	public static ItemModelTrackStraight trackStraight;
	public static ItemModelTrackTurn trackCorner;
	public static ItemModelTrackCross trackCross;
	
	public static ItemToyTrain toyTrain;
//	public static ItemDummyTrain dummyTrain;
	
	public static ItemLoggingCar loggingCar;
	
	public static ItemPasengerCarrage[] basicPassengerCarrages = new ItemPasengerCarrage[12];
	
	public static ItemCaboose[] basicCabooses = new ItemCaboose[12];
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event){
		trackStraight = new ItemModelTrackStraight();
		event.getRegistry().register(trackStraight);
		trackCorner = new ItemModelTrackTurn();
		event.getRegistry().register(trackCorner);
		trackCross = new ItemModelTrackCross();
		event.getRegistry().register(trackCross);
		
		toyTrain = new ItemToyTrain();
		event.getRegistry().register(toyTrain);
		
		loggingCar=new ItemLoggingCar(4);
		event.getRegistry().register(loggingCar);
		
/*		dummyTrain = new ItemDummyTrain();
		event.getRegistry().register.register(dummyTrain);*/
		
		for(int i = 0; i < basicPassengerCarrages.length; i++){
			basicPassengerCarrages[i] = new ItemPasengerCarrage(i);
			event.getRegistry().register(basicPassengerCarrages[i]);
		}
		
		for(int i = 0; i < basicCabooses.length; i++){
			basicCabooses[i] = new ItemCaboose(i);
			event.getRegistry().register(basicCabooses[i]);
		}
	}
	
	public static void clientPostInit(FMLPostInitializationEvent e){
		RenderItem ri = Minecraft.getMinecraft().getRenderItem();
		trackStraight.registerVarients();
		ri.getItemModelMesher().register(trackStraight, 0, new ModelResourceLocation(Data.MODID + ":" + "trackStraightnorth", "inventory"));
		ri.getItemModelMesher().register(trackStraight, 1, new ModelResourceLocation(Data.MODID + ":" + "trackStraighteast", "inventory"));
	
		trackCorner.registerVarients();
		ri.getItemModelMesher().register(trackCorner, 0, new ModelResourceLocation(Data.MODID + ":" + "trackCornernorth", "inventory"));
		ri.getItemModelMesher().register(trackCorner, 1, new ModelResourceLocation(Data.MODID + ":" + "trackCornersouth", "inventory"));
		ri.getItemModelMesher().register(trackCorner, 2, new ModelResourceLocation(Data.MODID + ":" + "trackCornereast", "inventory"));
		ri.getItemModelMesher().register(trackCorner, 3, new ModelResourceLocation(Data.MODID + ":" + "trackCornerwest", "inventory"));
	
		ri.getItemModelMesher().register(trackCross, 0, new ModelResourceLocation(Data.MODID + ":" + "trackStraightCross", "inventory"));
		
		for(int i = 0; i < basicPassengerCarrages.length; i++){
			ri.getItemModelMesher().register(basicPassengerCarrages[i], 0, new ModelResourceLocation(Data.MODID + ":" + "pasengercar" + i, "inventory"));
		}
		
		for(int i = 0; i < basicCabooses.length; i++){
			ri.getItemModelMesher().register(basicCabooses[i], 0, new ModelResourceLocation(Data.MODID + ":" + "caboose" + i, "inventory"));
		}
		
		ri.getItemModelMesher().register(toyTrain, 0, new ModelResourceLocation(Data.MODID + ":" + "toyTrain", "inventory"));
	}
	
}
