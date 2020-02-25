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
        connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test ", user, password);
        System.out.println("Connected");
        Statement tableStatement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS SERVERS(\n" + "Type VARCHAR(100), \n" + "Name VARCHAR(100), \n" + "Id int(100), \n" + "IpAdress VARCHAR(100), \n" + "Port INT(100), \n" + "Password VARCHAR(100)\n" + ");";
        sql = "CREATE TABLE IF NOT EXISTS `sun_channel` (\n" +
                "    `ID` int(11) unsigned NOT NULL auto_increment,\n" +
                "    `EMAIL` varchar(255) NOT NULL default '',\n" +
                "    `PASSWORD` varchar(255) NOT NULL default '',\n" +
                "    `PERMISSION_LEVEL` tinyint(1) unsigned NOT NULL default '1',\n" +
                "    `APPLICATION_COMPLETED` boolean NOT NULL default '0',\n" +
                "    `APPLICATION_IN_PROGRESS` boolean NOT NULL default '0',\n" +
                "    PRIMARY KEY  (`ID`)\n" +
                ")";
        tableStatement.executeUpdate(sql);
        System.out.println("Created table");
    }

    public boolean setUpTeamSpeak(String name, int id, String ipAdress, int port, String password) {
        try{
            Statement tableStatement = connection.createStatement();
            String sql = "INSERT INTO SERVERS (Type, Name, Id, IpAdress, Port, Password) \n" +
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
        String sql = "SELECT * FROM SERVERS WHERE Id = " + id + ";";
        rs = statement.executeQuery(sql);
        String name = rs.getString("Name");
        String ipAdress = rs.getString("IpAdress");
        int port = rs.getInt("Port");
        String password = rs.getString("Password");
        return new TeamSpeakServer(name, id, ipAdress, port, password);
    }

    public void close() throws SQLException {
        connection.close();
    }


}
