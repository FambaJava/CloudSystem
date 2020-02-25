package net.mika.cloudsystem.wrapper;

import net.mika.cloudsystem.wrapper.connection.netty.NettyWrapperServer;

public class Main {

    private static NettyWrapperServer nettyWrapperServer;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Try to start Server...");
        nettyWrapperServer = new NettyWrapperServer(5004);
        nettyWrapperServer.run();
    }

}
