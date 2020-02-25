package cloudsystem;


import cloudsystem.connection.manager.ConnectionManager;
import cloudsystem.connection.manager.ConnectionType;
import cloudsystem.database.SQLManager;

import java.sql.SQLException;

public class Main {


    private static ConnectionManager connectionManager;

    private static SQLManager sqlManager;

    public static void main(String[] args) throws SQLException {

        sqlManager = new SQLManager();

        connectionManager = new ConnectionManager();

        sqlManager.openConnection();

        getSqlManager().setUpTeamSpeak("test1", 1, "235.31.53.2", 2004, "asufb");
        getSqlManager().setUpTeamSpeak("test2", 2, "45.2.6.12", 2511, "alskjfjaoisf4");

        connectionManager.connect(ConnectionType.Master, connectionManager.getHost());

    }

    public static ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public static SQLManager getSqlManager() {
        return sqlManager;
    }
}
