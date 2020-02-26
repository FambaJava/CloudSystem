package cloudsystem.connection.manager;


import cloudsystem.connection.NettyClientConnection;

public class ConnectionManager {


    private NettyClientConnection netty;
    private ServerConnections serverConnections;

    public String getHost() {
        return "localhost";
    }

    public void connect(ConnectionType connectionType, String host) {
        serverConnections = new ServerConnections();
        int port = serverConnections.getWrapper().getPort();

        netty = new NettyClientConnection(getHost(), 5004);
        netty.connect();
    }

    public void wakeTSUpAndRenewTheAdminKey() {
        netty.wakeTSUpAndRenewTheAdminKey();
    }


}
