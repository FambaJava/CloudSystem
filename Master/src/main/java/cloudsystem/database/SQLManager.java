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
        String sql = "drop table if exists ONLINE_SERVERS; create table ONLINE_SERVERS\n" +
                "(\n" +
                "    TYPE     VARCHAR(100),\n" +
                "    NAME     VARCHAR(100),\n" +
                "    ID       INTEGER auto_increment,\n" +
                "    ADDRESS  VARCHAR(100),\n" +
                "    PORT     INTEGER,\n" +
                "    PASSWORD VARCHAR(100),\n" +
                "primary key (ID)\n" +
                ");";
        tableStatement.executeUpdate(sql);
        System.out.println("Created tables");
    }

    public void setUpTeamSpeak(String name, String ipAddress, int port, String password) throws SQLException {
        System.out.println("SetUp new teamspeak.");
        Statement tableStatement = connection.createStatement();
        String sql = "INSERT INTO ONLINE_SERVERS (TYPE, NAME, ADDRESS, PORT, PASSWORD)\n" +
                "VALUES ('TeamSpeak', '" + name + "', '" + ipAddress + "', " + port + "  , '" + password + "')";
        tableStatement.executeUpdate(sql);
        System.out.println("setup finished");
    }

    public boolean setUpMinecraft(String name, String ipAddress, int port, String type) throws SQLException {
        System.out.println("SetUp new minecraft server.");
        Statement tableStatement = connection.createStatement();
        String sql = "INSERT INTO ONLINE_SERVERS (TYPE, NAME, ADDRESS, PORT, PASSWORD)\n" +
                "VALUES ('Minecraft', '" + name + "', '" + ipAddress + "', " + port + "  , '" + type + "')";
        tableStatement.executeUpdate(sql);
        System.out.println("setup finished");
        return true;
    }

    public TeamSpeakServer getTeamSpeakServer(int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs;
        String sql = "SELECT * FROM ONLINE_SERVERS WHERE (ID = " + id + ") AND (TYPE = 'TeamSpeak');";
        rs = statement.executeQuery(sql);
        String name = null;
        String ipAddress = null;
        int port = 0;
        String password = null;
        while (rs.next()) {
            name = rs.getString("NAME");
            ipAddress = rs.getString("ADDRESS");
            port = rs.getInt("PORT");
            password = rs.getString("PASSWORD");
        }
        return new TeamSpeakServer(name, id, ipAddress, port, password);
    }

    public MinecraftServer getMinecraftServer(int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs;
        String sql = "SELECT * FROM ONLINE_SERVERS WHERE (ID = " + id + ") AND (TYPE = 'Minecraft');";
        rs = statement.executeQuery(sql);
        String name = null;
        String ipAddress = null;
        int port = 0;
        String type = null;
        while (rs.next()) {
            name = rs.getString("NAME");
            ipAddress = rs.getString("ADDRESS");
            port = rs.getInt("PORT");
            type = rs.getString("PASSWORD");
        }
        return new MinecraftServer(name, id, ipAddress, port, type);
    }

    public List<TeamSpeakServer> getAllTeamSpeakServers() throws SQLException {
        List<TeamSpeakServer> servers = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet rs;
        String sql = "SELECT * FROM ONLINE_SERVERS WHERE  (TYPE = 'TeamSpeak');";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            String name = rs.getString("NAME");
            int id = rs.getInt("ID");
            String ipAddress = rs.getString("ADDRESS");
            int port = rs.getInt("PORT");
            password = rs.getString("PASSWORD");
            servers.add(new TeamSpeakServer(name, id, ipAddress, port, password));
        }
        return servers;
    }

    public void close() throws SQLException {
        connection.close();
    }


}
