package me.kk47.modeltrains.network;

import io.netty.buffer.ByteBuf;
import me.kk47.modeltrains.tileentity.TileEntity3DPrinter;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketPrintTrain implements IMessage{

	public int trainRegistryID;
	public BlockPos pos;
	
	public PacketPrintTrain() {
		trainRegistryID = 0;
		pos = new BlockPos(0, 0, 0);
	}

	public PacketPrintTrain(int trainRegistryID, BlockPos pos) {
		super();
		this.trainRegistryID = trainRegistryID;
		this.pos = pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		trainRegistryID = buf.readInt();
		int x = buf.readInt();
		int y = buf.readInt();
		int z = buf.readInt();
		pos = new BlockPos(x, y, z);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(trainRegistryID);
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
	}
	
	public static class HandlePacketPrintTrain implements IMessageHandler<PacketPrintTrain, IMessage>{
		@Override
		public IMessage onMessage(PacketPrintTrain message, MessageContext ctx){
			TileEntity3DPrinter t3d = (TileEntity3DPrinter)ctx.getServerHandler().player.world.getTileEntity(message.pos); // or Minecraft.getMinecraft() on the client
			t3d.handlePrintPacket(message);
			return null;
		}
		
	}
}
