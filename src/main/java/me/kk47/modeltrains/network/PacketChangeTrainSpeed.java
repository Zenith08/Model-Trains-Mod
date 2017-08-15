package me.kk47.modeltrains.network;

import io.netty.buffer.ByteBuf;
import me.kk47.modeltrains.tileentity.TileEntityTrainController;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketChangeTrainSpeed implements IMessage{

	private byte newSpeed;
	private BlockPos pos;

	public PacketChangeTrainSpeed() {
		newSpeed = 0;
		pos = new BlockPos(0, 0, 0);
	}

	public PacketChangeTrainSpeed(byte newSpeed, BlockPos pos) {
		this.newSpeed = newSpeed;
		this.pos = pos;
	}

	public byte getNewSpeed() {
		return newSpeed;
	}

	public BlockPos getPos() {
		return pos;
	}

	@Override
	public String toString(){
		return "PacketChangeTrainSpeed {" + pos.toString() + ", speed " + newSpeed + "}";
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		newSpeed = buf.readByte();
		int x = buf.readInt();
		int y = buf.readInt();
		int z = buf.readInt();
		pos = new BlockPos(x, y, z);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(newSpeed);
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
	}

	public static class HandlePacketChangeTrainSpeed implements IMessageHandler<PacketChangeTrainSpeed, IMessage>{
		@Override
		public IMessage onMessage(PacketChangeTrainSpeed message, MessageContext ctx){
//			System.out.println("On Message");
			TileEntityTrainController tc = (TileEntityTrainController)ctx.getServerHandler().player.world.getTileEntity(message.pos); // or Minecraft.getMinecraft() on the client
			tc.handleTrainSpeedPacket(message);
			return null;
		}
		
	}
}
