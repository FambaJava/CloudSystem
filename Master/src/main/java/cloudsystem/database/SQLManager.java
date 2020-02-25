package cloudsystem.database;

import cloudsystem.model.TeamSpeakServer;

import java.sql.*;


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
        ResultSet rs;
        String sql = "SELECT * FROM SERVER WHERE Id = " + id + ";";
        rs = statement.executeQuery(sql);
        return new TeamSpeakServer(rs.getString("Name"), id, rs.getString("IpAdress"), rs.getInt("Port"), rs.getString("Password"));
    }


}
