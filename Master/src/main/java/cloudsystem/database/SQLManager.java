package cloudsystem.database;

import cloudsystem.model.MinecraftServer;
import cloudsystem.model.TeamSpeakServer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SQLManager {

    private String user, password;

    private Connection connection;

    public SQLManager() {
        this.user = "sa";
        this.password = "";
    }

    public void openConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        System.out.println("Connecting to DB...");
        connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", user, password);
        System.out.println("Connected");
        Statement tableStatement = connection.createStatement();
        String sql = "drop table if exists TEAMSPEAK_SERVERS; create table TEAMSPEAK_SERVERS\n" +
                "(\n" +
                "    NAME     VARCHAR(100),\n" +
                "    ID       INTEGER auto_increment,\n" +
                "    ADDRESS  VARCHAR(100),\n" +
                "    PORT     INTEGER,\n" +
                "    PASSWORD VARCHAR(100),\n" +
                "    ONLINE BOOLEAN,\n" +
                "primary key (ID)\n" +
                ");";
        tableStatement.executeUpdate(sql);

        sql = "drop table if exists MINECRAFT_SERVERS; create table MINECRAFT_SERVERS\n" +
                "(\n" +
                "    NAME     VARCHAR(100),\n" +
                "    ID       INTEGER auto_increment,\n" +
                "    ADDRESS  VARCHAR(100),\n" +
                "    PORT     INTEGER,\n" +
                "    TYPE VARCHAR(100),\n" +
                "    MAXRAM INTEGER(100),\n" +
                "    ONLINE BOOLEAN,\n" +
                "primary key (ID)\n" +
                ");";
        tableStatement.executeUpdate(sql);
        System.out.println("Created tables");
    }

    public void setUpTeamSpeak(String name, String ipAddress, int port, String password) throws SQLException {
        System.out.println("SetUp new teamspeak.");
        Statement tableStatement = connection.createStatement();
        String sql = "INSERT INTO TEAMSPEAK_SERVERS (NAME, ADDRESS, PORT, PASSWORD, ONLINE)\n" +
                "VALUES ('" + name + "', '" + ipAddress + "', " + port + "  , '" + password + "', false)";
        tableStatement.executeUpdate(sql);
        System.out.println("setup finished");
    }

    public void setUpMinecraft(String name, String ipAddress, int port, String type, int maxRam) throws SQLException {
        System.out.println("SetUp new minecraft server.");
        Statement tableStatement = connection.createStatement();
        String sql = "INSERT INTO MINECRAFT_SERVERS (NAME, ADDRESS, PORT, TYPE, MAXRAM, ONLINE)\n" +
                "VALUES ('" + name + "', '" + ipAddress + "', " + port + "  , '" + type + "', " + maxRam + ", false)";
        tableStatement.executeUpdate(sql);
        System.out.println("setup finished");
    }


    public List<MinecraftServer> getAllMinecraftServers() throws SQLException {
        List<MinecraftServer> servers = new ArrayList<>();
        String sql = "SELECT * FROM MINECRAFT_SERVERS;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            servers.add(new MinecraftServer(
                    resultSet.getString("NAME"),
                    resultSet.getInt("ID"),
                    resultSet.getString("ADDRESS"),
                    resultSet.getInt("PORT"),
                    resultSet.getString("TYPE"),
                    resultSet.getInt("MAXRAM"),
                    resultSet.getBoolean("ONLINE")
            ));
        }
        return servers;
    }

    public List<TeamSpeakServer> getAllTeamSpeakServers() throws SQLException {
        List<TeamSpeakServer> servers = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet rs;
        String sql = "SELECT * FROM TEAMSPEAK_SERVERS;";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            servers.add(new TeamSpeakServer(
                    rs.getString("NAME"),
                    rs.getInt("ID"),
                    rs.getString("ADDRESS"),
                    rs.getInt("PORT"),
                    rs.getString("PASSWORD"),
                    rs.getBoolean("ONLINE")
            ));
        }
        return servers;
    }

    public void close() throws SQLException {
        connection.close();
    }


}
