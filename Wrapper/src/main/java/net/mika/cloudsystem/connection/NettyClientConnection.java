package net.mika.cloudsystem.connection;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import net.mika.cloudsystem.connection.handler.NettyClientHandler;

public class NettyClientConnection {

    private String host;
    private int port;

    private boolean epoll;

    private static ChannelFuture channelFuture;

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
            bootstrap.channel(epoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new NettyClientHandler());
                }
            });

            channelFuture = bootstrap.connect(host, port);

            channelFuture.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static ChannelFuture getChannelFuture() {
        return channelFuture;
    }
}
