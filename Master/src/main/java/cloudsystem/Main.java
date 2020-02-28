package cloudsystem;


import cloudsystem.api.CloudClassLoader;
import cloudsystem.connection.manager.ConnectionManager;
import cloudsystem.connection.manager.ConnectionType;
import cloudsystem.database.SQLManager;
import cloudsystem.filestructure.FileManager;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;

public class Main {


    private static ConnectionManager connectionManager;

    private static SQLManager sqlManager;

    private static FileManager fileManager;

    private static CloudClassLoader cloudClassLoader;

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, JSONException, InterruptedException {
        start();        //With GUi new MasterGUI();     - In Coding...
    }

    public static void start() throws IOException, SQLException, ClassNotFoundException, JSONException {


        System.out.println(" ____    ____   ______    ______  __                         __  \n" +
                "|_   \\  /   _|.' ___  | .' ___  |[  |                       |  ] \n" +
                "  |   \\/   | / .'   \\_|/ .'   \\_| | |  .--.   __   _    .--.| |  \n" +
                "  | |\\  /| | | |       | |        | |/ .'`\\ \\[  | | | / /'`\\' |  \n" +
                " _| |_\\/_| |_\\ `.___.'\\\\ `.___.'\\ | || \\__. | | \\_/ |,| \\__/  |  \n" +
                "|_____||_____|`.____ .' `.____ .'[___]'.__.'  '.__.'_/ '.__.;__] \n" +
                "                                                                 ");
        System.out.println("\n\nCloud is loading...\n");

        fileManager = new FileManager();
        fileManager.createFilesIfNotExists();

        sqlManager = new SQLManager();

        connectionManager = new ConnectionManager();


        cloudClassLoader = new CloudClassLoader();


        getSqlManager().init();


        getSqlManager().openConnection();

        getSqlManager().setUpTeamSpeak("test1", "235.31.53.2", 2004, "asufb");
        getSqlManager().setUpTeamSpeak("test2", "45.2.6.12", 2511, "alskjfjaoisf4");
        getSqlManager().setUpTeamSpeak("test3", "33.34.63.21", 2621, "sdfsdgf3");

        getSqlManager().setUpMinecraft("Lobby-1", "235.32.52.214", 2356, "STATIC", 1024);
        getSqlManager().setUpMinecraft("Lobby-2", "35.23.62.156", 8326, "DYNAMIC", 2048);

        System.out.println("Remember when you are starting an Minecraft or TeamSpeak server,you automatically agree their terms.");

        connectionManager.connect(ConnectionType.Master, connectionManager.getHost());

    }

    public static ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public static SQLManager getSqlManager() {
        return sqlManager;
    }

    private static CloudClassLoader getCloudClassLoader() {
        return cloudClassLoader;
    }


    public static void stop() {
        try {
            getConnectionManager().getCommandManager().stop();
            getSqlManager().close();
        } catch (Exception ex) {
            System.out.println("There is no connection");
        }

    }
}
