package me.kk47.ueri;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import me.kk47.ueri.proxy.CommonProxy;
import me.kk47.ueri.util.JsonModelLoadCallback;
import me.kk47.ueri.util.ObjModelLoadCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {

	private List<JsonModelLoadCallback> jsonCallbacks = new ArrayList<JsonModelLoadCallback>();
	private List<ObjModelLoadCallback> objCallbacks = new ArrayList<ObjModelLoadCallback>();
	
	private Function<ResourceLocation, TextureAtlasSprite> textureGetter = new Function<ResourceLocation, TextureAtlasSprite>(){
		public TextureAtlasSprite apply(ResourceLocation location){
			return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
		}
	};
	
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(this);
		OBJLoader.INSTANCE.addDomain("ueri");
	}

	@Override
	public void init(FMLInitializationEvent e) {
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		
	}
	
	@SubscribeEvent
	public void onModelBake(ModelBakeEvent mbe) {
//		System.out.println("Model Bake Event, Callback should happen");
		//    	IBakedModel ibm = mbe.getModelManager().getModel(new ModelResourceLocation(Data.MODID + ":" + "/block/trackbed.json"));
		//    	testJson = new UERIJson(ibm, new ResourceLocation(Data.MODID + ":/textures/blocks/trackbed.png"));
		for(JsonModelLoadCallback jmlc : jsonCallbacks) {
			jmlc.getUeriContainer().addModelCallback(mbe.getModelManager().getModel(jmlc.getModelLocation()));
		}

		for(ObjModelLoadCallback omlc : objCallbacks) {
			try {
				IModel model = OBJLoader.INSTANCE.loadModel(omlc.getModelLocation());
				IBakedModel bakedModel = model.bake(model.getDefaultState(), DefaultVertexFormats.BLOCK, textureGetter);
				omlc.getUeriContainer().addModelCallback(bakedModel);
			} catch (Exception e) {
				e.printStackTrace();
				omlc.getUeriContainer().addModelCallback(null);
			}
		}
	}

	public JsonModelLoadCallback addJsonModelCallback(UERIJson ueriContainer, ModelResourceLocation model) {
		return addJsonModelCallback(new JsonModelLoadCallback(model, ueriContainer));
	}

	public JsonModelLoadCallback addJsonModelCallback(JsonModelLoadCallback callback) {
		jsonCallbacks.add(callback);
		return callback;
	}

	public ObjModelLoadCallback addObjModelCallback(UERIObj ueriContainer, ModelResourceLocation model) {
		return addObjModelCallback(new ObjModelLoadCallback(model, ueriContainer));
	}

	public ObjModelLoadCallback addObjModelCallback(ObjModelLoadCallback callback) {
		objCallbacks.add(callback);
		return callback;
	}
}
