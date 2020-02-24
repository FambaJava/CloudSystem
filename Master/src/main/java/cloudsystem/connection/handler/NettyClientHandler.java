package cloudsystem.connection.handler;


import cloudsystem.datas.AdminKeyHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Connected to server.");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        StringBuilder stringBuilder = new StringBuilder();

        while (byteBuf.isReadable())
            stringBuilder.append((char)byteBuf.readByte());

        String message = stringBuilder.toString();
        if(message.startsWith("key:")){
            String key = message.split("")[1];
            System.out.println(key);
            AdminKeyHandler.setAdminKey(key);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
