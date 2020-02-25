package net.mika.cloudsystem.teamspeak.connection.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import net.mika.cloudsystem.teamspeak.Main;

public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(ctx + "connected");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        StringBuilder stringBuilder = new StringBuilder();

        while (byteBuf.isReadable())
            stringBuilder.append((char) byteBuf.readByte());

        String message = stringBuilder.toString();

        if (message.startsWith("getAdminKey")) {
            String number = message.split("")[12];
            ctx.writeAndFlush(Unpooled.copiedBuffer("key: " + Main.getTeamSpeakManager().getAdminKey(Integer.parseInt(number)), CharsetUtil.UTF_8));
        } else if (message.equalsIgnoreCase("startBot")) {
            Main.getTeamSpeakManager().connect();
        } else if (message.equalsIgnoreCase("stopBot")) {
            Main.getTeamSpeakManager().closeConnection();
        } else
            System.out.println(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
    }
}
