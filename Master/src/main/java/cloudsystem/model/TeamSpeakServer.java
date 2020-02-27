package cloudsystem.model;

public class TeamSpeakServer {

    private String name;
    private int id;
    private String ipAddress;
    private int port;
    private String password;
    private boolean online;

    public TeamSpeakServer(String name, int id, String ipAddress, int port, String password, boolean online) {
        this.name = name;
        this.id = id;
        this.ipAddress = ipAddress;
        this.port = port;
        this.password = password;
        this.online = online;
    }

    public String getFullInfo() {
        return "TEAMSPEAK: " + name + " - " + id + " | " + ipAddress + ":" + port + " | " + password + " | " + online;
    }

    @Override
    public String toString() {
        return "TeamSpeakServer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                '}';
    }


    public boolean isServerOnline() {
        return online;
    }

    public void start() {

    }

    public void stop() {

    }
}
