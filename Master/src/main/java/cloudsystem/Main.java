package cloudsystem;


import cloudsystem.connection.manager.ConnectionManager;
import cloudsystem.connection.manager.ConnectionType;
import cloudsystem.database.SQLManager;

public class Main {


    private static ConnectionManager connectionManager;

    private static SQLManager sqlManager;

    public static void main(String[] args) {

        connectionManager = new ConnectionManager();

        connectionManager.connect(ConnectionType.Master, connectionManager.getHost());

    }

    public static ConnectionManager getConnectionManager() {
        return connectionManager;
    }


}
