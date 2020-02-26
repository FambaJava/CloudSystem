package cloudsystem;


import cloudsystem.api.CloudClassLoader;
import cloudsystem.connection.manager.ConnectionManager;
import cloudsystem.connection.manager.ConnectionType;
import cloudsystem.database.SQLManager;
import cloudsystem.filestructure.FileManager;

import java.sql.SQLException;

public class Main {


    private static ConnectionManager connectionManager;

    private static SQLManager sqlManager;

    private static FileManager fileManager;

    private static CloudClassLoader cloudClassLoader;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        sqlManager = new SQLManager();

        connectionManager = new ConnectionManager();

        fileManager = new FileManager();

        cloudClassLoader = new CloudClassLoader();

        fileManager.createFilesIfNotExists();

        cloudClassLoader.loadAllClazzes();

        getSqlManager().openConnection();

        getSqlManager().setUpTeamSpeak("test1", "235.31.53.2", 2004, "asufb");
        getSqlManager().setUpTeamSpeak("test2", "45.2.6.12", 2511, "alskjfjaoisf4");
        getSqlManager().setUpTeamSpeak("test3", "33.34.63.21", 2621, "sdfsdgf3");


        connectionManager.connect(ConnectionType.Master, connectionManager.getHost());

    }

    public static ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public static SQLManager getSqlManager() {
        return sqlManager;
    }

    public static CloudClassLoader getCloudClassLoader() {
        return cloudClassLoader;
    }
}
