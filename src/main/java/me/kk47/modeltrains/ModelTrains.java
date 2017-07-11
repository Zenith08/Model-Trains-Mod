package me.kk47.modeltrains;

import me.kk47.modeltrains.math.TurnHelper;
import me.kk47.modeltrains.network.PacketChangeTrainDirection;
import me.kk47.modeltrains.network.PacketChangeTrainSpeed;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Data.MODID, name = Data.MODNAME, version = Data.VERSION)
public class ModelTrains {

	@SidedProxy(clientSide=Data.CLIENT_PROXY, serverSide=Data.SERVER_PROXY)
	public static CommonProxy proxy;
	
    @Instance
    public static ModelTrains instance = new ModelTrains();
    
    public static SimpleNetworkWrapper packetHandler;
    
    public static final CreativeTabsModelTrains creativeTab = new CreativeTabsModelTrains();

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	//Initialize the turn array
//    	TurnHelper.initTurnArray();
    	
    	proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
    	proxy.init(e);
    	
    	packetHandler = NetworkRegistry.INSTANCE.newSimpleChannel(Data.MODID);
    	packetHandler.registerMessage(PacketChangeTrainSpeed.HandlePacketChangeTrainSpeed.class, PacketChangeTrainSpeed.class, 0, Side.SERVER);
    	packetHandler.registerMessage(PacketChangeTrainDirection.HandlePacketChangeTrainDirection.class, PacketChangeTrainDirection.class, 1, Side.SERVER);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	proxy.postInit(e);
    }
}