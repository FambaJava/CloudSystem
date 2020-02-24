package cloudsystem.connection;

import cloudsystem.command.CommandManager;
import cloudsystem.connection.handler.NettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class NettyClientConnection {

    private String host;
    private int port;

    private boolean epoll;

    private CommandManager commandManager;

    private Channel channel;

    public NettyClientConnection(String host, int port) {
        this.host = host;
        this.port = port;
        this.epoll = Epoll.isAvailable();
        this.commandManager = new CommandManager();
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
            commandManager.start();
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
