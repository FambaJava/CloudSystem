package cloudsystem.setup;

import cloudsystem.Main;
import cloudsystem.utils.CloudJsonAPI;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class SetUpManager {

    private BufferedReader reader;

    private String dbHost;
    private int dbPort;
    private String dbUsername;
    private String dbPassword;

    private CloudJsonAPI cloudJsonAPI;


    public SetUpManager() {
        dbHost = "127.0.0.1";
        dbPort = 8082;
        dbUsername = "sa";
        dbPassword = "";
        cloudJsonAPI = new CloudJsonAPI();
        reader = new BufferedReader(new InputStreamReader(System.in));

    }

    public boolean start() throws IOException, JSONException, SQLException {

        if (cloudJsonAPI.isFinished()) {
            return false;
        }

        System.out.println("\n\nDefault settings: \n" +
                "Address: " + dbHost + "\n" +
                "Port: " + dbPort + "\n" +
                "Username: " + dbUsername + "\n" +
                "Password: " + dbPassword + "\n\n");


        System.out.println("Please type in your database IP-Address.");
        System.out.print("> ");
        dbHost = reader.readLine();

        System.out.println("Please type in your database Port:");
        System.out.print("> ");
        try {
            dbPort = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException ex) {
            System.out.println("You have to type in a Number!");
            System.out.println("You have to restart the cloud.");
            Main.stop();
        }

        System.out.println("Please type in your database Username.");
        System.out.print("> ");
        dbUsername = reader.readLine();

        System.out.println("Please type in your database Password.");
        System.out.print("> ");
        dbPassword = reader.readLine();

        close();

        return true;
    }

    private void close() throws JSONException {
        cloudJsonAPI.setUser(dbUsername);
        cloudJsonAPI.setPassword(dbPassword);
        cloudJsonAPI.setHost(dbHost);
        cloudJsonAPI.setPort(dbPort);
        cloudJsonAPI.end();
    }


}
