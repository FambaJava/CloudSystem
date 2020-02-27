package cloudsystem.connection.manager;


import cloudsystem.connection.NettyClientConnection;

public class ConnectionManager {


    private NettyClientConnection netty;
   // private ServerConnections serverConnections;

    public String getHost() {
        return "localhost";
    }

    public void connect(ConnectionType connectionType, String host) {
        //serverConnections = new ServerConnections();
        netty = new NettyClientConnection(getHost(), 5004);
        //int port = serverConnections.getWrapper().getPort();
        if(isNotConnected()){
            netty.connect();
        }
    }

    public void wakeTSUpAndRenewTheAdminKey() {
        netty.wakeTSUpAndRenewTheAdminKey();
    }

    private boolean isNotConnected(){
        return !netty.connected;
    }


}
