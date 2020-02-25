package cloudsystem.database;

import cloudsystem.model.TeamSpeakServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLManager {

    private String  user, password;

    private Connection connection;

    public SQLManager() {
        this.user = "sa";
        this.password = "";
    }

    @lombok.SneakyThrows
    public void openConnection(){
        Class.forName("org.h2.Driver");
        System.out.println("Connecting to DB...");
        connection = DriverManager.getConnection("jdbc:h2:~/test", user, password);
        System.out.println("Connected");
        Statement tableStatement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS SERVER(\n" + "Type VARCHAR(100), \n" + "Name VARCHAR(100), \n" + "Id int(100), \n" + "IpAdress VARCHAR(100), \n" + "Port INT(100), \n" + "Password VARCHAR(100)\n" + ");";
        tableStatement.executeUpdate(sql);
        System.out.println("Created table");
    }

    public boolean setUpTeamSpeak(String name, int id, String ipAdress, int port, String password) throws SQLException {
        try{
            Statement tableStatement = connection.createStatement();
            String sql = "INSERT INTO SERVER (Type, Name, Id, IpAdress, Port, Password) \n" +
                    "VALUES (TeamSpeak, " + name + ", " + id + ", " + ipAdress + ", " + password + ", " + password + ";";

            tableStatement.executeUpdate(sql);
            return true;
        }catch (Exception ex){
            return false;
        }

    }

    public TeamSpeakServer getTeamSpeakServer(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String name = "SELECT Name FROM SERVER;";
        statement.executeUpdate(name);
        String ipAdress = "SELECT IpAdress FROM SERVER";
        statement.executeUpdate(ipAdress);
        String port = "SELECT Port FROM SERVER";
        statement.executeUpdate(port);
        String password = "SELECT Password FROM SERVER";
        statement.executeUpdate(password);
        return new TeamSpeakServer(name, id, ipAdress, Integer.parseInt(port), password);
    }


}
