package me.kk47.ueri;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import me.kk47.modeltrains.Data;
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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = UERIModData.MODID, name = UERIModData.NAME, version = UERIModData.VERSION)
//At least for now UERI will ship with the Model Trains Mod
//FIXME UERI Isn't guaranteed to work with servers!
public class UERIMod {
	
	@SidedProxy(clientSide="me.kk47.ueri.ClientProxy", serverSide="me.kk47.ueri.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	public static boolean isClientSided = false;
	public static ClientProxy cp;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
//		System.out.println("PreInit");
		proxy.preInit(e);
		
		if(proxy instanceof ClientProxy) {
			isClientSided = true;
			cp = (ClientProxy) proxy;
			cp.preInit(e);
		} else {
			isClientSided = false;
		}
	}
	
	public static JsonModelLoadCallback addJsonModelCallback(UERIJson ueriContainer, ModelResourceLocation model) {
		if(isClientSided) {
			return cp.addJsonModelCallback(new JsonModelLoadCallback(model, ueriContainer));
		}else {
			return null;
		}
	}

	public static JsonModelLoadCallback addJsonModelCallback(JsonModelLoadCallback callback) {
		if(isClientSided) {
			return cp.addJsonModelCallback(callback);
		}else {
			return null;
		}
	}

	public static ObjModelLoadCallback addObjModelCallback(UERIObj ueriContainer, ModelResourceLocation model) {
		if(isClientSided) {
			return cp.addObjModelCallback(new ObjModelLoadCallback(model, ueriContainer));
		}else {
			return null;
		}
	}

	public static ObjModelLoadCallback addObjModelCallback(ObjModelLoadCallback callback) {
		if(isClientSided) {
			return cp.addObjModelCallback(callback);
		}else {
			return null;
		}
	}
}
