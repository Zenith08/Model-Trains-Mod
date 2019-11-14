package me.kk47.modeltrains.blocks;

import me.kk47.modeltrains.Data;
import me.kk47.modeltrains.client.render.Render3DPrinter;
import me.kk47.modeltrains.client.render.RenderIndustryForrest;
import me.kk47.modeltrains.client.render.RenderIndustryVillage;
import me.kk47.modeltrains.client.render.RenderTrackbed;
import me.kk47.modeltrains.client.render.RenderTrain;
import me.kk47.modeltrains.items.ItemBlockTrainController;
import me.kk47.modeltrains.tileentity.TileEntity3DPrinter;
import me.kk47.modeltrains.tileentity.TileEntityIndustryForrest;
import me.kk47.modeltrains.tileentity.TileEntityIndustryVillage;
import me.kk47.modeltrains.tileentity.TileEntityTrackBed;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

	public static BlockTrackBed trackBed;
	public static Item itemTrackBed;

	public static BlockTrainController trainController;
	public static ItemBlockTrainController itemTrainController;

	public static BlockIndustryForrest forrest;
	public static Item itemForrest;
	public static BlockIndustryVillage village;
	public static Item itemVillage;

	public static Block3DPrinter printer3d;
	public static Item itemPrinter3d;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event){
		trackBed = new BlockTrackBed();
		event.getRegistry().register(trackBed);	
		trainController = new BlockTrainController();
		event.getRegistry().register(trainController);

		forrest = new BlockIndustryForrest();
		event.getRegistry().register(forrest);
		village = new BlockIndustryVillage();
		event.getRegistry().register(village);

		printer3d = new Block3DPrinter();
		event.getRegistry().register(printer3d);
	}

	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event){
		itemTrackBed = new ItemBlock(trackBed).setRegistryName("trackbed");
		event.getRegistry().register(itemTrackBed);
		itemTrainController = new ItemBlockTrainController(trainController);
		event.getRegistry().register(itemTrainController);

		itemForrest = new ItemBlock(forrest).setRegistryName("forrest").setMaxStackSize(1);
		event.getRegistry().register(itemForrest);
		itemVillage = new ItemBlock(village).setRegistryName("village").setMaxStackSize(1);
		event.getRegistry().register(itemVillage);

		itemPrinter3d = new ItemBlock(printer3d).setRegistryName("printer-3d").setMaxStackSize(1);
		event.getRegistry().register(itemPrinter3d);
	}

	public static void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityTrackBed.class, new ResourceLocation(Data.MODID, "trackbed"));
		GameRegistry.registerTileEntity(TileEntityTrainController.class, new ResourceLocation(Data.MODID, "traincontroller"));
		GameRegistry.registerTileEntity(TileEntityIndustryForrest.class, new ResourceLocation(Data.MODID, "industry-forrest"));
		GameRegistry.registerTileEntity(TileEntityIndustryVillage.class, new ResourceLocation(Data.MODID, "industry-village"));
		GameRegistry.registerTileEntity(TileEntity3DPrinter.class, new ResourceLocation(Data.MODID, "printer-3d"));
	}

	@SideOnly(Side.CLIENT)
	public static void clientInit(FMLInitializationEvent e){
		//Trackbed
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(itemTrackBed, 0, new ModelResourceLocation(Data.MODID + ":trackbed", "inventory"));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrackBed.class, new RenderTrackbed());

		//Train Controller
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(itemTrainController, 0, new ModelResourceLocation(Data.MODID + ":traincontroller", "inventory"));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrainController.class, new RenderTrain());

		//Forrest
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIndustryForrest.class, new RenderIndustryForrest());
		//Village
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityIndustryVillage.class, new RenderIndustryVillage());

		//3D Printer
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(itemPrinter3d, 0, new ModelResourceLocation(Data.MODID + ":printer-3d", "inventory"));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntity3DPrinter.class, new Render3DPrinter());

		//Village
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(itemVillage, 0, new ModelResourceLocation(Data.MODID + ":village", "inventory"));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntity3DPrinter.class, new Render3DPrinter());

		//Forrest
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(itemForrest, 0, new ModelResourceLocation(Data.MODID + ":forrest", "inventory"));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntity3DPrinter.class, new Render3DPrinter());
	}
}
