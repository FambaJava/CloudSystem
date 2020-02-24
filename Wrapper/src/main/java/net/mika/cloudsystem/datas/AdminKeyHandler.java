package net.mika.cloudsystem.datas;

import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import net.mika.cloudsystem.connection.NettyClientConnection;

public class AdminKeyHandler {

    private static String adminKey;

    public static String getAdminKey() throws InterruptedException {
        if(adminKey == null){
            NettyClientConnection.getChannelFuture().channel().writeAndFlush(Unpooled.copiedBuffer("getAdminKey", CharsetUtil.UTF_8));
            Thread.sleep(1000);
        }
        return adminKey;
    }

    public static void setAdminKey(String adminKey) {
        AdminKeyHandler.adminKey = adminKey;
    }
}
