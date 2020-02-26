package cloudsystem.database;

import cloudsystem.model.TeamSpeakServer;

import java.sql.*;


public class SQLManager {

    private String user, password;

    private Connection connection;

    public SQLManager() {
        this.user = "sa";
        this.password = "";
    }

    @lombok.SneakyThrows
    public void openConnection() {
        Class.forName("org.h2.Driver");
        System.out.println("Connecting to DB...");
        connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", user, password);
        System.out.println("Connected");
        Statement tableStatement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS `ONLINE_SERVERS`\n" +
                "(`TYPE` VARCHAR(100),\n" +
                "`NAME` VARCHAR(100), \n" +
                "`ID` INTEGER(100),\n" +
                "`ADRESS` VARCHAR(100), \n" +
                "`PORT` INTEGER(100), \n" +
                "`PASSWORD` VARCHAR(100))";
        tableStatement.executeUpdate(sql);
        System.out.println("Created table");
    }

    public boolean setUpTeamSpeak(String name, int id, String ipAdress, int port, String password) {
        try {
            System.out.println("SetUp new teamspeak.");
            Statement tableStatement = connection.createStatement();
            String sql = "INSERT INTO ONLINE_SERVERS (TYPE, NAME, ID, ADRESS, PORT, PASSWORD)\n" +
                    "VALUES ('TeamSpeak', '" + name + "', " + id + ", '" + ipAdress + "', " + port + "  , '" + password + "')";
            tableStatement.executeUpdate(sql);
            System.out.println("setup finished");
            return true;
        } catch (Exception ex) {
            System.out.println("setup finished with errors: " + ex.getMessage());
            return false;
        }
    }

    public TeamSpeakServer getTeamSpeakServer(int id) throws SQLException {
        System.out.println("create statement");
        Statement statement = connection.createStatement();
        ResultSet rs ;
        String sql = "SELECT * FROM ONLINE_SERVERS WHERE ID = " + id + ";";
        System.out.println("execute query");
        rs = statement.executeQuery(sql);
        String name = null;
        String ipAdress = null;
        int port = 0;
        String password = null;
        while (rs.next()){
            name = rs.getString("NAME");
            ipAdress = rs.getString("ADRESS");
            port = rs.getInt("PORT");
            password = rs.getString("PASSWORD");
        }
        System.out.println(name);
        return new TeamSpeakServer(name, id, ipAdress, port, password);
    }

    public void close() throws SQLException {
        connection.close();
    }


}
