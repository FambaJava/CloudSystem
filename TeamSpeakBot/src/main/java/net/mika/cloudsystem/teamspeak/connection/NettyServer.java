package net.mika.cloudsystem.teamspeak.connection;

import io.netty.bootstrap.ServerBootstrap;
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
import net.mika.cloudsystem.teamspeak.connection.handler.ServerHandler;

public class NettyServer {

    private int port;

    private boolean epoll;


    public NettyServer(int port) {
        this.port = port;
        this.epoll = Epoll.isAvailable();
    }

    public void connect() throws InterruptedException {
        EventLoopGroup bossGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        EventLoopGroup workerGroup = epoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(epoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 120)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            //TODO make smtnk
            System.out.println("Server started");
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
