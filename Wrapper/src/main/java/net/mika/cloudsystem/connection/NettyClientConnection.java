package net.mika.cloudsystem.connection;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import net.mika.cloudsystem.Main;
import net.mika.cloudsystem.connection.handler.NettyClientHandler;

public class NettyClientConnection {

    private String host;
    private int port;

    private boolean epoll;


    private Channel channel;

    public NettyClientConnection(String host, int port) {
        this.host = host;
        this.port = port;
        this.epoll = Epoll.isAvailable();
    }

    public void connect() throws InterruptedException {
        EventLoopGroup workerGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyClientHandler());
                }
            });

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channel = channelFuture.channel();
            Main.getCommandManager().start();
            System.out.println("Client started");
            channelFuture.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }

    public boolean wakeTSUpAndRenewTheAdminKey(){
       try {
           channel.writeAndFlush(Unpooled.copiedBuffer("getAdminKey 11", CharsetUtil.UTF_8));
           return true;
       }catch (Exception ex){
           ex.printStackTrace();
           return false;
       }
    }
}
