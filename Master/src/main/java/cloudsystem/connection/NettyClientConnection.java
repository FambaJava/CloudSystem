package cloudsystem.connection;

import cloudsystem.command.CommandManager;
import cloudsystem.connection.handler.NettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.io.IOException;

public class NettyClientConnection {

    private String host;
    private int port;

    public boolean connected = false;


    private CommandManager commandManager;

    private Channel channel;

    public NettyClientConnection(String host, int port) {
        this.host = host;
        this.port = port;
        this.commandManager = new CommandManager();
    }

    public void connect() throws IOException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

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
            System.out.println("Master connected successfully to the Wrapper!");
            System.out.println();
            commandManager.start();
            System.out.println("You can use now the CommandSystem.");
            System.out.println();
            System.out.println("Cloud is successfully started.");
            System.out.println();
            connected = true;
            channelFuture.channel().closeFuture().sync();
            connected = false;
        } catch (Exception ex) {
            System.out.println("Wrapper is offline.");
            System.out.println();
            System.out.println("You can now use commands...");
            System.out.println();
            commandManager.start();
        } finally {
            workerGroup.shutdownGracefully();

        }
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void wakeTSUpAndRenewTheAdminKey() {
        try {
            channel.writeAndFlush(Unpooled.copiedBuffer("getAdminKey 11", CharsetUtil.UTF_8));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
