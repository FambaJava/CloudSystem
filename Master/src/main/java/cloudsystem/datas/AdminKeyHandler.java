package cloudsystem.datas;

import net.mika.cloudsystem.Main;

public class AdminKeyHandler {

    private static String adminKey;

    public static String getAdminKey() throws InterruptedException {
        if(adminKey == null){
            //NettyClientConnection.getChannelFuture().channel().writeAndFlush(Unpooled.copiedBuffer("getAdminKey 1", CharsetUtil.UTF_8));
            Main.getConnectionManager().wakeTSUpAndRenewTheAdminKey();
            Thread.sleep(1000);
        }
        return adminKey;
    }

    public static void setAdminKey(String adminKey) {
        AdminKeyHandler.adminKey = adminKey;
    }
}
