package me.kk47.modeltrains.network;

import io.netty.buffer.ByteBuf;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketChangeTrainDirection implements IMessage{

	private boolean forwards;
	private BlockPos pos;

	public PacketChangeTrainDirection() {
		forwards = true;
		pos = new BlockPos(0, 0, 0);
	}

	public PacketChangeTrainDirection(boolean forwards, BlockPos pos) {
		this.forwards = forwards;
		this.pos = pos;
	}

	public boolean getNewDirection() {
		return forwards;
	}

	public BlockPos getPos() {
		return pos;
	}

	@Override
	public String toString(){
		return "PacketChangeTrainDirection {" + pos.toString() + ", direction " + forwards + "}";
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		forwards = buf.readBoolean();
		int x = buf.readInt();
		int y = buf.readInt();
		int z = buf.readInt();
		pos = new BlockPos(x, y, z);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(forwards);
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
	}
	
	public static class HandlePacketChangeTrainDirection implements IMessageHandler<PacketChangeTrainDirection, IMessage>{
		@Override
		public IMessage onMessage(PacketChangeTrainDirection message, MessageContext ctx){
			TileEntityTrainController tc = (TileEntityTrainController)ctx.getServerHandler().player.world.getTileEntity(message.pos); // or Minecraft.getMinecraft() on the client
			tc.handleTrainDirectionPacket(message);
			return null;
		}
		
	}

}
