package cloudsystem.database;

import cloudsystem.Main;
import cloudsystem.model.MinecraftServer;
import cloudsystem.model.TeamSpeakServer;
import org.h2.jdbc.JdbcSQLException;
import org.json.JSONException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SQLManager {

    private String user, password;

    private String url;

    private Connection connection;


    public void init() throws JSONException, IOException {
        this.user = "sa";
        this.password = "";
        this.url = "jdbc:h2:tcp://" + "localhost" + "/~/test";
    }

    public void openConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.h2.Driver");
            System.out.println("Connecting to H2 DataBase on URL:" + url + "...");
            connection = DriverManager.getConnection(url, user, password);
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
            System.out.println("The Minecraft and TeamSpeak table are created if they does not exists before.");
        } catch (JdbcSQLException | NullPointerException ex) {
            System.out.println("You need to start H2DB and correct datas!");
            System.out.println("Cloud is now stopping...");
            Main.stop();
            System.out.println("Cloud is now currently offline! Please start first the H2DB before you restart the server! " +
                    "\nIf your Database is currently online, please ask us on Twitter (@MCCloud).");
            System.exit(0);
        }
    }

    public TeamSpeakServer getTeamSpeakServerPerID(int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs;
        String sql = "SELECT * FROM TEAMSPEAK_SERVERS WHERE (ID = " + id + ");";
        rs = statement.executeQuery(sql);
        String name = null;
        String ipAddress = null;
        int port = 0;
        String password = null;
        boolean online = false;
        while (rs.next()) {
            name = rs.getString("NAME");
            ipAddress = rs.getString("ADDRESS");
            port = rs.getInt("PORT");
            password = rs.getString("PASSWORD");
            online = rs.getBoolean("ONLINE");
        }
        return new TeamSpeakServer(name, id, ipAddress, port, password, online);
    }

    public MinecraftServer getMinecraftServerPerID(int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs;
        String sql = "SELECT * FROM MINECRAFT_SERVERS WHERE (ID = " + id + ");";
        rs = statement.executeQuery(sql);
        String name = null;
        String ipAddress = null;
        int port = 0;
        String password = null;
        int maxRam = 0;
        boolean online = false;
        while (rs.next()) {
            name = rs.getString("NAME");
            ipAddress = rs.getString("ADDRESS");
            port = rs.getInt("PORT");
            password = rs.getString("TYPE");
            maxRam = rs.getInt("MAXRAM");
            online = rs.getBoolean("ONLINE");
        }
        return new MinecraftServer(name, id, ipAddress, port, password, maxRam, online);
    }

    public void setUpTeamSpeak(String name, String ipAddress, int port, String password) throws SQLException {
        try {
            System.out.println("SetUp new teamspeak.");
            Statement tableStatement = connection.createStatement();
            String sql = "INSERT INTO TEAMSPEAK_SERVERS (NAME, ADDRESS, PORT, PASSWORD, ONLINE)\n" +
                    "VALUES ('" + name + "', '" + ipAddress + "', " + port + "  , '" + password + "', false)";
            tableStatement.executeUpdate(sql);
            System.out.println("setup finished");
        } catch (NullPointerException ex) {
            System.out.println("You can not setup a new teamspeak server when the database is currently offline");
        }
    }

    public void setUpMinecraft(String name, String ipAddress, int port, String type, int maxRam) throws SQLException {
        try {
            System.out.println("SetUp new minecraft server.");
            Statement tableStatement = connection.createStatement();
            String sql = "INSERT INTO MINECRAFT_SERVERS (NAME, ADDRESS, PORT, TYPE, MAXRAM, ONLINE)\n" +
                    "VALUES ('" + name + "', '" + ipAddress + "', " + port + "  , '" + type + "', " + maxRam + ", false)";
            tableStatement.executeUpdate(sql);
            System.out.println("setup finished");
        } catch (NullPointerException ex) {
            System.out.println("You can not setup a new minecraft server when the database is currently offline.");
        }
    }

    public void setMinecraftStatus(int id, boolean online) throws SQLException {
        Statement tableStatement = connection.createStatement();
        String sql = "UPDATE MINECRAFT_SERVERS\n" +
                "SET ONLINE = " + online + "\n" +
                "WHERE ID = " + id + ";";
        tableStatement.executeUpdate(sql);
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
