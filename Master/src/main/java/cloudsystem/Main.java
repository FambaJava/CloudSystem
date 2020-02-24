package cloudsystem;


import cloudsystem.connection.manager.ConnectionManager;
import cloudsystem.connection.manager.ConnectionType;

public class Main {


    private static ConnectionManager connectionManager;

    public static void main(String[] args) {

        connectionManager = new ConnectionManager();

        connectionManager.init();

        connectionManager.connect(ConnectionType.TeamSpeak, connectionManager.getHost());

    }

    public static ConnectionManager getConnectionManager() {
        return connectionManager;
    }

}
