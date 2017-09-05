package me.kk47.ueri;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = UERIModData.MODID, name = UERIModData.NAME, version = UERIModData.VERSION)
//At least for now UERI will ship with the Model Trains Mod
public class UERIMod {

	private static List<JsonModelLoadCallback> jsonCallbacks = new ArrayList<JsonModelLoadCallback>();
	private static List<ObjModelLoadCallback> objCallbacks = new ArrayList<ObjModelLoadCallback>();

	private static Function<ResourceLocation, TextureAtlasSprite> textureGetter = new Function<ResourceLocation, TextureAtlasSprite>(){
		public TextureAtlasSprite apply(ResourceLocation location){
			return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString());
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
//		System.out.println("PreInit");
		MinecraftForge.EVENT_BUS.register(this);
		OBJLoader.INSTANCE.addDomain("ueri");
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

	public static JsonModelLoadCallback addJsonModelCallback(UERIJson ueriContainer, ModelResourceLocation model) {
		return addJsonModelCallback(new JsonModelLoadCallback(model, ueriContainer));
	}

	public static JsonModelLoadCallback addJsonModelCallback(JsonModelLoadCallback callback) {
		jsonCallbacks.add(callback);
		return callback;
	}

	public static ObjModelLoadCallback addObjModelCallback(UERIObj ueriContainer, ModelResourceLocation model) {
		return addObjModelCallback(new ObjModelLoadCallback(model, ueriContainer));
	}

	public static ObjModelLoadCallback addObjModelCallback(ObjModelLoadCallback callback) {
		objCallbacks.add(callback);
		return callback;
	}
}
